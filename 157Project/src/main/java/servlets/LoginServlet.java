package servlets;

import dao.CustomerDAO;
import model.Customer;
import util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            // Create a new CustomerDAO instance to authenticate the customer
            CustomerDAO customerDAO = new CustomerDAO(conn);
            Customer customer = customerDAO.authenticate(email, password);

            if (customer != null) {
                // If authentication is successful, create a session and store the customer object
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);

                // Redirect to the product list page (or the products servlet)
                response.sendRedirect(request.getContextPath() + "/products");  // Redirect to products page
            } else {
                // If authentication fails, set an error message and forward back to login page
                request.setAttribute("error", "Invalid email or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
