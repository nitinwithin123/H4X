

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/MessageSendingServlet")
public class MessageSendingServlet extends HttpServlet {

	
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
        // reads form fields
        String email = request.getParameter("email");
        
        String subject = "Smart School Query Response";
        String content = "Hello, \n \n"
        		+ "Thank for contacting us. "
        		+ "We have recieved your query. We'll get back to you soon. \n \n"
        		+ "Best regards, \n"
        		+ "Smart School Communications Team. \n \n"
        		+ "Note: This is a system generated e-mail, please do not reply to it. \n \n";
 
        String resultMessage = "";
 
        try {
            EmailSendingUtility.sendEmail(host, port,name, user, pass, email, subject,content);
            resultMessage = "The e-mail was sent successfully";
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.forward(request,response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }
    
}
