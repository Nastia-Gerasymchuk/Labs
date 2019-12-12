<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.12.2019
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit department</title>
</head>
<body>
<form action="/department_edit" method="post">
    <input type="number" name="id" value="${id}" readonly>
    <input type="text" name="name" value="${name}">
    <input type="submit" value="save change">
</form>
</body>
</html>
