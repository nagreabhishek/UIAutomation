package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import helper.BaseActions;
import utils.TestGlobals;

public class HomePage {

	BaseActions baseActions = null;

	public HomePage() {
		baseActions = new BaseActions();
		PageFactory.initElements(TestGlobals.getWebDriver(), this);
	}

	@FindBy(xpath = "//ul[contains(@class,'sf-menu clearfix menu-content')]/descendant::a[@title='Dresses'][2]")
	public static WebElement category_DressesTab;
	
	@FindBy(xpath = "//ul[contains(@class,'sf-menu clearfix menu-content')]/descendant::a[@title='Women'][1]")
	public static WebElement category_WomenTab;

	@FindBy(xpath = "//a[@title='Dresses']/following-sibling::ul[@class='submenu-container clearfix first-in-line-xs']/descendant::a")
	public static List<WebElement> list_Under_DressesTab;
	
	@FindBy(xpath = "//a[@title='Women']/following-sibling::ul[@class='submenu-container clearfix first-in-line-xs']/descendant::a")
	public static List<WebElement> list_Under_WomenTab;

	public void selectSubcategory(String category, String subCategory) {
		if (category.equalsIgnoreCase("Dresses")) {
			baseActions.selectSubcategory(category_DressesTab, list_Under_DressesTab, subCategory);
		} else if (category.equalsIgnoreCase("Women")){
			baseActions.selectSubcategory(category_WomenTab, list_Under_WomenTab, subCategory);
		} else {
			throw new IllegalArgumentException("Specified category does not have a subcategory - " + subCategory);
		}
	}

}
