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
                           ADMIN LOGIN
                        </h5>
                        <div class="card-body">
                            <form action="AdminLogin" method="post">
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
                                    <input type="submit" class="col-lg-6 col-md-6 col-sm-12 btn btn-primary" value="Login">
                                    <span class="col-lg-3 col-md-3"></span>
                                </div>
                                <div class="form-group row">
                                    <span class="col-lg-3 col-md-3"></span>
                                    <a href="register.jsp" class="col-lg-6 col-md-6 col-sm-12 btn btn-primary">Register</a>
                                    <span class="col-lg-3 col-md-3"></span>
                                </div>
                                <center><a data-toggle="modal" data-target="#myModal" href="#" >Forgot Password? Click here</a></center>
                                   

                            </form>

                        </div>
                    </div>
                </div>
                <div class="col-lg-3"></div>
            </div>
        </div>

        
  <div class="container">
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Forgot Password</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
          <div class="modal-body">
            
            <form action="resetPwd" method="get">
                <input type="text" name="email" class="form-control col-lg-12" placeholder="Enter your email">
        &nbsp
        <input type="submit" class="btn btn-primary" value="Reset Password">
    </form>



          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
