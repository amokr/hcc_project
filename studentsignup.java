
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
import javax.servlet.http.HttpSession;

@WebServlet("/studentsignup")
public class studentsignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /*public studentsignup() {
        super();
        // TODO Auto-generated constructor stub
    }*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter pw=response.getWriter();
		//reading all the info form signup page
		int check=0;
		String reg_no = request.getParameter("reg_no");
		String pwd=request.getParameter("pwd");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String  age=request.getParameter("age");
		String gender=request.getParameter("gender");
		String h_block=request.getParameter("h_block");
		String p_no=request.getParameter("p_no");
		String blood_gp=request.getParameter("blood_gp");
		String wgh=request.getParameter("wgh");
		String email=request.getParameter("email");
	// geating database connection
	try{
		Connection con=(Connection)new Database_Connectivity().connectfun();
		PreparedStatement ps=null;
		//for checking the existing of registration no
		ps=con.prepareStatement("select *from student where reg_no=?");
		ps.setString(1,reg_no);
		ResultSet rt=ps.executeQuery();
		if(rt.next()){
			check=-1;
		}
		else{
			ps=con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,reg_no);
			ps.setString(2,fname);
			ps.setString(3,lname);
			ps.setString(4,email);
			ps.setString(7, gender);
			ps.setString(10, blood_gp);
			ps.setString(9, p_no);
			ps.setString(5,pwd);
			ps.setString(6,age);
			ps.setString(8,h_block);
			ps.setString(11, wgh);
			check=ps.executeUpdate();
		}
		if(check>0)
		{
			// How i display a success message 
			HttpSession session=request.getSession();  
			session.setAttribute("reg_no",reg_no);
			pw.write("<h1>Registration successful</h1>");
			response.sendRedirect("student_profile1.jsp"); 
		}
		else if(check==-1)
		{
			pw.write("<h1>You are already registered</h1>");
			response.sendRedirect("Home_page.jsp");
		}
		else{
			pw.write("<h1>oop server probleam");
			response.sendRedirect("studentlogin.html");
		}
	}catch(Exception e){
		System.out.println(e);
	}
 }
}
