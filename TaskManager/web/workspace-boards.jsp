<%-- 
    Document   : workspace-boards
    Created on : Jun 30, 2024, 5:08:56 PM
    Author     : namdng09
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/colors/blue.css" />
    </head>
    <body class="gray">
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
                                <a href="home"><img src="images/logo.png" alt="" /></a>
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
                                                <img src="images/user-avatar-small-01.jpg" alt="" />
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
                                                    <img src="images/user-avatar-small-01.jpg" alt="" />
                                                </div>
                                                <div class="user-name">
                                                    Tom Smith <span>Freelancer</span>
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
                                                <a href="#"
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
                                        <li class="active">
                                            <a href="home"
                                               ><i class="icon-material-outline-dashboard"></i>
                                                Boards</a
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
                                               ><i class="icon-material-outline-business-center"></i>
                                                User</a
                                            >
                                            <ul>
                                                <li>
                                                    <a href="#"
                                                       >Manage User <span class="nav-tag">3</span></a
                                                    >
                                                </li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#"
                                               ><i class="icon-material-outline-assignment"></i>
                                                Boards</a
                                            >
                                            <ul>
                                                <li>
                                                    <a href="#"
                                                       >Manage Boards <span class="nav-tag">2</span></a
                                                    >
                                                </li>
                                                <li>
                                                    <a href="createBoard">Create Broad</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>

                                    <ul data-submenu-title="Account">
                                        <li>
                                            <a href="#"
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
                            <h3>Boards</h3>

                            <!-- Breadcrumbs -->
                            <nav id="breadcrumbs" class="dark">
                                <ul>
                                    <li><a href="home">Home</a></li>
                                    <li><a href="home">Boards</a></li>
                                </ul>
                            </nav>
                        </div>

                        <!-- Row -->
                        <div class="row">

                            <!-- Dashboard Box -->
                            <div class="col-xl-12">
                                <div class="dashboard-box margin-top-0">

                                    <!-- Headline -->
                                    <div class="headline">
                                        <h3><i class="icon-material-outline-dashboard"></i> YOUR WORKSPACES</h3>
                                    </div>

                                    <div class="content">
                                        <ul class="dashboard-box-list">
                                            <c:forEach items="${requestScope.boards}" var="board">
                                                <li>
                                                    <!-- Job Listing -->
                                                    <div class="job-listing">

                                                        <!-- Job Listing Details -->
                                                        <div class="job-listing-details">

                                                            <!-- Logo -->
                                                            <a href="#" class="job-listing-company-logo">
                                                                <img src="${board.image}" alt="">
                                                            </a>

                                                            <!-- Details -->
                                                            <div class="job-listing-description">
                                                                <h3 class="job-listing-title"><a href="#">${board.boardName}</a></h3>

                                                                <!-- Job Listing Footer -->
                                                                <div class="job-listing-footer">
                                                                    <ul>
                                                                        <li><i class="icon-material-outline-business-center"></i> ${board.owner}</li>
                                                                        <li><i class="icon-material-outline-access-time"></i> ${board.createDate}</li>
                                                                        <li>
                                                                            <i class="<c:choose>
                                                                                   <c:when test="${board.publiced}">
                                                                                       icon-material-outline-lock-open
                                                                                   </c:when>
                                                                                   <c:otherwise>
                                                                                       icon-material-outline-lock
                                                                                   </c:otherwise>
                                                                               </c:choose>">
                                                                            </i>
                                                                            <c:choose>
                                                                                <c:when test="${board.publiced}">
                                                                                    Public
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    Private
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </li>
                                                                        <li>
                                                                            <i class="<c:choose>
                                                                                   <c:when test="${board.completed}">
                                                                                       icon-feather-check-square
                                                                                   </c:when>
                                                                                   <c:otherwise>
                                                                                       icon-material-outline-highlight-off
                                                                                   </c:otherwise>
                                                                               </c:choose>">
                                                                            </i>
                                                                            <c:choose>
                                                                                <c:when test="${board.completed}">
                                                                                    DONE
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    NOT DONE
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Buttons -->
                                                    <div class="buttons-to-right">
                                                        <a href="#" class="button red ripple-effect ico" title="Remove" data-tippy-placement="left"><i class="icon-feather-trash-2"></i></a>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- Row / End -->

                    </div>
                </div>
                <!-- Dashboard Content / End -->

            </div>
            <!-- Dashboard Container / End -->
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
</html>
