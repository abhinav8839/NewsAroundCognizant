package Utilites;

import org.apache.poi.xssf.usermodel.*;
 
import java.io.*;
 
import java.util.List;

	public class ExcelUtility {	
		public static String path;
		public static FileOutputStream file;
		public static XSSFWorkbook wb;
		public static XSSFSheet s;
	
	public static void excelInit() throws FileNotFoundException
	
	{
	
		ExcelUtility.path = System.getProperty("user.dir")+"\\TestData\\Details.xlsx";
		ExcelUtility.file = new FileOutputStream(path);
		ExcelUtility.wb = new XSSFWorkbook();
		ExcelUtility.s = ExcelUtility.wb.createSheet("Sheet1");
	 
	}

	public static void saveDataToExcel(List<String> news, List<String> firstNews, List<String> secondNews) throws FileNotFoundException {

	ExcelUtility.excelInit();
	XSSFRow row1 = ExcelUtility.s.createRow(0); 
	row1.createCell(1).setCellValue("News");		

	for(int r=1;r<=5;r++) { 
		XSSFRow row = ExcelUtility.s.createRow(r); 
		row.createCell(1).setCellValue(news.get(r-1)); 
	}

	XSSFRow row2 = ExcelUtility.s.createRow(8); 
	row2.createCell(1).setCellValue("FirstPage News");

	for(int r=9;r<14;r++) {
		XSSFRow row = ExcelUtility.s.createRow(r);
		row.createCell(0).setCellValue(firstNews.get(r-9));
	}

	XSSFRow row3 = ExcelUtility.s.createRow(15);
	row3.createCell(0).setCellValue("SecondPage News");
	for(int r=16;r<21;r++) {
		XSSFRow row = ExcelUtility.s.createRow(r);
		row.createCell(0).setCellValue(secondNews.get(r-16));
	}

  }

	public static void closeExcel() throws IOException
	{
		ExcelUtility.wb.write(file);
		ExcelUtility.wb.close();
		ExcelUtility.file.close();
	}
 
}
