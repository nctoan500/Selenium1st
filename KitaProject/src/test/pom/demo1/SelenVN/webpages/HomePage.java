package pom.demo1.SelenVN.webpages;

import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public ContactPage navigateToContactPage() {
		// driver.findElement(By.id("contact_link")).click();
		click("id=contact_link");
		return new ContactPage(driver);
	}
}
