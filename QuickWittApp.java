import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickWittApp {
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Admin admin = new Admin("admin", "admin123");
        users.add(admin);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Quick Witt!\n1. Login\n2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Username: ");
                String username = scanner.nextLine().trim();
                System.out.print("Password: ");
                String password = scanner.nextLine().trim();

                User currentUser = users.stream()
                        .filter(user -> user.username.equals(username) && user.password.equals(password))
                        .findFirst().orElse(null);

                if (currentUser != null) {
                    while (true) {
                        currentUser.menu();
                        int menuChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (menuChoice == 3 || menuChoice == 4) break; // Logout

                        if (currentUser instanceof Admin && menuChoice == 1) {
                            System.out.print("Enter Teacher Username: ");
                            String tUsername = scanner.nextLine();
                            System.out.print("Enter Teacher Password: ");
                            String tPassword = scanner.nextLine();
                            ((Admin) currentUser).addTeacher(tUsername, tPassword, users);

                        } else if (currentUser instanceof Admin && menuChoice == 2) {
                            System.out.print("Enter Student Username: ");
                            String sUsername = scanner.nextLine();
                            System.out.print("Enter Student Password: ");
                            String sPassword = scanner.nextLine();
                            ((Admin) currentUser).addStudent(sUsername, sPassword, users);

                        } else if (currentUser instanceof Teacher && menuChoice == 1) {
                            System.out.print("Enter Student Username: ");
                            String sUsername = scanner.nextLine();
                            System.out.print("Enter Student Password: ");
                            String sPassword = scanner.nextLine();
                            ((Teacher) currentUser).addStudent(sUsername, sPassword, users);

                        } else if (currentUser instanceof Teacher && menuChoice == 2) {
                            System.out.print("Enter Question: ");
                            String questionText = scanner.nextLine();
                            List<String> options = new ArrayList<>();

                            for (char i = 'A'; i <= 'D'; i++) {
                                System.out.print("Enter Option " + i + ": ");
                                options.add(scanner.nextLine());
                            }

                            System.out.print("Enter Correct Answer (A/B/C/D): ");
                            String correctAnswer = scanner.nextLine();
                            ((Teacher) currentUser).addQuestion(questionText, options, correctAnswer);

                        } else if (currentUser instanceof Teacher && menuChoice == 3) {
                            ((Teacher) currentUser).viewLeaderboard();

                        } else if (currentUser instanceof Student && menuChoice == 1) {
                            ((Student) currentUser).takeQuiz();
                        }
                    }
                } else {
                    System.out.println("Invalid username or password.");
                }
            } else {
                break;
            }
        }
    }
}