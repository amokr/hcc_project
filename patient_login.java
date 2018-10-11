

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/patient_login")
public class patient_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String type=null,uid=null,parent_n=null;
	Connection con =null;
	int check=0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter pwd=response.getWriter();    // for writing on server 
		 HttpSession session=request.getSession();  
	         uid=(String)session.getAttribute("reg_no");
	       // System.out.println("User type: "+type+" regno:"+uid);
			String d_type=request.getParameter("d_type");
			System.out.println(d_type);
			String a_day=request.getParameter("a_day");
			//System.out.println(a_day);
			String email=request.getParameter("email");
			String p_no=request.getParameter("p_no");
			String parent_fn=request.getParameter("parent_fn");
			String parent_ln=request.getParameter("parent_ln");
			String parent_no=request.getParameter("parent_no");
			parent_n=(parent_fn+" "+parent_ln);
		try{
			Connection con=(Connection)new Database_Connectivity().connectfun();
			PreparedStatement ps=null;
			ps=con.prepareStatement("select distinct days from dr_appointment_day where dr_id in(select dr_id from doctor where specifn=?)");
			ps.setString(1, d_type);
			ResultSet rt = ps.executeQuery();
			if(rt.next()){
				System.out.println("Hello error");
				session.setAttribute("error", "Sorry no any doctor avilabe on this day");
				response.sendRedirect("Patientlogin.jsp");
			}
			//Inserting the info into appointment table
			else{
				ps=con.prepareStatement("insert into appointment values(?,?,?,?,?,?,?)");
				ps.setString(1,uid);
				ps.setString(2,d_type);
				ps.setString(3,a_day);
				ps.setString(4,email);
				ps.setString(5,p_no);
				ps.setString(6,parent_n);
				ps.setString(7,parent_no);
				check=ps.executeUpdate();
			if(check>0)
			{
				//display a success message 
				session.setAttribute("msg","Your appontment accepted");
				response.sendRedirect("successpage.jsp"); 
			}
			else{
				session.setAttribute("msg","Oops try agin");
				response.sendRedirect("Patientlogin.jsp");
			}
		}
	}catch(Exception e){
		System.out.println(e);
	}
		//System.out.println("hello");
  }
}