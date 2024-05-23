package Practice.Testng;

import org.testng.annotations.Test;

public class SampleTest {
	
	@Test
	public void createContactTest()
	{
		System.out.println("Execute login");
		System.out.println("Execute navigate to contact");
		System.out.println("Execute create contact");
		System.out.println("Execute Verify Contact");
		System.out.println("Execute create logout");
	}

	@Test
	public void createContactWithMobileNumberTest()
	{
		System.out.println("Execute createContactWithMobileNumberTest");
	}
}
