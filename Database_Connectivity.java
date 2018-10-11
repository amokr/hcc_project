
import java.sql.Connection;
import java.sql.DriverManager;

public class Database_Connectivity {
	static Connection con;
 public static Object connectfun(){
	 try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/hcc_database","Hcc_Admin","162049");
		}catch(Exception e){
			System.out.println(e);
		}
	 return con;
 }
	public static void main(String[] args) {
	}
}
