<%-- 
    Document   : Login
    Created on : 02-Apr-2022, 12:09:59 pm
    Author     : nihal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Voting System</title>
        <link rel="stylesheet" href="Votercss.css">
    </head>
    <body>
        
        <div id="Heading">
            <h1>Online Voting System</h1>
            <hr>
        </div>
        <div id="details">
            <h2>Login</h2>
            <form action="check" method="post">
                <input type="number" name="Voter_id" placeholder="Enter your voter_id"><br><br>
                <input type="password" name="password" placeholder="Enter your password"><br><br>
                <select id="drop" name="Role">
                    <option value="1">Voter</option>
                    <option value="2">Admin</option>
                </select><br> <br>
                <button style="color:wheat;">Login</button><br><br>
                <div style="font-family: 'Courier New', Courier, monospace;">
                    New User :<a href="Register.jsp">Register Here</a>
                </div>
            </form>
        </div>
    </body>
</html>
