package repositories.interfaces;

import models.Review;
import java.util.List;

public interface IReviewRepository {
    boolean addReview(Review review);
    boolean updateReviewStatus(int reviewId, String status);
    boolean deleteReview(int reviewId);
    List<Review> getAllReviews();
    List<Review> getReviewsByUserId(int userId);
    List<Review> getPendingReviews();
}
