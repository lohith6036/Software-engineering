package com.onlinemovie.booking;

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
         
String email=(String) request.getSession().getAttribute("email");
String username=(String) request.getSession().getAttribute("username");
System.out.println(clss);
//        System.out.println("movie: " + movieId);
//        System.out.println("genere: " + screen+" "+slot);
 
//<html><center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>
//        Tickets Booked</h2><hr><br/><h3>Total paid $30</h3><br><hr><p>order: 2 x gold tickets<br>
//                Movie: MS Dhoni- (190 minutes)</p><h2>Screen: 2</h2><br><h2>Slot: 1</h2></div></center></html>

        // do some processing here...
        try
        {
          // create a mysql database connection
          
          
          Class.forName(SqlConfig.jdbcDriver);
          Connection conn = DriverManager.getConnection(SqlConfig.jdbcURL, SqlConfig.jdbcUSERNAME,SqlConfig.jdbcPASSWORD);
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

        
         cost=CalculateCost(clss);
    
          
          int number =Integer.parseInt(num);
          cost*=number;
          System.out.println(cost);
          
String message="This is a ticket booking confirmation mail.\n";
message+="Movie : "+ name+"- ("+duration+" minutes) \n";
message+="Screen :"+screen+"\n";
message+="Slot : "+slot+"\n";
message+="Order :"+ number+" x " +clss+" tickets\n";
message+="Total : $"+cost+"\n\n";
          switch(screen){
          	case 1: if(slot==1)
          			{
          			showS1T1 m = new showS1T1(name, genere, duration, director);
          			m.booked=booked;
          			if(!m.bookNew(number)){
                                    if(number<0)
                                        htmlRespone += "<center><h2>Invalid value</h2><br/></center>"; 
                                    else
          				htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
          			}
          			else{
          				int ticket_id=createTicket(conn,username,showId,num,clss,cost);
                                        htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Paid $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2><br><h2>Ticket ID: "+ticket_id+"</h2></div></center>"; 
                                        sendMail(email, message,ticket_id);
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
                                            if(number<0)
                                                htmlRespone += "<center><h2>Invalid value</h2><br/></center>"; 
                                            else
                                                htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
              			}
              			else{
              			int ticket_id=createTicket(conn,username,showId,num,clss,cost);
                                htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3> Paid $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2><br><h2>Ticket ID: "+ticket_id+"</h2></div></center>"; 
      	          		sendMail(email, message,ticket_id);
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
              				    if(number<0)
                                                htmlRespone += "<center><h2>Invalid value</h2><br/></center>"; 
                                            else
                                                htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
              			}
              			else{
              			int ticket_id=createTicket(conn,username,showId,num,clss,cost);
                                            htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Paid $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2><br><h2>Ticket ID: "+ticket_id+"</h2></div></center>"; 
                                    sendMail(email, message,ticket_id);
  	          			                  
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
                                             if(number<0)
                                                htmlRespone += "<center><h2>Invalid value</h2><br/></center>"; 
                                            else
                                                htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
      			}
      			else{
      					int ticket_id=createTicket(conn,username,showId,num,clss,cost);
  	          		htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Paid $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2><br><h2>Ticket ID: "+ticket_id+"</h2></div></center>"; 
	          			sendMail(email, message,ticket_id);
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
                                                    if(number<0)
                                                htmlRespone += "<center><h2>Invalid value</h2><br/></center>"; 
                                                    else
                                                htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
  	          			}
  	          			else{
  	          			int ticket_id=createTicket(conn,username,showId,num,clss,cost);
  	          				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Paid $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2><br><h2>Ticket ID: "+ticket_id+"</h2></div></center>"; 
  	  	          		sendMail(email, message,ticket_id);
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
                                            if(number<0)
                                                htmlRespone += "<center><h2>Invalid value</h2><br/></center>"; 
                                            else
                                                htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
  	          			}
  	          			else{
  	          			int ticket_id=createTicket(conn,username,showId,num,clss,cost);
  	          				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Paid $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2><br><h2>Ticket ID: "+ticket_id+"</h2></div></center>"; 
  	  	          		sendMail(email, message,ticket_id);
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
                                            if(number<0)
                                                htmlRespone += "<center><h2>Invalid value</h2><br/></center>"; 
                                            else
                                                htmlRespone += "<center><h2>Show Full</h2><br/></center>"; 
                        }
      			else{
      					int ticket_id=createTicket(conn,username,showId,num,clss,cost);
  	          		htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Paid $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2><br><h2>Ticket ID: "+ticket_id+"</h2></div></center>"; 
	          			sendMail(email, message,ticket_id);
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
                                                    if(number<0)
                                                        htmlRespone += "<center><h2>Invalid value</h2><br/></center>"; 
                                                    else
                                                        htmlRespone += "<center><h2>Show Full</h2><br/></center>";  
  	          			}
  	          			else{
  	          			int ticket_id=createTicket(conn,username,showId,num,clss,cost);
  	          				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Paid $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2><br><h2>Ticket ID: "+ticket_id+"</h2></div></center>"; 
  	  	          		sendMail(email, message,ticket_id);
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
                                                    if(number<0)
                                                        htmlRespone += "<center><h2>Invalid value</h2><br/></center>"; 
                                                    else
                                                        htmlRespone += "<center><h2>Show Full</h2><br/></center>";  
  	          			}
  	          			else{
  	          		int ticket_id=createTicket(conn,username,showId,num,clss,cost);
  	          				htmlRespone += "<center><div style='border:1px solid black; border-radius:3px; width:400px;'><h2>Tickets Booked</h2><hr><br/><h3>Paid $" + cost + "</h3><br><hr><p>order: "+ number+" x " +clss+" tickets<br>Movie: "+name+"- ("+duration+" minutes)</p><h2>Screen: "+screen+"</h2><br><h2>Slot: "+slot+"</h2><br><h2>Ticket ID: "+ticket_id+"</h2></div></center>"; 
  	          		sendMail(email, message,ticket_id);
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
           htmlRespone="<center><br><br><a href='home.jsp'><button>Back</button></a></center>";
          writer.println(htmlRespone);
          //response.sendRedirect("options.jsp");
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }       
    }
        public int CalculateCost(String TicketClass)
        {
          if(TicketClass.equals("silver")){
        	  cost=10;
          }
          else if(TicketClass.equals("gold")){
        	  cost=15;
          }
          else if(TicketClass.equals("platinum")){
        	  cost=20;
          }
          return cost;
        }
        
        public void sendMail(String email, String message, int ticketID){
            System.out.println("sending mail to"+email);
            message+="Ticket ID : "+ticketID;
           boolean response= Mailer.sendMail(email, message, "ONLINE MOVIE BOOKING : Movie Booking Made");
            System.out.println("mail sending response "+response);
        }

    private int createTicket(Connection conn,String username, String showId, String num, String clss, int cost) {
        String query="INSERT INTO tickets(username, show_id, qty, class, amount, date_booked) VALUES (?,?,?,?,?,?) ";
        int TicketID=-1;
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, showId);
            preparedStmt.setString(3, num);
            preparedStmt.setString(4, clss);
            preparedStmt.setInt(5, cost);
            preparedStmt.setString(6, new java.util.Date().toString());
            
            preparedStmt.execute();
            
            ResultSet rs=preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                
				TicketID=rs.getInt(1);
                                System.out.println("Ticket ID " + TicketID);
                                
			}
                                
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TicketID;
    }
}