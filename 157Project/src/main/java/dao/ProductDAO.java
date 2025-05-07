package dao;

import model.Product;
import util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	public List<Product> getAllProducts() {
	    List<Product> productList = new ArrayList<>();
	    String sql = "SELECT p.*, " +
	                 "       p.rating AS product_rating, " +
	                 "       COALESCE(AVG(r.rating), 0) AS avg_rating, " +
	                 "       COUNT(r.rating) AS review_count " +
	                 "FROM Product p " +
	                 "LEFT JOIN Review r ON p.product_id = r.product_id " +
	                 "GROUP BY p.product_id";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Product product = new Product();
	            product.setProductId(rs.getInt("product_id"));
	            product.setName(rs.getString("name"));
	            product.setDescription(rs.getString("description"));
	            product.setPrice(rs.getBigDecimal("price"));
	            product.setCategory(rs.getString("category"));
	            product.setQuantity(rs.getInt("quantity"));
	            product.setProductRating(rs.getFloat("product_rating"));  // Set product rating
	            product.setAvgRating(rs.getFloat("avg_rating"));  // Set average rating
	            product.setReviewCount(rs.getInt("review_count"));  // Set review count
	            productList.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return productList;
	}


	// Get product by ID
	public Product getProductById(int productId) {
	    Product product = null;
	    try (Connection conn = DBConnection.getConnection()) {
	        String sql = "SELECT p.*, AVG(r.rating) AS avg_rating " +
	                     "FROM Product p " +
	                     "LEFT JOIN Review r ON p.product_id = r.product_id " +
	                     "WHERE p.product_id = ? " +
	                     "GROUP BY p.product_id";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, productId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            product = new Product();
	            product.setProductId(rs.getInt("product_id"));
	            product.setName(rs.getString("name"));
	            product.setCategory(rs.getString("category"));  // Add this line to set the category
	            product.setDescription(rs.getString("description"));
	            // Set price as BigDecimal
	            product.setPrice(rs.getBigDecimal("price"));  // Using BigDecimal
	            // Set dynamic average rating
	            product.setAvgRating(rs.getFloat("avg_rating"));
	            product.setQuantity(rs.getInt("quantity"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return product;
	}


    // Update product quantity
    public void updateProductQuantity(int productId, int newQuantity) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Product SET quantity = ? WHERE product_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Product> searchAndFilterProducts(String keyword, String category, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT p.*, " +
                                             "       p.rating AS product_rating, " + 
                                             "       COALESCE(AVG(r.rating), 0) AS avg_rating, " +
                                             "       COUNT(r.rating) AS review_count " +
                                             "FROM Product p " +
                                             "LEFT JOIN Review r ON p.product_id = r.product_id " +
                                             "WHERE 1=1");  // Start with a valid condition

        // Dynamically add conditions based on the search filters
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND p.name LIKE ?");
        }
        if (category != null && !category.isEmpty()) {
            sql.append(" AND p.category = ?");
        }
        if (minPrice != null) {
            sql.append(" AND p.price >= ?");
        }
        if (maxPrice != null) {
            sql.append(" AND p.price <= ?");
        }

        sql.append(" GROUP BY p.product_id");  // Group by product_id to get the correct aggregates

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;

            // Set the parameters for the dynamic filters
            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(index++, "%" + keyword + "%");  // Search for product names that contain the keyword
            }
            if (category != null && !category.isEmpty()) {
                stmt.setString(index++, category);  // Filter by category
            }
            if (minPrice != null) {
                stmt.setBigDecimal(index++, minPrice);  // Filter by minimum price
            }
            if (maxPrice != null) {
                stmt.setBigDecimal(index++, maxPrice);  // Filter by maximum price
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setProductRating(rs.getFloat("product_rating"));  // Get product rating from the table
                product.setAvgRating(rs.getFloat("avg_rating"));  // Get dynamic average rating
                product.setReviewCount(rs.getInt("review_count"));  // Get review count
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }



}
