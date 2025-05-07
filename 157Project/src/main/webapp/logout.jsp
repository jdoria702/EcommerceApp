<%@ page session="true" %>
<%
    session.invalidate();  // Ends the current session
    response.sendRedirect("index.jsp");  // Redirects to login
%>
