<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
</head>
<body>

<h3>Edit Product</h3>

    <form method="post" action="editPost">
        <input  name="code" value="${post.id_post}" />
        <table border="0">
            <tr>
                <td>Id</td>
                <td style="color:#6f57ff;">${post.id_post}</td>
            </tr>
            <tr>
                <td>Post name</td>
                <td><input type="text" name="name" value="${post.post_name}" /></td>
            </tr>
            <tr>
                <td>Post salary</td>
                <td><input type="text" name="salary" value="${post.post_salary}" /></td>
            </tr>
            <tr>
                <td>Hours post</td>
                <td><input type="text" name="hours" value="${post.hours_post}" /></td>
            </tr>
            <tr>
                <td colspan = "2">
                    <input type="submit" value="Submit" />
                </td>
            </tr>
        </table>
    </form>




</body>
</html>