
# User Registeration

# UserPojo

```
public class UserPojo {  
private String name,password,email,dob;  

public String getName() {  
    return name;  
}  
public void setName(String name) {  
    this.name = name;  
}  
public String getPassword() {  
    return password;  
}  
public void setPassword(String password) {  
    this.password = password;  
}  
public String getEmail() {  
    return email;  
}  
public void setEmail(String email) {  
    this.email = email;  
}  
public String getDob() {  
    return dob;  
}  
public void setDob(String dob) {  
    this.dob = dob;  
}  
  
}  
```
``Here I am using OOPS concept ``

#UserCrud

```import java.lang.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.*;
import java.sql.*;
public class UserCrud
{
    public static Connection getConnection()
    {
    Connection con=null;
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/kgfsl","root","");
    }catch(Exception e)
    {
        e.printStackTrace();
    }
    return con;
}

     public static List<UserPojo> getAllUser(){  
        List<UserPojo> list=new ArrayList<UserPojo>();  
          
        try{  
            Connection con=UserCrud.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from user");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                UserPojo e=new UserPojo();  
               
                e.setName(rs.getString(1));  
                e.setPassword(rs.getString(2));  
                e.setDob(rs.getString(3));  
                e.setEmail(rs.getString(4));  
                
                list.add(e);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }  public static int delete(String email){  
        int status=0;  
        try{  
            Connection con=UserCrud.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from user where email=?");  
            ps.setString(1,email);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }
    public static UserPojo getEmployeeById(String email){  
        UserPojo e=new UserPojo();  
          
        try{  
            Connection con=UserCrud.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from user where email=?");  
            ps.setString(1,email);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                e.setName(rs.getString(1));  
                e.setPassword(rs.getString(2));  
                e.setDob(rs.getString(3));  
                e.setEmail(rs.getString(4));  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return e;  
    }
     public static int update(UserPojo e){  
        int status=0;  
        try{  
            Connection con=UserCrud.getConnection();  
            PreparedStatement ps=con.prepareStatement("update user set username=?,password=?,dob=? where email=?");
            
            ps.setString(1,e.getName());  
            ps.setString(2,e.getPassword());  
            ps.setString(3,e.getDob());  
            ps.setString(4,e.getEmail());
            
            
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
  
}  
```
``Here I Using Sql query to perform crud operation on User Registeration``

# Login page to enter the details

# Welcome.jsp

 ```
 <html>
    <head>
        <title>Welcome Page</title>
    </head>
    <body>
    <center>
    <table border="30">
    <tr>
        <td>
    <div class="welcome">
    <center>
        <h1>HOME PAGE</h1>
        <img src="Penguins.jpg" class="img-responsive" alt="image" width="300" height="150">  
    </center>
    </div>
        <form action="Home" method="POST">
            <center>
            <table>
                <tr>    
                    <td>USER NAME</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>PASSWORD</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td><a href="registeration.jsp">Register here</a></td>
                    <td><input type="submit" value="Login"></td>
                </tr>
                <tr>
                
                </tr>
            </table>
            </center>
        </form>
        </td>
      </tr>
    </table>
    </center>
    </body>
</html>
 
 ```
`` Here I am gathering registered user information``

# validate the username, password 

 ```import java.io.IOException;  
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
 
 ```
 ``Here I am verifying user information based on database value``

 # Registeration form

 ```<html>
    <body>
    <center>
    <table border="10">
    <tr>
    <td>
    <center><h1>Registeration Form</h1>
     <img src="Penguins.jpg" class="img-responsive" alt="image" width="300" height="150">
     </center>
        <form action="Registeration" method="POST">
            <center>
            <table>
                <tr> 
                    <td>Username</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>DOB</td>
                    <td><input type="date" name="dob"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email"</td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="submit"></td>
                </tr>
            </table>
        </form>
        </td>
        </tr>
        </table>
        </center>
    </body>
</html>
 
 ```
 ``Here I am gathering new User information for register ``

 # Backend Registeration

 ```import java.io.IOException;  
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
```
``Here i am Inserting the new User data to Database``

 # send Email to new User

 ```import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {

public void writefile()
{
    FileWriter fr = null;
        String data = "First File writing program";
        try {
            fr = new FileWriter("information.txt");
            fr.write(data);
            System.out.println(System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
       
      String name=request.getParameter("username");
      String password=request.getParameter("password");
      String dob=request.getParameter("dob");
      String email=request.getParameter("email");
     /* try{
      FileWriter fileWriter=new FileWriter("information.txt");
      fileWriter.write("Username="+name);
      fileWriter.write("Password="+password);
      fileWriter.write("Dob="+dob);
      fileWriter.write("Email="+email);
      fileWriter.close();
    }catch(Exception e)
    {
      e.printStackTrace();
    }*/
     
 
      SendEmail s=new SendEmail();
      s.writefile();
    final String from = "hemachandiran.k@kggroup.com";
      final String pass="Hemanth@96";
 
      
      String host = "webmail.kggroup.com";
 
      
      Properties props = System.getProperties();
 
      
      props.put("mail.smtp.host",host);
     props.put("mail.smtp.auth", "true");  
  
Session session = Session.getDefaultInstance(props,  
 new javax.mail.Authenticator() {  
  protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(from,pass);  
   }  
});

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
     

      try {

         MimeMessage message = new MimeMessage(session);
         

         message.setFrom(new InternetAddress(from));
         

         message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
         

         message.setSubject("Welcome to registration");
         

         message.setText("Hello");
         BodyPart messageBodyPart = new MimeBodyPart();
 
         
         messageBodyPart.setText("Thanks for your registeration");
         
        
         Multipart multipart = new MimeMultipart();
 
         multipart.addBodyPart(messageBodyPart);
 
         
         messageBodyPart = new MimeBodyPart();
         String filename = "D:\\Java programs\\webapp\\tomcatapp\\webapp\\src\\main\\java\\information.txt";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);
 
         
         message.setContent(multipart );
         

         
         Transport.send(message);
         
         String title = "Hi,"+name+".......!Send Email";
         String res = "Sent message successfully....";
         String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
         
         out.println(docType +
            "<html>\n" +
               "<head><title>" + title + "</title></head>\n" +
               "<body bgcolor = \"#f0f0f0\">\n" +
                  "<h1 align = \"center\">" + title + "</h1>\n"+
                  "<p align = \"center\">" + res + "</p>\n" +"</body></html>");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
} 
 
 ```
