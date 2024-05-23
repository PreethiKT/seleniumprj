package Practice.DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class ValidateExpData_DBTest {

	@Test
	public void validate() throws SQLException, InterruptedException {
		
		String expectedProjectName = "TK_PROJ_2024";
		boolean flag = false;
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		System.out.println("Connected to database");
		Statement stat = conn.createStatement();
		ResultSet r = stat.executeQuery("select * from project");
		while(r.next())
		{
		
			String data=r.getString(1);
		if(data.equals(expectedProjectName))
		{
			flag=true;
			System.out.println(expectedProjectName+" is Available == PASS");
		}
		
		}
		
		if(flag == false)
		{
			System.out.println(expectedProjectName+" is Available == FAIL");
			Assert.fail();
		}
		Thread.sleep(2000);
		conn.close();

	}
}
