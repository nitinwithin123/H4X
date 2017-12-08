	
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

	
	@WebServlet(urlPatterns = {"/NewsSubmit"})
	public class News extends HttpServlet {

	   
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	             {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String email=request.getParameter("email");
	        
	         try{
	        	 
	            Connection con=MyConnection.getConnection();
	            Statement st=con.createStatement();
	            ResultSet rs=st.executeQuery("select * from news where email='"+email+"'");
		           
	            if(rs.next())
	            {
	            	
	            	out.println("<script type=\"text/javascript\">");  
	            	out.println("alert('The email address you have entered is already Subscribed.');");  
	            	out.println("</script>");
	            
	            	 
	               
	            }
	            else
	            {
	            st.execute("insert into news (Email) values('"+email+"')");
	            
	            out.println("<script type=\"text/javascript\">");  
            	out.println("alert('Congratulations You are Subscribed.');");  
            	out.println("</script>");
            	out.write("Subscribed");

			
	            }
	            
	            rs.next();
	        }
	        catch(Exception e){
	        	  out.write(e.toString());
	        }


	    }

	    private void alert(String string) {
			// TODO Auto-generated method stub
			
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
				
				response.sendRedirect("index.html");
				
			
	    }

	    @Override
	    public String getServletInfo() {
	        return "Short description";
	    }

	}

