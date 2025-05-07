<%@ page import="model.Customer" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<h2>Change Shipping Address</h2>

<form action="changeAddress" method="post">
    <label>Email:</label>
    <input type="email" name="email" required><br><br>

    <label>Password:</label>
    <input type="password" name="password" required><br><br>

    <label>New Address:</label>
    <input type="text" name="newAddress" required><br><br>

    <button type="submit">Update Address</button>
</form>

<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <p style="color: red;"><%= error %></p>
<% } %>
