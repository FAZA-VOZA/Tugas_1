package src.com.main;

import src.Books.*;
import src.Data.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static ArrayList<Book> bookList = new ArrayList<>();
    public static ArrayList<User> userList = new ArrayList<>() ;
    private static Admin admin = new Admin("Admin Name", "adminNIM","Faculty Name", "Program Name");

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
                    enterNim(scanner);
                    break;
                case "2":
                    admin.loginAdmin(scanner);
                    break;
                case "3":
                    System.out.println("\nTerimakasih telah mencoba program ini, Log out ...");
                    System.exit(0);
                default:
                    System.out.println("\nPilihanmu tidak ada, silahkan masukkan angka sesuai dengan opsi yang tersedia");
            }
        }

    }

    public static void enterNim(Scanner scanner) {
        System.out.print("Masukkan NIM (input 99 untuk kembali): ");
        String nim = scanner.nextLine();
        if (nim.equals("99")) {
            return;
        } else if (checkNim(nim)) {
            User user = getUser(nim);
            user.menu();
        } else {
            System.out.println("\nNIM kamu tidak valid :), silahkan masukkan NIM yang valid ya");
            enterNim(scanner);
        }
    }

    public static boolean checkNim(String nim) {
        for (User user : userList) {
            if (user instanceof Student && ((Student) user).getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }

    public static User getUser(String nim) {
        for (User user : userList) {
            if (user instanceof Student && ((Student) user).getNim().equals(nim)) {
                return user;
            }
        }
        return null;
    }


    public static void initializeData() {
        bookList.add(new HistoryBook("A1", "BUKU 1", "KAKA", "Story", 8, 7));
        bookList.add(new StoryBook("A2", "BUKU 2", "KIKI", "History Books", 11, 14));
        bookList.add(new TextBook("A3", "BUKU 3", "KUKU", "Story", 3, 30, 2));
        bookList.add(new TextBook("A4", "BUKU 4", "KEKE", "Novel", 3, 30, 2));
        bookList.add(new TextBook("A5", "BUKU 5", "KOKO", "Novel", 3, 30, 2));

        userList.add(new Student("FAZA ABDILLAH", "202310370311154", "TEKNIK", "INFORMATIKA"));
    }
}
