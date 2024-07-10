<%-- 
    Document   : workspace-create-a-board
    Created on : Jul 1, 2024, 3:34:25 PM
    Author     : namdng09
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <!-- Mirrored from www.vasterad.com/themes/hireo_21/dashboard-settings.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 29 Jun 2024 07:12:09 GMT -->

    <head>
        <!-- Basic Page Needs
    ================================================== -->
        <title>Task Tracking</title>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, maximum-scale=1"
            />

        <!-- CSS
    ================================================== -->
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/colors/blue.css" />
        <style>
            .snackbar {
                visibility: hidden;
                min-width: 250px;
                margin-left: -125px;
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

    <body class="gray">
        <c:set var="user" value="${requestScope.user}"/>
        <!-- Wrapper -->
        <div id="wrapper">
            <!-- Header Container
      ================================================== -->
            <header
                id="header-container"
                class="fullwidth dashboard-header not-sticky"
                >
                <!-- Header -->
                <div id="header">
                    <div class="container">
                        <!-- Left Side Content -->
                        <div class="left-side">
                            <!-- Logo -->
                            <div id="logo">
                                <a href="index.html"><img src="images/logo.png" alt="" /></a>
                            </div>

                            <div class="clearfix"></div>
                            <!-- Main Navigation / End -->
                        </div>
                        <!-- Left Side Content / End -->

                        <!-- Right Side Content / End -->
                        <div class="right-side">
                            <!-- User Menu -->
                            <div class="header-widget">
                                <!-- Messages -->
                                <div class="header-notifications user-menu">
                                    <div class="header-notifications-trigger">
                                        <a href="#">
                                            <div class="user-avatar status-online">
                                                <img src="images/user-avatar.jpg" alt="" />
                                            </div>
                                        </a>
                                    </div>

                                    <!-- Dropdown -->
                                    <div class="header-notifications-dropdown">
                                        <!-- User Status -->
                                        <div class="user-status">
                                            <!-- User Name / Avatar -->
                                            <div class="user-details">
                                                <div class="user-avatar status-online">
                                                    <img src="images/user-avatar.jpg" alt="" />
                                                </div>
                                                <div class="user-name">
                                                    ${user.firstName} ${user.lastName}
                                                </div>
                                            </div>
                                        </div>

                                        <ul class="user-menu-small-nav">
                                            <li>
                                                <a href="home"
                                                   ><i class="icon-material-outline-dashboard"></i>
                                                    Boards</a
                                                >
                                            </li>
                                            <li>
                                                <a href="setting"
                                                   ><i class="icon-material-outline-settings"></i>
                                                    Settings</a
                                                >
                                            </li>
                                            <li>
                                                <a href="logout"
                                                   ><i
                                                        class="icon-material-outline-power-settings-new"
                                                        ></i>
                                                    Logout</a
                                                >
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- User Menu / End -->

                            <!-- Mobile Navigation Button -->
                            <span class="mmenu-trigger">
                                <button class="hamburger hamburger--collapse" type="button">
                                    <span class="hamburger-box">
                                        <span class="hamburger-inner"></span>
                                    </span>
                                </button>
                            </span>
                        </div>
                        <!-- Right Side Content / End -->
                    </div>
                </div>
                <!-- Header / End -->
            </header>
            <div class="clearfix"></div>
            <!-- Header Container / End -->

            <!-- Dashboard Container -->
            <div class="dashboard-container">
                <!-- Dashboard Sidebar
                ================================================== -->
                <div class="dashboard-sidebar">
                    <div class="dashboard-sidebar-inner" data-simplebar>
                        <div class="dashboard-nav-container">
                            <!-- Responsive Navigation Trigger -->
                            <a href="#" class="dashboard-responsive-nav-trigger">
                                <span class="hamburger hamburger--collapse">
                                    <span class="hamburger-box">
                                        <span class="hamburger-inner"></span>
                                    </span>
                                </span>
                                <span class="trigger-title">Dashboard Navigation</span>
                            </a>

                            <!-- Navigation -->
                            <div class="dashboard-nav">
                                <div class="dashboard-nav-inner">
                                    <ul data-submenu-title="Start">
                                        <li>
                                            <a href="home"
                                               ><i class="icon-material-outline-dashboard"></i>
                                                Broads</a
                                            >
                                        </li>
                                        <li>
                                            <a href="#"
                                               ><i class="icon-material-outline-star-border"></i>
                                                Bookmarks</a
                                            >
                                        </li>
                                    </ul>

                                    <ul data-submenu-title="Organize and Manage">
                                        <li>
                                            <a href="#"
                                               ><i class="icon-material-outline-assignment"></i>
                                                Dashboard</a
                                            >
                                            <ul>
                                                <li>
                                                    <a href="createBoard">Create Board</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>

                                    <ul data-submenu-title="Account">
                                        <li class="active-submenu">
                                            <a href="setting"
                                               ><i class="icon-material-outline-settings"></i>
                                                Settings</a
                                            >
                                        </li>
                                        <li>
                                            <a href="logout"
                                               ><i
                                                    class="icon-material-outline-power-settings-new"
                                                    ></i>
                                                Logout</a
                                            >
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- Navigation / End -->
                        </div>
                    </div>
                </div>
                <!-- Dashboard Sidebar / End -->

                <!-- Dashboard Content
                ================================================== -->
                <div class="dashboard-content-container" data-simplebar>
                    <div class="dashboard-content-inner" >

                        <!-- Dashboard Headline -->
                        <div class="dashboard-headline">
                            <h3>${user.firstName} ${user.lastName}</h3>

                            <!-- Breadcrumbs -->
                            <nav id="breadcrumbs" class="dark">
                                <ul>
                                    <li><a href="home">Home</a></li>
                                    <li><a href="setting">Settings</a></li>
                                </ul>
                            </nav>
                        </div>
                        <form action="setting" method="post">
                            <!-- Row -->
                            <div class="row">

                                <!-- Dashboard Box -->
                                <div class="col-xl-12">
                                    <div class="dashboard-box margin-top-0">

                                        <!-- Headline -->
                                        <div class="headline">
                                            <h3><i class="icon-feather-folder-plus"></i> User's Information Submission Form</h3>
                                        </div>

                                        <div class="content with-padding padding-bottom-10">
                                            <div class="row">

                                                <div class="col-xl-4">
                                                    <div class="submit-field">
                                                        <h5>First Name</h5>
                                                        <input type="text" name="fName" class="with-border" value="${user.firstName}" required>
                                                    </div>
                                                </div>

                                                <div class="col-xl-4">
                                                    <div class="submit-field">
                                                        <h5>Last Name</h5>
                                                        <input type="text" name="lName" class="with-border" value="${user.lastName}" required>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col-xl-4">
                                                    <div class="submit-field">
                                                        <h5>BirthDate</h5>
                                                        <input type="date" class="form-control" name="birthDate" id="birthDate" value="${user.birthDate}" required>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xl-12">
                                <button type="submit" class="button ripple-effect big margin-top-30"><i class="icon-feather-plus"></i> Update Account</button>
                            </div>
                        </form>
                        <!-- Snackbar -->
                        <div id="snackbar"></div>
                        <script>
                            window.onload = function () {
                                var status = "<%= request.getAttribute("status") != null ? request.getAttribute("status") : "" %>";
                                var message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";

                                if (status && message) {
                                    Snackbar.show({
                                        text: message,
                                        pos: 'bottom-center',
                                        showAction: false,
                                        duration: 3000,
                                        textColor: '#fff',
                                        backgroundColor: status === 'success' ? '#4caf50' : '#f44336'
                                    });
                                }
                            };
                        </script>


                    </div>
                    <!-- Row / End -->

                </div>
            </div>
            <!-- Dashboard Content / End -->
        </div>
        <!-- Dashboard Container / End -->
    </div>
    <!-- Wrapper / End -->

    <!-- Scripts
================================================== -->
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

    <!-- Snackbar // documentation: https://www.polonel.com/snackbar/ -->
    <script>
                            // Snackbar for user status switcher
                            $("#snackbar-user-status label").click(function () {
                                Snackbar.show({
                                    text: "Your status has been changed!",
                                    pos: "bottom-center",
                                    showAction: false,
                                    actionText: "Dismiss",
                                    duration: 3000,
                                    textColor: "#fff",
                                    backgroundColor: "#383838",
                                });
                            });
    </script>

    <!-- Chart.js // documentation: http://www.chartjs.org/docs/latest/ -->
    <script src="js/chart.min.js"></script>
    <script>
                            Chart.defaults.global.defaultFontFamily = "Nunito";
                            Chart.defaults.global.defaultFontColor = "#888";
                            Chart.defaults.global.defaultFontSize = "14";

                            var ctx = document.getElementById("chart").getContext("2d");

                            var chart = new Chart(ctx, {
                                type: "line",

                                // The data for our dataset
                                data: {
                                    labels: ["January", "February", "March", "April", "May", "June"],
                                    // Information about the dataset
                                    datasets: [
                                        {
                                            label: "Views",
                                            backgroundColor: "rgba(42,65,232,0.08)",
                                            borderColor: "#2a41e8",
                                            borderWidth: "3",
                                            data: [196, 132, 215, 362, 210, 252],
                                            pointRadius: 5,
                                            pointHoverRadius: 5,
                                            pointHitRadius: 10,
                                            pointBackgroundColor: "#fff",
                                            pointHoverBackgroundColor: "#fff",
                                            pointBorderWidth: "2",
                                        },
                                    ],
                                },

                                // Configuration options
                                options: {
                                    layout: {
                                        padding: 10,
                                    },

                                    legend: {display: false},
                                    title: {display: false},

                                    scales: {
                                        yAxes: [
                                            {
                                                scaleLabel: {
                                                    display: false,
                                                },
                                                gridLines: {
                                                    borderDash: [6, 10],
                                                    color: "#d8d8d8",
                                                    lineWidth: 1,
                                                },
                                            },
                                        ],
                                        xAxes: [
                                            {
                                                scaleLabel: {display: false},
                                                gridLines: {display: false},
                                            },
                                        ],
                                    },

                                    tooltips: {
                                        backgroundColor: "#333",
                                        titleFontSize: 13,
                                        titleFontColor: "#fff",
                                        bodyFontColor: "#fff",
                                        bodyFontSize: 13,
                                        displayColors: false,
                                        xPadding: 10,
                                        yPadding: 10,
                                        intersect: false,
                                    },
                                },
                            });
    </script>

    <!-- Google Autocomplete -->
    <script>
        function initAutocomplete() {
            var options = {
                types: ["(cities)"],
                // componentRestrictions: {country: "us"}
            };

            var input = document.getElementById("autocomplete-input");
            var autocomplete = new google.maps.places.Autocomplete(input, options);

            if ($(".submit-field")[0]) {
                setTimeout(function () {
                    $(".pac-container").prependTo("#autocomplete-container");
                }, 300);
            }
        }
    </script>

    <!-- Google API -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAaoOT9ioUE4SA8h-anaFyU4K63a7H-7bc&amp;libraries=places&amp;callback=initAutocomplete"></script>
</body>

<!-- Mirrored from www.vasterad.com/themes/hireo_21/dashboard-settings.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 29 Jun 2024 07:12:09 GMT -->
</html>


