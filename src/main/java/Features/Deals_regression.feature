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
	|Price \| Value|
	|Price \| Currency|
	|Start Date|
	|End Date|


Scenario:  Verify the pagination exists for the list in the deals page for admin
	Given admin user login to RX UI with valid username and password
	When Click on Deals option under Sales
	And User displayed with Deals page
	Then Verify the pagination of the listed rows in the Page with a selection of 20 rows per page with 12 columns



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
	|RTBHouse Deals|Name|


Scenario:  Verify enabling and disabling of a deal from the overview page
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    Then Verify the search functionality with the following names
	|Name|CoumnName|
	|RTBHouse Deal|Name|
	Then Verify enabling and disabling of a deal from the overview page


Scenario:  Verify sorting of the list's columns of the deals overview page
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
	Then Verify the sorting functionality with the following columns
	|ColumnName|SortType|
	|ID|desc|
	|Publisher|desc|
	|Active|asc|
	|DSP|desc|
	|Start Date|desc|
	|End Date|desc|

Scenario: Verify if create deal menu is opened
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Click on publisher input
    And Select publisher by name: "Viber"
		Then Verify the currency is correct
		And Verify required fields



#Scenario:  Verify onclicking relevant status from table options shows only that particular table rows with that status
#    Given admin user login to RX UI with valid username and password
#    When Click on Deals option under Sales
#	And User displayed with Deals page
#    Then User click on table options button
#    And Verify that column "Active" only shows relevant rows in the table with filter "Active"
#    And Verify that column "Active" only shows relevant rows in the table with filter "Inactive"

#Scenario:  Verify searching deal with available and non available deal name
#    Given admin user login to RX UI with valid username and password
#    When Click on Deals option under Sales
#	And User displayed with Deals page
#	Then Verify the search functionality with the following names
#	|Name|CoumnName|
#	|testings|Name|
#	|testingss|Name|


#Scenario:  Verify enabling and disabling of a deal from the overview page
#    Given admin user login to RX UI with valid username and password
#    When Click on Deals option under Sales
#	And User displayed with Deals page
#    Then Verify the search functionality with the following names
#	|Name|CoumnName|
#	|testings|Name|
#	Then Verify enabling and disabling of a deal from the overview page


#Scenario:  Verify sorting of the list's columns of the deals overview page
#   Given admin user login to RX UI with valid username and password
#   When Click on Deals option under Sales
#	And User displayed with Deals page
#	Then Verify the sorting functionality with the following columns
#	|ColumnName|SortType|
#	|ID|asc|
#	|Publisher|desc|
#	|Active|asc|
#	|DSP|desc|
#	|Start Date|desc|
#	|End Date|desc|

#Scenario: Verify if create deal menu is opened
#    Given admin user login to RX UI with valid username and password
#    When Click on Deals option under Sales
#	And User displayed with Deals page
#    And Click create a new deal
#    Then Create deal menu is opened
#    And Click on publisher input
#    And Select publisher by name: "Viber"
#		Then Verify the currency is correct
#		And Verify required fields


Scenario: Verify that the Alert message displayed for changing the publisher for deal,on cancel retain the filled value and accept removed the filled values.
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Click on publisher input
  	And enter the following values
  	|publisher|PrivateAuction|DSPValue|EntDealName|Values|
	|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
	And "Enable" the DSP buyer
	And enter the following DSP buyer details.
		|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
		|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
 	When change the publisher name to "Viki"
  	Then Verify the following message is displayed when the publisher changed for deal
	|Message|
	|By changing the Publisher the form will be reset and the previous changes will not be saved.|
	And Select "Cancel" on the publisher change banner displayed for deal
	Then Verify the following general value with the created data of deal
	|FieldName|
	|Publisher Name|
	|Deal Name|
	|Private Auction|
	|Date Range|
	|Value|
	|Currency|
	|DSP|
	And Verify the following buyers details with the created data of deal
	|FieldName|
	|DSP Seat Name|
	|Advertiser ID|
	|Advertiser Name|
	|DSP Seat Passthrough String|
	|DSP Domain Advertiser Passthrough String|
