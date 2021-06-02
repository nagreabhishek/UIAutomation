package stepDefinition;

import java.util.List;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddressPage;
import pages.HomePage;
import pages.PaymentPage;
import pages.ProductDetailsPage;
import pages.ShippingPage;
import pages.SigninPage;
import pages.SummaryPage;
import utils.DriverManager;
import utils.PropertiesReader;

public class PlaceOrder extends DriverManager {

	HomePage homepage;
	AddressPage addresspage;
	SigninPage signinpage;
	PaymentPage paymentpage;
	SummaryPage summarypage;
	ShippingPage shippingpage;
	ProductDetailsPage productdetailspage;

	PropertiesReader conf = new PropertiesReader();

	@Given("User launches the website")
	public void user_launches_the_website() throws Throwable {
		DriverManager.launchBrowser();
	}

	@When("User selects the sub category from main category")
	public void user_selects_the_sub_cataegory_from_main_category(DataTable datatable) {
		List<String> data = datatable.asList();
		homepage = new HomePage();
		homepage.selectSubcategory(data.get(1), data.get(0));
	}

	@When("User adds product {string} to the cart")
	public void user_adds_product_to_the_cart(String product) throws InterruptedException {
		productdetailspage = new ProductDetailsPage();
		productdetailspage.addProdToCart(product);
	}

	@When("User clicks on Continue Shopping")
	public void user_clicks_on_continue_shopping() {
		productdetailspage = new ProductDetailsPage();
		productdetailspage.clickOnContToShop();
	}

	@When("User verifies product quantiy in the cart")
	public void user_verifies_product_quantity_in_the_cart() throws InterruptedException {
		productdetailspage = new ProductDetailsPage();
		productdetailspage.verifyCart(2);
	}

	@When("User clicks on Proceed to checkout")
	public void user_clicks_on_proceed_to_checkout() {
		productdetailspage = new ProductDetailsPage();
		productdetailspage.clickOnproceedToCheck();
	}

	@When("User clicks on Proceed to checkout on Summary Page")
	public void user_clicks_on_proceed_to_checkout_on_summary_page() throws InterruptedException {
		summarypage = new SummaryPage();
		summarypage.clickOnSummaryProceedToCheckout();
	}
	
	@When("User enters email address inorder to create account")
	public void user_enters_email_address_inorder_to_create_account() throws InterruptedException {
		summarypage = new SummaryPage();
		summarypage.createAccount();
	}
	
	@When("User furnishes personal information to register")
	public void user_furnishes_personal_information_to_register(DataTable dataTable) throws InterruptedException {
		signinpage = new SigninPage();
		signinpage.enterPersonalInformation(dataTable);
		signinpage.clickOnRegister();
	}
	
	@When("User clicks on Proceed to checkout on Address Page")
	public void user_clicks_on_proceeds_to_checkout_on_address_page() {
		addresspage = new AddressPage();
		addresspage.clickAddressProceedToCheckout();
	}
	
	@When("User proceeds to checkout on Shipping Page by agreeing to the terms")
	public void user_proceeds_to_checkout_on_shipping_page_by_agreeing_to_the_terms() throws InterruptedException {
		shippingpage = new ShippingPage();
		shippingpage.clickOnTermsCondition();
		shippingpage.clickOnShippingProceedToCheckout();
	}

	@When("User selects payment option {string}")
	public void user_selects_payment_option(String string) throws InterruptedException {
		paymentpage = new PaymentPage();
		paymentpage.clickOnPayByBankWire();
	}
	
	@When("User confirms the order")
	public void user_confirms_the_order() throws InterruptedException {
		paymentpage = new PaymentPage();
		paymentpage.clickOnConfirmOrder();
	}
	
	@Then("User receives confirmation message {string}")
	public void user_receives_confirmation_message(String messageToVerify) {
		PaymentPage.verifyConfirmOrderMessage(messageToVerify);
	}	
}