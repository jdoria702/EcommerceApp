package servlets;

import model.Review;
import dao.ReviewDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/submitReview")
public class SubmitReviewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String reviewText = request.getParameter("reviewText");
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String reviewerName = request.getParameter("reviewerName");

        // Create a new Review object
        Review newReview = new Review();
        newReview.setProductId(productId);
        newReview.setRating(rating);
        newReview.setReview(reviewText);
        newReview.setCustomerId(customerId);
        newReview.setReviewerName(reviewerName);

        // Use the DAO to save the review in the database
        ReviewDAO reviewDAO = new ReviewDAO();
        reviewDAO.saveReview(newReview);

        // After saving the review, redirect back to the product details page
        response.sendRedirect("productDetail?productId=" + productId);
    }
}
