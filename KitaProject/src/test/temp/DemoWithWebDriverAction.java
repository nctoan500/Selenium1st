package temp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoWithWebDriverAction {
	@Test
	public void loginWithBlankField() throws Exception {
		WebDriver driver = new FirefoxDriver();
		WebDriverAction myAction = new WebDriverAction(driver);

		myAction.open("https://accounts.google.com");

		myAction.sendKeys("id=Email", "id");
		myAction.sendKeys("name=Email", "name");
		myAction.sendKeys("css=#Email", "css");
		myAction.sendKeys("xpath=//input[@id='Email']", "xpath");
		myAction.clear("id=Email");

		myAction.click("id=next");
		Thread.sleep(3000);
		String msg = myAction.getText("id=errormsg_0_Email");
		Assert.assertEquals(msg, "Vui lòng nhập email của bạn.");

		myAction.close();
	}

	@Test
	public void testLoginWithBlankkPassword() throws Exception {
		WebDriver driver = new FirefoxDriver();
		WebDriverAction myAction = new WebDriverAction(driver);

		myAction.open("https://accounts.google.com");
		myAction.sendKeys("id=Email", "nctoan500@gmail.com");
		myAction.click("id=next");
		Thread.sleep(3000);
		myAction.click("id=signIn");
		Thread.sleep(3000);
		String msg = myAction.getText("id=errormsg_0_Passwd");
		Assert.assertEquals(msg, "Vui lòng nhập mật khẩu của bạn.");

		myAction.close();
	}
}
