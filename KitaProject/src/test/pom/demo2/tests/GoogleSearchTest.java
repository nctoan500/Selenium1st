package pom.demo2.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import pom.demo2.webpages.GoogleHomePageObjects;
import pom.demo2.webpages.SeleniumPageObjects;

public class GoogleSearchTest {

	WebDriver driver = new FirefoxDriver();

	@Test
	public void testGoogle() throws InterruptedException {
		driver.navigate().to("https://google.com");

		// Object for page
		// Creating instance of GoogleHomePage
		GoogleHomePageObjects page = new GoogleHomePageObjects(driver);

		// Search for Selenium
		page.SearchGoogle("Selenium");
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
