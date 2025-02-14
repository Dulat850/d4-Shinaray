package models;

public class OrderItem {
    private int bookId;
    private String bookTitle;
    private int quantity;
    private double price;

    public OrderItem(int bookId, String bookTitle, int quantity, double price) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.price = price;
    }

    public int getBookId() { return bookId; }
    public String getBookTitle() { return bookTitle; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "OrderItem{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
