<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Registration Form</title>
    </head>
    <body>

        <div class="container">
            <div class="row" style="margin-top: 10%;"></div>
            <div class="row">
                <div class="col-lg-3"></div>
                <div class="col-lg-6 col-md-12 col-sm-12">
                    <div class="card">
                        <h5 class="card-header text-center">
                            REGISTER
                        </h5>
                        <div class="card-body">
                            <form action="register" method="post">
                                <div class="form-group row">
                                    <label for="inputFirstName" class="col-lg-3 col-md-2 col-sm-2">First Name</label>
                                    <div class="col-lg-9 col-md-10 col-sm-10">
                                        <input type="text" required name="first_name" class="form-control" id="inputFirstName" placeholder="Enter First Name">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputLastName" class="col-lg-3 col-md-2 col-sm-2">Last Name</label>
                                    <div class="col-lg-9 col-md-10 col-sm-10">
                                        <input type="text" required name="last_name"  class="form-control" id="inputLastName" placeholder="Enter Last Name">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputEmail" class="col-lg-3 col-md-2 col-sm-2">Email</label>
                                    <div class="col-lg-9 col-md-10 col-sm-10">
                                        <input type="text" required name="email" class="form-control" id="inputEmail" placeholder="Enter Email">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputUserName" class="col-lg-3 col-md-2 col-sm-2">Username</label>
                                    <div class="col-lg-9 col-md-10 col-sm-10">
                                        <input type="text" required name="username" class="form-control" id="inputUserName" placeholder="Enter Username">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-lg-3 col-md-2 col-sm-2">Password</label>
                                    <div class="col-lg-9 col-md-10 col-sm-10">
                                        <input type="password" required name="password" class="form-control" id="inputPassword" placeholder="Enter Password">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputAddress" class="col-lg-3 col-md-2 col-sm-2">Address</label>
                                    <div class="col-lg-9 col-md-10 col-sm-10">
                                        <input type="text" name="address" class="form-control" id="inputAddress" placeholder="Enter Address">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputphone" class="col-lg-3 col-md-2 col-sm-2">Phone</label>
                                    <div class="col-lg-9 col-md-10 col-sm-10">
                                        <input type="text" required name="phone" class="form-control"  maxlength="10" id="inputphone" placeholder="Enter Phone Number">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <span class="col-lg-3 col-md-3"></span>
                                    <button type="submit" class="col-lg-6 col-md-6 col-sm-12 btn btn-primary">Register</button>
                                    <span class="col-lg-3 col-md-3"></span>
                                    
                                </div>
                                <center><a href="index.jsp" >Already Registered? Click here to Login</a></center>                            </form>

                        </div>
                    </div>
                </div>
                <div class="col-lg-3"></div>
            </div>
        </div>
        <!--        <form action="guru_register" method="post">
                    <table style="with: 50%">
                        <tr>
                            <td>First Name</td>
                            <td><input type="text" name="first_name" /></td>
                        </tr>
                        <tr>
                            <td>Last Name</td>
                            <td><input type="text" name="last_name" /></td>
                        </tr>
                        <tr>
                            <td>UserName</td>
                            <td><input type="text" name="username" /></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name="password" /></td>
                        </tr>
                        <tr>
                            <td>Address</td>
                            <td><input type="text" name="address" /></td>
                        </tr>
                        <tr>
                            <td>Contact No</td>
                            <td><input type="text" name="contact" /></td>
                        </tr></table>
                    <input type="submit" value="Submit" /></form>-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>