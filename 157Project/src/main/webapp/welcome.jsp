<%@ page import="model.Customer" %>
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
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome, <%= customer.getEmail() %>!</h2>
    <p>Your customer ID is: <%= customer.getCustomerId() %></p>
    <p>Your address: <%= customer.getAddress() %></p>

    <a href="logout.jsp">Logout</a>
</body>
</html>
