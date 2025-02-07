/*package controllers.interfaces;

public interface IReviewController {
    String addReview(int userId, String comment, int rating);
    String getReviewsByUser(int userId);
    String getReviewsByUser(int userId, int offset, int limit);
    String getReviewsByUser(int userId, int offset, int limit, String sort);
}*/
package controllers.interfaces;

public interface IReviewController {
    String addReview(int userId, String comment, int rating);
    String approveReview(int reviewId);
    String rejectReview(int reviewId);
    String deleteReview(int reviewId);
    String showAllReviews();
    String showPendingReviews();
}

