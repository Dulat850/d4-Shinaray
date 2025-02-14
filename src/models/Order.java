package models;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date orderDate;
    private double totalPrice;
    private User user;
    private List<OrderItem> items;

    public Order(int id, Date orderDate, double totalPrice, User user) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.user = user;
    }

    // Getters and setters
    public int getId() { return id; }
    public Date getOrderDate() { return orderDate; }
    public double getTotalPrice() { return totalPrice; }
    public User getUser() { return user; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", user=" + user +
                ", items=" + items +
                '}';
    }
}
