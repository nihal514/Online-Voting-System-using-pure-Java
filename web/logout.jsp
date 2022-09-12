<%-- 
    Document   : logout
    Created on : 07-Apr-2022, 9:32:51 pm
    Author     : nihal
--%>

<%@page import="com.mysql.cj.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            session.invalidate();
            response.sendRedirect("Login.jsp");
        %>
        
    </body>
</html>
