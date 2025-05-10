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

        int productId = Integer.parseInt(request.getParameter("productId"));

        ProductDAO productDAO = new ProductDAO();
        ReviewDAO reviewDAO = new ReviewDAO();
        SellerDAO sellerDAO = new SellerDAO();

        Product product = productDAO.getProductById(productId);
        Seller seller = sellerDAO.getSellerById(product.getSellerId());
        List<Product> sellerProducts = productDAO.getProductsBySellerId(seller.getSellerId());
        List<Review> reviews = reviewDAO.getReviewsByProductId(productId);

        request.setAttribute("product", product);
        request.setAttribute("reviews", reviews);
        request.setAttribute("seller", seller);
        request.setAttribute("sellerProducts", sellerProducts);

        request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
    }
}