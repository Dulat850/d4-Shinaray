/*package repositories;

import data.interfaces.IDB;
import models.Review;
import repositories.interfaces.IReviewRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository {
    private final IDB db;

    public ReviewRepository(IDB db) { this.db = db; }

    @Override
    public boolean addReview(Review review) {
        try (Connection con = db.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO reviews (user_id, comment, rating) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, review.getUserId());
            stmt.setString(2, review.getComment());
            stmt.setInt(3, review.getRating());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Review> getReviewsByUser(int userId) {
        List<Review> reviews = new ArrayList<>();
        try (Connection con = db.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM reviews WHERE user_id = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reviews.add(new Review(userId, rs.getString("comment"), rs.getInt("rating")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}*/
package repositories;

import data.interfaces.IDB;
import models.Review;
import repositories.interfaces.IReviewRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository {
    private final IDB db;

    public ReviewRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addReview(Review review) {
        String query = "INSERT INTO reviews (user_id, comment, rating, status) VALUES (?, ?, ?, 'Pending')";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, review.getUserId());
            ps.setString(2, review.getComment());
            ps.setInt(3, review.getRating());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateReviewStatus(int reviewId, String status) {
        String query = "UPDATE reviews SET status = ? WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, reviewId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteReview(int reviewId) {
        String query = "DELETE FROM reviews WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, reviewId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM reviews";
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("comment"),
                        rs.getInt("rating"),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<Review> getReviewsByUserId(int userId) {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM reviews WHERE user_id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("comment"),
                        rs.getInt("rating"),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<Review> getPendingReviews() {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM reviews WHERE status = 'Pending'";
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("comment"),
                        rs.getInt("rating"),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
