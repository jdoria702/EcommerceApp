<%@ page import="model.Customer" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>

<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <h2>Welcome, <%= customer.getEmail() %>!</h2>
    <p>Your customer ID is: <%= customer.getCustomerId() %></p>
    <p>Your address: <%= customer.getAddress() %></p>
    <p><a href="logout.jsp">Logout</a></p>

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
                    <td><%= product.getRating() %></td>
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
