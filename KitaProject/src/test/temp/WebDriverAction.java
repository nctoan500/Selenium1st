package temp;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverAction {

	public Logger APP_LOGS = Logger.getLogger("actionLog");

	WebDriver driver = null;

	// DRIVER
	public WebDriverAction(WebDriver driver) {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
		this.driver = driver;
	}

	// GET BY
	private By getBy(String locator) {
		By by = null;

		// id
		if (locator.startsWith("id=")) {
			locator = locator.substring(3);
			by = By.id(locator);
		}

		// className
		else if (locator.startsWith("class=")) {
			locator = locator.substring(6);
			by = By.className(locator);
		}

		// tagName
		else if (locator.startsWith("tagName=")) {
			locator = locator.substring(8);
			by = By.tagName(locator);
		}

		// name
		else if (locator.startsWith("name=")) {
			locator = locator.substring(5);
			by = By.name(locator);
		}

		// linkText
		else if (locator.startsWith("link=")) {
			locator = locator.substring(5);
			by = By.linkText(locator);
		}

		// partialLinkText
		else if (locator.startsWith("partialLink=")) {
			locator = locator.substring(12);
			by = By.partialLinkText(locator);
		}

		// cssSelector
		else if (locator.startsWith("css=")) {
			locator = locator.substring(4);
			by = By.cssSelector(locator);
		}

		// xpath
		else if (locator.startsWith("xpath=")) {
			locator = locator.substring(6);
			by = By.xpath(locator);
		} else {
			APP_LOGS.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + "\n[ERROR] NOT SUPPORT LOCATOR: \"" + locator
					+ "\"\n(Supported: id= | class= | tagName= | name= | link= | partialLink= | css= | xpath=)\n");
		}

		return by;
	}

	// GET ELEMENT
	public WebElement getWebElement(String locator) {
		return driver.findElement(getBy(locator));
	}

	// GET, OPEN URL
	public void open(String url) {
		APP_LOGS.debug("[info] Executing: open (" + url + ")");
		driver.get(url);
	}

	// CLICK
	public void click(String locator) {
		try {
			APP_LOGS.debug("[info] Executing: click (" + locator + ")");
			WebElement element = getWebElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			APP_LOGS.debug("[ERROR] NOT FOUND ELEMENT: \"" + locator + "\" to click.\n" + e.getMessage() + "\n");
		}
	}

	// SENDKEYS (INPUT)
	public void sendKeys(String locator, String value) {
		try {
			APP_LOGS.debug("[info] Executing: sendKeys (" + locator + ",\"" + value + "\")");
			WebElement element = getWebElement(locator);
			element.sendKeys(value);
		} catch (NoSuchElementException e) {
			APP_LOGS.debug("[ERROR] NOT FOUND ELEMENT: \"" + locator + "\" to sendKeys.\n" + e.getMessage() + "\n");
		}
	}

	// CLEAR
	public void clear(String locator) {
		try {
			APP_LOGS.debug("[info] Executing: clear (" + locator + ")");
			WebElement element = getWebElement(locator);
			element.clear();
		} catch (NoSuchElementException e) {
			APP_LOGS.debug("[ERROR] NOT FOUND ELEMENT: \"" + locator + "\" to clear.\n" + e.getMessage() + "\n");
		}
	}

	// GET TEXT
	public String getText(String locator) {
		String result = "";
		try {
			APP_LOGS.debug("[info] Executing: getText (" + locator + ")");
			WebElement element = getWebElement(locator);
			result = element.getText();
		} catch (NoSuchElementException e) {
			APP_LOGS.debug("[ERROR] NOT FOUND ELEMENT: \"" + locator + "\" to getText\n" + e.getMessage() + "\n");
		}
		return result;
	}

	// CLOSE (1 WINDOWs)
	public void close() {
		APP_LOGS.debug("[info] Executing: close");
		driver.close();
	}

	// QUIT (ALL WINDOWs)
	public void quit() {
		driver.quit();
	}
}
