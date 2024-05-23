package Practice.DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

//import com.mysql.cj.jdbc.Driver;

public class JDBCTestSelectQuery {

	public static void main(String[] args) throws SQLException, Throwable
	{
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		System.out.println("Connected to database");
		Statement stat = conn.createStatement();
		ResultSet r = stat.executeQuery("select * from project");
		while(r.next())
		{
		System.out.println(r.getString(1));
		}
		conn.close();
		
		

	}

}
