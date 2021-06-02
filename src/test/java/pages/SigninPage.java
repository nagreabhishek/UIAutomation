package pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import helper.BaseActions;
import io.cucumber.datatable.DataTable;
import utils.Base;
import utils.TestGlobals;

public class SigninPage extends Base {

	BaseActions baseActions = null;

	public SigninPage() {
		baseActions = new BaseActions();
		PageFactory.initElements(TestGlobals.getWebDriver(), this);
	}

	@FindBy(xpath = "//div[@class='radio-inline'][1]")
	public static WebElement title1;

	@FindBy(xpath = "//div[@class='radio-inline'][2]")
	public static WebElement title2;

	@FindBy(xpath = "(//div[@class='clearfix'])[2]")
	public static WebElement scrollTo;

	@FindBy(id = "customer_firstname")
	public static WebElement firstName;

	@FindBy(id = "customer_lastname")
	public static WebElement lastName;

	@FindBy(id = "passwd")
	public static WebElement password;

	@FindBy(xpath = "(//select[@class='form-control'])[1]")
	public static WebElement day;

	@FindBy(xpath = "(//select[@class='form-control'])[2]")
	public static WebElement month;

	@FindBy(xpath = "(//select[@class='form-control'])[3]")
	public static WebElement year;

	@FindBy(id = "company")
	public static WebElement company;

	@FindBy(xpath = "(//p[@class='required form-group'])[3]/child::input")
	public static WebElement address;

	@FindBy(id = "city")
	public static WebElement city;

	@FindBy(xpath = "(//select[@class='form-control'])[4]")
	public static WebElement state;

	@FindBy(xpath = "//p[@class='required postcode form-group']/child::input[@type='text']")
	public static WebElement postalCode;

	@FindBy(xpath = "(//select[@class='form-control'])[5]")
	public static WebElement country;

	@FindBy(id = "phone_mobile")
	public static WebElement mobilePhone;

	@FindBy(id = "submitAccount")
	public static WebElement register;

	

	public void enterPersonalInformation(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> map = dataTable.asMaps(String.class, String.class);
		Map<String, String> userInfo = map.get(0);
		
		String title = userInfo.get("Title");
		if (title.equalsIgnoreCase("Mr.")) {
			Assert.assertTrue(baseActions.isElementDisplayed(scrollTo, 15));
			baseActions.scrollToView(scrollTo);
			baseActions.click(title1);
			logger.info("Successfully selected title: " + title1);
		} else if (title.equalsIgnoreCase("Mr.")) {
			baseActions.scrollToView(title2);
			Assert.assertTrue(baseActions.isElementDisplayed(title2, 5));
			baseActions.click(title2);
			logger.info("Successfully selected title: " + title2);
		}

		Assert.assertTrue(baseActions.isElementDisplayed(firstName, 5));
		baseActions.enterText(firstName, userInfo.get("FirstName"));
		logger.info("Successfully entered firstname: " + userInfo.get("FirstName"));

		Assert.assertTrue(baseActions.isElementDisplayed(lastName, 5));
		baseActions.enterText(lastName, userInfo.get("LastName"));
		logger.info("Successfully entered lastname: " + userInfo.get("LastName"));

		Assert.assertTrue(baseActions.isElementDisplayed(password, 5));
		baseActions.enterText(password, userInfo.get("Password"));
		logger.info("Successfully entered password: " + userInfo.get("Password"));

		String dob = userInfo.get("DoB");
		String[] splitDOB = dob.split("/");
		System.out.println(splitDOB[1]);
		baseActions.selectDropdownByValue(day, splitDOB[0]);
		baseActions.selectDropdownByValue(month, splitDOB[1]);
		baseActions.selectDropdownByValue(year, splitDOB[2]);
		logger.info("Successfully selected DoB: " + dob);

		baseActions.scrollToView(company);
		Assert.assertTrue(baseActions.isElementDisplayed(company, 5));
		baseActions.enterText(company, userInfo.get("Company"));
		logger.info("Successfully entered company: " + userInfo.get("Company"));

		Assert.assertTrue(baseActions.isElementDisplayed(address, 5));
		baseActions.enterText(address, userInfo.get("Address"));
		logger.info("Successfully entered address: " + userInfo.get("Address"));

		Assert.assertTrue(baseActions.isElementDisplayed(city, 5));
		baseActions.enterText(city, userInfo.get("City"));
		logger.info("Successfully entered city: " + userInfo.get("City"));

		baseActions.selectDropdownByText(state, userInfo.get("State"));
		logger.info("Successfully selected state: " + userInfo.get("State"));

		Assert.assertTrue(baseActions.isElementDisplayed(postalCode, 5));
		baseActions.enterText(postalCode, userInfo.get("Zip"));
		logger.info("Successfully entered postalcode: " + userInfo.get("Zip"));

		Assert.assertTrue(baseActions.isElementDisplayed(mobilePhone, 5));
		baseActions.enterText(mobilePhone, userInfo.get("Mobile"));
		logger.info("Successfully entered mobilePhone: " + userInfo.get("Mobile"));
	}

	public void clickOnRegister() {
		baseActions.click(register);
	}

}
