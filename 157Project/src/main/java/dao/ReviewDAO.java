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
        	String sql = "SELECT r.review, c.email AS customer_name " +
                    "FROM Review r JOIN Customer c ON r.customer_id = c.customer_id " +
                    "WHERE r.product_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setReview(rs.getString("review"));
                review.setCustomerName(rs.getString("customer_name"));
                reviews.add(review);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
