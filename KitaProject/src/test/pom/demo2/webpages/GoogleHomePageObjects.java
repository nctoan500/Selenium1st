package pom.demo2.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePageObjects {

	public GoogleHomePageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Using FindBy for locating elements
	@FindBy(name = "q")
	public WebElement txtSearch;

	@FindBy(name = "btnG")
	public WebElement btnSearch;

	@FindBy(linkText = "Selenium - Web Browser Automation")
	public WebElement linkSelenium;

	/*Defining all the user actions that can be performed in the ... page in the form of methods*/
	public void SearchGoogle(String searchText) {
		txtSearch.sendKeys(searchText);
		btnSearch.click();
	}

	 /*Take note of return type of this method- as clicking Link "Selenium..." will navigate user to Selenium page, 
	  * so return type of this method is marked as SeleniumPage.*/
	public SeleniumPageObjects ClickSelenium() {
		linkSelenium.click();
		return new SeleniumPageObjects();
	}
}
