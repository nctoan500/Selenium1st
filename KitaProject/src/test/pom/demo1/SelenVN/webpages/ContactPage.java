package pom.demo1.SelenVN.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends AbstractPage {
	public ContactPage(WebDriver driver) {
		super(driver);
	}

	public void enterName(String value) {
		// driver.findElement(By.name("name_field")).sendKeys(value);
		sendKeys("name=name_field", value);
	}

	public void checkInformation(boolean value) {
		// code
	}
	
	public void enterAddress(String value) {
		sendKeys("name=address_field", value);
	}
	
	public void enterPostCode(String value) {
		sendKeys("name=postcode_field", value);
	}
	
	public void enterEmail(String value) {
		sendKeys("name=email_field", value);
	}
	
	public ConfirmationMessage submit() {
		click("id=submit_message");
		return new ConfirmationMessage(driver);
	}

	public HomePage naviateToHomePage() {
		driver.findElement(By.id("home_link")).click();
		navigateToWebApp();
		return new HomePage(driver);
	}
}
