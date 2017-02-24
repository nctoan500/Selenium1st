package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import util.Log;
import webpage.NewCustomer;

public class TestNewCustomer {
	WebDriver driver;
	NewCustomer objNewCustomer;

	/**
	 * This test go to http://demo.guru99.com/V4/ Verify login page title as
	 * guru99 bank Login to application Verify the home page using Dashboard
	 * message
	 */

	@Test(priority = 0)
	public void test_New_Customer_Form_Text() {
		Log.startTestCase("test_New_Customer_Form_Text");

		//Verify home page
		Assert.assertTrue(objNewCustomer.getCustomerNameText().contains("Customer Name"));
		Assert.assertTrue(objNewCustomer.getGenderText().contains("Gender"));

		Log.endTestCase();
	}

	@Test(priority = 1)
	public void test_Input_Fields() {
		Log.startTestCase("test_Input_Fields");
		
		objNewCustomer.setCustomerName("nctoan");
		objNewCustomer.selectMale();
		
		Log.endTestCase();
	}
}
