package start;

import controllers.interfaces.IOrderController;
import java.util.Scanner;

public class OrderApplication {
    private final IOrderController orderController;
    private final Scanner scanner;

    public OrderApplication(IOrderController orderController) {
        this.orderController = orderController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Order Management ===");
            System.out.println("1. View Order Details");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewOrderDetails();
                    break;
                case 0:
                    System.out.println("Exiting Order Management...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void viewOrderDetails() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        orderController.getFullOrderDescription(orderId);
    }
}
