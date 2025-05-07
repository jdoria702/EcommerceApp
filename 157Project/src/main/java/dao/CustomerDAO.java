package dao;

import model.Customer;
import util.DBConnection;

import java.sql.*;

public class CustomerDAO {

    // Save customer to the database
    public void saveCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (email, password, first_name, last_name, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, customer.getEmail());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getFirstName());
            stmt.setString(4, customer.getLastName());
            stmt.setString(5, customer.getAddress());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve customer by email (for login)
    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM Customer WHERE email = ?";
        Customer customer = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	customer = new Customer(
            		    rs.getInt("customer_id"),
            		    rs.getString("email"),
            		    rs.getString("password"),
            		    rs.getString("first_name"),
            		    rs.getString("last_name"),
            		    rs.getString("address")
            		);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}

