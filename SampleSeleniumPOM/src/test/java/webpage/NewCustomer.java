package webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.Log;

public class NewCustomer {
	WebDriver driver;

	@FindBy(xpath = "//tr[4]/td")
	private WebElement customerName;

	@FindBy(xpath = "//tr[5]/td")
	private WebElement gender;

	@FindBy(xpath = "//input[@value='m']")
	private WebElement genderMale;

	@FindBy(xpath = "//input[@value='f']")
	private WebElement genderFemale;

	public NewCustomer(WebDriver driver) {
		Log.info("[OR] NewCustomer");

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getCustomerNameText() {
		Log.info("[OR] getCustomerNameText [" + customerName.getText() + "]");
		return customerName.getText();
	}

	public String getGenderText() {
		Log.info("[OR] getCustomerNameText [" + gender.getText() + "]");
		return gender.getText();
	}

	public void setCustomerName(String strCusName) {
		Log.info("[OR] setCustomerName [" + strCusName + "]");
		this.setCustomerName(strCusName);
	}

	public void selectMale() {
		Log.info("[OR] selectMale");
		genderMale.click();
	}

	public void selectFemale() {
		Log.info("[OR] selectFemale");
		genderFemale.click();
	}
}
