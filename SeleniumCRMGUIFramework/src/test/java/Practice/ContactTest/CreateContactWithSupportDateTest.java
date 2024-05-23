package Practice.ContactTest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER =prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME =prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");
		
		//Generate the random Number
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		
		//Read Test Script Data From Excel
		FileInputStream fisExcel = new FileInputStream(".\\src\\test\\resources\\TestScript1.xlsx");
		Workbook wb = WorkbookFactory.create(fisExcel);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(4);
		String lastName =row.getCell(2).toString()+randomInt;
	
		
		
		//Launch Browser - using Common Data from Properties File
		
		//WebDriver driver= new ChromeDriver();
		
		WebDriver driver = null;
		if(BROWSER.equals("edge"))
		{
		driver = new EdgeDriver();
}
		else if(BROWSER.equals("Chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
	    {
		driver = new ChromeDriver();
		}
		
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Step 1: Login to app
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//Step 2: Navigate to Organization Module
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 3: Click on "Create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 4: Enter all the details and create an organization
		//driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
        Date dateobj = new Date();
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sim.format(dateobj);
	//	System.out.println(actDate);
		
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String endDate = sim.format(cal.getTime());
	//	System.out.println(dateRequires);
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		//verify Header orgName info
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.equals(startDate)) {
			System.out.println(startDate + " information for startdate is Verified  ==PASS");
		}else
		{
			System.out.println(startDate + " information for startdate is not Verified ==FAIL");
		}	
		String actendtDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actendtDate.equals(endDate)) {
			System.out.println(endDate + " information for enddate is Verified  ==PASS");
		}else
		{
			System.out.println(endDate + " information for enddate is not Verified ==FAIL");
		}	
		//Step 5: Logout of the Application
		driver.quit();
	}

}
