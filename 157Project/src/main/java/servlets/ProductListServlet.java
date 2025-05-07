package servlets;

import dao.ProductDAO;
import model.Product;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve parameters from the request
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;

        // Parse the price range if provided
        try {
            if (request.getParameter("minPrice") != null && !request.getParameter("minPrice").isEmpty()) {
                minPrice = new BigDecimal(request.getParameter("minPrice"));
            }
            if (request.getParameter("maxPrice") != null && !request.getParameter("maxPrice").isEmpty()) {
                maxPrice = new BigDecimal(request.getParameter("maxPrice"));
            }
        } catch (NumberFormatException e) {
            // Handle invalid price format if needed
        }

        // Call the DAO to get the filtered products
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.searchAndFilterProducts(keyword, category, minPrice, maxPrice);

        // Set the product list as a request attribute
        request.setAttribute("products", products);

        // Forward the request to the JSP page to display the results
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
