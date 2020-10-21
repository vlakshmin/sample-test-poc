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
	|Default Sizes|
	|Default Floor Price|
	
	
Scenario:  Verify the pagination exists for the list in the adspots page for admin
	Given admin user login to RX UI with valid username and password 
	When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Verify the pagination of the listed rows in the Page with a selection of 50 rows per page with 7 columns
	
    

Scenario:  Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password 
	When Click on Adspots option under Inventory
	Then User displayed with Adspots page
    And User click on table options button
    Then Verify that column "AdSpot Name" can be hidden and shown
    
 
Scenario:  Verify onclicking relevant status from table options shows only that particular table rows with that status 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
    Then User click on table options button
    And Verify that column "Active/Inactive" only shows relevant rows in the table with filter "Active"
    And Verify that column "Active/Inactive" only shows relevant rows in the table with filter "Inactive"
    
Scenario:  Verify searching adspots with avaiable and non available adspot name  
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Verify the search functionality with the following names
	|Name|CoumnName|
	|jay_test|AdSpot Name|
	|jaya_testad|AdSpot Name|

	
Scenario:  Verify enabling and disabling of an adspot from the overview page  
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
    Then Verify the search functionality with the following names
	|Name|CoumnName|
	|jay_test|AdSpot Name|
	Then Verify enabling and disabling of an adspot from the overview page

	
Scenario:  Verify sorting of the table's columns of the adspots overview page 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Verify the sorting functionality with the following columns
	|ColumnName|SortType|
	|ID|asc|
	|Publisher|desc|
	|Related Media|asc|
	
Scenario:  Verify mandatory fields in the Create Adspot Page 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	And Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	Then Click on save button
	Then Verify following fields are mandatory for create page
	|FieldName|
	|Publisher Name|
	And Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	And "Enable" the in-banner video card
	And "Expand" the in-banner video card
	And Click on save button
	Then Verify following fields are mandatory for create page
	|FieldName|
	|AdSpot Name|
	|Related Media|
	|Position|
	|Default Ad Sizes|
	|Default Floor Price|
	|Playback Methods|
	

Scenario:  Verify Changing publisher name alert the user and then on change every fields go to default state 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	And Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	Then Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|3|
	And Verify Categories filed has subcategories
	Then Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|AdSpot Name|Auto_Test||
	|Related Media|ListValue|2|
	|Categories|ListValue|2|
	|Position|ListValue|2|
	|Filter|ListValue|1|
	|Default Ad Sizes|ListValue|2,3|
	|Default Floor Price|10||
	And "Enable" the banner card
	And "Enable" the native card
	And "Enable" the in-banner video card
	When Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	Then Verify the following message is displayed when the publisher changed
	|Message|
	|By changing the Publisher the form will be reset and the previous changes will not be saved.|
	And Select "Cancel" on the publisher change banner
	Then Verify the following columns value with the created data for the general card of adspot
	|FieldName|
	|AdSpot Name|
	|Related Media|
	|Categories|
	|Position|
	|Filter|
	|Default Ad Sizes|
	|Default Floor Price|
	And Verify the banner card is "Enabled"
	And Verify the native card is "Enabled"
	And Verify the in-banner video card is "Enabled"
	And Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	And Select "Accept" on the publisher change banner
	Then Verify the following columns values for the general card of adspot is empty
	|FieldName|
	|AdSpot Name|
	|Related Media|
	|Categories|
	|Position|
	|Filter|
	|Default Ad Sizes|
	|Default Floor Price|
	Then Verify the banner card is "Disabled"
	And Verify the native card is "Disabled"
	And Verify the in-banner video card is "Disabled"
	

	
