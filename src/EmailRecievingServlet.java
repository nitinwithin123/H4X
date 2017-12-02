import .java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/EmailRecievingServlet")
public class EmailRecievingServlet extends HttpServlet {

	private String host;
    private String port;
    private String name;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        name = context.getInitParameter("name");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
       
    	
        try{
           Connection con=MyConnection.getConnection();
           Statement st=con.createStatement(); 
           ResultSet rs=st.executeQuery("select * from contact"); 
           rs.next();

        String sender = "monishasurana42@gmail.com";
        String First_Name=request.getParameter("First_Name");
        String phone=request.getParameter("phone");
        String Email=request.getParameter("email");
      //  String Msg=request.getParameter("message");
        String Msg = "Name :" +First_Name  + "\nEmail :" + Email  + " \nPhone:" + phone +  "\nMessage :" +request.getParameter("message");
        String resultMessage = "";
 
        try {
        	EmailRecieveingUtility.sendEmail(host, port,name, user, pass, sender,First_Name,Email,phone,Msg);
            resultMessage = "The e-mail was recieved successfully"; 
            
            RequestDispatcher rd = request.getRequestDispatcher("/MessageSendingServlet");
			rd.forward(request,response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }catch(Exception e){
  	  System.out.println("Message Not Recieved");
  	  e.printStackTrace();
	        	
	        }  
       }
    
}
