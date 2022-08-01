import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showdata")
public class ShowUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String query = "select id,name,email,mobile,dob,city,gender from user";
	public static Map<String,String[]> db = new HashMap<>(); 
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 PrintWriter pw = res.getWriter();
		 res.setContentType("text/html");
		 pw.println("<link rel='stylesheet' href='css/bootstrap.css' />");
		 pw.println("<style>.bg-itadori {"
		 		+ "	background: url(\"https://www.xtrafondos.com/en/descargar.php?id=7461&resolucion=1920x1080\");"
		 		+ "	height:100vh;"
		 		+ "}"
		 		+ ""
		 		+ ".glassmorp {"
		 		+ " color:white;"
		 		+"background: rgba( 255, 255, 255, 0.1 );"
		 		+"box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );"
		 		+"backdrop-filter: blur( 12.5px );"
		 		+"-webkit-backdrop-filter: blur( 12.5px );"
		 		+"border-radius: 10px;"
		 		+"border: 1px solid rgba( 255, 255, 255, 0.18 );</style>");
		 
		 
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 try(Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemgmt","root","passwordtoor");
				 PreparedStatement ps = con.prepareStatement(query);){
			 ResultSet rs = ps.executeQuery(query);
			 pw.println("<div class='bg-itadori' style='margin:auto;'>") ;
			 pw.println("<table class='table glassmorp table-striped text-white'>");
				 pw.println("<tr>");
				 	 pw.println("<th>ID</th>");
					 pw.println("<th>Name</th>");
					 pw.println("<th>Email</th>");
					 pw.println("<th>Mobile</th>");
					 pw.println("<th>DOB</th>");
					 pw.println("<th>City</th>");
					 pw.println("<th>Gender</th>");
					 pw.println("<th>Edit</th>");
					 pw.println("<th>Delete</th>");
					 pw.println("<br />");
				 pw.println("</tr>");
				 while(rs.next()) {
					 pw.println("<tr>");
						 pw.println("<td>"+rs.getInt(1)+"</td>");
						 pw.println("<td>"+rs.getString(2)+"</td>");
						 pw.println("<td>"+rs.getString(3)+"</td>");
						 pw.println("<td>"+rs.getString(4)+"</td>");
						 pw.println("<td>"+rs.getString(5)+"</td>");
						 pw.println("<td>"+rs.getString(6)+"</td>");
						 pw.println("<td>"+rs.getString(7)+"</td>");
						 pw.println("<td><a href='editurl?id="+rs.getInt(1)+"' />"
						 		+ "<img style='width:20px;height:20px;filter:invert(1)' src='https://cdn-icons-png.flaticon.com/512/84/84380.png' />"
						 		+ "</a></td>");
						 pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"' />"
						 		+ "<img style='width:20px;height:20px;filter:invert(1)' src='https://cdn-icons-png.flaticon.com/512/1345/1345874.png' />"
						 		+ "</a></td>");
						 pw.println("<br />");
					 pw.println("</tr>");
					 
				 }
			 pw.println("</table>");
		
		 }catch(SQLException se) {
			 pw.println("<h2 class='bg-danger text-light text-center' >"+se.getMessage()+"</h2>");
			 se.printStackTrace();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 pw.println("<a href='home.html'><button class='btn btn-outline-success'> Home </button> </a>");
		 pw.print("</div>");
		 pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		
	} 
}
