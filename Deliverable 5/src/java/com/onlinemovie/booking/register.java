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
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
                String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		 PasswordEncryption pwdEnc=new PasswordEncryption();
		String passwordEncrypted=pwdEnc.hashPassword(password);
                  response.setContentType("text/html");  
                PrintWriter out = response.getWriter();
          try
        {
                    
                    String url = request.getRequestURL().toString();
String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();

                    Class.forName(SqlConfig.jdbcDriver);
                    Connection conn = DriverManager.getConnection(SqlConfig.jdbcURL, SqlConfig.jdbcUSERNAME,SqlConfig.jdbcPASSWORD);
                    // the mysql insert statement
                    Statement stmt = null;
                    
                    stmt = conn.createStatement();
                    //check if a user already exists in the database having the username [username/email must always be unique for every user]
                    
                    String query="Select * from Users where username=? or email=?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, username);
                    preparedStmt.setString (2, email);
                   
                    ResultSet rs=preparedStmt.executeQuery();
                    
                    if(rs.next()){
                        //username already exist hence stop registration process and give an error message
                           out.print("A User having the supplied username or email already exist");
                    RequestDispatcher req = request.getRequestDispatcher("register.jsp");
			req.include(request, response);
                        return;
                    }
                    
                    query="INSERT INTO Users(first_name,last_name,email,username,password,address,phone) values(?,?,?,?,?,?,?)";
                   
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, first_name);
                    preparedStmt.setString (2, last_name);
                    preparedStmt.setString (3, email);
                    preparedStmt.setString (4, username);
                    preparedStmt.setString (5, passwordEncrypted);
                    preparedStmt.setString (6, address);
                    preparedStmt.setString (7, phone);
                    preparedStmt.execute();
                   
                    
                    //generate token and send mail
                    String code=generateRandomCode();
                    query="INSERT INTO verification(username,code) values(?,?)";
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, username);
                    preparedStmt.setString (2, code);
                    preparedStmt.execute();
                    conn.close();
                    
                    url=baseURL+"/verify?id="+username+"&auth_code="+code;
                    System.out.println("url"+url);
                    String body="You created an account. Click "+url+" to verify your account";
                    Mailer.sendMail(email, body, "ONLINE MOVIE BOOKING : Email Verification");
                    
                    out.print("Registration succesfull. Account verification link has been sent to your mail.");
                    RequestDispatcher req = request.getRequestDispatcher("register.jsp");
			req.include(request, response);
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