



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/GetChartByMAC")
public class GetChartByMAC extends HttpServlet {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7314508769364810954L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
		//response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String mac_address=request.getParameter("macAddress");
			String DateSearch=request.getParameter("date");
			String StartTime=request.getParameter("s_time");
			String EndTime=request.getParameter("e_time");
			String data="";
			
		
			         try{
			        	 
			            Connection con=MyConnection.getConnection();
			            Statement st=con.createStatement();
			            ResultSet rs;
			            if(mac_address.equals("ALL"))
			            {
			            	 rs=st.executeQuery("select * from health_status");
			            }else{
			            	
			            	rs=st.executeQuery("select * from health_status where mac='"+mac_address+"' and date='"+DateSearch+"' and time between '"+StartTime+"' and '"+EndTime+"' ");
			            }  
			          
			           data+="[";
			           while(rs.next())
			            {
			                
			        	   data+="{ date:\""+rs.getString("date")+"\",";
			        	   data+=" mac:\""+rs.getString("mac")+"\",";
			        	   data+=" CPU:"+rs.getString("cpu_usage")+",";
			        	   data+=" ram:"+rs.getString("ram")+",";
			        	   data+=" GPU:"+rs.getString("gpu_usage")+",";
			        	   data+=" thermal:"+rs.getString("thermal")+",";
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