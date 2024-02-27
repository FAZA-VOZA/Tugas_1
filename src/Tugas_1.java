import java.util.Scanner;

public class Tugas_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println(" ");
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose option (1-3): ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // login - siswa
                    System.out.print("Enter your NIM: ");
                    String nim = scanner.next();

                    if (nim.length() != 15) {
                        System.out.println("USER NOT FOUND !");
                        break;
                    }

                    System.out.println("Successful login as student");
                    break;

                case 2:
                    // login - admin
                    System.out.print("Enter your username (admin) : ");
                    String username = scanner.next();

                    System.out.print("Enter your password (admin) : ");
                    String password = scanner.next();

                    if ("admin".equals(username) && "admin123".equals(password)) {
                        System.out.println("Successful login as admin");
                    } else {
                        System.out.println("Admin Not Found !");
                    }

                    break;

                case 3:
                    // Exit
                    break;

                default:
                    // ketika pilihan tidak ada
                    System.out.println("pilihanmu tidak ada, silahkan masukkan pilahan yang lainnya");
                    break;
            }
        } while(option != 3);

        scanner.close();
    }
}