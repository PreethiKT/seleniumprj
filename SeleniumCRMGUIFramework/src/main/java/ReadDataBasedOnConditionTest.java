import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnConditionTest {

	public static void main(String[] args) throws Throwable {
		String ExpectedData ="tc_03";
		String data1 ="";
		String data2="";
		String data3="";
		boolean flag = false;
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Shiny Ingrid C\\OneDrive\\Desktop\\Test.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet("org");
		
		int lrn = sheet.getLastRowNum();
		for(int i=0;i<=lrn;i++)
		{
			String data ="";
			try
			{
			 //String data = sheet.getRow(i).getCell(0).toString(); // data is local. So we need to declare it globally
				data = sheet.getRow(i).getCell(0).toString();
				if(data.equals(ExpectedData))
				{
					flag = true;
					data1=sheet.getRow(i).getCell(0).toString();
					data2=sheet.getRow(i).getCell(1).toString();
					data3=sheet.getRow(i).getCell(2).toString();
				}
			}
			catch (Exception e) {}
		}
		
		
		if(flag==true)
		{
			System.out.println(data1+"\t"+data2+"\t"+data3);
			//System.out.println(data2);
			//System.out.println(data3);
		}
		else
		{
			System.out.println(ExpectedData+" is not present");
		}

	}

}
