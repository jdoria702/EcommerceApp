package servlets;

import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/testdb")
public class TestDBServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("✅ DB Connection Successful!");
            response.getWriter().println("✅ DB Connection Successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("❌ DB Connection Failed: " + e.getMessage());
        }
    }
}
