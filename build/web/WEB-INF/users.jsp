<%-- 
    Document   : users
    Created on : 23-Oct-2022, 10:10:18 PM
    Author     : Eric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <table>
            <tr><th>Email</th><th>First Name</th><th>Last Name</th><th>Role</th></tr>
                    <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.role.roleName}</td>
                    <td><a href="<c:out value='/users?email=${user.email}&action=edit'/>">Edit</a></td>
                    <td><a href="<c:out value='/users?email=${user.email}&action=delete'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:choose>
            <c:when test="${action == 'edit'}">
                <h2>Edit User</h2>
                <form method="post">
                    Email: ${user.email}<br>
                    First Name: <input type="text" name="first-name" id="first-name" value="${user.firstName}" required><br>
                    Last Name: <input type="text" name="last_name" id="last-name" value="${user.lastName}" required><br>
                    Password: <input type="text" name="password" id="password" required><br>
                    <select name="role" id="role" required">
                        <option value="admin" selected>system admin</option>
                        <option value="user">regular user</option>
                    </select>
                    <br>
                    <input type="hidden" name="action" value="edit">
                    <input type="submit" value="Edit user">

                </form>
            </c:when>
            <c:otherwise>
                <h2>Add User</h2><br>
                <form method="post">
                    Email: <input type="email" name="email" id="email" required><br>
                    First Name: <input type="text" name="first-name" id="first-name" required><br>
                    Last Name: <input type="text" name="last-name" id="last-name" required><br>
                    Password: <input type="text" name="password" id="password" required><br>
                    <select name="role" id="role" required>
                        <option value="admin" selected>system admin</option>
                        <option value="user">regular user</option>
                    </select><br>
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Add user">
                </form>

            </c:otherwise>
        </c:choose>


    </body>
</html>
