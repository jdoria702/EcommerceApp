<%@ page import="model.Product" %>
<%@ page import="model.Review" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>

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
    <p><strong>Rating:</strong> <%= product.getRating() %></p>
    <p><strong>Quantity Available:</strong> <%= product.getQuantity() %></p>

    <form action="addToOrder" method="post">
        <input type="hidden" name="productId" value="<%= product.getProductId() %>" />
        Quantity: <input type="number" name="quantity" value="1" min="1" max="<%= product.getQuantity() %>" required />
        <button type="submit">Purchase!</button>
    </form>

    <hr>
    <h3>Reviews</h3>
    <ul>
        <%
            if (reviews != null && !reviews.isEmpty()) {
                for (Review review : reviews) {
        %>
            <li><strong><%= review.getCustomerName() %>:</strong> <%= review.getContent() %></li>
        <%
                }
            } else {
        %>
            <li>No reviews yet.</li>
        <%
            }
        %>
    </ul>

    <p><a href="products">Back to products</a></p>
</body>
</html>
