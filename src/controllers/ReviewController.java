/*package controllers;

import controllers.interfaces.IReviewController;
import models.Review;
import repositories.interfaces.IReviewRepository;
import java.util.List;

public class ReviewController implements IReviewController {
    private final IReviewRepository repo;

    public ReviewController(IReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public String addReview(int userId, String comment, int rating) {
        Review review = new Review(userId, comment, rating);
        boolean created = repo.addReview(review);
        return (created) ? "Review added successfully" : "Failed to add review";
    }

    @Override
    public String getReviewsByUser(int userId) {
        return "";
    }

    @Override
    public String getReviewById(int id) {
        Review review = repo.getReviewById(id);
        return (review == null) ? "Review not found" : review.toString();
    }

    @Override
    public String getAllReviews() {
        List<Review> reviews = repo.getAllReviews();
        StringBuilder response = new StringBuilder();
        for (Review review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String updateReviewStatus(int id, String status) {
        boolean updated = repo.updateReviewStatus(id, status);
        return (updated) ? "Review status updated" : "Failed to update review status";
    }
}*/
package controllers;

import models.Review;
import repositories.interfaces.IReviewRepository;
import java.util.List;

public class ReviewController {
    private final IReviewRepository reviewRepo;

    public ReviewController(IReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public String addReview(int userId, String comment, int rating) {
        if (rating < 1 || rating > 5) {
            return "Invalid rating! Please enter a rating between 1 and 5.";
        }
        Review review = new Review(userId, comment, rating);
        return reviewRepo.addReview(review) ? "Review added successfully!" : "Failed to add review.";
    }

    public String approveReview(int reviewId) {
        return reviewRepo.updateReviewStatus(reviewId, "Approved") ? "Review approved!" : "Failed to approve review.";
    }

    public String rejectReview(int reviewId) {
        return reviewRepo.updateReviewStatus(reviewId, "Rejected") ? "Review rejected!" : "Failed to reject review.";
    }

    public String deleteReview(int reviewId) {
        return reviewRepo.deleteReview(reviewId) ? "Review deleted successfully!" : "Failed to delete review.";
    }

    public String showAllReviews() {
        List<Review> reviews = reviewRepo.getAllReviews();
        if (reviews.isEmpty()) return "No reviews found.";
        StringBuilder sb = new StringBuilder();
        for (Review r : reviews) sb.append(r).append("\n");
        return sb.toString();
    }

    public String showPendingReviews() {
        List<Review> reviews = reviewRepo.getPendingReviews();
        if (reviews.isEmpty()) return "No pending reviews.";
        StringBuilder sb = new StringBuilder();
        for (Review r : reviews) sb.append(r).append("\n");
        return sb.toString();
    }
}

