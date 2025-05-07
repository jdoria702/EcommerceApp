package dao;

import util.DBConnection;
import model.Seller;
import java.sql.*;

public class SellerDAO {
    public Seller getSellerById(int sellerId) {
        Seller seller = null;
        String sql = "SELECT * FROM Seller WHERE seller_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                seller = new Seller();
                seller.setSellerId(rs.getInt("seller_id"));
                seller.setName(rs.getString("name"));
                seller.setAddress(rs.getString("address"));
                seller.setDescription(rs.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seller;
    }
}
