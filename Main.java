import java.util.Scanner;
import java.util.ArrayList;

class tugas {
    static ArrayList<Book> bookList = new ArrayList<>();
    static ArrayList<Student> userStudent = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n");
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    inputNim(scanner);
                    break;
                case "2":
                    Admin admin = new Admin();
                    admin.menuAdmin(scanner);
                    break;
                case "3":
                    System.out.println("\nTerimakasih telah mencoba program ini, Log out ...");
                    System.exit(0);
                default:
                    System.out.println("\nPilihanmu tidak ada, silahkan masukkan angka sesuai dengan opsi yang tersedia");
            }
        }
    }

    public static void inputNim(Scanner scanner) {
        System.out.print("Masukkan NIM (input 99 untuk kembali): ");
        String nim = scanner.nextLine();
        if (nim.equals("99")) {
            return;
        } else if (checkNim(nim)) {
            Student student = new Student(nim);
            student.menuStudent(scanner);
        } else {
            System.out.println("\nNIM kamu tidak valid :), silahkan masukkan NIM yang valid ya");
            inputNim(scanner);
        }
    }

    public static boolean checkNim(String nim) {
        for (Student student : userStudent) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }

    public static void initializeData() {

        bookList.add(new Book("1", "BUKU 1", "KAKA", "Sejarah", 8));
        bookList.add(new Book("2", "BUKU 2", "KIKI", "Sejarah", 4));
        bookList.add(new Book("3", "BUKU 3", "KUKU", "Sejarah", 3));

        userStudent.add(new Student("FAZA ABDILLAH", "202310370311154", "TEKNIK", "INFORMATIKA"));
    }

}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int loanDuration;
    private int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getStock() { return stock; }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getLoanDuration() { return loanDuration; }
    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration; }
}

class User {
    protected static ArrayList<Book> bookList = new ArrayList<>();

    public void displayBooks() {
        System.out.println("List of Books:");
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Author || Category || Stock ||");
        int index = 1;
        for (Book book : bookList) {
            System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }
}
class Student extends User{
    private String name;
    private String nim;
    private String faculty;
    private String program;
    private ArrayList<Book> borrowedBooks;
    private int loanDuration;

    public Student(String name, String nim, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
        this.borrowedBooks = new ArrayList<>();
    }


    public Student(String nim) {

        for (Student student : tugas.userStudent) {
            if (student.getNim().equals(nim)) {
                this.name = student.getName();
                this.nim = student.getNim();
                this.faculty = student.getFaculty();
                this.program = student.getProgram();
                this.borrowedBooks = student.getBorrowedBooks();
                break;
            }
        }
    }

    public String getNim() { return nim; }

    public void menuStudent(Scanner scanner) {
        while (true) {
            System.out.println(" ");
            System.out.println("=== Student Menu ===");
            System.out.println("1. Buku terpinjam");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Kembalikan buku");
            System.out.println("4. Pinjam Buku atau Logout");
            System.out.print("Choose option (1-4): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Buku terpinjam:");
                    displayBorrowedBooks();
                    break;
                case "2":
                    displayBooks();
                    borrowBook(scanner);
                    break;
                case "3":
                    returnBook(scanner);
                    break;
                case "4":
                    System.out.println("\nLog Out ...");
                    return;
                default:
                    System.out.println("\nPilihanmu tidak ada, silahkan masukkan angka sesuai dengan opsi yang tersedia");
            }
        }
    }
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Scanner scanner) {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Tidak ada buku yang dipinjam");
            return;
        }

        displayBorrowedBooks();
        System.out.print("Masukkan ID buku yang akan dikembalikan");
        int bookIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (bookIndex < 0 || bookIndex >= borrowedBooks.size()) {
            System.out.println("ID buku tidak valid");
            return;
        }

        Book returnedBook = borrowedBooks.remove(bookIndex);
        returnedBook.setStock(returnedBook.getStock() + 1);
        System.out.println("Book '" + returnedBook.getTitle() + "' Berhasil dikembalikan");
    }


    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Tidak ada buku yang dipinjam");
        } else {
            System.out.println("=================================================================================");
            System.out.println("|| No. || Id Buku        || Nama Buku    || Author       || Category   || Durasi ||");
            System.out.println("=================================================================================");
            int index = 1;
            for (Book book : borrowedBooks) {
                System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + " || " + book.getLoanDuration() + " ||" );
                index++;
            }
            System.out.println("=================================================================================");
        }
    }


    public void displayBooks() {
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Author || Category || Stock ||");
        int index = 1;
        for (Book book : tugas.bookList) {
            System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }
    public void borrowBook(Scanner scanner) {
        System.out.print("Masukan ID buku yang akan kamu pinjam : ");
        int bookIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (bookIndex < 0 || bookIndex >= tugas.bookList.size()) {
            System.out.println("ID buku tidak valid");
            return;
        }

        Book selectedBook = tugas.bookList.get(bookIndex);
        System.out.print("Masukkan berapa durasi peminjaman buku (dalam HARI): ");
        int loanDuration = Integer.parseInt(scanner.nextLine());

        if (selectedBook.getStock() > 0) {
            // Decrease the stock of the selected book
            selectedBook.setStock(selectedBook.getStock() - 1);
            selectedBook.setLoanDuration(loanDuration); // Set loan duration for the book
            borrowBook(selectedBook);
            System.out.println("Book '" + selectedBook.getTitle() + "' borrowed successfully for " + loanDuration + " days.");
        } else {
            System.out.println("Mohon Maaf, untuk buku ini stoknya sedang habis");
        }
    }

    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    public int getLoanDuration() {
        return loanDuration;
    }
    public String getName() { return name; }
    public String getFaculty() { return faculty; }
    public String getProgram() { return program; }
    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }
}

