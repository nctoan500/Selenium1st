package pom.demo2_ExeAuto.tests;

import java.io.IOException;

import org.kita.utils.ExcelLib_JXL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;
import pom.demo2_ExeAuto.webpages.GoogleHomePageObjects;
import pom.demo2_ExeAuto.webpages.SeleniumPageObjects;

public class GoogleSearchTest {

	@Test
	public void testGoogle() throws InterruptedException, BiffException, IOException {

		ExcelLib_JXL excel = new ExcelLib_JXL(".\\resources\\excel files\\test_jxl.xls");

		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("https://google.com");
		Thread.sleep(3000);

		// Creating instance of GoogleHomePage (Object for page.)
		GoogleHomePageObjects page = new GoogleHomePageObjects(driver);

		for (int row = 1; row < excel.RowCount(); row++) {
			System.out.println(excel.ReadCell(1, row));
		}

		// Search for Selenium
		page.SearchGoogle(excel.ReadCell(1, 1));
		Thread.sleep(1000);

		// CLick the Selenium Web Site link, will return the Selenium Web Site
		page.ClickSelenium();

		SeleniumPageObjects selPage = new SeleniumPageObjects(driver);
		Thread.sleep(5000);

		// Click Download tab
		selPage.ClickDownload();
		selPage.NavigateHome();

	}
}
