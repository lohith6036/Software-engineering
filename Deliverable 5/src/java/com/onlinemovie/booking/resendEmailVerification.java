package com.onlinemovie.booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class guru_register
 */
@WebServlet("/resendEmailVerification")
public class resendEmailVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
        @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		  response.setContentType("text/html");  
                PrintWriter out = response.getWriter();
          try
        {
                    
                    
                    Class.forName(SqlConfig.jdbcDriver);
                    Connection conn = DriverManager.getConnection(SqlConfig.jdbcURL, SqlConfig.jdbcUSERNAME,SqlConfig.jdbcPASSWORD);
                    String query="Select * from Users where username=?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, username);
                   
                    ResultSet rs=preparedStmt.executeQuery();
                    
                    if(rs.next()){
                        //check if user has been activated previously
                        int isActive=rs.getInt("isActive");
                        if(isActive==1){
                            //
                             out.print("User Account already verified.");
                    RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.include(request, response);
                        return;
                        }else{
                            //generate another code and send mail to the user
                            String email=rs.getString("email");
                    
                    //generate token and send mail
                    String code=generateRandomCode();
                    query="INSERT INTO verification(username,code) values(?,?)";
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, username);
                    preparedStmt.setString (2, code);
                    preparedStmt.execute();
                    conn.close();
                          
                    String url = request.getRequestURL().toString();
String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();

                    url=baseURL+"/verify?id="+username+"&auth_code="+code;
                    System.out.println("url"+url);
                    String body="You created an account. Click "+url+" to verify your account";
                   
                    Mailer.sendMail(email, body, "ONLINE MOVIE BOOKING : Email Verification");
                    
                    out.print("Account verification link has been sent to your mail.");
                    RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.include(request, response);          
                        }
                       
                        
                    }
                    
                  
        }
            catch (Exception e)
        {
          System.err.println("exception!");
          System.err.println(e.getMessage());
          out.print("exception!");
                    RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.include(request, response);
        }   
        
          
		
	}

    private String generateRandomCode() {
        String list="0123456789";
        String code="";
        Random random= new Random();
        for (int i=0; i<6; i++){
            code+= list.charAt(random.nextInt(list.length()));
        }
        return code;
    }
 
}