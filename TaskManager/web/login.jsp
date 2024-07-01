<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from brandio.io/envato/iofrm/html/login25.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 19 Jun 2024 09:09:30 GMT -->
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Task Tracking</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/fontawesome-all.min.css">
        <link rel="stylesheet" type="text/css" href="css/iofrm-style.css">
        <link rel="stylesheet" type="text/css" href="css/iofrm-theme22.css">
    </head>

    <body>
        <div class="form-body without-side">
            <div class="website-logo">
                <a href="index.html">
                    <div class="logo">
                        <img class="logo-size" src="images/logo-light.svg" alt="">
                    </div>
                </a>
            </div>
            <div class="row">
                <div class="img-holder">
                    <div class="bg"></div>
                    <div class="info-holder">
                        <img src="images/graphic3.svg" alt="">
                    </div>
                </div>
                <div class="form-holder">
                    <div class="form-content">
                        <div class="form-items">
                            <h3>Login to account</h3>
                            <p>Access to the most powerfull tool in the entire design and web industry.</p>
                            <div class="page-links">
                                <a href="login" class="active">Login</a><a href="register">Register</a>
                            </div>
                            <h5 style="color: red">${requestScope.error}</h5>
                            <c:set var="cookie" value="${pageContext.request.cookies}"/>
                            <form action="login" method="post">
                                <input class="form-control" type="text" name="name" placeholder="Username" value="${requestScope.uName}" required>
                                <input class="form-control" type="password" name="pass" placeholder="Password" value="${uPass}"" required>
                                <input type="checkbox" id="chk1" ${reMem==null?"":"checked"} name="rem"><label for="chk1">Remmeber me</label>
                                <div class="form-button">
                                    <button id="submit" type="submit" class="ibtn">Login</button>
                                </div>
                            </form>
                            <div class="other-links">
                                <div class="text">Or login with</div>
                                <a href="#"><i class="fab fa-facebook-f"></i>Facebook</a><a href="#"><i class="fab fa-google"></i>Google</a><a href="#"><i class="fab fa-linkedin-in"></i>Linkedin</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>

    <!-- Mirrored from brandio.io/envato/iofrm/html/login25.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 19 Jun 2024 09:09:31 GMT -->

</html>
