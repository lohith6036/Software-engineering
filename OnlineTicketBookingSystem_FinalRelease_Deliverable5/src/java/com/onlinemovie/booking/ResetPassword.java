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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
@WebServlet("/resetPwd")
public class ResetPassword extends HttpServlet {
        
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

                 String username = request.getParameter("username");
                 String code = request.getParameter("auth_code");
                 String newPwd = request.getParameter("new_pwd");
                 PasswordEncryption pwdEnc=new PasswordEncryption();
		String passwordEncrypted=pwdEnc.hashPassword(newPwd);
          
                  response.setContentType("text/html");  
                PrintWriter out = response.getWriter();
                
          try
        {
                    
                    
                    Class.forName(SqlConfig.jdbcDriver);
                    Connection conn;
                    conn = DriverManager.getConnection(SqlConfig.jdbcURL, SqlConfig.jdbcUSERNAME,SqlConfig.jdbcPASSWORD);
                  
                    String query="select * from password_resets where username=? and code=?";
                   

                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, username);
                    preparedStmt.setString (2, code);
                    
                    ResultSet rs=preparedStmt.executeQuery();

                    if (rs.next()) {
                        //valid password request code hence proceed with updating user password
                       query="Update Users set password=? where username=? ";
                   

                     preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, passwordEncrypted);
                    preparedStmt.setString (2, username);
                    preparedStmt.executeUpdate();

                    //delete the password reset code
                    query="DELETE from password_resets where username=? and code=?";
                  preparedStmt.setString (1, username);
                    preparedStmt.setString (2, code);
                    
                   preparedStmt.executeUpdate();
                   
                     out.print("Password Reset Success.");
                    RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.include(request, response);
                   
                    }else{
                        out.print("Password Reset Failed due to invalid authentication");
                    RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.include(request, response);
                   
                    }
                    
                    conn.close();
        }
            catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        } 
                 
		
     }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
                String email = request.getParameter("email");
		 
                response.setContentType("text/html");  
                PrintWriter out = response.getWriter();
                
          try
        {
                    
                    
                    Class.forName(SqlConfig.jdbcDriver);
                    Connection conn;
                    conn = DriverManager.getConnection(SqlConfig.jdbcURL, SqlConfig.jdbcUSERNAME,SqlConfig.jdbcPASSWORD);
                  
                    String query="select * from Users where email=?";
                   

                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, email);
                    
                    ResultSet rs=preparedStmt.executeQuery();

                    if (rs.next()) {
                        //email exist
                        String code=generateRandomCode();
                        String username=rs.getString("username");
                     query="INSERT INTO password_resets(username,code) values(?,?)";
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, username);
                    preparedStmt.setString (2, code);
                    preparedStmt.execute();
                    conn.close();
                              
                    String url = request.getRequestURL().toString();
String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();

                        url=  baseURL+"/reset.jsp?id="+username+"&auth_code="+code;
                    
                    String body="A password reset request was made. Click "+url+" to reset your account password";
                    Mailer.sendMail(email, body, "ONLINE MOVIE BOOKING : Password Reset");
                    
                     out.print("Password Reset has been sent to your mail.");
                    RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.include(request, response);
                   
                    }else{
                        out.print("No such email address exist. Password Reset error");
                    RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.include(request, response);
                   
                    }
                    
                    conn.close();
        }
            catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
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
