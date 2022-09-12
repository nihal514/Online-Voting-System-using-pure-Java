<%-- 
    Document   : Vote
    Created on : 02-Apr-2022, 7:15:06 pm
    Author     : Amans
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
        <script>
            window.onpageshow = function (event) {
                if (event.persisted) {
                    window.location.reload()
                }
            };

            $(window).bind("pageshow", function (event) {
                if (event.originalEvent.persisted) {
                    window.location.reload()
                }
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Voter Profile</title>
        <link rel="stylesheet" href="stylesheet.css">
        <link rel="stylesheet" href="Votercss.css">
        <style>


            .card {
                margin: 0;
                position: absolute;
                top: 30%;
                width: 50%;
                height: 40%;
            }

            .party
            {
                position: absolute;
                top:20%;
                right: 2%;
                width: 50%;
                border: 3px solid blue;
                border: solid;
                border-radius: 10px;
            }

            button
            {
                position: absolute;
                top:4%;
                right:2%;
            }

        </style>
        <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/voting" user="root" password="" scope="session" />
    </head>
    <body>
        <div id="Heading">
            <h1>Online Voting System</h1>
            <hr>
        </div>
        <form action="logout.jsp">
            <button>Logout</button>
        </form>

        <%!
            //for database connection
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "voting";
            String dbUsername = "root";
            String dbPassword = "";
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            // parameters to be fetched            
            String uname = null;
            String Address = null;
            int r = 1, status = 0, voterid;


        %>




        <%
            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
            st = con.createStatement();
            rs = st.executeQuery("select * from voting1");
            voterid = (int) session.getAttribute("voterid");

            while (rs.next()) {
                int v = rs.getInt("voter_id");

                if (voterid == v) {
                    uname = rs.getString("username");
                    Address = rs.getString("Address");
                    r = rs.getInt("Role");
                    status = (int) rs.getInt("Voted");

                    break;
                }
            }

        %>

<!-- ========================================= HTML for User Detials is Written Below ========================================================================= -->
        <div class="grid-container">
            <div class="card">
                <img class="photo" src="img.png" alt="Profile Photo">
                <br>           
                <span class="attribute">Name :</span>
                <span class="value"><%=uname%></span>
                <br>
                <span class="attribute">Address :</span>
                <span class="value"><%=Address%></span>
                <br>
                <%
                    if (status == 0) {
                %>
                <br>
                <span class="attribute">Status :</span> <strong style="color: red">Note Voted</strong>
                <br>
                <%
                    }
                %>

                <%
                    if (status == 1) {
                %>
                <br>
                <span class="attribute">Status :</span><strong style="color: green">Voted</strong>  
                <br>
                <%
                    }
                %>            
            </div>

<!-- ========================================= User Detials is Over ========================================================================= -->

<!--=============================== Logic For Getting Parties Data is written Below=========================================================-->


            <div class="party">
                <sql:query dataSource="${db}" var="rs">  
                    SELECT * from party1;
                </sql:query> 

                <table border="2" width="100%">  
                    <tr>  
                        <th>Party Name</th>  
                        <th>Leader Name</th>  
                        <th>Party Logo</th>
                        <th>Click To Vote</th>
                    </tr>  
                    <c:forEach var="table" items="${rs.rows}">  
                        <tr>  
                            <td><c:out value="${table.partyname}"/></td>  
                            <td><c:out value="${table.leaderName}"/></td>  
                            <td> <img src="PhotoServlet?partyname=${table.partyname}" width="50px" height="50px" />
                            </td>  
                            <td>
                                <form action="votecount.jsp">
                                    <%
                                        if (status == 1) {
                                    %>
                                    <input type="submit" name="votebuttons" value="${table.partyname}" disabled>
                                    <%
                                    } else {
                                    %>
                                    <input type="submit" name="votebuttons" value="${table.partyname}">
                                    <%
                                        }
                                    %>
                                </form>
                            </td>
                        </tr>  
                    </c:forEach>  
                </table>

                <%  con.close();
                    st.close();
//                    st1.close();
                    rs.close();
                    //                  rs1.close();
//                    rs2.close();
//                    st2.close();
                %>

            </div>
        </div>
    </body>
</html>