#	|Related Proposal|
	And Verify the buyer is "Disabled"
	When change the publisher name to "Viki"
	Then Verify the following message is displayed when the publisher changed for deal
	|Message|
	|By changing the Publisher the form will be reset and the previous changes will not be saved.|
	And Select "Accept" on the publisher change banner displayed for deal
  	Then Verify the following general values are reset to default values
	|FieldName|
	|Publisher Name|
	|Deal Name|
	|Private Auction|
	|Date Range|
	|Value|
	|Currency|
	|DSP|
	And Verify the following buyers details are reset to default values
	|FieldName|
	|DSP Seat Name|
	|Advertiser ID|
	|Advertiser Name|
	|DSP Seat Passthrough String|
	|DSP Domain Advertiser Passthrough String|
#	|Related Proposal|
	And Verify the buyer is "Enabled"


    Scenario: Verify that the user is not allowed to fill other fields without selecting the publisher.
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Verify the following general deal details are not fillable
    |FieldName|
	|Deal Name|
	|Private Auction|
	|Date Range|
	|Value|
	|Currency|
	|DSP|
	And Verify the following buyer details of deal are not fillable
	|FieldName|
	|DSP Seat Name|
	|Advertiser ID|
	|Advertiser Name|
	|DSP Seat Passthrough String|
	|DSP Domain Advertiser Passthrough String|
#	|Related Proposal|
	And Verify the buyer is "Enabled"



    Scenario: Verify Changing DSP alert the user and then on accept the new DSP and on cancel old earlier DSP ratained.
   	Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Click on publisher input
  	And enter the following values
  	|publisher|PrivateAuction|DSPValue|EntDealName|Values|
	|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
	And "Enable" the DSP buyer
	And enter the following DSP buyer details.
		|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
		|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
 	When change the DSP name to "RBidder"
  	Then Verify the following message is displayed when the DSP changed for deal
	|Message|
	|By changing the DSP, the Buyers section will be reset and will not be saved.|
	And Select "Cancel" on the DSP change banner displayed for deal
	Then Verify the following general value with the created data of deal
	|FieldName|
	|Publisher Name|
	|Deal Name|
	|Private Auction|
	|Date Range|
	|Value|
	|Currency|
	|DSP|
	And Verify the following buyers details with the created data of deal
	|FieldName|
	|DSP Seat Name|
	|Advertiser ID|
	|Advertiser Name|
	|DSP Seat Passthrough String|
	|DSP Domain Advertiser Passthrough String|
#	|Related Proposal|
	And Verify the buyer is "Disabled"
	When change the DSP name to "RBidder"
	Then Verify the following message is displayed when the DSP changed for deal
	|Message|
	|By changing the DSP, the Buyers section will be reset and will not be saved.|
	And Select "Accept" on the DSP change banner displayed for deal
  	Then Verify the following general value with the created data of deal
	|FieldName|
	|Publisher Name|
	|Deal Name|
	|Private Auction|
	|Date Range|
	|Value|
	|Currency|
	|DSP|
	And Verify the following buyers details are reset to default values
	|FieldName|
	|DSP Seat Name|
	|Advertiser ID|
	|Advertiser Name|
	|DSP Seat Passthrough String|
	|DSP Domain Advertiser Passthrough String|
