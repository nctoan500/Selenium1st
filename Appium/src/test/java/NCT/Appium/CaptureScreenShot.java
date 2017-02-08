package NCT.Appium;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CaptureScreenShot {
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	Dimension size;
	String destDir;
	DateFormat dateFormat;

	@SuppressWarnings("rawtypes")
	@BeforeTest
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "cca223a6d8c5");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "4.3");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.hmh.api");
		capabilities.setCapability("appActivity", "com.hmh.api.ApiDemos");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void ScrollToTab() {
		// Scroll till element which contains "Views" text If It Is not visible on screen.
		String str1 = "Views";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str1+"\").instance(0))");
		
		// Click on Views.
		driver.findElement(By.name("Views")).click();
		
		// Scroll till element which contains "Tabs" text If It Is not visible on screen.
		String str2 = "Tabs";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str2+"\").instance(0))");
	
		
		//Call takeScreenShot() function to capture screenshot of android screen.
		takeScreenShot();
	}

	public void takeScreenShot() {
		// Set folder name to store screenshots.
		destDir = "screenshots";
		
		// Create folder under project with name "screenshots" provided to destDir.
		new File(destDir).mkdirs();
		
		// Capture screenshot.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		// Set date format to set It as screenshot file name.
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		
		// Set file name using current date time.
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			// Copy paste file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			System.out.println(scrFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void End() {
		driver.quit();
	}
}