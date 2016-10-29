package pom.demo2_ExeAuto.tests;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.kita.utils.ExcelLib_JXL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReadDataTest_SignIn {
	// Global initialization of Variables
	static ExcelLib_JXL excelLib;
	WebDriver driver = new FirefoxDriver();

	// Constructor to initialize Excel for Data source
	public ReadDataTest_SignIn() throws BiffException, IOException {
		// Let's assume we have only one Excel File which holds all Testcases. Just for Demo !!!
		excelLib = new ExcelLib_JXL("Resources\\Excel\\SignInGmail.xls");

		// Load the Excel Sheet Col in to Dictionary for Further use in our Testcases.
		excelLib.ColumnDictionary();
	}

	@BeforeTest
	public void EnvironmentalSetup() {
		driver.get("http://demoqa.com/registration/");
	}

	@Test
	public void GmailLoginPage() throws InterruptedException {

		//Create a for loop for iterate through our Excel sheet for all the test cases.
		for (int rowCnt = 1; rowCnt < excelLib.RowCount(); rowCnt++) {

			//Enter First Name
			WebElement firstName = driver.findElement(By.id("name_3_firstname"));
			firstName.clear();
			firstName.sendKeys(excelLib.ReadCell(excelLib.GetCell("First Name"), rowCnt));
			
			//Enter Last Name
			WebElement lastName = driver.findElement(By.id("name_3_lastname"));
			lastName.clear();
			lastName.sendKeys(excelLib.ReadCell(excelLib.GetCell("Last Name"), rowCnt));

			//Enter Phone Number
			WebElement phoneNumber = driver.findElement(By.id("phone_9"));
			phoneNumber.clear();
			phoneNumber.sendKeys(excelLib.ReadCell(excelLib.GetCell("Phone Number"), rowCnt));

			Thread.sleep(2000);
		}
	}
}
