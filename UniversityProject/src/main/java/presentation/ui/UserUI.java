package presentation.ui;

import business.services.UserService;
import model.dto.User;

import java.util.Scanner;

public class UserUI {
    private static UserService userService = new UserService();
    private static Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            menu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1: {
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    boolean b = userService.login(user);
                    if (b) {
                        System.out.println("Login succesfully.");
                        AppUI.start();
                    } else {
                        System.out.println("User credits incorrect!");
                    }
                    break;
                }
                case 2: {
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    boolean x = userService.register(username, password);
                    if (x) {
                        System.out.println("User already registered!");
                    } else {
                        System.out.println("Registered succesfully.");
                    }
                    break;
                }
                case 0: {
                    System.exit(0);
                    break;
                }
            }
        }
    }

    private static void menu() {
        System.out.println("---UNIVERSITY OF CRAIOVA---\n1.LOGIN\n2.REGISTER\n\n0.EXIT\n");
    }
}
