<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.12.2019
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit person</title>
</head>
<body>
<form action="/person_edit" method="post">
<input type="number" name="id" value="${id}" readonly>
<input type="text" name="name" value="${name}" readonly>
<input type="text" name="surname" value="${surname}" readonly>
<input type="text" name="middle_name" value="${middle_name}" readonly>
<input type="text" name="address" value="${address}" >
<input type="text" name="birthday" value="${birthday}" readonly>
<input type="text" name="login" value="${login}" readonly>
<input type="text" name="password" value="${password}" readonly>
<input type="submit" value="save change">
</form>
</body>
</html>
