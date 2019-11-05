<!doctype html>
<html lang="en">
    <head>
        <title>MOVIE TICKET BOOKING</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="row" style="margin-top: 10%;"></div>
            <div class="row">
                <div class="col-lg-3"></div>
                <div class="col-lg-6">
                    <div class="card">
                        <h5 class="card-header text-center">
                            LOGIN
                        </h5>
                        <div class="card-body">
                            <form action="Login" method="post">
                                <div class="form-group">
                                    <label for="inputUserName">Username</label>
                                    <input type="text" required class="form-control" id="inputUserName" placeholder="Enter username" name="username">

                                </div>
                                <div class="form-group">
                                    <label for="inputPassword">Password</label>
                                    <input type="password" required class="form-control" id="inputPassword" placeholder="Password" name="password">
                                </div>
                                <div class="form-group row">
                                    <span class="col-lg-3 col-md-3"></span>
                                    <button type="submit" class="col-lg-6 col-md-6 col-sm-12 btn btn-primary">Login</button>
                                    <span class="col-lg-3 col-md-3"></span>
                                </div>
                                <div class="form-group row">
                                    <span class="col-lg-3 col-md-3"></span>
                                    <button style="color:white" class="col-lg-6 col-md-6 col-sm-12 btn btn-primary"><a href="register.jsp" class="col-lg-6 col-md-6 col-sm-12 btn btn-primary">Register</a></button>
                                    <span class="col-lg-3 col-md-3"></span>
                                </div>

                            </form>

                        </div>
                    </div>
                </div>
                <div class="col-lg-3"></div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
