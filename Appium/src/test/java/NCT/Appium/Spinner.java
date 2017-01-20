package NCT.Appium;

import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Spinner {
	AndroidDriver driver;

	@BeforeTest
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "ZX1B32FFXF");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "io.appium.android.apis");
		capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void selectPlanetValue() {
		// Scroll till element which contains "Views" text.
		String str1 = "Views";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str1+"\").instance(0))");
		
		// Click on Views.
		driver.findElement(By.name("Views")).click();
		
		// Scroll till element which contains "Spinner" text.
		String str2 = "Spinner";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str2+"\").instance(0))");
		
		// Click on element which contains "Spinner" text.
		driver.findElement(By.name("Spinner")).click();
		
		//Find parent element of Planet drop down to uniquely Identify It's child drop down.
		WebElement ele = driver.findElementById("io.appium.android.apis:id/spinner2");
		
		//Click on Planet drop down to open drop down list.
		ele.findElement(By.id("android:id/text1")).click();
		
		// Scroll till element which contains "Pluto" text In drop down list.
		String str3 = "Pluto";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str3+"\").instance(0))");
	
		//Select "Pluto" from drop down list Items.
		driver.findElementByName("Pluto").click();
	}

	@AfterTest
	public void End() {
		driver.quit();
	}
}