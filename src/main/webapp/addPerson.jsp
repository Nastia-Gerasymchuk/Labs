<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 02.12.2019
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Person</title>
</head>
<body>
<form action="/addPerson" method="post">

    Second name:<input type="text" name="secondName"><br>
    First name:<input type="text" name="firstName"><br>
    Middle name:<input type="text" name="middleName"><br>
    Date born:<input type="date" name="birthday"><br>
    Address:<input type="text" name="address"><br>
    Login:<input type="text" name="login"><br>
    Password:<input type="text" name="password">
    <input type="submit" value="Add person">
</form>
</body>
</html>
