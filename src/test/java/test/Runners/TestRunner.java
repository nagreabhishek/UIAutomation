package test.Runners;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import cucumber.api.testng.CucumberFeatureWrapper;
import io.cucumber.testng.*;
import utils.TestGlobals;

@CucumberOptions
(
		features =   {"src/test/resources/features/"},
		glue     =   {"test.Runners", "stepDefinition"},
		plugin   =   {"pretty","json:target/cucumber-reports/cucumber.json","html:target/cucumber/report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		tags 	 = 	  "@orderTwoProducts",
				dryRun=false
		)

public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;
	
	@Test(description = "Runs Cucumber Features", dataProvider = "scenarios")
	private void runScenarios(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
//		System.out.println("entered runScenarios");
		try {
			testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider(name = "scenarios", parallel = false)
	private Object[][] getScenario() {
		Object[][] scenarios = null;
		try {
			if(testNGCucumberRunner == null){
		        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		scenarios = testNGCucumberRunner.provideScenarios();
		return (scenarios);
	}
	
	@AfterMethod
	private synchronized void scenarioClosure(ITestResult result) throws IOException {
		TestGlobals.removeDriver();
	}
	
	@AfterClass
	private void tearDown() {
		testNGCucumberRunner.finish();
	}
}
