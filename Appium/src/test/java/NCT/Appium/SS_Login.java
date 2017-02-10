package NCT.Appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SS_Login {
	/* 
	 * PRE CONDITION
	 * Enable Wifi + Bluetooth
	 * Welcome already
	 */
	
	private static AndroidDriver<MobileElement> driver;
	public static String username="";
	public static String password="";
	public static String firstName="";

	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "cca223a6d8c5");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "4.3");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage","mozo.o2o.biglabs.com.smartmozo");
		capabilities.setCapability("appActivity","mozo.o2o.biglabs.com.smartmozo.activity.LauncherActivity");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		capabilities.setCapability("unicodeKeyboard", "true");
		capabilities.setCapability("resetKeyboard", "true");
	}

	@AfterTest 
	public void End() { 
		driver.quit(); 
	}
	 
	@Test(priority = 1)
	public void LaunchAPP() throws Exception {
		// First Launch
		if (driver.findElementsById("mozo.o2o.biglabs.com.smartmozo:id/btn_skip").size() != 0) {
			Dimension size = driver.manage().window().getSize();

			//Click Skip
			driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/btn_skip").click();

			//Scroll till bottom
			int starty = (int) (size.height * 0.80);
			int endy = (int) (size.height * 0.20);
			int startx = size.width / 2;

			for (int i = 0; i < 6; i++) {
				driver.swipe(startx, endy, startx, starty, 3000);
				Thread.sleep(1000);
			}

			//Click Skip
			driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/bt_skip").click();
		}

		// Blue-tooth pop-up
		if (driver.findElementsById("mozo.o2o.biglabs.com.smartmozo:id/cancel_button").size() != 0) {
			driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/cancel_button").click();
			Thread.sleep(2000);
		}
		
		//Click Profile tab
		driver.findElementsByXPath("//android.widget.TabWidget/android.widget.LinearLayout").get(4).click();
	}
	
	@Test(priority = 2)
	public void Register() throws Exception {
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		
		username = "toan_auto" + n + "@gmail.com";
		System.out.println(username);
		password = "123456";
		firstName = "Toan" + n;
		
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/txt_register").click();
		
		// Fill in form
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/edt_email").sendKeys(username);
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/edt_first_name").sendKeys(firstName);
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/edt_password").sendKeys(password);
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/edt_confirm_password").click();
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/edt_confirm_password").sendKeys(password);
		driver.navigate().back();
		Thread.sleep(2000);
		
		// Submit
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/btn_signup").click();
		Thread.sleep(10000);
		String result = driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/tv_hi_user").getText();
		Assert.assertEquals(result, "Hi, " + firstName, "NOT match. Actual: " + result);
	}
	
	@Test(priority = 3)
	public void LoginEmpty() throws Exception {
		Thread.sleep(2000);
		
		//Logout
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/ln_logout").click();
		Thread.sleep(2000);
		
		// Sign-in
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/btn_signin").click();
		Thread.sleep(2000);
		
		String result = driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/content_text").getText();
		Assert.assertEquals(result, "Phone number / email, password are required.", "NOT match. Actual: " + result);
		
		Thread.sleep(5000);
	}
	
	@Test(priority = 4)
	public void LoginPass() throws Exception {
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/edt_email").sendKeys(username);
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/edt_password").sendKeys(password);
		
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/btn_signin").click();
		Thread.sleep(10000);
		
		String result = driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/tv_hi_user").getText();
		Assert.assertEquals(result, "Hi, " + firstName, "NOT match. Actual: " + result);
		
		driver.findElementById("mozo.o2o.biglabs.com.smartmozo:id/ln_logout").click();
	}
}
