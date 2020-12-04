Feature: Private Auctions page regression

Scenario:  Verify the default column names in the private auctions overview page
	Given admin user login to RX UI with valid username and password 
	When Click on Private Auctions option under Sales
	And User displayed with Private Auctions page
	Then Verify the overview page contains following columns
	|ColumnName|
	|ID|
	|Name|
	|Publisher|
	|Optimize|
	|Active|
	|Related Deals|
	
	
	
Scenario:  Verify the pagination exists for the list in the private auctions page for admin
	Given admin user login to RX UI with valid username and password 
	When Click on Private Auctions option under Sales
	And User displayed with Private Auctions page
	Then Verify the pagination of the listed rows in the Page with a selection of 50 rows per page with 6 columns
	
    

Scenario:  Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password 
	When Click on Private Auctions option under Sales
	And User displayed with Private Auctions page
    And User click on table options button
    Then Verify that column "Name" can be hidden and shown
    
 
Scenario:  Verify onclicking relevant status from table options shows only that particular table rows with that status 
    Given admin user login to RX UI with valid username and password 
    When Click on Private Auctions option under Sales
	And User displayed with Private Auctions page
    Then User click on table options button
    And Verify that column "Active" only shows relevant rows in the table with filter "Active"
    And Verify that column "Active" only shows relevant rows in the table with filter "Inactive"
    
Scenario:  Verify searching private auction with available and non available private auction name  
    Given admin user login to RX UI with valid username and password 
    When Click on Private Auctions option under Sales
	And User displayed with Private Auctions page
	Then Verify the search functionality with the following names
	|Name|CoumnName|
	|reg_test|Name|
	|reg_testa|Name|

	
Scenario:  Verify enabling and disabling of a private auction from the overview page  
    Given admin user login to RX UI with valid username and password 
    When Click on Private Auctions option under Sales
	And User displayed with Private Auctions page
    Then Verify the search functionality with the following names
	|Name|CoumnName|
	|reg_test|Name|
	Then Verify enabling and disabling of an auction from the overview page

	
Scenario:  Verify sorting of the list's columns of the private auctions overview page 
    Given admin user login to RX UI with valid username and password 
    When Click on Private Auctions option under Sales
	And User displayed with Private Auctions page
	Then Verify the sorting functionality with the following columns
	|ColumnName|SortType|
	|ID|asc|
	|Publisher|desc|
	|Active|asc|
	
