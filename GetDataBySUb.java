

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



@WebServlet("/GetDataSub")
public class GetDataBySUb extends HttpServlet {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		 //response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String data="";
			
			try{
				Connection con=MyConnection.getConnection();
	            Statement st=con.createStatement();
	           
	            ResultSet rs=st.executeQuery("select distinct Class_Id from Class");
                  // rs=st.executeQuery("select Section from Class where Class_Id ="'+class_id+'");
			
	           data+="[";
	            while(rs.next())
	            {
	            	 
		        	   //data+="{ Subject_Id:\""+rs.getString("Subject_Id")+"\",";
		        	   //data+=" Section:\""+rs.getString("Section")+"\",";
		        	   data+="{ Class_Id:\""+rs.getString("Class_Id")+"\",},";
		        	 //  data+=" Teacher_Id:\""+rs.getString("Teacher_Id")+"\",";
		        	   //data+=" Subject_Name :\""+rs.getString("Subject_Name")+"\",},";
		                //response.sendRedirect("./add%20information.jsp");
	            }
	            data+="]";
		        out.write(data);
	            
	        }
	        catch(Exception e){
	        	out.println("eyttttttttttttttu"+e.toString());
	        	  
	        	
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




