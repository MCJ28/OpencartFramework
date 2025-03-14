package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders
{
	@DataProvider(name="data")
	public Object[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\testData\\ExcelForFramework.xlsx";
		
		ExcelUtility utility = new ExcelUtility(path);

		int totalRows = utility.getRowNumber("Sheet1");	
		int totalColumns = utility.getCellNumber("Sheet1", 1);
		
		Object loginData[][] = new Object[totalRows][totalColumns];
		
		for(int i=1; i<=totalRows; i++)
		{
			for(int j=0; j<=totalColumns-1; j++)
			{
				loginData[i-1][j] = utility.getCellData("Sheet1", i, j);	//[1][0]
			}
		}
		
		return loginData;
	} 
}
