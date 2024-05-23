package Practice.OrgTest;

import java.io.FileInputStream;
import java.time.Duration;
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
import org.testng.annotations.Test;

public class CreateOrgWithPhoneNumberTest {
		@Test
		public void createNewContactOrg() throws Throwable
		{
			//public void CreateOrgtest() throws Throwable {
				//Read CommonData from Properties File
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
						Sheet sh = wb.getSheet("org");
						Row row = sh.getRow(7);
						String orgName=row.getCell(2).getStringCellValue();
						String phoneNumber=row.getCell(3).getStringCellValue()+randomInt;
					
						
						
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
						driver.findElement(By.linkText("Organizations")).click();
						
						//Step 3: Click on "Create Organization" Button
						driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
						
						//Step 4: Enter all the details and create an organization
						driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
						driver.findElement(By.id("phone")).sendKeys(phoneNumber);
						
						driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
						
						//verify Header msg Excepted Result
						String actphoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
						if(actphoneNumber.equals(phoneNumber)) {
							System.out.println(phoneNumber + "is Created==PASS");
						}else
						{
							System.out.println(phoneNumber + "is not Created==FAIL");
						}
						
							
						
						//Step 5: Logout of the Application
						
						driver.findElement(By.linkText("Sign Out")).click();
						driver.quit();
	}

}
