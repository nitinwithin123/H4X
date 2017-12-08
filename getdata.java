
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


/**
 * 
 */
@WebServlet("/GetData")
public class getdata extends HttpServlet {
	
	
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		 response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String data="";
			
			try{
				Connection con=MyConnection.getConnection();
	            Statement st=con.createStatement();
	            
	            ResultSet rs=st.executeQuery("select * from clients");
	           data+="[";
	            while(rs.next())
	            {
	                
	        	   data+="{ mac:\""+rs.getString("mac")+"\",";
	        	   data+=" type:\""+rs.getString("type")+"\",";
	        	   data+=" host_name:\""+rs.getString("host_name")+"\",";
	        	   data+="  ip:\""+rs.getString("ip")+"\",";
	        	   data+="  cpu_usage :\""+rs.getString("cpu_usage")+"\",";
	        	   data+=" status :\""+rs.getString("status")+"\",},";
	               
	                
	            }
	            data+="]";
		        out.write(data);
	            
	        }
	        catch(Exception e){
	        	  
	        	
	        } 
			            
			         
			      
			      
				
			
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}




