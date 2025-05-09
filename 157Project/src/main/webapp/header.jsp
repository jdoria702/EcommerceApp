<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>RainForest</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #e0e0e0;
            font-family: Arial, sans-serif;
        }

        .page-wrapper {
            max-width: 1000px;
            margin: 30px auto;
            background-color: white;
            padding: 30px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            border-radius: 10px;
        }

        .top-bar {
            background-color: #f9f9f9;
            padding: 15px;
            text-align: center;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .btn {
            display: inline-block;
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        .btn-secondary {
            background-color: #2196F3;
        }

        .btn-danger {
            background-color: #f44336;
        }

        .btn:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
<div class="page-wrapper">

<%@ page import="model.Customer" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<div class="top-bar">
    <h2>Welcome, <%= customer.getFirstName() %>!</h2>
    <p>
        Shipping address: <%= customer.getAddress() %>
        <a href="changeAddress.jsp" style="margin-left: 10px;">(Change)</a>
    </p>
    <a href="orderHistory" class="btn">View Order History</a>
</div>
