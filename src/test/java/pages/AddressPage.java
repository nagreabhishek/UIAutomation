package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helper.BaseActions;
import utils.Base;
import utils.TestGlobals;

public class AddressPage extends Base{

	public static WebDriver driver;

	BaseActions baseActions = null;

	public AddressPage() {
		baseActions = new BaseActions();
		PageFactory.initElements(TestGlobals.getWebDriver(), this);
	}

	@FindBy(xpath = "//button[@type='submit' and @name='processAddress']")
	public static WebElement addressProceedToCheckout;

	public void clickAddressProceedToCheckout() {
		baseActions.click(addressProceedToCheckout);
	}
}
