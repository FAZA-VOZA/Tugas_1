import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Book> bookList = new ArrayList<>();
    static ArrayList<Student> userStudent = new ArrayList<>();

    public static void main(String[] args) {
        //list buku yang sudah dibuat
        bookList.add(new Book(1, "BUKU 1", "LIANA", 5));
        bookList.add(new Book(2, "BUKU 2", "JIHAN", 3));
        bookList.add(new Book(3, "BUKU 3", "FIKRI", 7));

        //id yang sudah dibuat
        userStudent.add(new Student("FAZA ABDILLAH", "202310370311154", "Teknik", "Informatika"));

        Main main = new Main();
        Scanner scanner = new Scanner(System.in);

        //pilihan menu (systemnya)
        while (true) {
            Menu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    main.menuStudent(scanner);
                    break;
                case 2:
                    main.menuAdmin(scanner);
                    break;
                case 3:
                    System.out.println("Log Out...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nPilihan salah (hanya ada pilihan 1-3 saja), silahkan coba lagi ya...\n");
            }
        }
    }

    //pilihan menu yang ditampilkan
    static void Menu() {
        System.out.println("\n===== Library System =====");
        System.out.println("1. Login sebagai mahasiswa");
        System.out.println("2. Login sebagai Admin");
        System.out.println("3. Keluar");
        System.out.println("Pilihlah (1-3): ");
    }

    //pilihan nomer 1
    static void menuStudent(Scanner scanner) {
        System.out.println("\nMasukkan (99) untuk kembali : ");
        System.out.println("Masukkan NIM Anda : ");
        String nim = scanner.next();
        if (nim.equals("99")) return;
        Student student = findStudentByNim(nim);
        if (student != null) {
            while (true) {
                System.out.println("\n===== Student Menu =====");
                System.out.println("1. List buku yang dapat dipinjam");
                System.out.println("2. Meminjam buku");
                System.out.println("3. Mengembalikan buku");
                System.out.println("4. Keluar dari student menu");
                System.out.println("Pilihlah (1-4): ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        student.displayBooks();
                        break;
                    case 2:
                        student.borrowBook(scanner);
                        break;
                    case 3:
                        student.returnBook(scanner);
                        break;
                    case 4:
                        System.out.println("System logout...");
                        return;
                    default:
                        System.out.println("Pilihan salah, silahkan coba lagi.");
                }
            }
        } else {
            System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
        }
    }

    //pilihan nomer 2
    static void menuAdmin(Scanner scanner) {
        Admin admin = new Admin();
        while (true) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Tambahkan mahasiswa");
            System.out.println("2. Tampilan mahasiswa terdaftar");
            System.out.println("3. Keluar");
            System.out.println("Pilihlah (1-3): ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    admin.addStudent(scanner);
                    break;
                case 2:
                    admin.displayStudent();
                    break;
                case 3:
                    System.out.println("Keluar dari akun admin.");
                    return;
                default:
                    System.out.println("\nPilihan salah, silahkan coba lagi\n");
            }
        }
    }

    static Student findStudentByNim(String nim) {
        for (Student student : userStudent) {
            if (student.getNim().equals(nim)) {
                return student;
            }
        }
        return null;
    }
}

class Book {
    int id;
    String judul;
    String author;
    int stok;

    Book(int id, String judul, String author, int stok) {
        this.id = id;
        this.judul = judul;
        this.author = author;
        this.stok = stok;
    }
}

class Student {
    String nama;
    String nim;
    String fakultas;
    String prodi;
    ArrayList<Book> borrowedBooks = new ArrayList<>();

    Student(String nama, String nim, String fakultas, String prodi) {
        this.nama = nama;
        this.nim = nim;
        this.fakultas = fakultas;
        this.prodi = prodi;
    }

    void displayBooks() {
        System.out.println("\nList buku yang tersedia:");
        for (Book book : Main.bookList) {
            System.out.println("ID: " + book.id + ", Judul: " + book.judul + ", Author: " + book.author + ", Stok: " + book.stok);
        }
    }

    String getNim() {
        return nim;
    }

    void borrowBook(Scanner scanner) {
        System.out.println("Masukkan ID buku yang ingin Anda pinjam: ");
        int bookId = scanner.nextInt();
        for (Book book : Main.bookList) {
            if (book.id == bookId) {
                if (book.stok > 0) {
                    book.stok--;
                    borrowedBooks.add(book);
                    System.out.println("Anda telah berhasil meminjam buku dengan judul: " + book.judul);
                } else {
                    System.out.println("Maaf, stok buku tersebut habis.");
                }
                return;
            }
        }
        System.out.println("Buku dengan ID tersebut tidak ditemukan.");
    }

    void returnBook(Scanner scanner) {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Anda belum meminjam buku apapun.");
            return;
        }
        System.out.println("Masukkan ID buku yang ingin Anda kembalikan: ");
        int bookId = scanner.nextInt();
        for (Book book : borrowedBooks) {
            if (book.id == bookId) {
                book.stok++;
                borrowedBooks.remove(book);
                System.out.println("Anda telah berhasil mengembalikan buku dengan judul: " + book.judul);
                return;
            }
        }
        System.out.println("Buku dengan ID tersebut tidak ditemukan dalam daftar buku yang Anda pinjam.");
    }
}

class Admin {
    void addStudent(Scanner scanner) {
        System.out.println("Masukan nama: ");
        String nama = scanner.next();
        System.out.println("Masukan NIM: ");
        String nim = scanner.next();
        if (nim.length() != 15) {
            System.out.println("NIM salah, harus berjumlah 15 angka.");
            return;
        }
        System.out.println("Masukan fakultas: ");
        String fakultas = scanner.next();
        System.out.println("Masukan prodi: ");
        String prodi = scanner.next();
        Main.userStudent.add(new Student(nama, nim, fakultas, prodi));
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    void displayStudent() {
        System.out.println("\nList mahasiswa yang terdaftar:");
        for (Student student : Main.userStudent) {
            System.out.println("\nNama : " + student.nama + ", Fakultas : " + student.fakultas + ", NIM : " + student.nim + ", Program : " + student.prodi);
        }
    }
}