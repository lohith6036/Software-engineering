
<%@page import="com.onlinemovie.booking.SqlConfig"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

if(session.getAttribute("logged_in")==null){
    response.sendRedirect("index.jsp");
    return;
}
String zipSearch="";
if(request.getParameter("zip")!=null){
    zipSearch="Where zip_code = ?";
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
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Admin Login</h4>
          </div>
          <div class="modal-body">
            
            <form action="AdminLogin" method="POST">
        <input type="text" name="username" placeholder="Username">
        &nbsp
        <input type="password" name="password" placeholder="Password">
        <input type="submit" class="btn btn-primary">
    </form>



          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
<center>

<div id="carousel-example-generic" class="carousel slide container-fluid" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="images/mo3.jpg" alt="..." style="width:800px; height:500px;">
      <div class="carousel-caption">
        <b>Movie 1</b>
      </div>
    </div>
    <div class="item">
      <img src="images/mo2.jpg" alt="..." style="width:800px;height:500px;">
      <div class="carousel-caption">
       <b>Movie 2</b>
      </div>
    </div>
    <div class="item">
      <img src="images/mo1.jpg" alt="..." style="width:800px;height:500px;">
      <div class="carousel-caption">
      <b>Movie 3</b>
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
<br>
<br>
<br>
<form method="post">
    <h4>Search Movies by zip code</h4>
    <input type="text" name="zip"  placeholder="Enter zip code" />
    <input type="submit" value="Search" />
</form>
<form action="payment.jsp" method="post">
<div>

  <div class="container" style="float:left; margin-left:150px;">
    <table class="table table-hover">
        <thead>
            <tr>
            <th>Select</th>
                <th>Movie</th>
                <th>Show Slot</th>
                <th>Duration</th>
                <th>Screen No.</th>
                <th>Available</th>
                <th>Zip Code</th>
                <th>Venue</th>
                <th>Showing Date</th>
            </tr>
        </thead>
        <tbody>


            <%
                Class.forName(SqlConfig.jdbcDriver);
                Connection conn = null;
                conn = DriverManager.getConnection(SqlConfig.jdbcURL, SqlConfig.jdbcUSERNAME,SqlConfig.jdbcPASSWORD);
                Statement stmt = null;
                stmt = conn.createStatement();
                String query = "select * from shows JOIN moviedatabase ON (shows.MId=moviedatabase.id) "+zipSearch;
                ResultSet rs = null;
                if(!zipSearch.isEmpty()){
                    PreparedStatement pstmt=conn.prepareStatement(query);
                    pstmt.setString(1, request.getParameter("zip"));
                    rs=pstmt.executeQuery();
                }else{
                
                rs = stmt.executeQuery(query);
                }
                boolean movieEmpty=true;
                while(rs.next()){
                    movieEmpty=false;
            %>
            
                <%
                    
                    int id = rs.getInt("id");
                  int Mid = rs.getInt("Mid");
                  int screen = rs.getInt("screen");
                  int slot = rs.getInt("slot");
                  int avail = rs.getInt("booked");
                 
                      String moviename = rs.getString("title");
                      String zipcode = rs.getString("zip_code");
                      String venue = rs.getString("venue");
                      String date = rs.getString("date_");
                     int  duration= rs.getInt("duration");
                    
                    
                    int start=0, end=0;
                    if(slot==1){
                      start=9;
                      end=1;
                    }
                    else if(slot==2){
                      start=1;
                      end=5;
                    }
                    else if(slot==3){
                      start=5;
                      end=9;
                    }
                    if(screen==1){
                    	avail=100-avail;
                    }
                    if(screen==2){
                    	avail=150-avail;
                    }
                    if(screen==3){
                    	avail=200-avail;
                    }
                
                %>
                
                <tr>
                <td><input type="radio" value=<%=id %> name="id"></td>
                <td><%=moviename %></td>
                <td><%=start %> to <%=end %></td>
                <td><%=duration %> minutes</td>
                <td><%=screen %></td>
                <td><%=avail %></td>
                <td><%=zipcode %></td>
                <td><%=venue %></td>
                <td><%=date %></td>
            </tr>               

            <%      
                }

            %>

        </tbody>
        <%
            if(movieEmpty){ %>
        <h1>No Movie show available</h1>
        <% }%>
    </table>
            
            
  </div>
</div>

<div class="testbox3"  style="float:right; margin-right:400px;">
        <h1>Book Movie</h1>
       

          <input type="number" name="num" placeholder="No. Of Seats">
          <br />
          <!--Disabling categories of tickets for now-->
          <select name="class" value="silver" >
              <option value="silver" selected>Silver - $10</option>
              <option value="gold">Gold - $15</option>
              <option value="platinum">Platinum - $20</option>
        </select>
          <br /><br />
          <input class="btn btn-primary" type="submit" value="BOOK NOW">
      
</div>
</form>
</center>


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