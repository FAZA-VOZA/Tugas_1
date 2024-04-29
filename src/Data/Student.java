package src.Data;

import src.com.main.*;
import src.util.iMenu;
import src.Books.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements iMenu {
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Student(String name, String nim, String faculty, String program) {
        super(name, nim, faculty, program);
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("NIM: " + nim);
        System.out.println("Faculty: " + faculty);
        System.out.println("Program: " + program);
    }

    @Override
    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("tidak ada buku yang dipinjam");
        } else {
            System.out.println("buku yang dipinjam :");
            for (Book book : borrowedBooks) {
                System.out.println(" ");
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Category: " + book.getCategory());
                System.out.println("Duration: " + book.getDuration() + " days");
                System.out.println();
            }
        }
    }

    public void choiceBook(Scanner scanner) {
        System.out.println("===== Choose a Book =====");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Category");
        System.out.print("Choose option (1-2): ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (option) {
            case 1:
                searchBookByTitle(scanner);
                break;
            case 2:
                System.out.print("Enter the category: ");
                String category = scanner.nextLine();
                searchBookByCategory(category, scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void searchBookByTitle(String title) {
        boolean bookFound = false;
        for (Book book : Main.bookList) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getStock() > 0) {
                borrowedBooks.add(book);
                book.decreaseStock();
                System.out.println("You have successfully borrowed the book: " + book.getTitle());
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            System.out.println("Book not found or not available.");
        }
    }

    public void searchBookByTitle(Scanner scanner) {
        System.out.print("Enter the title of the book you want to borrow: ");
        String title = scanner.nextLine();
        boolean bookFound = false;
        for (Book book : Main.bookList) {
            System.out.print("How many days do you want to borrow the book? (maximum 14 days): ");
            int days = scanner.nextInt();
            scanner.nextLine();
            if (days <= 14) {
                // Menggunakan konstruktor yang sesuai di sini
                borrowedBooks.add(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock(), days));
                book.setStock(book.getStock() - 1);
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Invalid number of days.");
            }
            return;
        }
    }

    public void searchBookByCategory(String category, Scanner scanner) {
        System.out.println("===== Books in Category: " + category + " =====");
        boolean foundBooks = false;
        for (Book book : Main.bookList) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Category: " + book.getCategory());
                System.out.println("Stock: " + book.getStock());
                System.out.println();
                foundBooks = true;
            }
        }
        if (!foundBooks) {
            System.out.println("No books found in the category: " + category);
        } else {
            System.out.print("Enter the title of the book you want to borrow: ");
            String title = scanner.nextLine();
            boolean bookFound = false;

            for (Book book : Main.bookList) {
                if (book.getTitle().equalsIgnoreCase(title) && book.getCategory().equalsIgnoreCase(category)) {
                    System.out.print("How many days do you want to borrow the book? (maximum 14 days): ");
                    int days = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (days <= 14) {
                        // Menggunakan konstruktor yang sesuai di sini
                        borrowedBooks.add(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock(), days));
                        book.setStock(book.getStock() - 1);
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Invalid number of days.");
                    }
                }
            }
            if (!bookFound) {
                System.out.println("Book not found in the specified category or invalid title.");
            }
        }
    }

    @Override
    public void borrowBook(Scanner scanner) {
        System.out.println("Available Books:");
        for (Book book : Main.bookList) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Category: " + book.getCategory());
            System.out.println("Duration: " + book.getDuration() + " days");
            System.out.println("Stock: " + book.getStock());

            System.out.println();
        }
        System.out.print("Enter the title of the book you want to borrow: ");
        String bookTitle = scanner.nextLine();

        for (Book book : Main.bookList) {
            if (book.getTitle().equalsIgnoreCase(bookTitle) && book.getStock() > 0) {
                System.out.print("How many days do you want to borrow the book? (maximum 14 days): ");
                int days = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (days <= 14) {
                    // Menggunakan konstruktor yang sesuai di sini
                    borrowedBooks.add(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock(), days));
                    book.setStock(book.getStock() - 1);
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Invalid number of days.");
                }
                return;
            }
        }
        System.out.println("Book not available or invalid title.");
    }
    @Override
    public void returnBook(Scanner scanner) {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed yet.");
            return;
        }
        System.out.println("Borrowed Books:");
        for (Book book : borrowedBooks) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Category: " + book.getCategory());
            System.out.println("Duration: " + book.getDuration() + " days");
            System.out.println();
        }
        System.out.print("Enter the title of the book you want to return: ");
        String returnTitle = scanner.nextLine();

        for (Book book : borrowedBooks) {
            if (book.getTitle().equalsIgnoreCase(returnTitle)) {
                borrowedBooks.remove(book); // Hapus buku dari daftar buku yang dipinjam
                book.setStock(book.getStock() + 1); // Tambahkan stok buku yang dikembalikan
                for (Book b : Main.bookList) {
                    if (b.getId().equals(book.getId())) {
                        b.setStock(b.getStock() + 1);
                        break;
                    }
                }
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Book not found in your borrowed books.");
    }


    @Override
    public void logout() {
        System.out.println("Logging out from student account.");
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" ");
            System.out.println("===== Student Menu =====");
            System.out.println("1. Display Information");
            System.out.println("2. Show Borrowed Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Choose Book");
            System.out.println("6. Logout");
            System.out.print("Choose option (1-6): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println(" ");
                    displayInfo();
                    break;
                case "2":
                    System.out.println(" ");
                    showBorrowedBooks();
                    break;
                case "3":
                    borrowBook(scanner);
                    break;
                case "4":
                    returnBook(scanner);
                    break;
                case "5":
                    choiceBook(scanner); // Panggil method choiceBook() untuk memilih buku
                    break;
                case "6":
                    logout();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}