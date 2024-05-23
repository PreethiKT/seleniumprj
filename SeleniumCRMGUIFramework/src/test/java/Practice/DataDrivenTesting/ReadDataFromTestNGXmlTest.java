package Practice.DataDrivenTesting;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromTestNGXmlTest {

	@Test
	public void sampleTest(XmlTest test) {
		System.out.println("execute sample test");
		System.out.println(test.getParameter("Browser"));
		System.out.println(test.getParameter("Url"));
		System.out.println(test.getParameter("Username"));
		System.out.println(test.getParameter("Password"));

		
	}

	}


