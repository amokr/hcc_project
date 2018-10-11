

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login_check")
public class login_check extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public login_check() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// taking user type form login page
		String type=request.getParameter("usertype");
		int k=0;
		if(type.equals("student"))
			k=1;
		else if(type.equals("doctor"))
			k=2;
		else if(type.equals("deceptionist"))
			k=3;
		else if(type.equals("pharmacist"))
			k=4;
		else if(type.equals("lab Attendant"))
			k=5;
		else if(type.equals("admin"))
			k=6;
		//taking other field of login page
		String uid=request.getParameter("uid");
		String pwd=request.getParameter("pwd");
		response.setContentType("text/html");
		try{
			PreparedStatement ps=null;
			//PrintWriter out=response.getWriter();
		//	Class.forName("com.mysql.jdbc.Driver");
			//Connection con=DriverManager.getConnection("jdbc:mysql://localhost/hcc_database","Hcc_Admin","162049");
		Connection con=(Connection)new Database_Connectivity().connectfun();
			switch(k){
			case 1:
				 ps=con.prepareStatement("Select pwd from student where reg_no=? and pwd=?");
				 ps.setString(1,uid);
				 ps.setString(2,pwd);
				 break;
			case 2:
				 ps=con.prepareStatement("Select pwd from doctor where dr_id=? and pwd=?");
				 ps.setString(1,uid);
				 ps.setString(2,pwd);
				 break;
			case 3:
				 ps=con.prepareStatement("Select pwd from receptionist where reg_no=? and pwd=?");
				 ps.setString(1,uid);
				 ps.setString(2,pwd);
				 break;
			case 4:
				 ps=con.prepareStatement("Select pwd from pharmacist where reg_no=? and pwd=?");
				 ps.setString(1,uid);
				 ps.setString(2,pwd);
				 break;
			case 5:
				 ps=con.prepareStatement("Select pwd from lab Attendant where reg_no=? and pwd=?");
				 ps.setString(1,uid);
				 ps.setString(2,pwd);
				 break;
			case 6:
				 ps=con.prepareStatement("Select pwd from admin where reg_no=? and pwd=?");
				 ps.setString(1,uid);
				 ps.setString(2,pwd);
				 break;
			default:
				 System.out.println("Error table not match or not found");
			}
			ResultSet rt = ps.executeQuery();
			if(k==1 && rt.next()){
				HttpSession session=request.getSession();  
		        session.setAttribute("reg_no",uid);
		        session.setAttribute("utype",type);// taking value of uid into reg_no
				response.sendRedirect("student_profile1.jsp"); 
			}
			else if(k==2 && rt.next()){
				HttpSession session=request.getSession();  
		        session.setAttribute("reg_no",uid);  // taking value of uid into reg_no
		        session.setAttribute("utype",type);
				response.sendRedirect("doctor_profile.jsp");
			}
			else if(k==3 && rt.next()){
				HttpSession session=request.getSession();  
		        session.setAttribute("reg_no",uid);  // taking value of uid into reg_no
				response.sendRedirect("receptionist_profile.jsp"); 
			}
			else if(k==4 && rt.next()){
				HttpSession session=request.getSession();  
		        session.setAttribute("reg_no",uid);  // taking value of uid into reg_no
				response.sendRedirect("pharmacist_profile.jsp"); 
			}
			else if(k==5 && rt.next()){
				HttpSession session=request.getSession();  
		        session.setAttribute("reg_no",uid);  // taking value of uid into reg_no
				response.sendRedirect("lab_attendant_profile.jsp"); 
			}
			else if(k==6  && rt.next()){
				HttpSession session=request.getSession();  
		        session.setAttribute("reg_no",uid);  // taking value of uid into reg_no
				response.sendRedirect("admin.jsp"); 
			}
		else{
			response.sendRedirect("Login.jsp");
			}
		}catch(Exception e)
			{
			System.out.println(e);
			}
	}
}

