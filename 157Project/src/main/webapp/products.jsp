<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
</head>
<body>

    <!-- Search and Filter Form -->
    <form action="productList" method="get">
        <h3>Search and Filter</h3>
        
        <!-- Search Bar -->
        <label for="keyword">Search for product name:</label>
        <input type="text" name="keyword" id="keyword" placeholder="Search for product name..."/><br><br>
        
        <!-- Category Dropdown -->
        <label for="category">Category:</label>
        <select name="category" id="category">
            <option value="">All Categories</option>
            <!-- Add more categories here as needed -->
            <option value="Electronics">Electronics</option>
            <option value="Furniture">Furniture</option>
            <option value="Clothing">Clothing</option>
            <option value="Toys">Toys</option>
        </select><br><br>
        
        <!-- Price Range Inputs -->
        <label for="minPrice">Min Price:</label>
        <input type="number" name="minPrice" id="minPrice" step="0.01" min="0"/><br><br>
        
        <label for="maxPrice">Max Price:</label>
        <input type="number" name="maxPrice" id="maxPrice" step="0.01" min="0"/><br><br>
        
        <!-- Submit Button -->
        <button type="submit">Search</button>
    </form>

    <hr>

<!-- Display Product List -->
<h2>Product List</h2>
<table border="1" cellpadding="10">
    <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category</th>
            <th>Rating</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");

            if (products != null && !products.isEmpty()) {
                for (Product product : products) {
                    // Get the average rating and review count for the product
                    float avgRating = product.getAvgRating();
                    int reviewCount = product.getReviewCount();  // Assuming you added a getReviewCount() method
        %>
            <tr>
                <td>
                    <a href="productDetail?productId=<%= product.getProductId() %>">
                        <%= product.getName() %>
                    </a>
                </td>
                <td><%= product.getDescription() %></td>
                <td>$<%= product.getPrice() %></td>
                <td><%= product.getCategory() %></td>
                <td>
                    <%= String.format("%.1f", avgRating) %> (<%= reviewCount %>) <!-- Format the rating and show review count -->
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="5">No products available</td>
            </tr>
        <%
            }
        %>
    </tbody>
</table>

</body>
</html>

<jsp:include page="footer.jsp" />
