<%@ page import="model.Customer" %>

<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<div style="background-color: #f4f4f4; padding: 10px; text-align: center;">
    <h2>Welcome, <%= customer.getFirstName() %>!</h2>
    <p>Your address: <%= customer.getAddress() %></p>
    <a href="orderHistory" class="btn btn-primary">View Order History</a>
</div>
