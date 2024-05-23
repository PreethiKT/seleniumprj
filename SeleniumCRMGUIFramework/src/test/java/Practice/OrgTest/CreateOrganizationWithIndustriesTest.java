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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustriesTest {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER =prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME =prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");
		
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		
		//Read Test Script Data From Excel
		FileInputStream fisExcel = new FileInputStream(".\\src\\test\\resources\\TestScript1.xlsx");
		Workbook wb = WorkbookFactory.create(fisExcel);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(4);
		String orgName=row.getCell(2).toString();
		String industry=row.getCell(3).toString();
		String type=row.getCell(4).toString();
		wb.close();
		
		
		//Launch Browser - using Common Data from Properties File
		
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
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
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
		WebElement wbsele = driver.findElement(By.name("industry"));
		Select sell = new Select(wbsele);
		sell.selectByVisibleText(industry);
		
		WebElement wbsele2 = driver.findElement(By.name("accounttype"));
		Select sell2 = new Select(wbsele2);
		sell2.selectByVisibleText(type);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		//verify the industries and type info
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustries.equals(industry)) {
			System.out.println(industry + " information is Verified ==PASS");
		}else
		{
			System.out.println(industry + " information is Verified ==FAIL");
		}
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.equals(type)) {
			System.out.println(type + " information is Verified ==PASS");
		}else
		{
			System.out.println(type + " information is Verified ==FAIL");
		}
		
		//Step 5: Logout of the Application
		driver.quit();
	}




	}


