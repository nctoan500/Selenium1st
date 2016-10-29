package pom.demo1_SelenVN.webpages;

import org.openqa.selenium.WebDriver;

public class ConfirmationMessage extends AbstractPage {
	public ConfirmationMessage(WebDriver driver) {
		super(driver);
	}

	public String getConfirmMessage() {
		return getText("css=p");
	}
}
