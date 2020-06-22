Feature: Targeting page validation

Scenario:  Verify the pagination exists for the list in the targeting page for admin
	Given admin user login to RX UI with valid username and password 
	When Click on Targeting option under Rules
	Then User displayed with targeting page
	Then Verify the pagination of the listed rows in the Page with a selection of 5 rows per page with 2 columns
	

Scenario:  Verify the pagination exists for the list in the targeting page for publisher
	Given Publisher user login to RX UI with valid username and password 
	When Click on Targeting option under Rules
	Then User displayed with targeting page
	Then Verify the pagination of the listed rows in the Page with a selection of 5 rows per page with 2 columns