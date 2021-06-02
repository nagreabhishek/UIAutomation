package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import helper.BaseActions;
import utils.DriverManager;
import utils.TestGlobals;

public class SummaryPage extends DriverManager{

	public static WebDriver driver;

	BaseActions baseActions = null;

	public SummaryPage() {
		baseActions = new BaseActions();
		PageFactory.initElements(TestGlobals.getWebDriver(), this);
	}

	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium' and @title='Proceed to checkout']")
	public static WebElement proceedToCheckout;
	
	@FindBy(id = "SubmitCreate")
	public static WebElement createAccount;

	@FindBy(id = "email_create")
	public static WebElement emailAddress;

	public void clickOnSummaryProceedToCheckout() throws InterruptedException {
		Assert.assertTrue(baseActions.isElementDisplayed(proceedToCheckout, 15));
		baseActions.scrollToView(proceedToCheckout);
		baseActions.click(proceedToCheckout);
		logger.info("Successfully cllicked on Proceed to checkout button");
	}
	
	public void createAccount() throws InterruptedException {
		Assert.assertTrue(baseActions.isElementDisplayed(emailAddress, 15));
		baseActions.scrollToView(emailAddress);
		baseActions.enterText(emailAddress, baseActions.getRandomEmailID());
		Assert.assertTrue(baseActions.isElementDisplayed(createAccount, 5));
		baseActions.click(createAccount);
		logger.info("Successfully clicked on create an account button.");
	}

}
