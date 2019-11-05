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
          try
        {
                    String myDriver = "com.mysql.jdbc.Driver";
                    String myUrl = "jdbc:mysql://localhost:3306/UserDB";
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(myUrl, "root", "123456");
                    // the mysql insert statement
                    Statement stmt = null;
                    
                    stmt = conn.createStatement();
                    String query="INSERT INTO users(first_name,last_name,email,username,password,address,phone) values(?,?,?,?,?,?,?)";
                   
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString (1, first_name);
                    preparedStmt.setString (2, last_name);
                    preparedStmt.setString (3, email);
                    preparedStmt.setString (4, username);
                    preparedStmt.setString (5, passwordEncrypted);
                    preparedStmt.setString (6, address);
                    preparedStmt.setString (7, phone);
                    preparedStmt.execute();
                    conn.close();
                    RequestDispatcher req = request.getRequestDispatcher("home.jsp");
			req.include(request, response);
        }
            catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        } 
          
		
	}
 
}