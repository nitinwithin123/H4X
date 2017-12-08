
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	@WebServlet(urlPatterns = {"/MessageContact"})
	public class contact extends HttpServlet {

	    
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	             {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String First_Name=request.getParameter("First_Name");
	        String Phone=request.getParameter("phone");
	        String Email=request.getParameter("email");
	        String Message=request.getParameter("message");
	        	    
		         try{
		        	 
		            Connection con=MyConnection.getConnection();
		            Statement st=con.createStatement();
		            	st.execute("insert into contact (First_Name,Phone,Email,Message) values('"+First_Name+"','"+Phone+"','"+Email+"','"+Message+"')");
		            	//out.println("<html><body onload=\"alert('You are Successfully registered')\"></body></html>");
		            	out.write("Your Message is Submitted.");
//            	RequestDispatcher rd = request.getRequestDispatcher("/EmailRecievingServlet");
		//				rd.forward(request,response);
		         }
              catch(Exception e){
            	  System.out.println("Message Not Submitted");
            	  out.write(e.toString());
			        	
			        }  
	             }
		         
		    @Override
		    protected void doGet(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
					processRequest(request, response);
					 System.out.println("response output " + response);

		    }

		    @Override
		    protected void doPost(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
					processRequest(request, response);
					
					RequestDispatcher rd = request.getRequestDispatcher("/sendMail2.jsp");
					rd.forward(request,response);
					
				
		    }

}

