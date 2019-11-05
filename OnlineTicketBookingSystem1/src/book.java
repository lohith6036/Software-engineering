import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/book")
public class book extends HttpServlet {
	static int cost=0;
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         
        // read form fields
        String showId = request.getParameter("id");
        String num = request.getParameter("num");
        String clss = request.getParameter("class");
         
        System.out.println(clss);
//        System.out.println("movie: " + movieId);
//        System.out.println("genere: " + screen+" "+slot);
 
        // do some processing here...
        try
        {
          // create a mysql database connection
          String myDriver = "com.mysql.jdbc.Driver";
          String myUrl = "jdbc:mysql://localhost:3306/UserDB";
          Class.forName(myDriver);
          Connection conn = DriverManager.getConnection(myUrl, "root", "system");
          // the mysql insert statement
          Statement stmt = null;
          stmt = conn.createStatement();
          String query = " select MId, screen, slot, booked from shows where id="+showId;
          System.out.println(showId);
          ResultSet rs = null, rs2=null;
          int screen=0, slot=0, booked=0, MId=0;
          rs = stmt.executeQuery(query);
          PrintWriter writer = response.getWriter();
          String htmlRespone = "<html>";    
        
          
          if(rs==null){
        	  htmlRespone += "<h2>Wrong Entry</h2><br/>"; 
          }
          while(rs.next()){
        	  screen = rs.getInt("screen");
        	  slot = rs.getInt("slot");
        	  booked = rs.getInt("booked");
        	  MId = rs.getInt("MId");
          }
          
          query = " select * from moviedatabase where id="+MId;
          rs = stmt.executeQuery(query);
          String name=null,genere=null,director=null;
          int duration=0;
          while(rs.next()){
        	  name = rs.getString("title");
        	  genere = rs.getString("genere");
        	  duration = rs.getInt("duration");
        	  director = rs.getString("director");
        	  
          }
         String htmlResponse2;
        // return response
          
          if(clss.equals("silver")){
        	  cost=10;
          }
          else if(clss.equals("gold")){
        	  cost=15;
          }
          else if(clss.equals("platinum")){
        	  cost=20;
          }
          
          
          int number =Integer.parseInt(num);
          cost*=number;
          System.out.println(cost);
          screen=1;
          switch(screen){
          	case 1: if(slot==1)
          			{
          			showS1T1 m = new showS1T1(name, genere, duration, director);
          			m.booked=booked;
          			if(!m.bookNew(number)){
          				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
          			}
          			else{
          				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Please pay $ " + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2></div></center>"; 
  	          			query = " update shows set booked="+m.booked+" where id="+showId;
  	          		System.out.println(query);
  	          		PreparedStatement preparedStmt = conn.prepareStatement(query);
  	          	preparedStmt.execute();
          			}
          			}
          			else if(slot==2){
          				showS1T2 m=new showS1T2(name, genere, duration, director);
          				m.booked=booked;
          				if(!m.bookNew(number)){
              				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
              			}
              			else{
              				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Please pay $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2></div></center>"; 
      	          		query = " update shows set booked="+m.booked+" where id="+showId;
      	          	System.out.println(query);
      	          		PreparedStatement preparedStmt = conn.prepareStatement(query);
      	  	          	preparedStmt.execute();
              			}
          			}
          			else{
          				showS1T3 m=new showS1T3(name, genere, duration, director);
          				m.booked=booked;
          				if(!m.bookNew(number)){
              				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
              			}
              			else{
              				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Please pay $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2></div></center>"; 
      	          		query = " update shows set booked="+m.booked+" where id="+showId;
      	          	System.out.println(query);
      	          	PreparedStatement preparedStmt = conn.prepareStatement(query);
      	          	preparedStmt.execute();
              			}
          			}
          			break;
          			
          	case 2: if(slot==1){
          		showS2T1 m=new showS2T1(name, genere, duration, director);
          		m.booked=booked;
          		if(!m.bookNew(number)){
      				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
      			}
      			else{
      				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Please pay $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2></div></center>"; 
	          			query = " update shows set booked="+m.booked+" where id="+showId;
	          			System.out.println(query);
	          			PreparedStatement preparedStmt = conn.prepareStatement(query);
	      	          	preparedStmt.execute();
      			}
          		}
  					else if(slot==2){
  						showS2T2 m=new showS2T2(name, genere, duration, director);
  						m.booked=booked;
  						if(!m.bookNew(number)){
  	          				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
  	          			}
  	          			else{
  	          				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Please pay $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2></div></center>"; 
  	  	          		query = " update shows set booked="+m.booked+" where id="+showId;
  	  	          	System.out.println(query);
  	  	          	PreparedStatement preparedStmt = conn.prepareStatement(query);
  	  	          	preparedStmt.execute();
  	          			}
  					}
  					else{
  						showS2T3 m=new showS2T3(name, genere, duration, director);
  						m.booked=booked;
  						if(!m.bookNew(number)){
  	          				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
  	          			}
  	          			else{
  	          				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Please pay $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2></div></center>"; 
  	  	          		query = " update shows set booked="+m.booked+" where id="+showId;
  	  	          	System.out.println(query);
  	  	          	PreparedStatement preparedStmt = conn.prepareStatement(query);
  	  	          	preparedStmt.execute();
  	          			}
  					}
  					break;
  					
          	case 3: if(slot==1){
          		showS3T1 m=new showS3T1(name, genere, duration, director);
          		m.booked=booked;
          		if(!m.bookNew(number)){
      				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
      			}
      			else{
      				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Please pay $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2></div></center>"; 
	          			query = " update shows set booked="+m.booked+" where id="+showId;
	          			System.out.println(query);
	          			PreparedStatement preparedStmt = conn.prepareStatement(query);
	      	          	preparedStmt.execute();
      			}
          	}
  					else if(slot==2){
  						showS3T2 m=new showS3T2(name, genere, duration, director);
  						m.booked=booked;
  						if(!m.bookNew(number)){
  	          				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
  	          			}
  	          			else{
  	          				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Please pay $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2></div></center>"; 
  	  	          		query = " update shows set booked="+m.booked+" where id="+showId;
  	  	          	System.out.println(query);
  	  	          	PreparedStatement preparedStmt = conn.prepareStatement(query);
  	  	          	preparedStmt.execute();
  	          			}
  					}
  					else{
  						showS3T3 m=new showS3T3(name, genere, duration, director);
  						m.booked=booked;
  						if(!m.bookNew(number)){
  	          				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
  	          			}
  	          			else{
  	          				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Please pay $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2></div></center>"; 
  	          		query = " update shows set booked="+m.booked+" where id="+showId;
  	          		System.out.println(query);
  	          	PreparedStatement preparedStmt = conn.prepareStatement(query);
  	          	preparedStmt.execute();
  	          			}
  					}
  					break;
  					
  			default: htmlRespone += "<center><h2>Wrong Input</h2><br/></center>"; 
 
          }
          
          conn.close();
          htmlRespone += "</html>";
          writer.println(htmlRespone);
         htmlRespone="<br> <centre><br><br><a href='home.jsp'><button>Back</button></a></center>";
          writer.println(htmlRespone);
          //response.sendRedirect("options.jsp");
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }       
    }
}