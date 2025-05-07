<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ecommerce-Website</title>
</head>
<body>
    <h2>Welcome to Rainforest!</h2>
    
    <!-- Login Link -->
    <p><a href="<%= request.getContextPath() %>/login.jsp">Login</a></p>

    <!-- Sign-up Form -->
    <h3>Sign Up</h3>
    <form action="<%= request.getContextPath() %>/signup" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required /><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required /><br><br>

        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required /><br><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required /><br><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required /><br><br>

        <button type="submit">Sign Up</button>
    </form>
</body>
</html>
