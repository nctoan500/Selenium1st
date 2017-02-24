package webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.Log;

public class Login {
	// All WebElements are identified by @FindBy annotation
	WebDriver driver;

	@FindBy(name = "uid")
	WebElement user;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(className = "barone")
	WebElement titleText;

	@FindBy(name = "btnLogin")
	WebElement btnLogin;

	public Login(WebDriver driver) {
		Log.info("[OR] Login page");
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	//Set user name in textbox
	public void setUserName(String strUserName) {
		Log.info("[OR] setUserName [" + strUserName + "]");
		
		user.clear();
		user.sendKeys(strUserName);
	}

	//Set password in password textbox
	public void setPassword(String strPassword) {
		Log.info("[OR] setPassword [" + strPassword + "]");
		
		password.clear();
		password.sendKeys(strPassword);
	}

	//Click on login button
	public void clickLogin() {
		Log.info("[OR] clickLogin");
		
		btnLogin.click();
	}

	//Get the title of Login Page
	public String getLoginTitle() {
		Log.info("[OR] getLoginTitle [" + titleText.getText() + "]");
		
		return titleText.getText();
	}

	/**
	 * This POM method will be exposed in test case to login in the application
	 * 
	 * @param strUserName
	 * @param strPasword
	 * @return
	 */

	public void loginToWeb(String strUserName, String strPasword) {
		Log.info("[OR] loginToWeb [" + strUserName + "][" + strPasword + "]");
		
		this.setUserName(strUserName);
		this.setPassword(strPasword);
		this.clickLogin();
	}
}

/*
 * Page:
 * http://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html
 */
