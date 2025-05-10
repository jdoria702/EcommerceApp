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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomerByEmail(email);

        if (customer != null && customer.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);

            response.sendRedirect("products");
        } else {
            response.sendRedirect("login.jsp?error=Invalid login");
        }
    }
}
