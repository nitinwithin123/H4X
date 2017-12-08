



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


@WebServlet("/GetChartByTeacher")
public class GetDataByTeacher extends HttpServlet {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7314508769364810954L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		//response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String class_id=request.getParameter("classId");
			String section=request.getParameter("Section");
			String data="";
			
		
			         try{
			        	 
			            Connection con=MyConnection.getConnection();
			            Statement st=con.createStatement();
			            ResultSet rs;
			            if(class_id.equals("ALL"))
			            {
			            	 rs=st.executeQuery("select * from Teacher");
			            }else{
			            	
			            	//rs=st.executeQuery("select * from Teacher where Class_Id='"+class_id+"'");
			            	rs=st.executeQuery("select Teacher.Teacher_Id,Teacher.T_First_Name,Teacher.Section, Teacher.Class_Id,Teacher.T_Last_Name,Teacher.T_Email from Teacher,Subject where Teacher.Teacher_Id=Subject.Teacher_Id and Subject.Class_Id='"+class_id+"'");
			            }
			           data+="[";
			           while(rs.next())
			            {
			                
			        	   data+="{ Teacher_Id:\""+rs.getString("Teacher_Id")+"\",";
			        	   data+=" Section:\""+rs.getString("Section")+"\",";
			        	   data+=" Class_Id:\""+rs.getString("Class_Id")+"\",";
			        	   data+=" T_First_Name:\""+rs.getString("T_First_Name")+"\",";
			        	   data+=" T_Last_Name:\""+rs.getString("T_Last_Name")+"\",";
			        	   data+=" T_Email :\""+rs.getString("T_Email")+"\",},";
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
