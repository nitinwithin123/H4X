	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	
	@WebServlet(urlPatterns = {"/LoginCheck"})
	public class Login extends HttpServlet {

	   
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	             {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String email=request.getParameter("email");
	        String password=request.getParameter("password");
	        String user_type = request.getParameter("user_type");
	        
	        System.out.println(email);
	        System.out.println(password);
	        System.out.println(user_type);
	        
	        
	        String status;// = request.getParameter("status");
	         try{
	        	 
	            Connection con=MyConnection.getConnection();
	            Statement st=con.createStatement();
	            
	            ResultSet rs=st.executeQuery("select * from user where email='"+email+"'");
	            System.out.println("hello");
	            rs.next();
	            status = rs.getString("status");
	            if(password.equals(rs.getString("password")) && (user_type.equals(rs.getString("user_type"))))
	            {
	            	

	    	        System.out.println("1st meet");
	            	//status = rs.getString("status");
	            	if(status.equals("pending"))
	            	{

		    	        System.out.println("1st  meet and 2nd");
	            		
	            		out.println("<script type=\"text/javascript\">");  
		            	out.println("alert('Your Registration status is pending.');");  
		            	out.println("</script>");

		            	
	            	}
	            	else if (status.equals("rejected"))
	            	{	            		
	            		out.println("<script type=\"text/javascript\">");  
		            	out.println("alert('Your Registration status is rejected.');");  
	            		//response.sendRedirect("index.html");
	            		out.println("</script>");
	            	}
	            	
	            	else if(status.equals("approved") && user_type.equals("Master Admin")  ) 
	            	{
	            		  System.out.println("1st  meet and 3rd");
	            		response.sendRedirect("masteradmin.html");

	            	}
	            	else if(status.equals("approved") && user_type.equals("Admin")  ) 
	            	{
	            		  System.out.println("1st  meet and 4rd");
	            		response.sendRedirect("admin.html");
	            	}
	            	else if(status.equals("approved") && user_type.equals("Support Engineer")  ) 
	            	{
	            		  System.out.println("1st  meet and 5th");
	            		response.sendRedirect("supportengineer.html");
	            	}
	            }
	            else{
	            	
	                out.write("<html>");
	                 out.write("login failed ");
	                 out.write("</html>");
	            }
	        }
	        catch(Exception e){
	        	
	        	  out.write("<html>");
	              out.write("login failed user name not found "+email);
	              out.write("</html>");
	        	
	        }
	    }

	  
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
				processRequest(request, response);
			
	    }

	    
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	      
				processRequest(request, response);
				
			
	    }

	    @Override
	    public String getServletInfo() {
	        return "Short description";
	    }
	}

