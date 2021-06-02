package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import helper.BaseActions;
import utils.Base;
import utils.TestGlobals;

public class PaymentPage extends Base{

	public static WebDriver driver;

	BaseActions baseActions = null;

	public PaymentPage() {
		baseActions = new BaseActions();
		PageFactory.initElements(TestGlobals.getWebDriver(), this);
	}

	@FindBy(css = "a[title='Pay by bank wire']")
	public static WebElement payByBankWire;

	@FindBy(css = "button[class='button btn btn-default button-medium']")
	public static WebElement confirmOrder;

	@FindBy(css = "p[class='cheque-indent']")
	public static WebElement confirmedOrderMessage;

	public void clickOnPayByBankWire() throws InterruptedException {
		Assert.assertTrue(baseActions.isElementDisplayed(payByBankWire, 15));
		baseActions.scrollToView(payByBankWire);
		baseActions.click(payByBankWire);
	}

	public void clickOnConfirmOrder() throws InterruptedException {
		Assert.assertTrue(baseActions.isElementDisplayed(confirmOrder, 15));
		baseActions.scrollToView(confirmOrder);
		baseActions.click(confirmOrder);
	}

	public static void verifyConfirmOrderMessage(String messageToVerify) {
		Assert.assertEquals(confirmedOrderMessage.getText(), messageToVerify);
		logger.info("Message present on screen and verified successfully");
	}

}
