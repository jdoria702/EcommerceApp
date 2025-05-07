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

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");

        // Create Customer object and save to the database
        Customer newCustomer = new Customer(email, password, firstName, lastName, address);

        // Save customer to the database (use your DAO here)
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.saveCustomer(newCustomer);

        // Redirect to login page or directly to the login page after signup
        response.sendRedirect("login.jsp");
    }
}
