package stepDefinition;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import utils.DriverManager;
import utils.TestGlobals;

public class Hooks extends DriverManager {

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				logger.info(scenario.getName() + " is Fail");
				final byte[] screenshot = ((TakesScreenshot) TestGlobals.getWebDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", scenario.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				logger.info(scenario.getName() + " is Pass");
				final byte[] screenshot = ((TakesScreenshot) TestGlobals.getWebDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", scenario.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		TestGlobals.getWebDriver().quit();
		logger.info("Browser closed Successfully");
	}

}
