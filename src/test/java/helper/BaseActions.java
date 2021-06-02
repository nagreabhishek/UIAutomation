package helper;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.Base;
import utils.TestGlobals;

public class BaseActions extends Base {
	
	public void addProdToCart(WebElement prod, String product) throws InterruptedException {
		Assert.assertTrue(this.isElementDisplayed(prod, 15));
		this.scrollToView(prod);
		this.hoverOnElement(prod);
		WebElement addToCart = TestGlobals.getWebDriver()
				.findElement(By.xpath("((//h5[@itemprop='name']/child::a[@title='" + product
						+ "'])[1]/following::div/descendant::span[text()='Add to cart'])[1]"));
		Assert.assertTrue(this.isElementDisplayed(addToCart, 5));
		this.click(addToCart);
		logger.info("Successfully clicked on addToCart button");
	}

	public void verifyCart(int prodQty) throws InterruptedException {
		this.scrollToTop();
		WebElement prodCart = TestGlobals.getWebDriver().findElement(By.xpath("//a[@title='View my shopping cart']"));
		WebElement prodQuantity = TestGlobals.getWebDriver()
				.findElement(By.xpath("(//span[@class='ajax_cart_quantity'])[1]"));
		Assert.assertTrue(this.isElementDisplayed(prodCart, 5));
		this.hoverOnElement(prodCart);
		Assert.assertTrue(this.isElementDisplayed(prodQuantity, 5));
		String actualQtyStr = prodQuantity.getText();
		int actualQty = Integer.parseInt(actualQtyStr);
		Assert.assertEquals(actualQty, prodQty);
		logger.info("Successfully verified product quantity in cart: " + actualQty);
	}
	
	public boolean isTitleDisplayed(int explicitWaitSeconds) {
		String title = null;
		try {
			title = TestGlobals.getWebDriver().getTitle();
			WebDriverWait wait = new WebDriverWait(TestGlobals.getWebDriver(), explicitWaitSeconds);
			wait.until(ExpectedConditions.titleIs(title));
		} catch (Exception e) {
			String exceptionMessage = "Expected title is not available " + explicitWaitSeconds
					+ " Seconds.	ExpectedTitle = " + title;
			logger.error(exceptionMessage);
			e.printStackTrace();
		}
		return true;
	}
	
	public String getRandomAlphaNumericValue(Object... objLength) {
		int length = ( (objLength.length > 0) && ((int)(objLength[0]) != 0) ) ? (int) objLength[0] : 10;
		StringBuilder randomValue = new StringBuilder("");
		String characters = "abcdefghijklmnopqrstuvwxABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		while(length > 0)	{
			Random random = new Random();
			randomValue.append(characters.charAt(random.nextInt(characters.length())));
			length --;
		}		
		return String.valueOf(randomValue);
	}
	
	public String getRandomEmailID(Object... objLength)	 {
		int length = ( (objLength.length > 0) && ((int)(objLength[0]) != 0) ) ? (int) objLength[0] : 25;
		return (this.getRandomAlphaNumericValue( length - ("@yahooo.com").length()) + "@yahooo.com");
	}

	public void selectDropdownByText(WebElement webElement, String valueToSelect) {
		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(valueToSelect);
			logger.info("Successfully selected text from the dropdown");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectDropdownByValue(WebElement webElement, String valueToSelect) {
		try {
			Select select = new Select(webElement);
			select.selectByValue(valueToSelect);
			logger.info("Successfully selected value from the dropdown");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void click(WebElement webElement) {
		try {
			if (isElementDisplayed(webElement, 5)) {
				webElement.click();
				logger.info("Successfully clicked on element");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterText(WebElement webElement, String valueToSend) {
		try {
			webElement.clear();
			webElement.sendKeys(valueToSend);
			logger.info("Text entered Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectSubcategory(WebElement category, List<WebElement> clothList, String productName) {
		try {
			String clothing = null;
			Assert.assertTrue(this.isElementDisplayed(category, 5));
			this.hoverOnElement(category);
			for (WebElement ele : clothList) {
				clothing = ele.getText();
				if (clothing.equalsIgnoreCase(productName)) {
					this.click(ele);
					logger.info("Successfully selected subCategory: " + clothing);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToView(WebElement element) throws InterruptedException {
		((JavascriptExecutor) TestGlobals.getWebDriver()).executeScript("arguments[0].scrollIntoView(false);", element);

	}

	public void scrollToTop() throws InterruptedException {
		((JavascriptExecutor) TestGlobals.getWebDriver()).executeScript("window.scrollTo(0, 0);");
		Thread.sleep(500);
	}

	public void hoverOnElement(WebElement element) {
		Actions act = new Actions(TestGlobals.getWebDriver());
		act.moveToElement(element).build().perform();
		act.release();
	}

	public void javaScriptClick(WebElement ele) {
		JavascriptExecutor executor = (JavascriptExecutor) TestGlobals.getWebDriver();
		executor.executeScript("arguments[0].click();", ele);
	}

	public boolean isElementDisplayed(WebElement element, int explicitWaitSeconds) {
		boolean isDisplayed = false;
		try {
			WebDriverWait wait = new WebDriverWait(TestGlobals.getWebDriver(), explicitWaitSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			isDisplayed = true;

		} catch (TimeoutException e) {
			logger.error("Element Not displayed even after waiting for " + explicitWaitSeconds + " Seconds");
			e.printStackTrace();
		}
		return isDisplayed;
	}

}
