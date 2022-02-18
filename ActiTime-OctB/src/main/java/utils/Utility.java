package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility
{
	public static String getDataFromExcel(String sheet, int row, int col) throws EncryptedDocumentException, IOException {
		String path = "C:\\Users\\Ashwini\\OneDrive\\Desktop\\velocity.xlsx";
		FileInputStream file = new FileInputStream(path) ;
		String data = WorkbookFactory.create(file).getSheet(sheet).getRow(row).getCell(col).getStringCellValue();
		return data;
		
	}
	
	public static void getScreenshot(WebDriver driver, int testID) throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd hh_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\Ashwini\\Velocity\\Test123"+testID+" "+dtf.format(now)+".jpg");
		FileHandler.copy(src, dest);
		
		
		
	}
	
	

}
