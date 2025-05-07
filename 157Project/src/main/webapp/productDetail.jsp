<%@ page import="model.Customer" %>
<%@ page import="model.Product" %>
<%@ page import="model.Review" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%@ include file="header.jsp" %>

<%
    Product product = (Product) request.getAttribute("product");
    List<Review> reviews = (List<Review>) request.getAttribute("reviews");
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= product.getName() %> - Details</title>
</head>
<body>
    <h2><%= product.getName() %></h2>
    <p><strong>Category:</strong> <%= product.getCategory() %></p>
    <p><strong>Description:</strong> <%= product.getDescription() %></p>
    <p><strong>Price:</strong> $<%= product.getPrice() %></p>
    <p><strong>Rating:</strong> <%= product.getAvgRating() %></p>
    <p><strong>Quantity Available:</strong> <%= product.getQuantity() %></p>

    <form action="addToOrder" method="post">
        <input type="hidden" name="productId" value="<%= product.getProductId() %>" />
        Quantity: <input type="number" name="quantity" value="1" min="1" max="<%= product.getQuantity() %>" required />
        <button type="submit">Purchase!</button>
    </form>

    <hr>
    
    
	<style>
	    .review-container {
	        background-color: #f9f9f9;
	        padding: 20px;
	        border-radius: 8px;
	        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	        margin-bottom: 30px;
	    }
	
	    .review-container h3 {
	        text-align: center;
	        color: #333;
	    }
	
	    .review-form {
	        display: flex;
	        flex-direction: column;
	        gap: 15px;
	    }
	
	    .review-form label {
	        font-weight: bold;
	        color: #333;
	    }
	
	    .review-form input, .review-form textarea {
	        padding: 10px;
	        border: 1px solid #ddd;
	        border-radius: 5px;
	        font-size: 1rem;
	    }
	
	    .review-form input[type="submit"] {
	        background-color: #4CAF50;
	        color: white;
	        border: none;
	        cursor: pointer;
	        font-size: 1.2rem;
	        transition: background-color 0.3s ease;
	    }
	
	    .review-form input[type="submit"]:hover {
	        background-color: #45a049;
	    }
	
	    .rating-input {
	        display: flex;
	        justify-content: flex-start;
	        gap: 10px;
	    }
	
	    .rating-input input {
	        margin-right: 5px;
	    }
	
	    .rating-input label {
	        font-size: 1rem;
	    }
	</style>
	
	<div class="review-container">
	    <h3>Leave a Review</h3>
	    <form action="submitReview" method="post" class="review-form">
	        <input type="hidden" name="productId" value="<%= product.getProductId() %>" />
	        <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>" />
	
	        <div class="rating-input">
	            <label for="rating">Rating: </label>
	            <input type="radio" name="rating" value="1" /> 1
	            <input type="radio" name="rating" value="2" /> 2
	            <input type="radio" name="rating" value="3" /> 3
	            <input type="radio" name="rating" value="4" /> 4
	            <input type="radio" name="rating" value="5" /> 5
	        </div>
	
	        <label for="reviewText">Your Review:</label>
	        <textarea name="reviewText" rows="4" placeholder="Write your review here..."></textarea>
	
	        <input type="submit" value="Submit Review" />
	    </form>
	</div>


    
    <h3>Reviews</h3>
    <ul>
        <%
            if (reviews != null && !reviews.isEmpty()) {
                for (Review review : reviews) {
        %>
            <li><strong><%= review.getReviewerName() %> (Rating: <%= review.getRating() %>/5):</strong> <%= review.getReview() %></li>
        <%
                }
            } else {
        %>
            <li>No reviews yet.</li>
        <%
            }
        %>
    </ul>
</body>
</html>

<jsp:include page="footer.jsp" />