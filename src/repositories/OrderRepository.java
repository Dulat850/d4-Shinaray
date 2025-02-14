package repositories;

import data.interfaces.IDB;
import models.Order;
import models.OrderItem;
import models.User;
import repositories.interfaces.IOrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final IDB db;

    public OrderRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Order getFullOrderDescription(int orderId) {
        String sql = """
            SELECT o.id AS order_id, o.order_date, o.total_price,
                   u.id AS user_id, u.name AS user_name, u.email,
                   oi.book_id, b.title AS book_title, oi.quantity, oi.price
            FROM orders o
            JOIN users u ON o.user_id = u.id
            JOIN order_items oi ON o.id = oi.order_id
            JOIN books b ON oi.book_id = b.id
            WHERE o.id = ?;
        """;

        try (Connection connection = db.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            Order order = null;
            List<OrderItem> items = new ArrayList<>();
            User user = null;

            while (rs.next()) {
                if (order == null) {
                    user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("email"));
                    order = new Order(rs.getInt("order_id"), rs.getDate("order_date"), rs.getDouble("total_price"), user);
                }
                OrderItem item = new OrderItem(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                items.add(item);
            }

            if (order != null) {
                order.setItems(items);
            }
            return order;
        } catch (SQLException e) {
            System.out.println("SQL Error in getFullOrderDescription: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
