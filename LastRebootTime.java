





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



@WebServlet("/GetLRT")
public class LastRebootTime extends HttpServlet {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7314508769364810954L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		//response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String MAC=request.getParameter("mac");
			 
			
			String data="";
			
		
			         try{
			        	 
			            Connection con=MyConnection.getConnection();
			            Statement st=con.createStatement();
			            
			            ResultSet rs=st.executeQuery("select * from LastRebootTime");
			           data+="[";
			           while(rs.next())
			            {
			                
			        	   data+="{ mac:\""+rs.getString("mac")+"\",";
			        	    data+=" date:\""+rs.getString("date")+"\",";
			        	    data+=" time:\""+rs.getString("time")+"\",},";
			        	 
			               
			                
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