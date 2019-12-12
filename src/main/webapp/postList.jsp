<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.11.2019
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Posts</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
${posts}
    <c:choose>
        <c:when test="${size > 0}">
            <table class="table table-striped table-hover">
                <tr>
                    <th>Name</th>
                    <th>Post salary</th>
                    <th>Hours post</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="p" items="${posts}">
                    <tr>
                        <td>${p.name}</td>
                        <td>${p.salary}</td>
                        <td>${p.hoursPost}</td>
                        <td>
                            <form action="/post_edit" method="get" >
                                <input type="text"  value="${p.idPost}" name="edit_id">
                                <input type="submit" value="edit">
                            </form>
                        </td>
                        <td>
                            <form action="/posts" method="post">
                                <input type="text"  value="${p.idPost}" name="del_id">
                                <input type="submit" value="delete">
                            </form>
<%--                            <form action="/posts" method="post">--%>
<%--                                <input type="text" hidden value="${p.id}" name="del_id">--%>
<%--                                <input type="submit" value="delete">--%>
<%--                            </form>--%>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
    <form action="/addPost.jsp" method="get">
        <input type="text" hidden value="addPost" name="add">
        <input type="submit" value="Add post">
    </form>


</body>
</html>
