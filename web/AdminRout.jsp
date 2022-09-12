<%-- 
    Document   : Admin
    Created on : 03-Apr-2022, 3:14:24 pm
    Author     : amans
--%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>

    </head>

    <body>
        <c:set var = "task" scope = "session" value = "${param.task}"/>
        <%--<c:out value="${task}"></c:out>--%>

        <c:choose>

            <c:when test = "${task == 'Voting'}">
                <c:redirect url="Vote.jsp"/>  
            </c:when>
            
            <c:when test = "${task == 'EnterParty'}">
                <c:redirect url="AdminEnterNewParty.jsp"/>  
            </c:when>

            <c:when test = "${task == 'SeeVotes'}">
                <c:redirect url="AdminSeeVotes.jsp"/>  
            </c:when>

            <c:otherwise>
                No comments ...
            </c:otherwise>
        </c:choose>


    </body>
</html>
