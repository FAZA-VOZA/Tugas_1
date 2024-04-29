package src.Data;
import src.util.iMenu;

import java.util.Scanner;

public abstract class User implements iMenu {
    protected String name;
    protected String nim;
    protected String faculty;
    protected String program;

    public User(String name, String nim, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProgram() {
        return program;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("NIM: " + nim);
        System.out.println("Faculty: " + faculty);
        System.out.println("Program: " + program);
    }

//    @Override
    public void showBorrowedBooks() {
        System.out.println("No books borrowed yet.");
    }

//    @Override
    public void borrowBook(Scanner scanner) {
        System.out.println("Borrowing books is not applicable for this user type.");
    }

//    @Override
    public void returnBook(Scanner scanner) {
        System.out.println("Returning books is not applicable for this user type.");
    }

//    @Override
    public void logout() {
        System.out.println("Logging out from user account.");
    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== User Menu =====");
            System.out.println("1. Display Information");
            System.out.println("2. Show Borrowed Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Logout");
            System.out.print("Choose option (1-5): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    displayInfo();
                    break;
                case "2":
                    showBorrowedBooks();
                    break;
                case "3":
                    borrowBook(scanner);
                    break;
                case "4":
                    returnBook(scanner);
                    break;
                case "5":
                    logout();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
