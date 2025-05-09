<%@ page import="model.Customer" %>
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
    <meta charset="UTF-8">
    <title>Change Address</title>
    <style>
        body {
            background-color: #e9ecef;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 30px;
        }
        .page-container {
            background-color: #fff;
            max-width: 500px;
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
        form {
            margin-top: 20px;
        }
        label {
            font-weight: bold;
        }
        input[type="email"],
        input[type="password"],
        input[type="text"] {
            width: 100%;
            padding: 8px 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
        .error-message {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="page-container">
    <h2>Change Shipping Address</h2>

    <form action="changeAddress" method="post">
        <label>Email:</label>
        <input type="email" name="email" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <label>New Address:</label>
        <input type="text" name="newAddress" required>

        <button type="submit">Update Address</button>
    </form>

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p class="error-message"><%= error %></p>
    <% } %>
</div>

</body>
</html>
