<%--
  Created by IntelliJ IDEA.
  User: Shlanlinec
  Date: 13.06.2020
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Add new meal</title>
</head>
<body>
<h2>
    <form method="post" action="mealServlet" name="formAddUser">
        <label>Meal id:</label><br/>
        <input type="text" readonly="readonly" /> <br/>
        <label>DateTime:</label><br/>
        <input type="datetime-local" name="dateTime" /> <br/>
        <label>Description:</label><br/>
        <input type="text" name="description" /> <br/>
        <label>Calories:</label><br/>
        <input type="text" name="calories" /> <br/>
        <input type="submit" value="Submit"/>
    </form>
</h2>
</body>
</html>
