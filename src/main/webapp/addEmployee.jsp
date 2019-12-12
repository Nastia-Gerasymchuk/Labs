<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.12.2019
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Employee</title>
</head>
<body>
<form action="/add_Employee" method="post">
<%--    id person:<input type="number" name="idPerson"><br>--%>
    Second name:<input type="text" name="secondName"><br>
    First name:<input type="text" name="firstName"><br>
    Middle name:<input type="text" name="middleName"><br>
    Date born:<input type="date" name="birthday"><br>
    Address:<input type="text" name="address"><br>
    Login:<input type="text" name="login"><br>
    Password:<input type="text" name="password"><br>
<%--   id employee:<input type="number" name="idEmployee"><br>--%>
    Post:
    <select name="idPosts" id="idPosts">
        <c:forEach var="p" items="${posts}">
            <option value="${p.idPost}">${p.name}</option>
        </c:forEach>
    </select><br>
    Department:
    <select name="idDepartments" id="idDepartments">
        <c:forEach var="d" items="${departments}">
            <option value="${d.id}">${d.name}</option>
        </c:forEach>
    </select><br>
    Category:
    <select name="idCategory" id="idCategory">
        <option value="UPPER">вища</option>
        <option value="FIRST">перша</option>
        <option value="SECOND">друга</option>
        <option value="THIRD">третя</option>
        <option value="NONE">немає</option>
    </select><br>
    Working rate:
    <input type="number" min="0" value="0" name="idWorkingRate" id="idWorkingRate"><br>
    Date coming at work:
    <input type="date" name="idDateComing" id="idDateComing"><br>
    <input type="submit" value="Add employee">
</form>
</body>
</html>
