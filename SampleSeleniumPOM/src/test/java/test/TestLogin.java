package test;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.Constants;
import util.Log;
import webpage.HomePage;
import webpage.Login;

public class TestLogin {
	WebDriver driver;
	Login objLogin;
	HomePage objHomePage;
	
	@BeforeTest
	public void setup() {
		DOMConfigurator.configure("log4j.xml");
		Log.info("SETUP BROWSER");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.URL);
	}

	@AfterTest
	public void quit() {
		Log.info("CLOSE BROWSER");
		driver.close();
	}

	/**
	 * This test go to http://demo.guru99.com/V4/ Verify login page title as
	 * guru99 bank Login to application Verify the home page using Dashboard
	 * message
	 */

	@Test(priority = 0)
	public void test_Home_Page_Appear_Correct() {
		Log.startTestCase("test_Home_Page_Appear_Correct");
		
		//Create Login Page object
		objLogin = new Login(driver);

		//Verify login page title
		String loginPageTitle = objLogin.getLoginTitle();
		Assert.assertTrue(loginPageTitle.toLowerCase().contains(Constants.Login_Page_Title));

		//login to application
		objLogin.loginToWeb(Constants.USERNAME, Constants.PASSWORD);

		// go the next page
		objHomePage = new HomePage(driver);

		//Verify home page
		Assert.assertTrue(objHomePage.getHomePageDashboardUserName()
				.toLowerCase().contains("manger id : " + Constants.USERNAME));
		
		Log.endTestCase();
	}
	
	@Test(priority = 1)
	public void test_Home_Page_Appear_Wrong() {
		Log.startTestCase("test_Home_Page_Appear_Wrong");
		
		//Verify home page
		Assert.assertTrue(objHomePage.getHomePageDashboardUserName()
				.toLowerCase().contains("mmmmmmanger id : " + Constants.USERNAME));
		
		Log.endTestCase();
	}
}

/*
 * Page:
 * http://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate
 * -guide.html
 */