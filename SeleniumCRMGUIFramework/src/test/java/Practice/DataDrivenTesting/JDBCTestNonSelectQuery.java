package Practice.DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JDBCTestNonSelectQuery {

	public static void main(String[] args) throws Throwable  {
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		System.out.println("Connected to database");
		Statement stat = conn.createStatement();
		int r = stat.executeUpdate("insert into project values('Ty_PROJ_1123456221','Preethi','04/27/2023','Insta','On_Going','100');");
		ResultSet r1 = stat.executeQuery("select * from project");
		while(r1.next())
		{
		System.out.println(r1.getString(1)+"\t"+r1.getString(2)+"\t"+r1.getString(3));
		}
		conn.close();
		
	}}

	

