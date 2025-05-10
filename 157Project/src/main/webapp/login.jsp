<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Login</title>
    <style>
    body {
        margin: 0;
        padding: 0;
        background-color: #e0e0e0;
        font-family: Arial, sans-serif;
    }

    .page-wrapper {
        max-width: 600px;
        margin: 50px auto;
        background-color: #fff;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 0 15px rgba(0,0,0,0.1);
    }

    h2, h3 {
        text-align: center;
        color: #333;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    label {
        margin-top: 10px;
        font-weight: bold;
    }

    input[type="text"],
    input[type="email"],
    input[type="password"] {
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    button, input[type="submit"] {
        margin-top: 20px;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button:hover, input[type="submit"]:hover {
        background-color: #45a049;
    }

    .link {
        text-align: center;
        margin-top: 20px;
    }

    .link a {
        color: #2196F3;
        text-decoration: none;
    }

    .link a:hover {
        text-decoration: underline;
    }
</style>
    
</head>
<body>
<div class="page-wrapper">
    <h2>Login</h2>
    <form action="login" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" required />

        <label for="password">Password:</label>
        <input type="password" name="password" required />

        <input type="submit" value="Login" />
    </form>

    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
        <p style="color: red; text-align: center;"><%= error %></p>
    <% } %>

    <div class="link">
        <p>Don't have an account? <a href="index.jsp">Sign up</a></p>
    </div>
</div>
</body>
</html>