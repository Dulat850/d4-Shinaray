package controllers.interfaces;

import models.Payment;

public interface IBookController {
    String createBook(String title, String author, String genre, double price);
    String getBookById(int id);
    String getAllBooks();

    interface IPaymentController {
        String createPayment(int userId, double amount);
        Payment getPayment(int id);

        String getPaymentById(int id);

        String getAllPayments();

        String updatePaymentStatus(int id, String status);
        void showPayments();
    }
}


