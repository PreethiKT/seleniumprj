package Practice.Testng;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class
SampleTestForScreenShot {
	
	@Test
	public void amazonTest() throws Throwable
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://amazon.in");
		
		//Step-1 Create an object to EventFiring Webdriver
		EventFiringWebDriver edriver  = new EventFiringWebDriver(driver);
		
		//Step-2 use getscreenshotAs method to get file type of screenshot
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		
		//Step -3 Store screenShot in Local drive
		FileUtils.copyFile(srcFile,new File("./ScreenShot/test.png"));
		
		
	}

}
