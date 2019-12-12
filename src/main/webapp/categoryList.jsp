<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.11.2019
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
    <c:choose>
        <c:when test="${size>0}">
            <table>
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Persent money</td>
                </tr>
                <c:forEach var="c" items="${categories}">
                    <tr>
                        <td>${c.idCategory}</td>
                        <td>${c.name}</td>
                        <td>${c.persentMoney}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</body>
</html>
