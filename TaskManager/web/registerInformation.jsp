<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from brandio.io/envato/iofrm/html/parked-domain1.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 19 Jun 2024 09:09:32 GMT -->
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>iofrm</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/fontawesome-all.min.css">
        <link rel="stylesheet" type="text/css" href="css/iofrm-style.css">
        <link rel="stylesheet" type="text/css" href="css/iofrm-theme30.css">
    </head>
    <body>
        <div class="form-body on-top-mobile">
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
                    <div class="info-holder simple-info">
                        <div class="mb-5">
                            <div class="type-animation">
                                <h2 class="mb-2 animated">iofrmlabs.com</h2>
                            </div>
                            <h3 class="mb-4"><i class="font-weight-light">Domain for sale!</i></h3>
                        </div>
                        <div><p>Fill the form, make your offer<br>And get the domain name before it’s gone.</p></div>
                    </div>
                    <div class="bottom-view"><p><small class="font-weight-light">Powered by iofrm domains - 2023</small></p></div>
                </div>
                <div class="form-holder">
                    <div class="form-content">
                        <div class="form-items">
                            <h3>Register your interest</h3>
                            <p class="text-black">Use the contact form below to make your<br>best offer. LoremIpsum or any kind of crap text is not  allowed.</p>
                            <form class="container-custom-padding" action="register" method="post">
                                <input type="hidden" name="stage" value="additionalInfo">
                                <div class="row">
                                    <div class="col-12 col-sm-6">
                                        <input type="text" class="form-control" name="firstName" placeholder="First name" required>
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <input type="text" class="form-control" name="lastName" placeholder="Last name" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 col-sm-6">
                                        <input type="date" class="form-control" name="birthDate" id="birthDate" placeholder="Birth Date" required>
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <input type="text" class="form-control" name="phone" placeholder="Phone Number" required>
                                    </div>
                                </div>
                                <div class="row top-padding">
                                    <div class="col-12 col-sm-6">
                                        <input type="checkbox" id="chk1" required><label for="chk1">I agree on <a href="#">terms & conditions</a> of iofrm</label>
                                    </div>
                                    <div class="col-12 col-sm-6">
                                        <div class="form-button text-right">
                                            <button id="submit" type="submit" class="ibtn less-padding">Submit Application</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var birthDateInput = document.getElementById('birthDate');

                var today = new Date();

                var yesterday = new Date(today);
                yesterday.setDate(today.getDate() - 1);

                var yyyy = yesterday.getFullYear();
                var mm = String(yesterday.getMonth() + 1).padStart(2, '0'); 
                var dd = String(yesterday.getDate()).padStart(2, '0');
                var maxDate = yyyy + '-' + mm + '-' + dd;

                birthDateInput.max = maxDate;

                birthDateInput.value = '1999-01-01';
            });
        </script>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>

    <!-- Mirrored from brandio.io/envato/iofrm/html/parked-domain1.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 19 Jun 2024 09:09:32 GMT -->
</html>
