<%-- 
    Document   : login
    Created on : Jun 7, 2024, 10:30:34 PM
    Author     : namdng09
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>LOGIN FORM</h2>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="login" method="post">
            <label for="username">Username: </label>
            <input type="text" name="name" id="username" required><br><br>
            <label for="password">Password: </label>
            <input type="text" name="pass" id="password" required><br><br>
            <input type="checkbox" name="rem" id="remember" value="ON"><br><br>
            <label for="remember">Remember</label>
            <input type="submit" name="Login">
        </form>
    </body>
</html>
