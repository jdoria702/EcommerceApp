<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>

<%
    List<Order> orderHistory = (List<Order>) request.getAttribute("orderHistory");
%>

<h2>Your Order History</h2>

<% if (orderHistory != null && !orderHistory.isEmpty()) { %>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Order Date</th>
                <th>Total Price</th>
            </tr>
        </thead>
        <tbody>
            <% for (Order order : orderHistory) { %>
                <tr>
                    <td><%= order.getOrderId() %></td>
                    <td><%= order.getProductName() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td><%= order.getOrderDate() %></td>
                    <td><%= order.getTotalPrice() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
<% } else { %>
    <p>You have not made any purchases yet.</p>
<% } %>

<jsp:include page="footer.jsp" />