import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {

	
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
        System.out.println(pass);
        System.out.println(user);
        
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        String email = request.getParameter("email");
        String recipient2 = "monishasurana42@gmail.com";
        String subject = "Smart School Registration";
        String content = "Dear Applicant,Congratulations!! \n \n"
        		+ "Thank you for registering with Smart School. We have received your email and our Customer support team will be responding to "
        		+ "you soon. We'll get back to you within 48 hours. \n \n"
        		+ "Best regards, \n"
        		+ "Smart School Communications Team. \n \n"
        		+ "Note: This is a system generated e-mail, please do not reply to it. \n \n"
        		+ "*** This message is intended only for the person or entity to which it is addressed and may contain confidential and/or privileged "
        		+ "information. If you have received this message in error, please notify the sender immediately and delete this message from your "
        		+ "system ***";
 
        String resultMessage = "";
 
        try {
            EmailSendingUtility.sendEmail(host, port,name, user, pass, email, subject,content);
            resultMessage = "The e-mail was sent successfully";
            EmailSendingUtility.sendEmail(host, port,name, user, pass, recipient2, subject,content);
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
