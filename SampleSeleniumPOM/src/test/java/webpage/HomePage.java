package webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.Log;

public class HomePage {
	WebDriver driver;

	@FindBy(xpath = "//table//tr[@class='heading3']")
	private WebElement homePageUserName;
	
	@FindBy(linkText = "New Customer")
	private WebElement menuNewCustomer;

	public HomePage(WebDriver driver) {
		Log.info("[OR] HomePage");
		
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	//Get the User name from Home Page
	public String getHomePageDashboardUserName() {
		Log.info("[OR] getHomePageDashboardUserName [" + homePageUserName.getText() + "]");
		
		return homePageUserName.getText();
	}
	
	public void clickNewCustomer() {
		Log.info("[OR] clickNewCustomer");
		
		menuNewCustomer.click();
	}
}

/*
 * Page:
 * http://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate
 * -guide.html
 */