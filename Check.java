

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.*;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hllo");
		PrintWriter out=response.getWriter();
		out.println("hello");
		/*pw.print("<scipt>alert('hello')<script/>");
		String registration = request.getParameter("regno");
		String pwd=request.getParameter("pass");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		int  age=(Integer.parseInt(request.getParameter("age")));
		String gender=request.getParameter("gen");
		String h_block=request.getParameter("block");
		String phno=request.getParameter("phno");
		/*System.out.println(fname);
		System.out.println(lname);
		System.out.println(registration);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(h_block);
		System.out.println(phno);*/
		/*Student_login s=new Student_login(registration,fname,lname,age,gender,h_block,phno,pwd);
		int k=s.register();
		if(k>0)
		{
			pw.write("<h1>Registration successful</h1>");
		}
		else if(k==-1)
		{
			pw.write("<h1>You are already registered</h1>");

		}
		else{
			pw.write("<h1>oop server probleam");
		}*/
	}

}

class Student_login {
	String regist,fnam,lnam,gende,bloc,phn,pass;
	int ag,k;
	public Student_login(String registration,String fname,String lname,int age,String gender,String block,String phno,String pwd)
	{
		regist= registration;
		pass=pwd;
		fnam=fname;
		 lnam=lname;
		 ag=age;
		 gende=gender;
		 bloc=block;
		 phn=phno;
	}
	public int register()
	{
		PreparedStatement ps=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/hcc_database","Hcc_Admin","162049");
			ps=con.prepareStatement("select *from student where reg_no=?");
			ps.setString(1, regist);
			ResultSet rt=ps.executeQuery();
			if(rt.next()){
				k=-1;
			}
			else{
			ps=con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?)");
			ps.setString(1, regist);
			ps.setString(2, fnam);
			ps.setString(3, lnam);
			ps.setInt(4, ag);
			ps.setString(5, gende);
			ps.setString(6, bloc);
			ps.setString(7, phn);
			ps.setString(8,pass);
			k=ps.executeUpdate();
			}
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	return k;	
	}
}