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

        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;

        try {
            if (request.getParameter("minPrice") != null && !request.getParameter("minPrice").isEmpty()) {
                minPrice = new BigDecimal(request.getParameter("minPrice"));
            }
            if (request.getParameter("maxPrice") != null && !request.getParameter("maxPrice").isEmpty()) {
                maxPrice = new BigDecimal(request.getParameter("maxPrice"));
            }
        } catch (NumberFormatException e) {}

        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.searchAndFilterProducts(keyword, category, minPrice, maxPrice);
        List<String> categories = productDAO.getDistinctCategories();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
