

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetSubscriber")
public class subscribe extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		 response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String data="";
			
			try{
				Connection con=MyConnection.getConnection();
	            Statement st=con.createStatement();
	            
	            ResultSet rs=st.executeQuery("select * from news");
	           data+="[";
	            while(rs.next())
	            {
	                
	        	   data+="{ email:\""+rs.getString("Email")+"\",},";
   
	            }
	            data+="]";
		        out.write(data);
	            
	        }
	        catch(Exception e){
	        	  
	        	
	        } 
	
			
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}




