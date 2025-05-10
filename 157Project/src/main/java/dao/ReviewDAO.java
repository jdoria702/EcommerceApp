package dao;

import model.Review;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
	public List<Review> getReviewsByProductId(int productId) {
	    List<Review> reviews = new ArrayList<>();
	    try (Connection conn = DBConnection.getConnection()) {
	        String sql = "SELECT r.review_id, r.review, r.rating, r.customer_id, c.first_name, c.last_name " +
	                     "FROM Review r JOIN Customer c ON r.customer_id = c.customer_id " +
	                     "WHERE r.product_id = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, productId);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Review review = new Review();
	                    review.setReviewId(rs.getInt("review_id"));
	                    review.setReview(rs.getString("review"));
	                    review.setRating(rs.getInt("rating"));
	                    review.setCustomerId(rs.getInt("customer_id"));
	                    review.setReviewerName(rs.getString("first_name") + " " + rs.getString("last_name"));
	                    reviews.add(review);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return reviews;
	}

    public void saveReview(Review review) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Review (product_id, rating, review, customer_id, reviewer_name) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, review.getProductId());
                stmt.setInt(2, review.getRating());
                stmt.setString(3, review.getReview());
                stmt.setInt(4, review.getCustomerId());
                stmt.setString(5, review.getReviewerName());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteReview(int reviewId) {
        String sql = "DELETE FROM Review WHERE review_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getCustomerIdByReviewId(int reviewId) {
        String sql = "SELECT customer_id FROM Review WHERE review_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("customer_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
