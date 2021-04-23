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
	|TestUpdated|Name|
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
	
Scenario:  Verify mandatory fields in the Create private auction Page 
    Given admin user login to RX UI with valid username and password 
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
	And Click on the Pricate Auction create button
	Then Click on Save Private Auction & Close button
	Then Verify following fields are mandatory for create page
	|FieldName|
	|Publisher Name|
	And Enter the following data in the general card of private auction
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	Then Click on Save Private Auction & Close button
	Then Verify following fields are mandatory for create page
	|FieldName|
	|Name|
	|Date Range|
	
Scenario:  Verify on publisher user login, publisher field is disabled in the Create private auction Page 
    Given Publisher user login to RX UI with valid username and password 
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
	And Click on the Pricate Auction create button
 	Then Verify publisher field is disabled on create/edit page

  Scenario: Verify select/unselect for targeting options
    Given Admin user login by entering valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Select publisher for Private Auction
    Then Verify select/unselect targeting options items
      | Device           | Phone       |
      | Operating System | Android     |
      | Geo              | Afghanistan |
      | Ad Format        | Audio       |
      | Ad Size          | 120x60      |

  Scenario: Verify select/unselect all for targeting options
    Given Admin user login by entering valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Select publisher for Private Auction
    Then Verify select/unselect all targeting options items
      | Device           |
      | Operating System |
      | Geo              |
#      | Ad Format        |
      | Ad Size          |

  Scenario: Verify Search for targeting options
    Given Admin user login by entering valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Select publisher for Private Auction
    Then Verify search targeting options items
      | Device           | Phone       |
      | Operating System | Android     |
      | Geo              | Afghanistan |
      | Ad Format        | Audio       |
      | Ad Size          | 120x60      |

  Scenario:  Verify without selecting publisher the card is not enabled to fill in the Create private auction Page
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
	And Click on the Pricate Auction create button
	Then Verify following fields are not enabled for create page
	|FieldName|
	|Name|
	|Date Range|
	|Active|
	|Always on|
	|Optimize|

Scenario:  Verify default values for toggle fields in the Create private auction Page 
    Given admin user login to RX UI with valid username and password 
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
	And Click on the Pricate Auction create button
	And Enter the following data in the general card of private auction
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	Then Verify input values for following toggle fields in create page
	|FieldName|Active|
	|Active|Yes|
	|Always on|No|
	|Optimize|Yes|
	
Scenario:  Verify Changing publisher name alert the user and then on change every fields go to default state 
    Given admin user login to RX UI with valid username and password 
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
	And Click on the Pricate Auction create button
	And Enter the following data in the general card of private auction
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|1|
	|Name|Test||
	|Date Range|Future||
	And "Disable" following toggle fields in create page
	|FieldName|
	|Active|
	|Always on|
	|Optimize|
	And Enter the following data in the general card of private auction
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	Then Verify the following message is displayed when the publisher changed
	|Message|
	|By changing the Publisher the form will be reset and the previous changes will not be saved.|
	And Select "Cancel" on the publisher change banner
	Then Verify the following columns value with the created data for the general card of private auction
	|FieldName|
	|Name|
	|Date Range|
	And Enter the following data in the general card of private auction
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	And Select "Accept" on the publisher change banner
	Then Verify input values for following toggle fields in create page
	|FieldName|Active|
	|Active|Yes|
	|Always on|No|
	|Optimize|Yes|
	Then Verify the following columns values for the general card of private auction is empty
	|FieldName|
	|Name|
	|Date Range|

Scenario:  Verify successful creation of private auction on clicking \Save and close\
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
	And Click on the Pricate Auction create button
	And Enter the following data in the general card of private auction
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|1|
	|Name|Test||
	|Date Range|Future||
	Then Click on Save and wait for dialog to close
	Then Verify the created private auction data is matching with its overview list values
	Then Verify clicking on Create a deal banner opens create deal entity page

Scenario:  Verify creation of private auction and navigating to create deal page on clicking \Save and create deal\
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
	And Click on the Pricate Auction create button
	And Enter the following data in the general card of private auction
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|1|
	|Name|Test||
	|Date Range|Future||
	Then Click on Save Private Auction & Create Deal button and verify create deal page is opened

Scenario:  Verify successful editing of private auction
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
	And Click on the Pricate Auction create button
	And Enter the following data in the general card of private auction
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|1|
	|Name|Test||
	|Date Range|Future||
	Then Click on Save and wait for dialog to close
	Then Verify the created private auction data is matching with its overview list values
	Then Click on the created auction name in the overview page
	Then Verify publisher field is disabled on create/edit page
	Then Verify the following columns value with the created data for the general card of private auction
	|FieldName|
	|Name|
	|Publisher Name|
	And Enter the following data in the general card of private auction
	|FieldName|Value|ListValueIndex|
	|Name|TestUpdated||
	Then Click on Save and wait for dialog to close
	Then Verify the created private auction data is matching with its overview list values
	Then Click on the created auction name in the overview page
	Then Verify the following columns value with the created data for the general card of private auction
	|FieldName|
	|Name|
	|Publisher Name|