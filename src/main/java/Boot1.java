import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Boot1")
public class Boot1 extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException { 
try{
response.setContentType("text/html");
PrintWriter out = response.getWriter(); 
String name = request.getParameter("username");
String pass = request.getParameter("password");
String pass1=pass;
String name1=name;
if((pass1.equals(pass))&&(name1.equals(name)))
{
HttpSession session = request.getSession();
session.setAttribute("hello",name);
response.sendRedirect("Boot2");
// out.print("<a href='Wel'></a>"); 
out.close();
}
else
{ 
response.setContentType("text/html");
out.println("Sorry UserName Password Error!"); 

} 
} 
catch(Exception e){System.out.println(e);} 

} 
}