#	|Related Proposal|
	And Verify the buyer is "Enabled"

	Scenario: Verify Publisher,Private Auction and DSP are non-editable in deal
   	Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Click on publisher input
  	And enter the following values
  	|publisher|PrivateAuction|DSPValue|EntDealName|Values|
	|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
	And enter the following DSP buyer details.
		|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
		|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
 	When click on Save deal
	And copy the deal ID
	And search the deal ID
 	And Select the deal and click on edit
	Then Verify Publisher,Private Auction and DSP are non-editable


	Scenario: Verify that the publisher field is not editable and preselected for single publisher
   	Given Single Publisher user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Verify Publisher field is not editable and preselected with "Viber"



	Scenario: Verify Floor Prices field
	Given admin user login to RX UI with valid username and password
	When Click on Deals option under Sales
	And User displayed with Deals page
	And Click create a new deal
	Then Create deal menu is opened
	And Click on publisher input
	And Select publisher by name: "Viber"
	When Enter correct floor prices values: "1,9999,9,700"
	And Enter incorrect floor prices values: "-2,0,10001,99999"
	Then Verify Floor Prices

	Scenario: Verify that the user can add max 10 seats in create deal page.
   	Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Click on publisher input
    And Select publisher by name: "Viber"
    And Click on Add more seats ten times
    Then Verify that the Add more seats is disabled and ten DSP panels are added

	Scenario: Verify that the user can enable,disable and delete DSP Buyer
   	Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Click on publisher input
    And Select publisher by name: "Viber"
    And Click on Add more seats ten times
    Then Verify that the added seat is enabled
    And Disabled added seats
    Then Verify that the added seat is disabled
	And Enabled added seats
	Then Verify that the added seat is enabled
    And Delete the added seats
    Then Verify that the added seat is deleted

	Scenario: Verify that autofill works properly for the same Publisher
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter previously used DSP buyer details using autofill.
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created data of deal
			|FieldName|
			|DSP Seat ID|
			|DSP Seat Name|
			|Advertiser ID|
			|Advertiser Name|
			|DSP Seat Passthrough String|
			|DSP Domain Advertiser Passthrough String|

	Scenario: Verify that any Required field can be saved and autofilled
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat Name|
			|TestAutodSPSeatName|
		When click on Save deal
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter previously used DSP buyer details using autofill.
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created data of deal
			|FieldName|
			|DSP Seat Name|

	Scenario: Verify autofill values can be modified
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter previously used DSP buyer details using autofill.
		And enter the following DSP buyer details with clear.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created data of deal
			|FieldName|
			|DSP Seat ID|
			|DSP Seat Name|
			|Advertiser ID|
			|Advertiser Name|
			|DSP Seat Passthrough String|
			|DSP Domain Advertiser Passthrough String|

	Scenario: Verify that autofill values can be populated after changing DSP
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And enter the following DSP buyer details with clear.
			|DSP Seat ID|
			|TestAutoSeatID|
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created data of deal
			|FieldName|
			|DSP Seat ID|
			|DSP Seat Name|
			|Advertiser ID|
			|Advertiser Name|
			|DSP Seat Passthrough String|
			|DSP Domain Advertiser Passthrough String|

	Scenario: Verify that any amount of values can be changed after autofill
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|ASeatID|ASeatName|AAdvId|AAdvName|ASeatPass|ADomainAdv|
		When click on Save deal
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter previously used DSP buyer details using autofill.
		And enter the following DSP buyer details with clear.
			|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthrosughString|
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created data of deal
			|FieldName|
			|DSP Seat ID|
			|DSP Seat Name|
			|Advertiser ID|
			|Advertiser Name|
			|DSP Seat Passthrough String|
			|DSP Domain Advertiser Passthrough String|

	Scenario: Verify that ID fields can be saved as String
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter the original DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created data of deal
			|FieldName|
			|DSP Seat ID|
			|DSP Seat Name|
			|Advertiser ID|
			|Advertiser Name|
			|DSP Seat Passthrough String|
			|DSP Domain Advertiser Passthrough String|


	Scenario: Verify that ID fields can be saved as String
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter the original DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created data of deal
			|FieldName|
			|DSP Seat ID|
			|DSP Seat Name|
			|Advertiser ID|
			|Advertiser Name|
			|DSP Seat Passthrough String|
			|DSP Domain Advertiser Passthrough String|


	Scenario: Verify autofill works with long values
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter the 255 character as DSP buyer details
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|||||||
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created data of deal
			|FieldName|
			|DSP Seat ID|
			|DSP Seat Name|
			|Advertiser ID|
			|Advertiser Name|
			|DSP Seat Passthrough String|
			|DSP Domain Advertiser Passthrough String|

	Scenario: Verify that ID fields can be saved as int
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|Test_Spe|TheTradeDesk|TestAutoDeal|2|
		And enter the random int as DSP buyer details
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|||||||
		When click on Save deal
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created data of deal
			|FieldName|
			|DSP Seat ID|
			|DSP Seat Name|
			|Advertiser ID|
			|Advertiser Name|
			|DSP Seat Passthrough String|
			|DSP Domain Advertiser Passthrough String|