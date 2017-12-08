
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



@WebServlet("/UpdateUserStatus")
public class UpdateUserStatus extends HttpServlet {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		 response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String id=request.getParameter("id");
			String statusData=request.getParameter("statusData");
			try{
				Connection con=MyConnection.getConnection();
	            Statement st=con.createStatement();
	            st.execute("update user set status='"+statusData+"' where id ="+id);
	            out.write("done");
	            
	        }
	        catch(Exception e){
	        	  
	        	 out.write(e.toString());
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



