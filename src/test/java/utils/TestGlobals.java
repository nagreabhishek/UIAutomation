package utils;

import org.openqa.selenium.WebDriver;

public class TestGlobals {
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static void setWebDriver(WebDriver driver) {
		tlDriver.set(driver);
	}
	
	public static WebDriver getWebDriver() {
		return tlDriver.get();
	}
	
	public synchronized static void removeDriver() {
		if (TestGlobals.getWebDriver() != null)
			TestGlobals.getWebDriver().quit();
		if (getWebDriver() != null)
			tlDriver.remove();
	}
	
}
