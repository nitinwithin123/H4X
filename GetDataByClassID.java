



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



@WebServlet("/GetChartByClassID")
public class GetDataByClassID extends HttpServlet {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7314508769364810954L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		//response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String class_id=request.getParameter("classId");
			String data="";
			
		
			         try{
			        	 
			            Connection con=MyConnection.getConnection();
			            Statement st=con.createStatement();
			            ResultSet rs;
			            if(class_id.equals("ALL"))
			            {
			            	 rs=st.executeQuery("select * from Student");
			            }else{
			            	
			            	rs=st.executeQuery("select * from Student where Class_Id='"+class_id+"'");
			            }
			           data+="[";
			           while(rs.next())
			            {
			                
			        	   data+="{ Class_Id:\""+rs.getString("Class_Id")+"\",";
			        	   data+=" Student_Id:\""+rs.getString("Student_Id")+"\",";
			        	   data+=" S_First_Name:\""+rs.getString("S_First_Name")+"\",";
			        	   data+=" S_Last_Name:\""+rs.getString("S_Last_Name")+"\",";
			        	   data+=" DOB:\""+rs.getString("DOB")+"\",";
			        	   data+=" S_Email:\""+rs.getString("S_Email")+"\",";
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
