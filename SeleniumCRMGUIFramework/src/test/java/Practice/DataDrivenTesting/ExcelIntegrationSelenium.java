package Practice.DataDrivenTesting;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ExcelIntegrationSelenium {

	public static void main(String[] args) throws Throwable
	{
		//Read CommonData from Properties File
				FileInputStream fis = new FileInputStream(".\\\\src\\\\test\\\\resources\\\\CommonData.properties");
				Properties prop = new Properties();
				prop.load(fis);
				String BROWSER =prop.getProperty("browser");
				String URL = prop.getProperty("url");
				String USERNAME =prop.getProperty("username");
				String PASSWORD = prop.getProperty("password");
				//Read Test Script Data From Excel
				FileInputStream fisExcel = new FileInputStream(".\\src\\test\\resources\\TestScript1.xlsx");
				Workbook book = WorkbookFactory.create(fisExcel);
				Sheet sheet = book.getSheet("org");
				Row row = sheet.getRow(1);
				String orgName=row.getCell(2).toString();
			//Launch Browser - using Common Data from Properties File
				WebDriver driver = null;
				if(BROWSER.equals("edge"))
				{
					driver = new EdgeDriver();
				}
				else if(BROWSER.equals("chrome"))
				{
					driver = new ChromeDriver();
				}
				else if(BROWSER.equals("firefox"))
				{
					driver = new FirefoxDriver();
				}
				else
				{
					driver = new EdgeDriver();
				}
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
				
				//Step 1: Login to app
				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				//Step 2: Navigate to Organization Module
				driver.findElement(By.linkText("Organizations")).click();
				
				//Step 3: Click on "Create Organization" Button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//Step 4: Enter all the details and create an organization
				driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
				
				//Step 5: Logout of the Application
				Actions act = new Actions(driver);
				Thread.sleep(2000);
		      act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
//				//driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
//				driver.findElement(By.linkText("Sign Out")).click();

				//act.moveToElement(driver.findElement(By.xpath("//td[@onmouseout=\"fnHideDrop('ondemand_sub');\"]"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
	}

}
