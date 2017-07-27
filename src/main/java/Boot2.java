import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Boot2")
public class Boot2 extends HttpServlet { 

public void doGet(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException { 
try{
response.setContentType("text/html"); 
PrintWriter out = response.getWriter(); 
HttpSession session=request.getSession(false); 
String name=(String)session.getAttribute("hello"); 
out.print("hello"+name); 
} 
catch(Exception e){System.out.println(e);} 
}

}
