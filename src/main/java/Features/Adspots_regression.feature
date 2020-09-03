Feature: Adspots page regression

Scenario:  Verify the pagination exists for the list in the adspots page for admin
	Given admin user login to RX UI with valid username and password 
	When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Verify the pagination of the listed rows in the Page with a selection of 50 rows per page with 12 columns
	
    

Scenario:  Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password 
	When Click on Adspots option under Inventory
	Then User displayed with Adspots page
    And User click on table options button
    Then Verify that column AdSpot Name can be hidden and shown
    
 
Scenario:  Verify onclicking relevant status from table options shows only that particular table rows with that status 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
    Then User click on table options button
    And Verify that column Active/Inactive only shows relevant rows in the table with filter Active
    And Verify that column Active/Inactive only shows relevant rows in the table with filter Inactive
    
Scenario:  Verify searching adspots with avaiable and non available adspot name  
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Verify the search functionality with the following adspot names
	|AdspotName|
	|jay_test|
	|jaya_testad|
	
Scenario:  Verify enabling and disabling of an adspot from the overview page  
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Verify enabling and disabling of an adspot from the overview page
	
#Scenario:  Verify sorting of the table columns of the adspots overview page 
#    Given admin user login to RX UI with valid username and password 
#    When Click on Adspots option under Inventory
#	And User displayed with Adspots page
#	Then Verify the sorting functionality with the following columns
#	|ColumnName|
#	|ID|
#	|Publisher|
#	|Create Date|
	
	