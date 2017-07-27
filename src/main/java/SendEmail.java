import java.io.*;
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



   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
       
       response.setContentType("text/html");
      PrintWriter out = response.getWriter();

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
     
 
      writefile();
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
         

         
        // Transport.send(message);
         
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
   public void writefile()
{
    
        String data = "First File writing program";
        try {
     PrintWriter fr = new PrintWriter(new    
       FileOutputStream("information.txt",true));  
            fr.write(data);
            System.out.println(System.getProperty("line.separator"));
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        } /*finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

}
}