Feature: RX Publisher page Validation
Scenario: Verify if an admin user will be able to login with a valid username and valid password
	Given Admin user click on Login by entering valid username and password 
	When login should be successful and user is displayed the publisher page with Rakuten Exchange
	Then Verify the pagination of the listed rows in the Page with a selection of 5 rows per page with 7 columns