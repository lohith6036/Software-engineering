package com.onlinemovie.booking;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
@WebServlet("/Login")
public class Login extends HttpServlet {
        
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String first_name = request.getParameter("first_name");
//		String last_name = request.getParameter("last_name");
//                email
         
         
		String username = request.getParameter("username");
		String password = request.getParameter("password");
                
                response.setContentType("text/html");  
                PrintWriter out = response.getWriter();
                PasswordEncryption pwdEnc=new PasswordEncryption();
                String passwordEncrypted=pwdEnc.hashPassword(password);
//		String address = request.getParameter("address");
//		String contact = request.getParameter("contact");
                
          try
        {
                    
                    
                    Class.forName(SqlConfig.jdbcDriver);
                    Connection conn;
                    conn = DriverManager.getConnection(SqlConfig.jdbcURL, SqlConfig.jdbcUSERNAME,SqlConfig.jdbcPASSWORD);
                    // the mysql insert statement
                    Statement stmt = null;
                    
                    stmt = conn.createStatement();
                    String query="select * from Users where username=? and password=?";
                   

                    PreparedStatement preparedStmt = conn.prepareStatement(query);
//                  preparedStmt.setString (1, first_name);
                    preparedStmt.setString (1, username);
                    preparedStmt.setString (2, passwordEncrypted);
                    
                    ResultSet rs=preparedStmt.executeQuery();

                    if (rs.next()) {
                        
                        //check if user is verified
                        String email=rs.getString("email");
                        int isActive=rs.getInt("isActive");
                        
                        if(isActive==0){
                            //user has not verified email.
                                 String url = request.getRequestURL().toString();
String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();

                            url="<a href='"+baseURL+"/resendEmailVerification?username="+username+"'> Click here </a> to resend verification link ";
                             out.print("Email Account not verified. "+url);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");
                        requestDispatcher.include(request, response);
                        return;
                        }
                        request.getSession().setAttribute("logged_in", true);
                        request.getSession().setAttribute("username", username);
                        request.getSession().setAttribute("email", email);
                         System.out.println("Success");
//                         RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.jsp");
//                        requestDispatcher.forward(request, response);
                        response.sendRedirect("home.jsp");
                    }else{
                    
                         out.print("Sorry UserName or Password is incorrect!");
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                        requestDispatcher.include(request, response);
                    }
//                    If wrong details entered Go to eror message page and show button to go back to login page with proper error message
                    conn.close();
        }
            catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        } 
          
		
	}
}
