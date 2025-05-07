package servlets;

import dao.OrderDAO;
import model.Customer;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch the order history for the logged-in customer
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderHistory = orderDAO.getOrderHistory(customer.getCustomerId());

        // Set the order history attribute and forward to the JSP page
        request.setAttribute("orderHistory", orderHistory);
        request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
    }
}
