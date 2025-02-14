package controllers;

import models.Book;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IUserRepository;
import repositories.interfaces.IReviewRepository;

public class AdminController {
    private final IBookRepository bookRepo;
    private final IUserRepository userRepo;
    private final IReviewRepository reviewRepo;

    public AdminController(IBookRepository bookRepo, IUserRepository userRepo, IReviewRepository reviewRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
    }

    public String addBook(String title, String author, String genre, double price) {
        Book book = new Book(title, author, genre, price);
        return bookRepo.createBook(book) ? "Book added successfully!" : "Failed to add book.";
    }

    public String deleteBook(int bookId) {
        return bookRepo.deleteBook(bookId) ? "Book deleted successfully!" : "Failed to delete book.";
    }

    public String deleteUser(int userId) {
        return userRepo.deleteUser(userId) ? "User deleted successfully!" : "Failed to delete user.";
    }

    public String removeReview(int reviewId) {
        return reviewRepo.deleteReview(reviewId) ? "Review deleted successfully!" : "Failed to delete review.";
    }

    public String showWorstRatedBook() {
        Book book = bookRepo.getWorstRatedBook();
        return (book != null) ? book.toString() : "No books found!";
    }

    public String removeWorstRatedBook() {
        return bookRepo.removeWorstRatedBook() ? "Worst-rated book removed successfully!" : "Failed to remove worst-rated book.";
    }
}

