<%--
  Created by IntelliJ IDEA.
  User: Shlanlinec
  Date: 13.06.2020
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<html>
<head>
    <title>Meals</title>
    <style>
        table, th, td {
            border: 2px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h3>
    <a href="index.html">Home</a>
</h3>
<hr>
<h2>Meals</h2>

<table style="width:100%">
    <tr>
        <th>mealId</th>
        <th>dateTime</th>
        <th>description</th>
        <th>calories</th>
        <th colspan=2>Action</th>
    </tr>
    <c:forEach var="m" items="${meals}">
        <tr style="${m.excess ? "color:red" : "color:green"}">
            <td>
                <c:out value="${m.id}"/>
            </td>
            <td>
                <javatime:format value="${m.dateTime}" pattern="yyyy-MM-dd HH:mm" var="dateToDisplay"/>
                <c:out value="${dateToDisplay}"/>
            </td>
            <td>
                <c:out value="${m.description}"/>
            </td>
            <td>
                <c:out value="${m.calories}"/>
            </td>
            <td><a href="mealServlet?action=edit&id=<c:out value="${m.id}"/>">Update</a></td>
            <td><a href="mealServlet?action=delete&id=<c:out value="${m.id}"/>">Delete</a></td>
        </tr>

    </c:forEach>

</table>
<p><a href="mealServlet?action=insert">Add User</a></p>
</body>
</html>
