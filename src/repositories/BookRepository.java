package repositories;

import data.interfaces.IDB;
import models.Book;
import repositories.interfaces.IBookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final IDB db;

    public BookRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createBook(Book book) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO books(title, author, genre, price) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, book.getTitle());
            st.setString(2, book.getAuthor());
            st.setString(3, book.getGenre());
            st.setDouble(4, book.getPrice());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteBook(int bookId) {
        try (Connection connection = db.getConnection()) {
            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, bookId);

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Book getBookById(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM books";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                books.add(new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price")));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return books;
    }

    @Override
    public Book getWorstRatedBook() {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT b.id, b.title, b.author, b.genre, b.price, AVG(r.rating) as avg_rating " +
                    "FROM books b JOIN reviews r ON b.id = r.book_id " +
                    "GROUP BY b.id ORDER BY avg_rating ASC LIMIT 1";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean removeWorstRatedBook() {
        Book worstBook = getWorstRatedBook();
        if (worstBook == null) return false;
        return deleteBook(worstBook.getId());
    }
}

