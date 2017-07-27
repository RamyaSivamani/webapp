import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet(name="DeleteUser" , urlPatterns="/DeleteUser")  
public class DeleteUser extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
             throws ServletException, IOException { 
                 PrintWriter out=response.getWriter(); 
        String Email=request.getParameter("email");
        
      
         
          UserCrud.delete(Email);  
          out.println("<h1>Updated Successfully</h1>");
          response.sendRedirect("ViewUser");
    }  
}  