<%-- 
    Document   : register
    Created on : Jun 13, 2024, 5:29:23 PM
    Author     : namdng09
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>REGISTER FORM</h2>
        <h3 style="color: red">${requestScope.error}</h3>
        <form action="register" method="post">
            <label for="email">Email: </label>
            <input type="text" name="email" id="email" required><br><br>
            <label for="username">Username: </label>
            <input type="text" name="name" id="username" required><br><br>
            <label for="password">Password: </label>
            <input type="text" name="pass" id="password" required><br><br>
            <input type="submit" name="Save">
        </form>
    </body>
</html>
