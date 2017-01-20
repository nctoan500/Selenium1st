package NCT.Appium;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ControlSeekBar {
	Process p;
	// Set path of your node.exe file.
	// Progra~1 represents Program Files folder.
	String nodePath = "C:/Program Files (x86)/Appium/node.exe";
	// Set path of your appium.js file.
	String appiumJSPath = "C:/Program Files/Appium/node_modules/appium/bin/appium.js";
	String cmd = nodePath + " " + appiumJSPath;
	AndroidDriver driver;

	// This method Is responsible for starting appium server.
	public void appiumStart() throws IOException, InterruptedException {
		// Execute command string to start appium server.
		p = Runtime.getRuntime().exec(cmd);
		// Provide wait time of 10 mins to start appium server properly.
		// If face any error(Could not start a new session...) then Increase this time to 20 or 25 mins.
		Thread.sleep(10000);
		if (p != null) {
			System.out.println("Appium server Is started now.");
		}
	}

	// This method Is responsible for stopping appium server.
	public void appiumStop() throws IOException {
		if (p != null) {
			p.destroy();
		}
		System.out.println("Appium server Is stopped now.");
	}

	@BeforeTest
	public void setUp() throws Exception {
		// Stop appium server If It Is already running.
		appiumStop();
		// Start appium server.
		appiumStart();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "F4AZFG142115");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "5.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "io.appium.android.apis");
		capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void ScrollToView() {
		// Scroll till element which contains "Views" text If It Is not visible on screen
		String str1 = "Views";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
				+ str1 + "\").instance(0))");

		// Click on Views.
		driver.findElement(By.name("Views")).click();

		// Scroll till element which contains "Seek Bar" text If It Is not visible on screen.
		String str2 = "Seek Bar";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
				+ str2 + "\").instance(0))");

		// Click on Seek Bar.
		driver.findElement(By.name("Seek Bar")).click();

		//Locate SeekBar element.
		WebElement seekBar = driver.findElementById("io.appium.android.apis:id/seek");

		//Get start point of seekbar.
		int startX = seekBar.getLocation().getX();
		System.out.println(startX);

		//Get end point of seekbar.
		int endX = seekBar.getSize().getWidth();
		System.out.println(endX);

		//Get vertical location of seekbar.
		int yAxis = seekBar.getLocation().getY();

		//Set sllebar move to position. 
		//endX * 0.6 means at 60% of seek bar width.
		int moveToXDirectionAt = (int) (endX * 0.6);
		System.out.println("Moving seek bar at " + moveToXDirectionAt + " In X direction.");

		//Moving seekbar using TouchAction class.
		TouchAction act = new TouchAction(driver);
		act.press(startX, yAxis).moveTo(moveToXDirectionAt, yAxis).release().perform();
	}

	@AfterTest
	public void End() throws IOException {
		driver.quit();
		// Stop appium server when test Is ended.
		appiumStop();
	}
}