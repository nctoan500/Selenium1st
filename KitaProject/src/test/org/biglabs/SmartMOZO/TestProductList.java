package org.biglabs.SmartMOZO;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.kita.utils.ExcelLib_JXL;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestProductList {

	public static final String DOMAIN = "http://192.168.1.76";
	public static final String PRODUCT_LIST_EXCEL_PATH = "Resources\\Excel\\Product List.xls";
	public static final String FOLDER_SCREENSHORT = "D:\\Smart Mozo";

	static String rowNo;
	static String fullURL;
	static String productNo;
	static String colorName;
	static String folderPath;
	static ExcelLib_JXL excelLib;

	WebDriver driver = new FirefoxDriver();

	public TestProductList() throws BiffException, IOException {
		// Read from excel file
		excelLib = new ExcelLib_JXL(PRODUCT_LIST_EXCEL_PATH);
		excelLib.ColumnDictionary();
		createFolder(FOLDER_SCREENSHORT);
	}

	@Test
	public void VerifyImageProductList() throws InterruptedException, IOException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(DOMAIN);

		// Loop product list
		for (int rowCnt = 1; rowCnt < excelLib.RowCount(); rowCnt++) {
			fullURL = DOMAIN + excelLib.ReadCell(excelLib.GetCell("URL"), rowCnt);

			// Product No
			String slipped2[] = fullURL.split("/");
			productNo = slipped2[slipped2.length - 1];

			// Open product detail
			driver.get(fullURL);
			Thread.sleep(1000);
			
			// Log
			rowNo = excelLib.ReadCell(excelLib.GetCell("No"), rowCnt);
			System.out.println(rowCnt + ". Open product URL: " + fullURL);
			Thread.sleep(1000);

			// View color options
			if (driver.findElements(By.className("mozoNotFound")).size() != 0) {
				System.out.println("[WARNING] Product is not found!");
				screenshort();
			} else {
				selectColorOption();
			}
		}
	}

	public void selectColorOption() throws InterruptedException, IOException {

		// Total colors
		int colorNo = driver.findElements(By.xpath("//li/div/ul/li[contains(@id, 'optionCOLOR')]")).size();

		for (int i = 1; i <= colorNo; i++) {
			// Locate Color
			WebElement colorOption = driver.findElement(By.xpath("//li/div/ul/li[" + i + "]"));

			// Color Name
			colorName = colorOption.getAttribute("data-product-option-value");
			String slipped1[] = colorName.split("\"");
			colorName = slipped1[slipped1.length - 4];

			// Click color
			colorOption.click();

			// Log
			Thread.sleep(1000);
			System.out.println("\tSelected color - " + i + ": " + colorName);

			// Screenshot
			screenshort(i + "-" + colorName.replace("/", ""));
		}
	}

	public void screenshort(String color) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(folderPath + "\\" + rowNo + ". No-" + productNo + "_Color-" + color + ".jpg"));
	}

	public void screenshort() throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(folderPath + "\\" + rowNo + ". No-" + productNo + "_NOT-FOUND.jpg"));
	}

	public String createFolder(String path) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
		Date date = new Date();
		String now = dateFormat.format(date);

		File dir1 = new File(path);
		if (!dir1.exists()) {
			try {
				dir1.mkdir();
			} catch (SecurityException e) {
			}
		}

		File dir2 = new File("D:\\Smart Mozo\\" + now);
		dir2.mkdir();
		folderPath = dir2.getPath();
		System.out.println(folderPath);
		return folderPath;
	}
}
