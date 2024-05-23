package Practice.DataDrivenTesting;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataTest {

	public static void main(String[] args) throws Throwable {
		
	FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\TestScript1.xlsx");
	Workbook wb = WorkbookFactory.create(fi);
	String value = wb.getSheet("org").getRow(1).getCell(0).getStringCellValue();
	System.out.println(value);
	
	
	}

}
