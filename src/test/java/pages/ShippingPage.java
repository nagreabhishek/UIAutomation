package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import helper.BaseActions;
import utils.TestGlobals;

public class ShippingPage {

	BaseActions baseActions = null;

	public ShippingPage() {
		baseActions = new BaseActions();
		PageFactory.initElements(TestGlobals.getWebDriver(), this);
	}

	@FindBy(xpath = "//div[@class='checker']/descendant::input")
	public static WebElement termsCondition;

	@FindBy(css = "button[name='processCarrier']")
	public static WebElement shippingProceedToCheckout;

	public void clickOnTermsCondition() throws InterruptedException {
		Assert.assertTrue(baseActions.isElementDisplayed(shippingProceedToCheckout, 15));
		baseActions.scrollToView(shippingProceedToCheckout);
		baseActions.javaScriptClick(termsCondition);
	}

	public void clickOnShippingProceedToCheckout() {
		baseActions.click(shippingProceedToCheckout);
	}

}
