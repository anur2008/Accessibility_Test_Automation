package matson_accessibility_core;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

import com.matson.accessibilty.constant.AppConstant;

public abstract class BasePage {
	public abstract boolean isPageLoaded();

	public abstract void waitForPageToLoad();

	private static final int DEFAULT_WAIT_TIME = 30;

	// WebDriver driver = null;
	public static WebDriver getDriver() {
		return DriverFactory.getInstance().getDriver();
	}

	public void go(String applicationURL) {

		try {
			RemoteWebDriver remoteDriver = ((RemoteWebDriver) getDriver());
			String browserName = remoteDriver.getCapabilities().getBrowserName();
			System.out.println("*** Application launch Start for browser: " + browserName + " at :" + new Date());
			getDriver().get(applicationURL);
			System.out.println("*** Application launch End for browser: " + browserName + " at :" + new Date());
			getDriver().manage().window().maximize();
		} catch (WebDriverException exp) {
			DriverFactory.getInstance().removeDriver();
			throw exp;
		}

	}

	public String getLocatorValue(final String locator) {
		return AppContext.getInstance().getLocatorProps().getProperty(locator, "");
	}

	public By getBy(final String desciptiveLoc) {
		String[] locValue;

		try {
			locValue = desciptiveLoc.split("=", 2);

		} catch (Exception exe) {

			throw exe;
		}
		switch (locValue[0].toUpperCase()) {
		case "ID":
			return By.id(locValue[1]);
		case "NAME":
			return By.name(locValue[1]);
		case "CSS":
			return By.cssSelector(locValue[1]);
		case "CLASSNAME":
			return By.cssSelector(locValue[1]);
		case "LINKTEXT":
			return By.linkText(locValue[1]);
		case "PARTIALLINKTEXT":
			return By.partialLinkText(locValue[1]);
		case "TAGNAME":
			return By.tagName(locValue[1]);
		default:
			return By.xpath(locValue[1]);
		}
	}

	private WebDriverWait getWait(final int... time) {
		// Verify is explicit wait time passed
		int waitTime = DEFAULT_WAIT_TIME;
		if (time.length > 0) {
			waitTime = time[0];
		}
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		final WebDriverWait wait = new WebDriverWait(getDriver(), waitTime, 2);
		wait.ignoring(WebDriverException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchWindowException.class);
		return wait;
	}

	protected void waitForElementVisible(final String descriptiveLocator, final int... timeoutSeconds) {
		try {
			getWait(timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(getBy(descriptiveLocator)));
		} catch (TimeoutException ignore) {
			// Ignore timeout exception
		}
	}

	protected WebElement findElement(String descLoc) {
		waitForElementVisible(descLoc);
		return getDriver().findElement(getBy(descLoc));
	}

	protected boolean isElementDisplayed(String desciptiveLoc) {
		boolean isElementDisplayed = false;
		List<WebElement> elements = getDriver().findElements(getBy(desciptiveLoc));
		if (elements.size() > 0) {
			isElementDisplayed = elements.get(0).isDisplayed();
		}
		return isElementDisplayed;
	}

}
//}