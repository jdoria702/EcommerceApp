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

        HttpSession session = request.getSession();
        Customer loggedInCustomer = (Customer) session.getAttribute("customer");

        if (loggedInCustomer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String newAddress = request.getParameter("newAddress");
        CustomerDAO customerDAO = new CustomerDAO();

        customerDAO.updateAddress(loggedInCustomer.getCustomerId(), newAddress);

        loggedInCustomer.setAddress(newAddress);
        session.setAttribute("customer", loggedInCustomer);

        response.sendRedirect("products");
    }
}
