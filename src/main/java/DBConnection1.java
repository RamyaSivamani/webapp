import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
public class DBConnection1 extends HttpServlet
{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
    {
        String driver_name="com.mysql.jdbc.Driver";
        String driver_url="jdbc:mysql://localhost/";
        String db_name="servlet";
        String user_name="root";
        String password="";
         response.setContentType("text/jsp");
         PrintWriter out=response.getWriter();
        try {
            Class.forName(driver_name);
            Connection con=DriverManager.getConnection(driver_url + db_name,user_name,password);
            String firstname=request.getParameter("firstname");
            String lastname=request.getParameter("lastname");
            String email=request.getParameter("email");
            String date=request.getParameter("date");
            String time=request.getParameter("time");
            String topic=request.getParameter("topic");
            String location=request.getParameter("location");
            String query="INSERT INTO mydetail(firstname,lastname,email,date,time,topic,location) values(?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(query);
            st.setString(1,firstname);
            st.setString(2,lastname);
            st.setString(3,email);
            st.setString(4,date);
            st.setString(5,time);
            st.setString(6,topic);
            st.setString(7,location);
            st.executeUpdate();
            out.println("kkkkk");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}