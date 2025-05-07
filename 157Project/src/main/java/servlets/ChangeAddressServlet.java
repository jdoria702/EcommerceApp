package servlets;

import model.Customer;
import dao.CustomerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/changeAddress")
public class ChangeAddressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String newAddress = request.getParameter("newAddress");

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomerByEmailAndPassword(email, password);

        if (customer != null) {
            customerDAO.updateAddress(customer.getCustomerId(), newAddress);

            // Update session
            HttpSession session = request.getSession();
            customer.setAddress(newAddress);
            session.setAttribute("customer", customer);

            response.sendRedirect("products");
        } else {
            request.setAttribute("error", "Invalid email or password.");
            request.getRequestDispatcher("changeAddress.jsp").forward(request, response);
        }
    }
}