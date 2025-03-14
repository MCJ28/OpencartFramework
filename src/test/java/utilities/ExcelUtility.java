package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	public FileInputStream fis;
	public XSSFWorkbook book;
	public XSSFSheet sheet;
	public int rows;
	public String path;
	public int columns;
	public XSSFRow row;
	public XSSFCell cell;
	public String Data;
	
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}

	public int getRowNumber(String sheetName) throws IOException
	{
		fis = new FileInputStream(path);
		book = new XSSFWorkbook(fis);
		sheet = book.getSheet(sheetName);
		rows = sheet.getLastRowNum();
		
		book.close();
		fis.close();
		return rows;	
	}
	
	public int getCellNumber(String sheetName, int row_Number) throws IOException
	{
		fis = new FileInputStream(path);
		book = new XSSFWorkbook(fis);
		sheet = book.getSheet(sheetName);
		columns = sheet.getRow(row_Number).getLastCellNum();
		
		book.close();
		fis.close();
		return columns;
	}
	
	public String getCellData(String sheetName, int row_Number, int cell_Number) throws IOException
	{
		fis = new FileInputStream(path);
		book = new XSSFWorkbook(fis);
		sheet = book.getSheet(sheetName);
		row = sheet.getRow(row_Number);
		cell = row.getCell(cell_Number);
		
		try
		{
			DataFormatter formatter = new DataFormatter();
			Data = formatter.formatCellValue(cell);
		}
		
		catch (Exception e)
		{
			Data="";
		}
		
		book.close();
		fis.close();
		return Data; 
	}
}
