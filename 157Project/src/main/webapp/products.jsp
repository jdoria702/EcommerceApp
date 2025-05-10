<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
</head>
<body>

    <form action="productList" method="get">
        <h3>Search and Filter</h3>
        <label for="keyword">Search for product name:</label>
        <input type="text" name="keyword" id="keyword" placeholder="Search for product name..."/><br><br>
		<label for="category">Category:</label>
		<select name="category" id="category">
		    <option value="">All Categories</option>
		    <%
		        List<String> categories = (List<String>) request.getAttribute("categories");
		        String selectedCategory = request.getParameter("category");
		
		        if (categories != null) {
		            for (String cat : categories) {
		                String selected = cat.equals(selectedCategory) ? "selected" : "";
		    %>
		                <option value="<%= cat %>" <%= selected %>><%= cat %></option>
		    <%
		            }
		        }
		    %>
		</select><br><br>
        
        <label for="minPrice">Min Price:</label>
        <input type="number" name="minPrice" id="minPrice" step="0.01" min="0"/><br><br>
        
        <label for="maxPrice">Max Price:</label>
        <input type="number" name="maxPrice" id="maxPrice" step="0.01" min="0"/><br><br>
        
        <button type="submit">Search</button>
    </form>

    <hr>

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
                    float avgRating = product.getAvgRating();
                    int reviewCount = product.getReviewCount();
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
                    <%= String.format("%.1f", avgRating) %> (<%= reviewCount %>)
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
