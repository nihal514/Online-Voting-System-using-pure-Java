<%-- 
    Document   : AdminEnterNewParty
    Created on : 03-Apr-2022, 8:30:22 pm
    Author     : amans
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*" %>
<%@page import="javax.servlet.http.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Party Entering Page</title>
        <link rel="stylesheet" href="Votercss.css">
    </head>
    <body>
        
        <div id="Heading">
            <h1>Add New Party Details</h1>
            <hr>
        </div>
        <form action="PartyEntering" method="post">
            <table>
                <tr>
                <td>
                    Enter Name of Party :
                </td>
                <td>
                    <input type="text" name="partyname">
                </td>
                </tr>
                <tr>
                <td>
                    Enter Name of Party Leader :
                </td>
                <td>
                    <input type="text" name="leadername">
                </td>
                </tr>
                <tr>
                <td>
                    Select Party logo :
                </td>
                <td>
                    <input type="file" name="partylogo" enctype="mul">
                </td>
                <br>
                <td>
                    <br><input type="submit" value="submit">
                </td>
                </tr>        
            </table>    
        </form>
    </body>
</html>
