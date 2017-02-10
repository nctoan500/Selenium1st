package NCT.Appium;

import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScrollingToText {
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;

	@BeforeTest
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "4.3");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.example.android.apis");
		capabilities.setCapability("appActivity", "com.example.android.apis.ApiDemos");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void ScrollToText() throws InterruptedException {
		//Scroll till element which contains "Views" text If It Is not visible on screen.
		String str1 = "Views";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str1+"\").instance(0))");
		
		// Click on Views/.
		driver.findElement(By.name("Views")).click();
		System.out.println("Scrolling has been started to find text -> Tabs.");
		
		// Scroll till element which contains Tabs text.
		String str2 = "Tabs";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str2+"\").instance(0))");
		
		System.out.println("Tabs text has been found and now clicking on It.");
		
		
		// Click on Tabs.
		driver.findElement(By.name("Tabs")).click();
	}

	@AfterTest
	public void End() {
		driver.quit();
	}
}