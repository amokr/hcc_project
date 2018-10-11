

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class docotor_registration
 */
@WebServlet("/docotor_registration")
public class docotor_registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con= null;
	String reg_no=null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//reading data form page
		int check;
		String uid=request.getParameter("uid");
		String pwd=request.getParameter("pwd");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String month=request.getParameter("month");
		String day=request.getParameter("day");
		String year=request.getParameter("year");
		String gender=request.getParameter("gender");
		String spec=request.getParameter("spec");
		String p_no=request.getParameter("p_no");
		String yoj=request.getParameter("yoj");
		String email=request.getParameter("email");
		String nod=request.getParameter("nod");
		String dob;
		/*System.out.println(uid);
		System.out.println(pwd);
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(gender);
		System.out.println(spec);
		System.out.println(p_no);
		System.out.println(yoj);
		System.out.println(email);
		System.out.println(nod);*/
		if(month==null){
			dob=null;
		}
		else{
			dob=(month+"-"+day+"-"+year);
		}
		//System.out.println(dob);
		//creating connection with database
		try{
			Connection con=(Connection)new Database_Connectivity().connectfun();
			PreparedStatement ps=null;
			//for checking the existing of registration no
			ps=con.prepareStatement("select *from doctor where dr_id=?");
			ps.setString(1,uid);
			ResultSet rt=ps.executeQuery();
			if(rt.next()){
				check=-1;
			}
			else{
				ps=con.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1,uid);
				ps.setString(2,fname);
				ps.setString(3,lname);
				ps.setString(4,dob);
				ps.setString(5,spec);
				ps.setString(6,gender);
				ps.setString(7,email);
				ps.setString(8,p_no);
				ps.setString(9,yoj);
				ps.setString(10,nod);
				ps.setString(11,pwd);
				check=ps.executeUpdate();
			}
			if(check>0)
			{
				// How i display a success message 
				HttpSession session=request.getSession();  
				session.setAttribute("reg_no",uid);
				response.sendRedirect("doctor_profile.jsp"); 
			}
			else if(check==-1)
			{
				// hoe i display you are all ready register
				response.sendRedirect("Home_page.jsp");
			}
			else{
				response.sendRedirect("doctor_registraiton.jsp");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
