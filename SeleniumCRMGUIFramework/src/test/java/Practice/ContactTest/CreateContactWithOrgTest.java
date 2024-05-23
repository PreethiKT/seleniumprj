package Practice.ContactTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithOrgTest {

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
		Row row = sh.getRow(7);
		String orgName=row.getCell(2).toString()+randomInt;
		String contactLastName =row.getCell(2).toString()+randomInt;
	
		
		
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
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.name("button")).click();
		
		//verify Header msg Excepted Result
		String headerInfo = driver.findElement(By.xpath("//span[@class=\'dvHeaderText\']")).getText();
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName + "is Created==PASS");
		}else
		{
			System.out.println(orgName + "is not Created==FAIL");
		}
		//Step 5: Navigate to Organization Module
				driver.findElement(By.linkText("Contacts")).click();
				String parentID=driver.getWindowHandle();
				//Step 6: Click on "Create Organization" Button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				//Step 7: Enter all the details and create an organization
			//	driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(contactLastName);
				driver.findElement(By.name("lastname")).sendKeys(contactLastName);
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
				//switch to child window
				Set<String> set = driver.getWindowHandles();
				Iterator<String> it = set.iterator();
				while(it.hasNext())
				{
					String windowID = it.next();
					driver.switchTo().window(windowID);
					
					String actUrl = driver.getCurrentUrl();
					if(actUrl.contains("module=Accounts")) {
						break;
					}
				}
				driver.findElement(By.name("search_text")).sendKeys(orgName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//dynamic Xpath
				
				//switch to parent Window
//				Set<String> set1 = driver.getWindowHandles();
//				Iterator<String> it1 = set1.iterator();
//				while(it1.hasNext()) 
//				{
//					String windowID = it1.next();
//					driver.switchTo().window(windowID);
//					
//					String actUrl1 = driver.getCurrentUrl();
//					if(actUrl1.contains("Contacts&action")) {
//						break;
//					}
//				}	
				
				
				driver.switchTo().window(parentID);
				driver.findElement(By.name("button")).click();
				
				//verify Header msg Excepted Result
			    headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(contactLastName)) {
					System.out.println(contactLastName + "is Created==PASS");
				}else
				{
					System.out.println(contactLastName + "is not Created==FAIL");
				}
				//verify Header orgName info
				String actorgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				if(actorgName.trim().equals(orgName)) {
					System.out.println(orgName + "is Created==PASS");
				}else
				{
					System.out.println(orgName + "is not Created==FAIL");
				}
				
		
		driver.quit();

	}

}
