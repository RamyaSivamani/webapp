import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/UpdateUser")  
public class UpdateUser extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
          throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
       
        String name=request.getParameter("name");  
        String password=request.getParameter("password");  
        String dob=request.getParameter("dob");  
        String email=request.getParameter("email");  
          
       UserPojo e=new UserPojo();  
         
        e.setName(name);  
        e.setPassword(password);  
        e.setDob(dob);  
        e.setEmail(email);  
          
        int status=UserCrud.update(e);  
        if(status>0){  
            response.sendRedirect("ViewUser");  
        }else{  
            out.println("Sorry! unable to update record");  
        }  
          
        out.close();  
    }  
  
}  