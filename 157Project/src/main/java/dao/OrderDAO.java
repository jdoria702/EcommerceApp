package dao;

import model.Order;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
	public List<Order> getOrderHistory(int customerId) {
	    List<Order> orders = new ArrayList<>();
	    String sql = "SELECT o.order_id, p.name AS product_name, o.quantity, o.order_date, " +
	                 "(p.price * o.quantity) AS total_price " +
	                 "FROM Orders o " +
	                 "JOIN Product p ON o.product_id = p.product_id " +
	                 "WHERE o.customer_id = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, customerId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Order order = new Order();
	            order.setOrderId(rs.getInt("order_id"));
	            order.setProductName(rs.getString("product_name"));
	            order.setQuantity(rs.getInt("quantity"));
	            order.setOrderDate(rs.getTimestamp("order_date"));
	            order.setTotalPrice(rs.getDouble("total_price"));
	            orders.add(order);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return orders;
	}

    public void addOrder(int customerId, int productId, int quantity) {
        String sql = "INSERT INTO Orders (customer_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
