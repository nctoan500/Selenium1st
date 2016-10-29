package webdriver.temp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

	private WebDriver driver = new FirefoxDriver();

	@BeforeTest
	public void setUp() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testLoginWithBlankkField() throws Exception {
		driver.get("https://accounts.google.com");
		driver.findElement(By.id("next")).click();
		Thread.sleep(2000);
		String msg = driver.findElement(By.id("errormsg_0_Email")).getText();
		Assert.assertEquals(msg, "Vui lòng nhập email của bạn.");
	}

	@Test
	public void testLoginWithBlankkPassword() throws Exception {
		driver.get("https://accounts.google.com");
		driver.findElement(By.id("Email")).sendKeys("nctoan500@gmail.com");
		driver.findElement(By.id("next")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("signIn")).click();
		Thread.sleep(2000);
		String msg = driver.findElement(By.id("errormsg_0_Passwd")).getText();
		Assert.assertEquals(msg, "Vui lòng nhập mật khẩu của bạn.");
	}
}
