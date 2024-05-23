package Practice.DataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class MultipleDataExcel {

	public static void main(String[] args) throws Throwable 
	{
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\TestScript1.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh= wb.getSheet("Sheet2");
		for(int i=1; i<30;i++)
		{
			Row row = sh.getRow(i);
		String columnData1 = row.getCell(0).getStringCellValue();
		String columnData2 = row.getCell(1).getStringCellValue();
		System.out.println(columnData1 + "\t"+columnData2 );
		}
		wb.close();

	}

}
