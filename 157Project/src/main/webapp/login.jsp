<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
    <label for="email">Email:</label>
    <input type="email" name="email" required /><br><br>

    <label for="password">Password:</label>
    <input type="password" name="password" required /><br><br>

    <input type="submit" value="Login" />
</form>

<% String error = (String) request.getAttribute("error");
   if (error != null) { %>
    <p style="color: red;"><%= error %></p>
<% } %>
</body>
</html>
