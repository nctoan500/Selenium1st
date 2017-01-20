package NCT.Appium;

import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TypeInTextBox {
 AndroidDriver driver;

 @BeforeTest
 public void setUp() throws Exception {
  DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setCapability("deviceName", "ZX1B32FFXF");
  capabilities.setCapability("browserName", "Android");
  capabilities.setCapability("platformVersion", "4.4.2");
  capabilities.setCapability("platformName", "Android");
  capabilities.setCapability("appPackage", "io.appium.android.apis");
  capabilities.setCapability("appActivity","io.appium.android.apis.ApiDemos");
  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 }

 @Test
 public void typeInText() throws InterruptedException {
  // Scroll till element which contains "Views" text If It Is not visible on screen.
  String str1 = "Views";
  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str1+"\").instance(0))");

  // Click on Views.
  driver.findElement(By.name("Views")).click();
  // Scroll till element which contains "Controls" text If It Is not visible on screen.
  String str2 = "Controls";
  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str2+"\").instance(0))");

  // Click on Controls.
  driver.findElement(By.name("Controls")).click();

  // Scroll till element which contains "2. Dark Theme" text If It Is not visible on screen.
  String str3 = "2. Dark Theme";
  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str3+"\").instance(0))");

  // Click on 2. Dark Theme.
  driver.findElement(By.name("2. Dark Theme")).click();
  
  // Typing in text box using sendKeys command.
  driver.findElement(By.id("io.appium.android.apis:id/edit")).sendKeys("Test");
 }

 @AfterTest
 public void End() {
//  driver.quit();
 }
}