import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class FirstProgram extends HttpServlet
{
    //String message=null;
    @Override
    public void init() throws ServletException {
        super.init();
        //message="hello every one=";
    }
    
    /*public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String[] str=request.getParameterValues("firstname");
        out.print("<html><body>");
        for(int i=0;i<str.length;i++)
        out.print("<h2>"+str+"</h2>");
        //out.print("\t<h2>"+request.getParameterValues("lastname")+"</h2>");
        out.print("</body></html>");
        System.out.println("this syso");
    }*/
@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/jsp");
        PrintWriter out=resp.getWriter();
        String[] str=req.getParameterValues("firstname");
        out.println("<html><body>");
        for(int i=0;i<str.length;i++)
        {
            out.println("<h1>"+str[i]+"</h1>");
        }
        out.println("</body></html>");
    }
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("hello everyone");
       
    }
}
