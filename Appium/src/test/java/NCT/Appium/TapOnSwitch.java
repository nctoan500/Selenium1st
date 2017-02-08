package NCT.Appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TapOnSwitch {
 @SuppressWarnings("rawtypes")
AndroidDriver driver;

 @SuppressWarnings("rawtypes")
@BeforeTest
 public void setUp() throws Exception {
  DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setCapability("deviceName", "emulator-5554");
  capabilities.setCapability("browserName", "Android");
  capabilities.setCapability("platformVersion", "4.2.2");
  capabilities.setCapability("platformName", "Android");
  capabilities.setCapability("appPackage", "com.example.android.apis");
  capabilities.setCapability("appActivity", "com.example.android.apis.ApiDemos");
  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 }

 @Test
 public void tapSwitchOnOff() throws InterruptedException {
  // Scroll till element which contains "Views" text If It Is not visible on screen.
  String str1 = "Views";
  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str1+"\").instance(0))");

  // Click on Views.
  driver.findElement(By.name("Views")).click();
  // Scroll till element which contains "Switches" text If It Is not visible on screen.
  String str2 = "Switches";
  driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str2+"\").instance(0))");

  // Click on Switches.
  driver.findElement(By.name("Switches")).click();
  
  //Get status of switch using it's text.
  String switchStatus1 = driver.findElementById("com.example.android.apis:id/monitored_switch").getText();
  System.out.println(switchStatus1);
  //If switchStatus1 = Monitored switch OFF, Execute inner code.
  if((switchStatus1.trim()).equals("Monitored switch OFF")){
   System.out.println("Doing Monitored switch ON");
   //Locate switch button element by name = Monitored switch OFF.
   MobileElement swt = (MobileElement) driver.findElementByName("Monitored switch OFF");
   //Tap on switch button "Monitored switch OFF" to make it ON.
   swt.tap(1, 1);
   System.out.println("Monitored switch is ON now.");
  }
  //Pause test for 5 seconds to put switch ON for 5 seconds.
  Thread.sleep(5000);
  
  //Get status of switch using it's text.
  String switchStatus2 = driver.findElementById("com.example.android.apis:id/monitored_switch").getText();
  System.out.println(switchStatus2);
  //If switchStatus1 = Monitored switch ON, Execute inner code.
  if((switchStatus2.trim()).equals("Monitored switch ON")){
   System.out.println("Doing Monitored switch OFF");
   //Locate switch button element by name = Monitored switch ON.
   MobileElement swt = (MobileElement) driver.findElementByName("Monitored switch ON");
   //Tap on switch button "Monitored switch OFF" to make it OFF.
   swt.tap(1, 1);
   System.out.println("Monitored switch is OFF now.");
  }
 }
 
 @AfterTest
 public void End() {
  driver.quit();
 }
}