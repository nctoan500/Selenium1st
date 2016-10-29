package pom.demo1_SelenVN.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pom.demo1_SelenVN.webpages.ConfirmationMessage;
import pom.demo1_SelenVN.webpages.ContactPage;
import pom.demo1_SelenVN.webpages.HomePage;

public class TestDemo {
	WebDriver driver;
	HomePage homePage;
	ContactPage contactPage;
	ConfirmationMessage confirmMessage;

	@BeforeMethod
	public void setUp() {
		driver = new FirefoxDriver();
		homePage = new HomePage(driver);
		contactPage = new ContactPage(driver);
		confirmMessage = new ConfirmationMessage(driver);
	}

	@Test
	public void testSomethings() throws InterruptedException {
		homePage.navigateToWebApp();
		homePage.navigateToContactPage();

		Thread.sleep(3000);

		contactPage.enterName("aaaaa");
		contactPage.enterAddress("107 Nguyen Dinh Chieu");
		contactPage.enterPostCode("123456789");
		contactPage.enterEmail("nctoan500@gmail.com");
		contactPage.submit();
		
		Assert.assertEquals(confirmMessage.getConfirmMessage(), "Many thanks for your message. We will contact you about your query as soon as possible. In the mean time why not have a look at our list of animals we have available for adoption in the adoption page.");
	}

	@AfterMethod
	public void end() {
		driver.close();
	}
}