Scenario: Verify with invalid value for floor price and adsizes>2 creation is unsuccessful 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	Then Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	And Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	|Active|Active||
	|AdSpot Name|Auto_Test||
	|Related Media|ListValue|2|
	|Categories|ListValue|2|
	|Position|ListValue|2|
	|Filter|ListValue|2|
	|Default Ad Sizes|ListValue|2,3,4|
	|Default Floor Price|-10||
	And "Enable" the banner card
	And "Expand" the banner card
	Then Enter the following data in the banner card of adspot
	|FieldName|Value|ListValueIndex|
	|Ad Sizes|ListValue|2,3,4|
	|Floor Price|-10||
	And "Enable" the native card
	And "Expand" the native card
	Then Enter the following data in the native card of adspot
	|FieldName|Value|
	|Floor Price|-10|
	And "Enable" the in-banner video card
	And "Expand" the in-banner video card
	Then Enter the following data in the in-banner video card of adspot
	|FieldName|Value|ListValueIndex|
	|Ad Sizes|ListValue|2,3,4|
	|Floor Price|-10||
	|Minimum Video Duration|ListValue|1|
	|Maximum Video Duration|ListValue|1|
	|Playback Methods|ListValue|1,2,3|
	And Click on save button
	Then Verify error messages for sizes and floor price for the following cards
	|Card|SizeErrorMsg|FloorPriceErrorMsg|
	|General|Default Ad Sizes must be 2 or less|Floor price must be between 0 and 10,000.00|
	|Banner|Ad Sizes must be 2 or less|Floor price must be between 0 and 10,000.00|
	|Native||Floor price must be between 0 and 10,000.00|
	|InBannerVideo|Ad Sizes must be 2 or less|Floor price must be between 0 and 10,000.00| 
	
Scenario: Verify successful creation and updation of an adspot 
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	When Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	Then Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	|Active|Active||
	|AdSpot Name|Auto_Test||
	|Related Media|ListValue|2|
	|Categories|ListValue|2|
	|Position|ListValue|2|
	|Filter|ListValue|2|
	|Default Ad Sizes|ListValue|2,3|
	|Default Floor Price|10||
	And "Enable" the banner card
	And "Expand" the banner card
	Then Enter the following data in the banner card of adspot
	|FieldName|Value|ListValueIndex|
	|Ad Sizes|ListValue|2,3|
	|Floor Price|5||
	And "Enable" the native card
	And "Expand" the native card
	Then Enter the following data in the native card of adspot
	|FieldName|Value|
	|Floor Price|5|
	And "Enable" the in-banner video card
	And "Expand" the in-banner video card
	Then Enter the following data in the in-banner video card of adspot
	|FieldName|Value|ListValueIndex|
	|Ad Sizes|ListValue|2,3|
	|Floor Price|5||
	|Minimum Video Duration|ListValue|1|
	|Maximum Video Duration|ListValue|1|
	|Playback Methods|ListValue|1,2,3|
	And Click on save button and wait for dialog to close
	Then Verify the created adspot data is matching with its overview list values
	When Click on the created adspotname in the overview page
	And Verify following fields are disabled on create/edit adspot page
	|FieldName|
	|Publisher Name|
	|Related Media|
	Then Verify the following columns value with the created data for the general card of adspot
	|FieldName|
	|Publisher Name|
	|Active|
	|AdSpot Name|
	|Related Media|
	|Categories|
	|Position|
	|Filter|
	|Default Ad Sizes|
	|Default Floor Price|
	And Verify the banner card is "Enabled"
	And Verify the native card is "Enabled"
	And Verify the in-banner video card is "Enabled"
	Then "Expand" the banner card
	And "Expand" the native card
	And "Expand" the in-banner video card
	Then Verify the following columns value with the created data for the banner card of adspot
	|FieldName|
	|Ad Sizes|
	|Floor Price|
	Then Verify the following columns value with the created data for the native card of adspot
	|FieldName|
	|Floor Price|
	Then Verify the following columns value with the created data for the in-banner video card of adspot
	|FieldName|
	|Ad Sizes|
	|Floor Price|
	|Minimum Video Duration|
	|Maximum Video Duration|      
	|Playback Methods|
	
	#Editing flow
	When Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Active|Inactive||
	|AdSpot Name|Auto_Test_Edit||
	|Categories|ListValue|2,4|
	|Position|ListValue|1|
	|Filter|ListValue|1|
	|Default Ad Sizes|ListValue|2,4|
	|Default Floor Price|8||
	And Enter the following data in the banner card of adspot
	|FieldName|Value|ListValueIndex|
	|Ad Sizes|ListValue|2,4|
	|Floor Price|6||
	And Enter the following data in the native card of adspot
	|FieldName|Value|
	|Floor Price|6|
	And Enter the following data in the in-banner video card of adspot
	|FieldName|Value|ListValueIndex|
	|Ad Sizes|ListValue|2,4|
	|Floor Price|6||
	|Minimum Video Duration|ListValue|2|
	|Maximum Video Duration|ListValue|2|
	|Playback Methods|ListValue|1,2,4|
	Then Click on save button and wait for dialog to close
	Then Verify the created adspot data is matching with its overview list values
	And Click on the created adspotname in the overview page
	Then Verify the following columns value with the created data for the general card of adspot
	|FieldName|
	|Publisher Name|
	|Active|
	|AdSpot Name|
	|Related Media|
	|Categories|
	|Position|
	|Filter|
	|Default Ad Sizes|
	|Default Floor Price|
	Then Verify the banner card is "Enabled"
	And Verify the native card is "Enabled"
	And Verify the in-banner video card is "Enabled"
	Then "Expand" the banner card
	And "Expand" the native card
	And "Expand" the in-banner video card
	Then Verify the following columns value with the created data for the banner card of adspot
	|FieldName|
	|Ad Sizes|
	|Floor Price|
	Then Verify the following columns value with the created data for the native card of adspot
	|FieldName|
	|Floor Price|
	Then Verify the following columns value with the created data for the in-banner video card of adspot
	|FieldName|
	|Ad Sizes|
	|Floor Price|
	|Minimum Video Duration|
	|Maximum Video Duration|
	|Playback Methods|
		
