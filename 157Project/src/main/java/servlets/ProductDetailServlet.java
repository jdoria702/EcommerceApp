package servlets;

import dao.ProductDAO;
import dao.ReviewDAO;
import model.Product;
import model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/productDetail")
public class ProductDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));

        ProductDAO productDAO = new ProductDAO();
        ReviewDAO reviewDAO = new ReviewDAO();

        Product product = productDAO.getProductById(productId);
        List<Review> reviews = reviewDAO.getReviewsByProductId(productId);

        request.setAttribute("product", product);
        request.setAttribute("reviews", reviews);

        request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
    }
}
