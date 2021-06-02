package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import helper.BaseActions;
import utils.TestGlobals;

public class ProductDetailsPage {

	BaseActions baseActions = null;

	public ProductDetailsPage() {
		baseActions = new BaseActions();
		PageFactory.initElements(TestGlobals.getWebDriver(), this);
	}

	@FindBy(xpath = "(//h5[@itemprop='name']/child::a[@title='Printed Dress'])[1]")
	public static WebElement prodPrintedDress;

	@FindBy(xpath = "(//h5[@itemprop='name']/child::a[@title='Blouse'])[1]")
	public static WebElement prodBlouse;

	@FindBy(xpath = "//span[@class='continue btn btn-default button exclusive-medium']/descendant::i[@class='icon-chevron-left left']")
	public static WebElement contToShop;
	
	@FindBy(xpath = "//a[@class='btn btn-default button button-medium']/descendant::i[@class='icon-chevron-right right']")
	public static WebElement proceedToCheck;

	@FindBy(xpath = "//ul[contains(@class,'sf-menu clearfix menu-content')]/descendant::a[@title='T-shirts'][2]")
	public static WebElement category_TShirts;

	public void addProdToCart(String product) throws InterruptedException {
		if (product.equalsIgnoreCase("Printed Dress")) {
			baseActions.addProdToCart(prodPrintedDress, product);
		} else if (product.equalsIgnoreCase("Blouse")) {
			baseActions.addProdToCart(prodBlouse, product);
		}
	}

	public void clickOnContToShop() {
		Assert.assertTrue(baseActions.isElementDisplayed(contToShop, 5));
		baseActions.click(contToShop);
	}
	
	public void clickOnproceedToCheck() {
		Assert.assertTrue(baseActions.isElementDisplayed(proceedToCheck, 5));
		baseActions.click(proceedToCheck);
	}

	public void verifyCart(int prodQty) throws InterruptedException {
		baseActions.verifyCart(prodQty);
	}

	public void selectSubcategory(String category, String subCategory) {
		HomePage hpage = new HomePage();
		hpage.selectSubcategory(category, subCategory);
	}
}
