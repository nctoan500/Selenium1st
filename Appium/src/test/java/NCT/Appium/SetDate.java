package NCT.Appium;

import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SetDate {
	AndroidDriver driver;

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
		capabilities.setCapability("unicodeKeyboard", "true");
		capabilities.setCapability("resetKeyboard", "true");
	}

	@Test
	public void dateSet() throws Exception {
		// Scroll till element which contains "Views" text.
		String str1 = "Views";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str1+"\").instance(0))");
		
		// Click on Views.
		driver.findElement(By.name("Views")).click();
		
		// Scroll till element which contains "Date Widgets" text.
		String str2 = "Date Widgets";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str2+"\").instance(0))");
		
		// Click on element which contains "Date Widgets" text.
		driver.findElement(By.name("Date Widgets")).click();
		
		// Scroll till element which contains "1. Dialog" text.
		String str3 = "1. Dialog";
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str3+"\").instance(0))");
		
		// Click on element which contains "1. Dialog" text.
		driver.findElement(By.name("1. Dialog")).click();
		
		// Click on button which contains "change the date" text.
		driver.findElement(By.name("change the date")).click();
		
		/*
		// WAY 1
		//Set Month = Aug.
		driver.findElement(By.xpath("//android.widget.NumberPicker[@index='0']")).sendKeys("apr");
		
		//Set Date = 25.
		driver.findElement(By.xpath("//android.widget.NumberPicker[@index='1']")).sendKeys("09");
		
		//Set Year = 2009.
		driver.findElement(By.xpath("//android.widget.NumberPicker[@index='2']")).sendKeys("1992");
		*/
		
		// WAY 2
		List<WebElement> date = driver.findElements(By.className("android.widget.NumberPicker"));
	    date.get(0).sendKeys("apr");
	    date.get(1).sendKeys("09");
	    date.get(2).sendKeys("1992");
		
		
		//Click on Done button.
		driver.findElement(By.id("android:id/button1")).click();
	}

	@Test
	public void timeSet() throws Exception {
		// Click on button which contains "change the time" text.
		driver.findElement(By.name("change the time")).click();
		
		//Set Hours = 1.
		driver.findElement(By.xpath("//android.widget.NumberPicker[@index='0']")).sendKeys("1");
		
		//Set Minutes = 25.
		driver.findElement(By.xpath("//android.widget.NumberPicker[@index='2']")).sendKeys("30");
		
		//Set pm.
		driver.findElement(By.xpath("//android.widget.NumberPicker[@index='1']")).sendKeys("am");
		
		//Click on Done button.
		driver.findElement(By.id("android:id/button1")).click();
	}

	@AfterTest
	public void End() {
		driver.quit();
	}
}