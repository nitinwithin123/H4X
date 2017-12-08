
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

		@WebServlet(urlPatterns = {"/Register"})
	public class Register extends HttpServlet {

	    
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	             {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String user_name=request.getParameter("user_name");
	        String password=request.getParameter("password");
	        String email=request.getParameter("email");
	        String user_type=request.getParameter("user_type");
	        String status=("pending");
	        	    
		         try{
		        	 
		            Connection con=MyConnection.getConnection();
		            Statement st=con.createStatement();
		            ResultSet rs=st.executeQuery("select * from user where email='"+email+"'");
		           
		            if(rs.next())
		            {
		                out.write("The email address you have entered is already registered");
		                
		            }
		            else{
		            	st.execute("insert into user (user_name,email,password,user_type,status) values('"+user_name+"','"+email+"','"+password+"','"+user_type+"','"+status+"')");
		            	out.write("Registered");
		            	}
		         	}
              catch(Exception e){
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
					//System.out.println("response output " + response);
					RequestDispatcher rd = request.getRequestDispatcher("/sendMail.jsp");
					rd.forward(request,response);
				
		    }


}
