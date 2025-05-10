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

        CustomerDAO customerDAO = new CustomerDAO();

        try {
            if (customerDAO.getCustomerByEmail(email) != null) {
                request.setAttribute("error", "An account with this email already exists.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            Customer newCustomer = new Customer(email, password, firstName, lastName, address);
            customerDAO.saveCustomer(newCustomer);

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
