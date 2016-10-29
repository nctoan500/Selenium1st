package pom.demo1_SelenVN.webpages;

import org.openqa.selenium.WebDriver;

import temp.WebDriverAction;

public class AbstractPage extends WebDriverAction {

	protected WebDriver driver;

	public AbstractPage(WebDriver driver) {
		super(driver);
	}

	public HomePage navigateToWebApp() {
		// driver.navigate().to("http://www.thetestroom.com/webapp/index.html");
		open("http://www.thetestroom.com/webapp/index.html");
		return new HomePage(driver);
	}
}
