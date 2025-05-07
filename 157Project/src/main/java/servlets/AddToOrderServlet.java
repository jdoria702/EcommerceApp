package servlets;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Customer;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addToOrder")
public class AddToOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);

        // Check if there is enough stock
        if (product.getQuantity() >= quantity) {
            // Insert the order into the Orders table
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.addOrder(customer.getCustomerId(), productId, quantity);

            // Update product quantity in the Product table
            productDAO.updateProductQuantity(productId, product.getQuantity() - quantity);

            // Redirect to the products page
            response.sendRedirect("products");
        } else {
            // Handle error if there is not enough stock
            request.setAttribute("error", "Not enough stock available.");
            request.getRequestDispatcher("productDetail.jsp").forward(request, response);
        }
    }
}
