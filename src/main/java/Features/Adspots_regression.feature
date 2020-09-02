Feature: Adspots page regression

Scenario:  Verify the pagination exists for the list and table options filter in the adspots page for admin
	Given admin user login to RX UI with valid username and password 
	When Click on Adspots option under Inventory
	Then User displayed with Adspots page
	Then Verify the pagination of the listed rows in the Page with a selection of 50 rows per page with 12 columns
	
    

Scenario:  Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password 
	When Click on Adspots option under Inventory
	Then User displayed with Adspots page
    Given User click on table options button
    Then Verify that column AdSpot Name can be hidden and shown
    
 
Scenario:  Verify onclicking relevant status from table options shows only status in table rows
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	Then User displayed with Adspots page
    Given User click on table options button
    And Verify that column Active/Inactive only shows relevant rows in the table with filter Active
    And Verify that column Active/Inactive only shows relevant rows in the table with filter Inactive