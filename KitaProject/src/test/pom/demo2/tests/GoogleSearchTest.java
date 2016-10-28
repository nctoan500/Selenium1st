package pom.demo2.tests;

import org.kita.utils.ExcelLib_JXL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import pom.demo2.webpages.GoogleHomePageObjects;
import pom.demo2.webpages.SeleniumPageObjects;

public class GoogleSearchTest {

	WebDriver driver = new FirefoxDriver();

	@Test
	public void testGoogle() throws InterruptedException {
		ExcelLib_JXL excel = new ExcelLib_JXL(".\\resources\\excel files\\test_jxl.xls");
		System.out.println(excel.GetCellValue(0, 1));

		driver.navigate().to("https://google.com");

		// Object for page
		// Creating instance of GoogleHomePage
		GoogleHomePageObjects page = new GoogleHomePageObjects(driver);

		// Search for Selenium
		page.SearchGoogle(excel.GetCellValue(0, 2));
		Thread.sleep(3000);

		// CLick the Selenium Web Site link, will return the Selenium Web Site
		page.ClickSelenium();

		SeleniumPageObjects selPage = new SeleniumPageObjects(driver);
		// Wait for the Page to load
		Thread.sleep(5000);

		// Click Download tab
		selPage.ClickDownload();
		selPage.NavigateHome();

	}
}
