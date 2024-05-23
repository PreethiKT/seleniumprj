package Practice.DataDrivenTesting;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnConditionTest {

	public static void main(String[] args) throws Throwable {
		String expectedTestData = "tc_100";
		boolean flag = false;
		String data1 = "";
		String data2 = "";
		String data3 = "";
		
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\TestScript1.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh= wb.getSheet("org");
		int rowCount = sh.getLastRowNum();
		
		for(int i=0; i<rowCount;i++)
		{
			String data ="";
			try {
		 data =sh.getRow(i).getCell(0).getStringCellValue();
		 if(data.equals(expectedTestData))
				 {
			 flag = true;
			 data1 =sh.getRow(i).getCell(1).getStringCellValue();
		     data2 =sh.getRow(i).getCell(2).getStringCellValue();
             data3 =sh.getRow(i).getCell(3).getStringCellValue();
		 }
		}
		catch(Exception e) {}
		//System.out.println(data);
		}
		if(flag == true) {
		
			System.out.println(data1);
		System.out.println(data2);
		System.out.println(data3);
		}
		else
		{
			System.out.println(expectedTestData + " is not avaible");
		}

		wb.close();


	}

}
