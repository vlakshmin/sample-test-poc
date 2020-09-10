Feature: Adspots page regression

Scenario:  Verify the column names in the adspots overview page
	Given admin user login to RX UI with valid username and password 
	When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Verify the overview page contains following columns
	|ColumnName|
	|ID|
	|AdSpot Name|
	|Publisher|
	|Related Media|
	|Active/Inactive|
	|Page Category|
	|Filter|
	|Test Mode|
	|Default Sizes|
	|Default Floor Price|
	|Create Date|
	|Update Date|
	
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

	
Scenario:  Verify sorting of the table's columns of the adspots overview page 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Verify the sorting functionality with the following columns
	|ColumnName|SortType|
	|ID|asc|
	|Publisher|desc|
	|Create Date|desc|
	|Related Media|asc|
	
Scenario:  Verify mandatory fields in the Create Adspot Page 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	Then Click on save button
	Then Verify following fields are mandatory for create page
	|FieldName|
	|Publisher Name|
	When Select publisher name from the dropdown list item index 3
	And Click on save button
	Then Verify following fields are mandatory for create page
	|FieldName|
	|AdSpot Name|
	|Related Media|
	|Categories|
	|Position|
	|Default Ad Sizes|
	|Default Floor Price|

Scenario:  Verify Changing publisher name alert the user and then on change every fields go to default state 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	When Select publisher name from the dropdown list item index 3
	Then Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|AdSpot Name|Auto_Test||
	|Related Media|ListValue|2|
	|Categories|ListValue|2|
	|Position|ListValue|2|
	|Filter|ListValue|2|
	|Default Ad Sizes|ListValue|2,3|
	|Default Floor Price|10||
	When Select publisher name from the dropdown list item index 2
	Then Verify the following message is displayed when the publisher changed
	|Message|
	|By changing the Publisher the form will be reset and the previous changes will not be saved.|
	And Select Cancel on the publisher change banner
	Then Verify the following columns value with the entered data for the general card of adspot
	|FieldName|
	|AdSpot Name|
	|Related Media|
	|Categories|
	|Position|
	|Filter|
	|Default Ad Sizes|
	|Default Floor Price|
	When Select publisher name from the dropdown list item index 2
	And Select Accept on the publisher change banner
	Then Verify the following columns values for the general card of adspot is empty
	|FieldName|
	|AdSpot Name|
	|Related Media|
	|Categories|
	|Position|
	|Filter|
	|Default Ad Sizes|
	|Default Floor Price|
	