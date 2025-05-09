<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>
<%
    List<Order> orderHistory = (List<Order>) request.getAttribute("orderHistory");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Order History</title>
    <style>
        body {
            background-color: #e9ecef;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 30px;
        }
        .page-container {
            background-color: #fff;
            max-width: 900px;
            margin: auto;
            border-left: 5px solid #ccc;
            border-right: 5px solid #ccc;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        thead {
            background-color: #007bff;
            color: white;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        p {
            text-align: center;
            font-size: 18px;
            margin-top: 30px;
        }
    </style>
</head>
<body>

<div class="page-container">
    <h2>Your Order History</h2>

    <% if (orderHistory != null && !orderHistory.isEmpty()) { %>
        <table>
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
                        <td>$<%= order.getTotalPrice() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>You have not made any purchases yet.</p>
    <% } %>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
