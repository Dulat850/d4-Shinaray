package controllers.interfaces;

import models.Book;
import models.Cart;
import models.User;

public interface ICartController {

    // Метод для добавления книги в корзину
    void addBookToCart(Book book, int userId);

    // Метод для удаления книги из корзины
    void removeBookFromCart(Book book);

    // Метод для получения текущей корзины
    Cart getCart();

    // Метод для получения общей стоимости корзины
    double getTotalCost();

    // Метод для очистки корзины
    void clearCart();

    // Метод для обновления количества книги в корзине
    void updateBookInCart(Book book, int userId);

    // Метод для получения книги по ID
    Book getBookById(int bookId);

    // Метод для получения пользователя по ID
    User getUserById(int userId);
}
