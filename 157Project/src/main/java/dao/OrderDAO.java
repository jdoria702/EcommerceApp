package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDAO {
    public void placeOrder(int customerId, int productId, int quantity) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Orders (customer_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
