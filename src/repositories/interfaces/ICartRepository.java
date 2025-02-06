package repositories.interfaces;

import models.Book;
import models.Cart;
import models.User;

public interface ICartRepository {

    void saveCart(Cart cart, int id);

    Cart getCartByUserId(int userId);

    void updateCart(Cart cart, int userId);

    void deleteCart(int userId);

    double getTotalCost(Cart cart);

    Book getBookById(int id);

    User getUserById(int id);
}


