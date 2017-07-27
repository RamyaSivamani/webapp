import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.servlet.ServletException;  
import javax.servlet.http.*;

@WebServlet(name= "Home", urlPatterns= "/Home")
public class Home extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
             response.setContentType("text/html");
             PrintWriter out=response.getWriter();
             int flag=0;
             String u_name,pass;
            String driver_name="com.mysql.jdbc.Driver";
            String driver_url="jdbc:mysql://localhost/";
            String db_name="kgfsl";
            String user_name="root";
            String password="";

         try
        {
            Class.forName(driver_name);
            Connection con=DriverManager.getConnection(driver_url+db_name,user_name,password);
            String query="SELECT * FROM user";

            PreparedStatement statement=con.prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            String username=request.getParameter("username");
            String pwd=request.getParameter("password");
            
           
          while(rs.next())
          {
                u_name=rs.getString(1);
                pass=rs.getString(2);
              if((username.equals(u_name))&&(pwd.equals(pass)))
              {
                  flag=1;
                   out.print("<br><center><h1>Hi,"+u_name+"</h1><h1>Welcome to our company</h1></center><br>");
                    RequestDispatcher rs1=request.getRequestDispatcher("welcome1.jsp");
                rs1.include(request,response);
                   break;
              } 
          }
          if(flag==0)
          {
                out.println("<h1>sorry Register here</h1>");
                RequestDispatcher rs1=request.getRequestDispatcher("welcome.jsp");
                rs1.include(request,response);
          }
           
    }catch(Exception e)   
    {
        e.printStackTrace();
    }
    }  
  
} 