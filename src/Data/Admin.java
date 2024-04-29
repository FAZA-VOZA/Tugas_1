package src.Data;
import src.com.main.Main;
import src.Books.*;
import java.util.Scanner;
import src.util.iMenu;
import java.util.ArrayList;

public class Admin extends User implements iMenu {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";


    public Admin(String name, String nim, String faculty, String program) {
        super(name, nim, faculty, program);
    }

    public void loginAdmin(Scanner scanner) {
        System.out.print("Enter your username (admin): ");
        String username = scanner.nextLine();
        System.out.print("Enter your password (admin): ");
        String password = scanner.nextLine();

        if (isAdmin(username, password) || (username.equals("") && password.equals(""))) {
            System.out.println("Login Berhasil");
            menu();
        } else {
            System.out.println("Username atau Passwrod salah, silahkan coba lagi");
        }
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" ");
            System.out.println("===== Admin Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Book");
            System.out.println("3. Display Registered Students");
            System.out.println("4. Display Available Books");
            System.out.println("5. Display Borrowed Books ");
            System.out.println("6. Logout ");
            System.out.print("Choose option (1-6): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    addStudent(scanner);
                    break;
                case "2":
                    addBook(scanner);
                    break;
                case "3":
                    displayRegisteredStudents();
                    break;
                case "4":
                    displayBooks();
                    break;
                case "5":
                    displayBorrowedBooks();
                    break;
                case "6":
                    System.out.println("\nLog Out dari menu admin ...");
                    return;
                default:
                    System.out.println("\nPilihanmu tidak ada, silahkan masukkan angka sesuai dengan opsi yang tersedia");
            }
        }
    }
    public void addStudent(Scanner scanner) {
        System.out.println("Enter student details:");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        String nim;
        do {
            System.out.print("Enter student NIM: ");
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("Student NIM must be 15 digits long.");
            }
        } while (nim.length() != 15);
        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter student program: ");
        String program = scanner.nextLine();

        // Panggil metode addStudent yang menerima parameter
        addStudent(name, nim, faculty, program);
    }
    public void addStudent(String name, String nim, String faculty, String program) {
        Main.userList.add(new Student(name, nim, faculty, program));
        System.out.println("Student successfully registered.");
    }

    public void addBook(Scanner scanner) {
        System.out.println("Select book category:");
        System.out.println("1. Story Book");
        System.out.println("2. History Book");
        System.out.println("3. Text Book");
        System.out.print("Choose category (1-3): ");
        int categoryOption = scanner.nextInt();
        scanner.nextLine();
        String category = "";
        switch (categoryOption) {
            case 1:
                category = "Story";
                break;
            case 2:
                category = "History";
                break;
            case 3:
                category = "Text";
                break;
            default:
                System.out.println("Invalid category.");
                return;
        }
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter the stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String bookId = generateId();
        switch (categoryOption) {
            case 1:
                Main.bookList.add(new StoryBook(bookId, title, author, category, stock, 0));
                break;
            case 2:
                Main.bookList.add(new HistoryBook(bookId, title, author, category, stock, 0));
                break;
            case 3:
                System.out.print("Enter duration: ");
                int duration = scanner.nextInt();
                scanner.nextLine();
                Main.bookList.add(new TextBook(bookId, title, author, category, stock, duration, 1)); // Misalnya edisi 1
                break;
        }
        System.out.println("Book successfully added to the library.");
    }

    public void displayRegisteredStudents() {
        System.out.println("List of Registered Students:");
        for (User user : Main.userList) {
            if (user instanceof Student) {
                Student student = (Student) user;
                System.out.println("Name: " + student.name);
                System.out.println("Faculty: " + student.faculty);
                System.out.println("NIM: " + student.nim);
                System.out.println("Program: " + student.program);
                System.out.println();
            }
        }
    }
    public void displayBooks() {
        System.out.println("List of Available Books:");
        for (Book book : Main.bookList) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Category: " + book.getCategory());
            System.out.println("Stock: " + book.getStock());
            System.out.println();
        }
    }

    public void displayBorrowedBooks() {
        // Display borrowed books for a specific student
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student NIM to display borrowed books: ");
        String nim = scanner.nextLine();
        Student student = findStudentByNIM(nim);
        if (student != null) {
            ArrayList<Book> borrowedBooks = student.getBorrowedBooks();
            System.out.println("List of Borrowed Books for " + student.getName() + ":");
            for (Book book : borrowedBooks) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Category: " + book.getCategory());
                System.out.println("Duration: " + book.getDuration() + " days");
                System.out.println();
            }
        } else {
            System.out.println("Student not found.");
        }
    }

//    public void displayBooks() {
//        System.out.println("List of Available Books:");
//        for (Book book : Main.bookList) {
//            System.out.println("Title: " + book.getTitle());
//            System.out.println("Author: " + book.getAuthor());
//            System.out.println("Category: " + book.getCategory());
//            System.out.println("Stock: " + book.getStock());
//            System.out.println();
//        }
//    }

    public boolean isAdmin(String username, String password) {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }

    public String generateId() {
        String uuid = java.util.UUID.randomUUID().toString();
        String id = uuid.substring(0, 14);
        return id;
    }
    private Student findStudentByNIM(String nim) {
        for (User user : Main.userList) {
            if (user instanceof Student && user.getNim().equals(nim)) {
                return (Student) user;
            }
        }
        return null;
    }
}
