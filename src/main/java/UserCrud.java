import java.lang.*;
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


