
<%
    
    
if(session.getAttribute("logged_in")==null){
    response.sendRedirect("index.jsp");
    return;
}

String showId="",num="",clss="";
    if(request.getParameter("id")!=null && request.getParameter("num")!=null && request.getParameter("class")!=null){
        
        showId = request.getParameter("id");
         num = request.getParameter("num");
         clss = request.getParameter("class");
    }else{
        response.sendRedirect("home.jsp");
        return;
    }
    
         

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>Online Ticket Booking System</title>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<nav class="navbar navbar-inverse" style="margin-bottom:0px;">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="home.jsp" style="font-size: 40px;"><b>Online Ticket Booking System</b></a>
    </div>
      
     
    <ul class="nav navbar-nav navbar-right">
       <!--<li><a href="#"><span class="glyphicon glyphicon-user"></span> Admin</a></li>--> 
      <li><a href="tickets.jsp"><span class="glyphicon glyphicon-chevron-left"></span><b> Booked Tickets</b></a></li>
      <!--<li><a data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-user"></span><b> Admin</b></a></li>-->
      <li><a href="logout.jsp" ><span class="glyphicon "></span><b> Logout</b></a></li>
    </ul>
  </div>
</nav>


        
        <div class="container">
            <div class="row" style="margin-top: 10%;"></div>
            <div class="row">
                <div class="col-lg-3"></div>
                <div class="col-lg-6">
                    <div class="card">
                        <h5 class="card-header text-center">
                            Payment Page
                        </h5>
                        <div class="card-body">
                            <form action="book" method="post">
                                
                                <div class="form-group">
                                    <label for="inputUserName">Show ID : <%=showId %> </label>
                                    <input type="hidden" required value="<%=showId %>" name="id">
                                </div>
                                
                                 <div class="form-group">
                                    <label >Number of Tickets Booked: <%=num %> </label>
                                    <input type="hidden" required value="<%=num %>" name="num">
                                </div>
                                
                                
                                 <div class="form-group">
                                    <label>Class : <%=clss %> </label>
                                    <input type="hidden" required value="<%=clss %>" name="class">
                                </div>
                                
                                <div class="form-group">
                                    <label for="inputUserName">Card Number</label>
                                    <input type="text" required class="form-control" id="inputUserName" placeholder="Enter card number" name="cardnumber">

                                </div>
                                <div class="form-group">
                                    <label for="inputExpiry">Expiry Date</label>
                                    <input type="text" required class="form-control" id="inputExpiry" placeholder="Expiry Date ( MM/YY)" name="expiry">
                                </div>
                                <div class="form-group">
                                    <label for="inputCvv">CVV</label>
                                    <input type="text" required class="form-control" id="inputCvv" placeholder="CVV" name="cvv">
                                </div>
                                <div class="form-group row">
                                    <span class="col-lg-3 col-md-3"></span>
                                    <input type="submit" class="col-lg-6 col-md-6 col-sm-12 btn btn-primary" value="Make Payment">
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
    
<style>
      /* NOTE: The styles were added inline because Prefixfree needs access to your styles and they must be inlined if they are on local disk! */
body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form, fieldset, input, textarea, p, blockquote, th, td { 
  padding:0;
  margin:0;}

body,
input,
textarea,
select {
  font-family: 'Open Sans', sans-serif;
  font-size: 16px;
  color: #4c4c4c;
}

h1 {
  font-size: 32px;
  font-weight: 300;
  color: #4c4c4c;
  text-align: center;
  padding-top: 10px;
  margin-bottom: 10px;
}

.testbox3 {
  /*margin: 20px auto;*/
  width: 300px; 
  /*height: 330px;*/ 
  -webkit-border-radius: 8px/7px; 
  -moz-border-radius: 8px/7px; 
  border-radius: 8px/7px; 
  background-color: #ebebeb; 
  -webkit-box-shadow: 1px 2px 5px rgba(0,0,0,.31); 
  -moz-box-shadow: 1px 2px 5px rgba(0,0,0,.31); 
  box-shadow: 1px 2px 5px rgba(0,0,0,.31); 
  border: solid 1px #cbc9c9;
}
.visuallyhidden:not(:focus):not(:active) {
  position: absolute;

  width: 1px;
  height: 1px;
  margin: -1px;
  border: 0;
  padding: 0;

  white-space: nowrap;

  clip-path: inset(100%);
  clip: rect(0 0 0 0);
  overflow: hidden;
}
.testbox3{
  height: 185px; 
}
form{
  margin: 0 30px;
}

input[type=text],input[type=password]{
  width: 200px; 
  height: 39px; 
  -webkit-border-radius: 0px 4px 4px 0px/5px 5px 4px 4px; 
  -moz-border-radius: 0px 4px 4px 0px/0px 0px 4px 4px; 
  border-radius: 0px 4px 4px 0px/5px 5px 4px 4px; 
  background-color: #fff; 
  -webkit-box-shadow: 1px 2px 5px rgba(0,0,0,.09); 
  -moz-box-shadow: 1px 2px 5px rgba(0,0,0,.09); 
  box-shadow: 1px 2px 5px rgba(0,0,0,.09); 
  border: solid 1px #cbc9c9;
  margin-left: -5px;
  margin-top: 13px; 
  padding-left: 10px;
}

input[type=password]{
  margin-bottom: 25px;
}

</style>
</html>
