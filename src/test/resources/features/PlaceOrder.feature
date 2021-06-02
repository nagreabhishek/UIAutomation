Feature: Place an order for two items
Description: Smoke test to be able to add two items to the cart and place an order

@orderTwoProducts
Scenario: Place an order by selecting two different products from the sub category
	Given User launches the website
	When User selects the sub category from main category
		| Casual Dresses | Dresses |
	And User adds product "Printed Dress" to the cart
	And User clicks on Continue Shopping
	And User selects the sub category from main category
		| Blouses | Women |
	And User adds product "Blouse" to the cart
	And User clicks on Proceed to checkout
	And User verifies product quantiy in the cart
	And User clicks on Proceed to checkout on Summary Page
	And User enters email address inorder to create account 
	And User furnishes personal information to register
		| Title | FirstName | LastName | Password | DoB			  | Company  | Address   | City   | State  | Zip   | Mobile 	  |
		| Mr.		| Abhi			| Nagre	   | Abhi$499 | 19/1/2010 | WooliesX | 2 Good St | Sydney | Alaska | 00000 | 0481019322 |
	And User clicks on Proceed to checkout on Address Page
	And User proceeds to checkout on Shipping Page by agreeing to the terms
	And User selects payment option "Pay by bank wire" 
	And User confirms the order
	Then User receives confirmation message "Your order on My Store is complete."
