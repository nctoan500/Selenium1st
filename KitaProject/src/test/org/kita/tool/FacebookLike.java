package org.kita.tool;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.kita.utils.ExcelLib_JXL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;

public class FacebookLike {
	public static final String FACEBOOK_FRIEND_LIST = "Resources\\Excel\\Facebook Friend List.xls";

	static String rowNo;
	static ExcelLib_JXL excelLib;

	WebDriver driver = new FirefoxDriver();

	public FacebookLike() throws BiffException, IOException {
		// Read from excel file
		excelLib = new ExcelLib_JXL(FACEBOOK_FRIEND_LIST);
		excelLib.ColumnDictionary();
	}

	@Test
	public void LikePersonal() throws InterruptedException, IOException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");

		if (driver.findElements(By.id("email")).size() != 0) {
			driver.findElement(By.id("email")).sendKeys("nctoan500@gmail.com");
			driver.findElement(By.id("pass")).sendKeys("Toan@9492...");
			driver.findElement(By.id("u_0_l")).click();
		}
		
		// Loop product list
		for (int rowCnt = 1; rowCnt < excelLib.RowCount(); rowCnt++) {
			String url = excelLib.ReadCell(excelLib.GetCell("URL"), rowCnt);
			String no = excelLib.ReadCell(excelLib.GetCell("No"), rowCnt);
			driver.get(url);
			System.out.println(no + ". " + url);
			
			if (driver.findElements(By.className("UIFullPage_Container")).size() != 0) {
				continue;
			}
			
			Thread.sleep(2000);
			
			WebElement element = driver.findElement(By.xpath(".//*/div[1]/div/div/div/div/div/span[1]/div/a[contains(@class, 'UFILikeLink')]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;

			if (element.getAttribute("aria-pressed").equals("false")) {
				executor.executeScript("arguments[0].click()", element);
			}

			Thread.sleep(3000);
		}
		
		driver.quit();
	}
}
