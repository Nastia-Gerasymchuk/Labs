<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 29.11.2019
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit post</title>
</head>
<body>
<form action="/post_edit" method="post">
    <input type="number" min="1" name="id" value="${id}" readonly>
    <input type="text" name="name" value="${name}" readonly>
    <input type="number" name="salary" min="${minSalary}" max="${maxSalary}" value="${salary}">
    <input type="number" name="hoursPost" value="${hoursPost}" readonly>
    <input type="submit" value="edit_Post">
</form>
</body>
</html>
