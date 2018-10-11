

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Student_edit")
public class Student_edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String type=null,uid=null;
	PreparedStatement ps=null;
	int check=0;
	static Connection con=null;
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pwd=response.getWriter();    // for writing on server 
		try{
			con=(Connection)new Database_Connectivity().connectfun();
		 HttpSession session=request.getSession();  
	         type=(String)session.getAttribute("utype"); 
	         uid=(String)session.getAttribute("reg_no");
	        System.out.println("User type: "+type+" regno:"+uid);
	        //response.sendRedirect("student_profile1.jsp");
		}catch(Exception e){
			System.out.println(e);
		}
		if(type.equals("student")){
			String  age=request.getParameter("age");
			String p_no=request.getParameter("p_no");
			String blood_gp=request.getParameter("blood_gp");
			String wgh=request.getParameter("wgh");
			String email=request.getParameter("email");
			try{
				ps=con.prepareStatement("update student set email=?,p_no=?,age=?,blood_gp=?,wght=? where reg_no=?");
				ps.setString(1,email);
				ps.setString(2,p_no);
				ps.setString(3,age);
				ps.setString(4,blood_gp);
				ps.setString(5,wgh);
				ps.setString(6,uid);
				check=ps.executeUpdate();
				if(check>0){
					pwd.println("Updation is successfull");
				}
				else{
					pwd.println("OOP there is problem");
				}
			}catch(Exception e){
					System.out.println(e);
				}
		}
		if(type.equals("doctor")){
			String p_no=request.getParameter("p_no");
			String email=request.getParameter("email");
			try{
				ps=con.prepareStatement("update doctor set email=?,p_no=? where dr_id=?");
				ps.setString(1,email);
				ps.setString(2,p_no);
				ps.setString(3,uid);
				check=ps.executeUpdate();
				if(check>0){
					pwd.println("Updation is successfull");
				}
				else{
					pwd.println("OOP there is problem");
				}
			}catch(Exception e){
					System.out.println(e);
				}
		}
		
	}
}
