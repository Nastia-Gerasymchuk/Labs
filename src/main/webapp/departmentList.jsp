<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.11.2019
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Departments</title>
</head>
<body>
<c:choose>
    <c:when test="${size>0}">
        <table>
            <tr>
                <td>Id</td>
                <td>Name</td>
            </tr>
            <c:forEach var="d" items="${departments}">
                <tr>
                    <td>${d.id}</td>
                    <td>${d.name}</td>
                    <td>
                        <form action="/departments" method="post">
                            <input type="text" value="${d.id}" name="del_id">
                            <input type="submit" value="delete">
                        </form>
                        <form action="/department_edit" method="get">
                            <input type="text" value="${d.id}" name="edit_department">
                            <input type="submit" value="edit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
<form action="addDepartment.jsp" method="post">
    <input type="submit" name="addDepartment" value="add" hidden>
    <input type="submit" value="Add department">
</form>

</body>
</html>
