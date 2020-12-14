Feature: Deals page regression

Scenario:  Verify the default column names in the deals overview page
	Given admin user login to RX UI with valid username and password 
	When Click on Deals option under Sales
	And User displayed with Deals page
	Then Verify the deal overview page contains following columns
	|ColumnName|
	|ID|
	|Name|
	|Publisher|
	|Private Auction|
	|Active|
	|DSP|
	|Related Proposal|
	|Start Date|
	|End Date|
	|Value|
	|Currency|
	

Scenario:  Verify the pagination exists for the list in the deals page for admin
	Given admin user login to RX UI with valid username and password
	When Click on Deals option under Sales
	And User displayed with Deals page
	Then Verify the pagination of the listed rows in the Page with a selection of 50 rows per page with 11 columns



Scenario:  Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password
	When Click on Deals option under Sales
	And User displayed with Deals page
    And User click on table options button
    Then Verify that column "Name" can be hidden and shown


Scenario:  Verify onclicking relevant status from table options shows only that particular table rows with that status
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    Then User click on table options button
    And Verify that column "Active" only shows relevant rows in the table with filter "Active"
    And Verify that column "Active" only shows relevant rows in the table with filter "Inactive"

Scenario:  Verify searching deal with available and non available deal name
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
	Then Verify the search functionality with the following names
	|Name|CoumnName|
	|testings|Name|
	|testingss|Name|


Scenario:  Verify enabling and disabling of a deal from the overview page
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    Then Verify the search functionality with the following names
	|Name|CoumnName|
	|testings|Name|
	Then Verify enabling and disabling of a deal from the overview page


Scenario:  Verify sorting of the list's columns of the deals overview page
    Given admin user login to RX UI with valid username and password
   When Click on Deals option under Sales
	And User displayed with Deals page
	Then Verify the sorting functionality with the following columns
	|ColumnName|SortType|
	|ID|asc|
	|Publisher|desc|
	|Active|asc|
	|DSP|desc|
	|Start Date|desc|
	|End Date|desc|
	
Scenario: Verify if create deal menu is opened
    Verify sorting of the list's columns of the deals overview page
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Click on publisher input
    And Select publisher by name: "Viber"
