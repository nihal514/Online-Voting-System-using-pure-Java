<%-- 
    Document   : votecount
    Created on : 03-Apr-2022, 9:03:51 pm
    Author     : amans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
<%@page import="java.io.*" %>
<%@page import="javax.servlet.http.*" %>
<%@page import="javax.servlet.*" %>
<%@page import="java.sql.*" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vote Counter</title>
    </head>
    <body>
        <%!
            //for database connection
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "voting";
            String dbUsername = "root";
            String dbPassword = "";
            Connection con = null;
            Statement st = null;//to update vote status of respective user

            Statement st1 = null;//to update votes of respective party


        %>
        <%
            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);

//      ========================          Changing State of user to voted       ===========================================
            st = con.createStatement();
            int vid = (int) session.getAttribute("voterid");
            String sql = "UPDATE voting1 " + "SET voted=1 WHERE voter_id=" + vid;

            int c = st.executeUpdate(sql);

            if (c == 1) {
                out.println("<h2 style='color:green;'>Your Vote Is Submitted Successfully </h2>");
                

            } else {
                out.println("<html><head></head><body onload=\"alert('Something Went Wrong Please try again')\"></body></html>");
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.include(request, response);
            }

//      ========================          Incrementing vote       ===========================================
            int v=0;
            String pname = request.getParameter("votebuttons");// to know which parties button is pressed

            ResultSet rs = st.executeQuery("select votes,partyname from party1");
            st1 = con.createStatement();
            while (rs.next()) {
                String n = rs.getString("partyname");

                if (pname.equals(n)) {
                    v = rs.getInt("votes");
                    break;
                }
            }
            //String sql1="UPDATE party1 SET votes=votes+1 WHERE partyname="+pname;
            System.out.println("Votes =======>>>>>>> "+v);
            System.out.println("party name =======>>>>>>> "+pname);
            String sql1="UPDATE party1 SET votes="+(v+1)+" WHERE partyname='"+pname+"'";
            int c1 = st1.executeUpdate(sql1);
            if (c1 == 1) {
                out.println("<html><head></head><body onload=\"alert('Votes of your selected party has been incremented by 1 Successfully')\"></body></html>");
                RequestDispatcher rd = request.getRequestDispatcher("Vote.jsp");
                rd.include(request, response);
            } else {
                out.println("<html><head></head><body onload=\"alert('Something Went Wrong Please try again vote note incremented')\"></body></html>");
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.include(request, response);
            }

            st.close();
            st1.close();
        %>



    </body>
</html>
