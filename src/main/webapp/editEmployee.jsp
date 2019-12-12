<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06.12.2019
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
    <form action="/edit_employee" method="post">
        <input type="number" name="id" id="id" value="${id}" readonly>
        <input type="text" name="name" value="${name}" id="name" readonly>
        <input type="text" name="surname" value="${surname}" id="surname" readonly>
        <input type="text" name="post" value="${post}" id="post" readonly>
        <input type="number" name="workingRate" value="${workingRate}" id="workingRate" min="0" step="0.25" max="2">
        <input type="submit" value="save">
    </form>
</body>
</html>
