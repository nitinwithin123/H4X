



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



@WebServlet("/GetChartBySection")
public class GetDataBySection extends HttpServlet {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7314508769364810954L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		//response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String section=request.getParameter("section");
			String data="";
			
		
			         try{
			        	 
			            Connection con=MyConnection.getConnection();
			            Statement st=con.createStatement();
			            ResultSet rs;
			            if(section.equals("ALL"))
			            {
			            	 rs=st.executeQuery("select * from Class");
			            }else{
			            	
			            	rs=st.executeQuery("select * from Class where Section='"+section+"' ");
			            }
			           data+="[";
			           while(rs.next())
			            {
			                
			        	   data+="{ Class_Id:\""+rs.getString("Class_Id")+"\",";
			        	   data+=" Section :\""+rs.getString("Section")+"\",},";
			                //response.sendRedirect("./add%20information.jsp");
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
