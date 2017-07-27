import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/EditUser")  
public class EditUser extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
           throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<h1>Update User</h1>");  
        String semail=request.getParameter("email");  
          
        UserPojo e=UserCrud.getEmployeeById(semail);  
          
        out.print("<form action='UpdateUser' method='post'>");  
        out.print("<table>");  
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+e.getName()+"'/></td></tr>");  
        out.print("<tr><td>Password:</td><td><input type='password' name='password' value='"+e.getPassword()+"'/></td></tr>");  
        out.print("<tr><td>Email:</td><td><input type='hidden' name='email' value='"+e.getEmail()+"'/></td></tr>"); 
        out.print("<tr><td>Dob:</td><td><input type='dob' name='dob' value='"+e.getDob()+"'/></td></tr>"); 
        out.print("<tr><td colspan='2'><input type='submit' value='update '/></td></tr>");  
        out.print("</table>");  
        out.print("</form>");  
          
        out.close();  
    }  
} 