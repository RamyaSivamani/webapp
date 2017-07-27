import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet(name="ViewUser" , urlPatterns="/ViewUser")  
public class ViewUser extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
               throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<center><a href='welcome.jsp'>Add New User</a>");  
        out.println("<h1>User List</h1>");  
          
        List<UserPojo> list=UserCrud.getAllUser(); 
        out.println("<head><style> .tap { background-image: url('student.jpg'); height:350px; width:750px;}</style></head>") ;
        out.print("<table class='tap' border='1' width='100%'");  
        out.print("<tr><th>Name</th><th>Password</th><th>DOB</th><th>Email</th></tr>");  
        for(UserPojo e:list)
        {  
         out.print("<tr><td>"+e.getName()+"</td><td>"+e.getPassword()+"</td><td>"+e.getDob()+"</td><td>"+e.getEmail()+"</td></tr>");  
        }  
        out.print("</table> </center>");  
          
        out.close();  
    }  
}  