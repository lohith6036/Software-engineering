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
@WebServlet("/verify")
public class Verify extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	String username = request.getParameter("id");
		String code = request.getParameter("auth_code");
                response.setContentType("text/html");  
                PrintWriter out = response.getWriter();  
                  try
        {
                    
                    
                    Class.forName(SqlConfig.jdbcDriver);
                    Connection conn = DriverManager.getConnection(SqlConfig.jdbcURL, SqlConfig.jdbcUSERNAME,SqlConfig.jdbcPASSWORD);
                   
                    //check if the code and username matches
                    String query="Select * from verification where username=? and code=?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, username);
                    preparedStmt.setString (2, code);
                   
                    ResultSet rs=preparedStmt.executeQuery();
                    
                    if(rs.next()){
                        //the auth code exist hence activate the user
                    query="Update Users set isActive=1 WHERE username=?";
                   
                    preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, username);
                          preparedStmt.executeUpdate();
                          conn.close();
                          
                          
                    out.print("Account Verification succesfull");
                    RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.include(request, response);
                    }else{
                         out.print("Invalid Authentication Code. Verification failed.");
                   
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
        
}
