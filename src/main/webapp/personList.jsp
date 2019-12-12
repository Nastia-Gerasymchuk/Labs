<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.11.2019
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Persons</title>
</head>
<body>
<c:choose>
    <c:when test="${size>0}">
       <table>
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Surname</td>
                <td>Middle name</td>
                 <td>Year Born</td>
                <td>Address</td>
                <td>Login</td>
                <td>Password</td>
            </tr>
            <c:forEach var="p" items="${persons}" >
                <tr>
                    <td>${p.idPerson}</td>
                    <td>${p.name}</td>
                    <td>${p.surname}</td>
                    <td>${p.fathername}</td>
                    <td>${p.yearBorn}</td>
                    <td>${p.address}</td>
                    <td>${p.login}</td>
                    <td>${p.pasw}</td>
                    <td>
                        <form action="/person" method="post">
                            <input type="text" name="del_id" value="${p.idPerson}">
                            <input type="submit" value="delete">
                        </form>
                        <form action="/person_edit" method="get">
                            <input type="text" name="edit_pers" value="${p.idPerson}">
                            <input type="submit" value="edit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
           </table>
           </c:when>
    </c:choose>
<form action="addPerson.jsp" method="post">
    <input type="text" name="addPerson" value="add" hidden>
    <input type="submit" value="Add person">
</form>
</body>
</html>
