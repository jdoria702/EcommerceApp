package servlets;

import dao.ProductDAO;
import dao.ReviewDAO;
import dao.SellerDAO;
import model.Product;
import model.Review;
import model.Seller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/productDetail")
public class ProductDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the productId from the request parameters
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Create instances of the DAO classes
        ProductDAO productDAO = new ProductDAO();
        ReviewDAO reviewDAO = new ReviewDAO();
        SellerDAO sellerDAO = new SellerDAO();

        // Retrieve the product and reviews by productId
        Product product = productDAO.getProductById(productId);
        List<Review> reviews = reviewDAO.getReviewsByProductId(productId);

        // Get the seller of the product (using the seller_id from the product)
        Seller seller = sellerDAO.getSellerById(product.getSellerId());

        // Get all products sold by the same seller
        List<Product> sellerProducts = productDAO.getProductsBySellerId(seller.getSellerId());

        // Set attributes to be accessed in the JSP
        request.setAttribute("product", product);
        request.setAttribute("reviews", reviews);  // Set the reviews
        request.setAttribute("seller", seller);
        request.setAttribute("sellerProducts", sellerProducts);  // Set the products from the same seller

        // Forward the request and response to productDetail.jsp
        request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
    }
}