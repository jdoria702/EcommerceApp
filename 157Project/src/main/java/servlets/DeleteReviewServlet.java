package servlets;

import dao.ReviewDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteReview")
public class DeleteReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        int productId = Integer.parseInt(request.getParameter("productId"));

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        ReviewDAO reviewDAO = new ReviewDAO();
        int reviewOwnerId = reviewDAO.getCustomerIdByReviewId(reviewId);

        if (customer != null && customer.getCustomerId() == reviewOwnerId) {
            reviewDAO.deleteReview(reviewId);
        } else {
            System.out.println("Unauthorized review deletion attempt.");
        }

        response.sendRedirect("productDetail?productId=" + productId);
    }
}
