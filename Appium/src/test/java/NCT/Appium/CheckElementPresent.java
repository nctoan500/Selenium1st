package NCT.Appium;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckElementPresent {
 AndroidDriver driver;

 @BeforeTest
 public void setUp() throws MalformedURLException {
  DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setCapability("deviceName", "F4AZFG142115");
  capabilities.setCapability("browserName", "Android");
  capabilities.setCapability("platformVersion", "5.0");
  capabilities.setCapability("platformName", "Android");
  capabilities.setCapability("appPackage", "com.hmh.api");
  capabilities.setCapability("appActivity", "com.hmh.api.ApiDemos");
  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 }

 //Check element App is present or not on page.
 @Test
 public void checkAppElementPresent() {
  //There is element with name App on screen.
  //So iselementpresent will be set to true.
  Boolean iselementpresent = driver.findElementsByName("App").size() != 0;
  //iselementpresent will be true so assertion will pass and so test method will pass too.
  Assert.assertTrue(iselementpresent,"Targeted element App is not present on screen");
  System.out.println("Targeted element App is present on screen.");
 }

 //Check element Loader is present or not on page.
 @Test
 public void checkLoaderElementPresent() {
  //There is not any element like Loader on screen.
  //So iselementpresent will be set to false.
  Boolean iselementpresent = driver.findElementsByName("Loader").size() != 0;
  //iselementpresent will be false so assertion will fail and so test method will fail too.
  Assert.assertTrue(iselementpresent,"Targeted element Loader is not present on screen");
  System.out.println("Targeted element Loader is present on screen.");
 }

 @AfterTest
 public void End() throws IOException {
  driver.quit();  
 }
}