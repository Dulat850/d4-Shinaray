package controllers;

import controllers.interfaces.IOrderController;
import models.Order;
import repositories.interfaces.IOrderRepository;

public class OrderController implements IOrderController {
    private final IOrderRepository orderRepo;

    public OrderController(IOrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void getFullOrderDescription(int orderId) {
        Order order = orderRepo.getFullOrderDescription(orderId);
        if (order != null) {
            System.out.println("\n=== Order Details ===");
            System.out.println("Order ID: " + order.getId());
            System.out.println("Date: " + order.getOrderDate());
            System.out.println("Total Price: $" + order.getTotalPrice());
            System.out.println("Buyer: " + order.getUser().getName() + " (" + order.getUser().getEmail() + ")");
            System.out.println("\n=== Order Items ===");
            order.getItems().forEach(item ->
                    System.out.println(item.getBookTitle() + " | Quantity: " + item.getQuantity() + " | Price: $" + item.getPrice())
            );
        } else {
            System.out.println("Order not found.");
        }
    }
}
