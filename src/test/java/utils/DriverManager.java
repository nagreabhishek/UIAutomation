package utils;


import java.net.MalformedURLException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;

import helper.BaseActions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager extends Base{

	public static PropertiesReader readConfig = new PropertiesReader();
	public static String browserName;
	public static String baseURL = readConfig.getPropertyValue("URL");

	public static void launchBrowser() throws MalformedURLException {
		browserName = readConfig.getPropertyValue("BROWSER");
		try {
			switch (browserName.toUpperCase()) {
			case "CHROME":
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.setHeadless(false);
				options.addArguments("--start-maximized");
				TestGlobals.setWebDriver(new ChromeDriver(options));
				break;

			case "FIREFOX":
				WebDriverManager.firefoxdriver().setup();
				TestGlobals.setWebDriver(new FirefoxDriver());
				break;

			case "INTERNETEXPLORER":
				WebDriverManager.iedriver().setup();

				InternetExplorerOptions ieOptions = new InternetExplorerOptions();
				ieOptions.setCapability("nativeEvents", false);
				ieOptions.setCapability("ignoreProtectedModeSettings", true);
				ieOptions.setCapability("ignoreZoomSetting", true);
				ieOptions.setCapability("disable-popup-blocking", true);
				ieOptions.setCapability("unexpectedAlertBehaviour", "accept");
				ieOptions.setCapability("enablePersistentHover", true);
				TestGlobals.setWebDriver(new InternetExplorerDriver(ieOptions));
				break;
			default:
				throw new IllegalArgumentException("Specified browser is not supported - " + browserName);
			}
			TestGlobals.getWebDriver().manage().window().maximize();
			TestGlobals.getWebDriver().manage().deleteAllCookies();
			TestGlobals.getWebDriver().get(baseURL);
			BaseActions ba = new BaseActions();
			Assert.assertTrue(ba.isTitleDisplayed(10));
			logger.info("Browser Launched Successfully");
		} catch (Exception e) {
			logger.error("Error while opening browser. Browser Name = " + browserName);
			e.printStackTrace();
		}
	}
}
