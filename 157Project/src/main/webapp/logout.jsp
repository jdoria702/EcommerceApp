<%@ page session="true" %>
<%
    session.invalidate();  // Ends the current session
    response.sendRedirect("login.jsp");  // Redirects to login
%>