Here I am sending email to New User for Conformation

User choose the option:

1. view user details
2. delete user
3. Update user


# 1.ViewUser

```
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

```
Here I am going to show the Registered User Information

#2.DeleteUser

```
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

```
Here I giving provision to delete the user information from our backend

# 3.UpdateUser

```
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

```
Here i am giving provision to Update User information

#JSP Files

#1.welcome.jsp

```
<html>
    <head>
        <title>Welcome Page</title>
    </head>
    <body>
    <center>
    <table border="30">
    <tr>
        <td>
    <div class="welcome">
    <center>
        <h1>HOME PAGE</h1>
        <img src="Penguins.jpg" class="img-responsive" alt="image" width="300" height="150">  
    </center>
    </div>
        <form action="Home" method="POST">
            <center>
            <table>
                <tr>    
                    <td>USER NAME</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>PASSWORD</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td><a href="registeration.jsp">Register here</a></td>
                    <td><input type="submit" value="Login"></td>
                </tr>
                <tr>
                
                </tr>
            </table>
            </center>
        </form>
        </td>
      </tr>
    </table>
    </center>
    </body>
</html>
```
#welcome1.jsg

```
<html>
    <head>
        <title>Welcome Page</title>
    </head>
    <body>
<div class="row">
<center>
<h1>Choose Option</h1>
</center>
<form action="Home" method="POST">
          <div class="col-xs-4">
          <center>
          <table border="10">
             <td><button class="btn btn-info" type="submit"><a href="ViewUser">View here</a></button></td>
             </div>
                <div class="col-xs-4" >
             <td><button class="btn btn-danger" type="submit"><a href="delete.jsp">Delete</a></button></td>
                 </div>
              <div class="col-xs-4">
             <td><button class="btn" type="submit"><a href="update.jsp">Update</a></button></td>
            </div>
            </table>
            </center>
</div>
</body>
</html>
```
#registration.jsg

```
<html>
    <body>
    <center>
    <table border="10">
    <tr>
    <td>
    <center><h1>Registeration Form</h1>
     <img src="Penguins.jpg" class="img-responsive" alt="image" width="300" height="150">
     </center>
        <form action="Registeration" method="POST">
            <center>
            <table>
                <tr> 
                    <td>Username</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>DOB</td>
                    <td><input type="date" name="dob"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email"</td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="submit"></td>
                </tr>
            </table>
        </form>
        </td>
        </tr>
        </table>
        </center>
    </body>
</html>
```
#delete.jsp

```
<center>
<table border="20">
<tr>
<td>
        <h1>Delete your Details here</h1>
        <h2>Enter your Email here</h2>
        <img src="Penguins.jpg" class="img-responsive" alt="image" width="350" height="150">  
<form action="DeleteUser" method="POST">
<center>
<table >
                <tr><td></td><td><input type="email" name="email"></td>
                </tr>
                </table>
                <center>
                <input type="submit" value="submit">
                </center>

</form>
</td>
</tr>
</table>
</center>
```
#update.jsp

```

 <center>
<table border="20">
<tr>
<td>
        <h1>Update your Details here</h1>
        <h2>Enter your Email here</h2>
        <img src="Penguins.jpg" class="img-responsive" alt="image" width="350" height="150">  
    </center>
<form action="EditUser" method="POST">
<center>
<table >
                <tr>  
                    <td><input type="email" name="email"></td>
                </tr></table>
                     <center><input type="submit" value="submit"> </center>
</form>
</td>
</tr>
</table>
 </center>
 ```
