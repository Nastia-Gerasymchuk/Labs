<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.11.2019
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
    <c:choose>
        <c:when test="${size>0}">
            <table>
                <tr>
                    <td>Id</td>  <%-- <select></select>--%>
                    <td>Last name</td>
                    <td>Name</td>
                    <td>Middle name</td>
                    <td>Date Coming At Work</td>
                    <td>Working Rate</td>
                    <td>Category</td>
                    <td>Post</td>
                    <td>Department</td>

                </tr>

            <c:forEach var="e" items="${employees}">
                <tr>
                    <td>${e.idEmployee}</td>
                    <td>${e.surname}</td>
                    <td>${e.name}</td>
                    <td>${e.fathername}</td>
                    <td>${e.dateComingAtWork}</td>
                    <td>${e.workingRate}</td>
                    <td>${e.category}</td>
                    <td>${e.myPost}</td>
                    <td>${e.department}</td>

<%--                    <td>--%>
<%--                        <form action="/edit_employee" method="post">--%>
<%--                            <input type="text" name="id" value="${e.idEmployee}">--%>
<%--                            <input type="submit" value="edit">--%>
<%--                        </form>--%>
<%--                    </td>--%>
                    <td>
                        <form action="/employee" method="post">
                            <input type="text" name="del_id" value="${e.idEmployee}">
                            <input type="submit" value="delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </c:when>
    </c:choose>
    <form action="/add_Employee">
        <input type="submit" value="add_employee">
    </form>


</body>
</html>