Scenario: Verify adspot can be created without any cards enabled when is inactive but for 
editing with activing the same adspot the edit is unsuccessful

    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	And Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	Then Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	|Active|Inactive||
	|AdSpot Name|Auto_Test||
	|Related Media|ListValue|2|
	|Categories|ListValue|2|
	|Position|ListValue|2|
	|Filter|ListValue|2|
	|Default Ad Sizes|ListValue|2,3|
	|Default Floor Price|10||
	And Click on save button and wait for dialog to close
	Then Verify the created adspot data is matching with its overview list values
	When Click on the created adspotname in the overview page
	And Verify following fields are disabled on create/edit adspot page
	|FieldName|
	|Publisher Name|
	|Related Media|
	Then Verify the following columns value with the created data for the general card of adspot
	|FieldName|
	|Publisher Name|
	|Active|
	|AdSpot Name|
	|Related Media|
	|Categories|
	|Position|
	|Filter|
	|Default Ad Sizes|
	|Default Floor Price|
	
	#Editing flow
	When Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Active|Active||
	And Click on save button
	Then Verify the save is failed
	
Scenario: Verify without adding any card ,creation of adspot is unsuccessful when the adspot is active

    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	When Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	Then Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|ListValue|2|
	|Active|Active||
	|AdSpot Name|Auto_Test||
	|Related Media|ListValue|2|
	|Categories|ListValue|2|
	|Position|ListValue|2|
	|Filter|ListValue|2|
	|Default Ad Sizes|ListValue|2,3|
	|Default Floor Price|10||
	And Click on save button
	Then Verify the save is failed
	
	
