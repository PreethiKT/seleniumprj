package Practice.DataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBackToExcelTest {

	public static void main(String[] args) throws Throwable {
		//Read mode object in physical File
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\TestScript1.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh= wb.getSheet("org");
		Row row = sh.getRow(1);
	    Cell cel = row.createCell(4);
	    cel.setCellType(CellType.STRING);//Specifing what type of datatype
	    cel.setCellValue("FAIL");//passing the value
	    //Write mode object in physical File
	    FileOutputStream fio = new FileOutputStream(".\\src\\test\\resources\\TestScript1.xlsx");
	    wb.write(fio);
	    wb.close();
	    System.out.println("=====Executed=====");
	    
	    
	    
		
		

	}

}
