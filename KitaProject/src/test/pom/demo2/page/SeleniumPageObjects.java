package pom.demo2.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumPageObjects {
	public SeleniumPageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public SeleniumPageObjects() {
		// TODO Auto-generated constructor stub
	}

	@FindBy(linkText = "Download")
	public WebElement linkDownload;

	@FindBy(xpath = "//div[@id='header']/h1/a")
	public WebElement linkHome;

	public void ClickDownload() {
		linkDownload.click();
	}

	public void NavigateHome() {
		linkHome.click();
	}
}