Scenario: Verify after creation of adspot if the publisher is disabled the editing of the adspot is unsuccessful
    Given admin user login to RX UI with valid username and password 
    When Click on Adspots option under Inventory
	And User displayed with Adspots page
	When Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	Then Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|TestPublisher_Jay||
	|Active|Inactive||
	|AdSpot Name|Auto_Test||
	|Related Media|ListValue|2|
	|Categories|ListValue|2|
	|Position|ListValue|2|
	|Default Ad Sizes|ListValue|2,3|
	|Default Floor Price|10||
	And Click on save button and wait for dialog to close
	Then Verify the created adspot data is matching with its overview list values
	And Click on publisher option under account
	And Publisher page should be displayed
	Then Verify the search functionality with the following names
	|Name|CoumnName|
	|TestPublisher_Jay|Publisher|
	And "Disable" a publisher from the publisher overview page
	When Click on Adspots option under Inventory
	And User displayed with Adspots page
	And Click on the created adspotname in the overview page
	Then Verify following fields are disabled on create/edit adspot page
	|FieldName|
	|Related Media|
	And Click on save button
	Then Verify the save is failed
	And User displayed with Adspots page
	And Click on publisher option under account
	And Publisher page should be displayed
	Then Verify the search functionality with the following names
	|Name|CoumnName|
	|TestPublisher_Jay|Publisher|
	And "Enable" a publisher from the publisher overview page

Scenario: Verify creation of adspot is successful if the related media is disabled
    Given admin user login to RX UI with valid username and password 
    When Click on Media option under Inventory
	And User displayed with media page
	Then Verify the search functionality with the following names
	|Name|CoumnName|
	|Mark Mc Desktop Site|Media Name|
	And "Disable" a media from the media overview page
	Then Click on Adspots sub menu
	And User displayed with Adspots page
	When Click on the following create button
	|CreateButtonName|
	|Create AdSpot|
	Then Enter the following data in the general card of adspot
	|FieldName|Value|ListValueIndex|
	|Publisher Name|Mark Mceachran||
	|Active|Inactive||
	|AdSpot Name|Auto_Test||
	|Related Media|Mark Mc Desktop Site||
	|Categories|ListValue|2|
	|Position|ListValue|2|
	|Default Ad Sizes|ListValue|2,3|
	|Default Floor Price|10||
	And Click on save button and wait for dialog to close
	And User displayed with Adspots page
	When Click on Media sub menu
	And User displayed with media page
	Then Verify the search functionality with the following names
	|Name|CoumnName|
	|Mark Mc Desktop Site|Media Name|
	And "Enable" a media from the media overview page
	
	
#Scenario: Verify after creation of adspot if the related media is disabled the editing of the adspot is unsuccessful
#    Given admin user login to RX UI with valid username and password 
#    When Click on Adspots option under Inventory
#	And User displayed with Adspots page
#	When Click on the following create button
#	|CreateButtonName|
#	|Create AdSpot|
#	Then Enter the following data in the general card of adspot
#	|FieldName|Value|ListValueIndex|
#	|Publisher Name|Mark Mceachran||
#	|Active|Inactive||
#	|AdSpot Name|Auto_Test||
#	|Related Media|Mark Mc Desktop Site||
#	|Categories|ListValue|2|
#	|Position|ListValue|2|
#	|Default Ad Sizes|ListValue|2,3|
#	|Default Floor Price|10||
#	Then Click on save button and wait for dialog to close
#	Then Verify the created adspot data is matching with its overview list values
#	Then Click on Media sub menu
#	Then User displayed with media page
#	Then Verify the search functionality with the following names
#	|Name|CoumnName|
#	|Mark Mc Desktop Site|Media Name|
#	Then "Disable" a media from the media overview page
#	Then Click on Adspots sub menu
#	And User displayed with Adspots page
#	And Click on the created adspotname in the overview page
#	And Verify following fields are disabled on create/edit adspot page
#	|FieldName|
#	|publisher Name|
#	|Related Media|
#	Then Click on save button
#	Then Verify the save is failed
#	And User displayed with Adspots page
#	Then Click on Media sub menu
#	Then User displayed with media page
#	Then Verify the search functionality with the following names
#	|Name|CoumnName|
#	|Mark Mc Desktop Site|Media Name|
#	Then "Enable" a media from the media overview page

