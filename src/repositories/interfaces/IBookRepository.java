package repositories.interfaces;

import models.Book;
import java.util.List;

public interface IBookRepository {
    boolean createBook(Book book);
    boolean deleteBook(int bookId);
    Book getBookById(int id);
    List<Book> getAllBooks();
    Book getWorstRatedBook();
    boolean removeWorstRatedBook();
}
