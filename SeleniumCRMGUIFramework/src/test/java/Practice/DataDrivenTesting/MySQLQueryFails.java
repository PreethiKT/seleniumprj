package Practice.DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class MySQLQueryFails {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
	    conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		System.out.println("Connected to database");
		Statement stat = conn.createStatement();
		ResultSet r = stat.executeQuery("select * from project");
		while(r.next())
		{
		System.out.println(r.getString(1));
		}
		}
		catch(Exception e)
		{
			System.out.println("handle Exception");
		}finally {
	
		conn.close();
		System.out.println("=====close====");
		

	}

}
}
