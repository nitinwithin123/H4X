
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



@WebServlet("/GetUser")
public class getuser extends HttpServlet {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		 response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String data="";
			
			try{
				Connection con=MyConnection.getConnection();
	            Statement st=con.createStatement();
	            
	            ResultSet rs=st.executeQuery("select * from user");
	           data+="[";
	            while(rs.next())
	            {
	                
	        	   data+="{ user_name:\""+rs.getString("user_name")+"\",";
	        	   data+=" id:"+rs.getString("id")+",";
	        	   data+=" email:\""+rs.getString("email")+"\",";
	        	   data+=" status:\""+rs.getString("status")+"\",";
	        	   data+=" user_type :\""+rs.getString("user_type")+"\",},";
	               
	                
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



