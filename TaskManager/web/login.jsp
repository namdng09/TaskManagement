<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from brandio.io/envato/iofrm/html/login25.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 19 Jun 2024 09:09:30 GMT -->

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Task Tracking</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/fontawesome-all.min.css">
        <link rel="stylesheet" type="text/css" href="css/iofrm-style.css">
        <link rel="stylesheet" type="text/css" href="css/iofrm-theme22.css">
        <style>
            .snackbar {
                visibility: hidden;
                min-width: 250px;
                background-color: #333;
                color: #fff;
                text-align: center;
                border-radius: 2px;
                padding: 16px;
                position: fixed;
                z-index: 1;
                left: 50%;
                bottom: 30px;
                font-size: 17px;
                transform: translateX(-50%);
            }

            .snackbar.show {
                visibility: visible;
                -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
                animation: fadein 0.5s, fadeout 0.5s 2.5s;
            }

            @-webkit-keyframes fadein {
                from {
                    bottom: 0;
                    opacity: 0;
                }

                to {
                    bottom: 30px;
                    opacity: 1;
                }
            }

            @keyframes fadein {
                from {
                    bottom: 0;
                    opacity: 0;
                }

                to {
                    bottom: 30px;
                    opacity: 1;
                }
            }

            @-webkit-keyframes fadeout {
                from {
                    bottom: 30px;
                    opacity: 1;
                }

                to {
                    bottom: 0;
                    opacity: 0;
                }
            }

            @keyframes fadeout {
                from {
                    bottom: 30px;
                    opacity: 1;
                }

                to {
                    bottom: 0;
                    opacity: 0;
                }
            }
        </style>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
                            <c:set var="cookie" value="${pageContext.request.cookies}"/>
                            <form action="login" method="post">
                                <input class="form-control" type="text" name="name" placeholder="Username" value="${requestScope.uName}" required>
                                <input class="form-control" type="password" name="pass" placeholder="Password" value="${uPass}"" required>
                                <input type="checkbox" id="chk1" ${reMem==null?"":"checked"} name="rem"><label for="chk1">Remmeber me</label>
                                <div class="form-button">
                                    <button id="submit" type="submit" class="ibtn">Login</button>
                                </div>
                            </form>
                            <!-- Snackbar -->
                            <div id="snackbar" class="snackbar"></div>
                            <script>
                                window.onload = function () {
                                    var status = "<%= request.getAttribute("status") != null ? request.getAttribute("status") : "" %>";
                                    var message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";

                                    if (status && message) {
                                        var snackbar = document.getElementById("snackbar");
                                        snackbar.className = "snackbar show";
                                        snackbar.innerText = message;
                                        snackbar.style.backgroundColor = status === 'success' ? '#4caf50' : '#f44336';

                                        setTimeout(function () {
                                            snackbar.className = snackbar.className.replace(" show", "");
                                        }, 3000);
                                    }
                                };
                            </script>
                            <div class="other-links">
                                <div class="text">Or login with</div>
                                <a href="#"><i class="fab fa-facebook-f"></i>Facebook</a><a href="#"><i class="fab fa-google"></i>Google</a><a href="#"><i class="fab fa-linkedin-in"></i>Linkedin</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/jquery-migrate-3.1.0.min.html"></script>
        <script src="js/mmenu.min.js"></script>
        <script src="js/tippy.all.min.js"></script>
        <script src="js/simplebar.min.js"></script>
        <script src="js/bootstrap-slider.min.js"></script>
        <script src="js/bootstrap-select.min.js"></script>
        <script src="js/snackbar.js"></script>
        <script src="js/clipboard.min.js"></script>
        <script src="js/counterup.min.js"></script>
        <script src="js/magnific-popup.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/custom.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>


    </body>

    <!-- Mirrored from brandio.io/envato/iofrm/html/login25.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 19 Jun 2024 09:09:31 GMT -->

</html>
