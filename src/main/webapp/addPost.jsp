<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 29.11.2019
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add post</title>
</head>
<body>
<form action="/addPost" method="post">
    Post name:<input type="text" name="name">
    Post salary:<input type="number" name="salary">
    Hours post:<input type="number" name="hoursPost">
    <input type="submit" value="addPost">
</form>
</body>
</html>