class Admin extends User{
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    public void menuAdmin(Scanner scanner) {
        if (loginAdmin(scanner)) {
            while (true) {
                System.out.println(" ");
                System.out.println("=== Admin Menu ===");
                System.out.println("1. Tambah Mahasiswa");
                System.out.println("2. Lihat Daftar Mahasiswa");
                System.out.println("3. Tambah Buku");
                System.out.println("4. Lihat Daftar Buku");
                System.out.println("5. Keluar");
                System.out.print("Choose option (1-5): ");
                String option = scanner.nextLine();
                switch (option) {
                    case "1":
                        addStudent(scanner);
                        break;
                    case "2":
                        displayStudent();
                        break;
                    case "3":
                        addBook(scanner);
                        break;
                    case "4":
                        displayBookList();
                        break;
                    case "5":
                        System.out.println("\nLog Out dari menu admin ...");
                        return;
                    default:
                        System.out.println("\nPilihanmu tidak ada, silahkan masukkan angka sesuai dengan opsi yang tersedia");
                }
            }
        }else {
            System.out.println("Gagal untuk login ke menu Admin, Log Out ...");
        }
    }

    private boolean loginAdmin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
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
                System.out.println("NIM must be 15 digits.");
            }
        } while (nim.length() != 15);
        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter student program: ");
        String program = scanner.nextLine();
        tugas.userStudent.add(new Student(name, nim, faculty, program));
        System.out.println("Student successfully registered.");
    }

    public void displayStudent() {
        System.out.println("List of Registered Students:");
        for (Student student : tugas.userStudent) {
            System.out.println("Nama: " + student.getName());
            System.out.println("Fakultas: " + student.getFaculty());
            System.out.println("NIM: " + student.getNim());
            System.out.println("Prodi: " + student.getProgram());
            System.out.println();
        }
    }
    public void addBook(Scanner scanner) {
        System.out.println("Choose book category:");
        System.out.println("1. Story Book");
        System.out.println("2. History Book");
        System.out.println("3. Text Book");
        System.out.println("4. Keluar");
        System.out.print("Enter choice (1-4): ");
        String categoryChoice = scanner.nextLine();

        String category;
        switch (categoryChoice) {
            case "1":
                category = "Story Book";
                break;
            case "2":
                category = "History Book";
                break;
            case "3":
                category = "Text Book";
                break;
            case "4":
                System.out.println("System logout...");
                return;
            default:
                System.out.println("Invalid choice. Defaulting to Story Book.");
                category = "Story Book";
                break;
        }
        System.out.println("Enter book details:");
        System.out.print("Enter book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        tugas.bookList.add(new Book(id, title, author, category, stock));
        System.out.println("Book successfully added to the library.");
    }
    public void displayBookList() {
        System.out.println("List of Books:");
        System.out.println("================================================================");
        System.out.println("|| No. || Id Buku || Nama Buku || Author || Category || Stock ||");
        int index = 1;
        for (Book book : tugas.bookList) {
            System.out.println("|| " + index + "  || " + book.getId() + " || " + book.getTitle() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStock() + " ||");
            index++;
        }
        System.out.println("================================================================");
    }
}