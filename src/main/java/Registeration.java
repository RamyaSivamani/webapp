import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;  
import javax.servlet.http.*;

@WebServlet(name= "Registeration", urlPatterns= "/Registeration")
public class Registeration extends HttpServlet
{
        String driver_name="com.mysql.jdbc.Driver";
        String driver_url="jdbc:mysql://localhost/";
        String db_name="kgfsl";
        String user_name="root";
        String password="";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.setContentType("text/html");  

        try
        {
            Class.forName(driver_name);
            Connection con=DriverManager.getConnection(driver_url+db_name,user_name,password);
            PrintWriter out = response.getWriter(); 
            String user_name=request.getParameter("username");
            String password=request.getParameter("password");
            String dob=request.getParameter("dob");
            String email=request.getParameter("email");

            String query="INSERT INTO user(username,password,dob,email) VALUES(?,?,?,?)";
            PreparedStatement statement=con.prepareStatement(query);

            statement.setString(1,user_name);
            statement.setString(2,password);
            statement.setString(3,dob);
            statement.setString(4,email);
            statement.executeUpdate();
            
            out.println(user_name);
            out.print("<br><h1>Your details registered successfully</h1><br>");
            RequestDispatcher rs1=request.getRequestDispatcher("SendEmail");
                   rs1.include(request,response); 
            RequestDispatcher rs=request.getRequestDispatcher("welcome.jsp");  
            rs.include(request, response);
           // response.sendRedirect("welcome.jsp");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
}