package pom.demo2.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import pom.demo2.page.GoogleHomePageObjects;
import pom.demo2.page.SeleniumPageObjects;

public class GoogleSearchTest {

	WebDriver driver = new FirefoxDriver();

	@Test
	public void testGoogle() throws InterruptedException {
		driver.navigate().to("https://google.com");

		// Object for page
		GoogleHomePageObjects page = new GoogleHomePageObjects(driver);

		// Search for Selenium
		page.SearchGoogle("Selenium");
		Thread.sleep(3000);

		// CLick the Selenium Web Site link, will return the Selenium Web Site
		SeleniumPageObjects selPage = page.ClickSelenium();

		// Wait for the Page to load
		Thread.sleep(3000);

		// Click Dwownload tab
		selPage.ClickDownload();
		selPage.NavigateHome();

	}
}
