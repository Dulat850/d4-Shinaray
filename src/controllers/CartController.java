package controllers;

import controllers.interfaces.ICartController;
import models.Book;
import models.Cart;
import models.User;
import repositories.CartRepository;
import repositories.interfaces.ICartRepository;

public class CartController implements ICartController {

    private final ICartRepository cartRepository;
    private Cart cart;

    public CartController(ICartRepository cartRepository) {
        this.cartRepository = cartRepository;
        this.cart = new Cart(); // создаем новую корзину при инициализации контроллера
    }

    @Override
    public void addBookToCart(Book book, int userId) {
        System.out.println("Adding book with ID: " + book.getId() + ", Title: " + book.getTitle());

        // Проверка, если книга уже в корзине
        int index = cart.getCartBooks().indexOf(book);
        double price = book.getPrice();

        // Если книга уже есть в корзине, увеличиваем количество и обновляем цену
        if (index != -1) {
            int currentQuantity = cart.getCartQuantities().get(index);
            cart.getCartQuantities().set(index, currentQuantity + 1);

            double currentSum = cart.getCartSum().get(index);
            double newSum = currentSum + price;
            cart.getCartSum().set(index, newSum);
        } else {
            // Если книги нет в корзине, добавляем ее
            cart.getCartBooks().add(book);
            cart.getCartQuantities().add(1);
            cart.getCartSum().add(price);
        }

        // Сохраняем корзину
        cartRepository.saveCart(cart, userId);
        System.out.println("Cart saved!");
    }

    @Override
    public void removeBookFromCart(Book book) {
        // Ищем книгу в корзине
        int index = cart.getCartBooks().indexOf(book);
        if (index != -1) {
            // Удаляем книгу, количество и цену
            cart.getCartBooks().remove(index);
            cart.getCartQuantities().remove(index);
            cart.getCartSum().remove(index);
            System.out.println("Book removed from cart!");
        } else {
            System.out.println("Book not found in cart!");
        }
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public double getTotalCost() {
        // Возвращаем общую стоимость корзины
        double totalCost = 0;
        for (double sum : cart.getCartSum()) {
            totalCost += sum;
        }
        return totalCost;
    }

    @Override
    public void clearCart() {
        // Очищаем корзину
        cart.getCartBooks().clear();
        cart.getCartQuantities().clear();
        cart.getCartSum().clear();
        System.out.println("Cart cleared!");
    }

    @Override
    public void updateBookInCart(Book book, int userId) {
        // Обновляем книгу в корзине
        int index = cart.getCartBooks().indexOf(book);
        if (index != -1) {
            int newQuantity = cart.getCartQuantities().get(index) + 1;
            cart.getCartQuantities().set(index, newQuantity);

            double price = book.getPrice();
            double newSum = price * newQuantity;
            cart.getCartSum().set(index, newSum);

            cartRepository.updateCart(cart, userId);
            System.out.println("Book quantity updated in cart!");
        } else {
            System.out.println("Book not found in cart!");
        }
    }

    @Override
    public Book getBookById(int bookId) {
        return cartRepository.getBookById(bookId);
    }

    @Override
    public User getUserById(int userId) {
        return cartRepository.getUserById(userId);
    }
}
