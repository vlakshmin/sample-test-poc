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

	@Ignore
Scenario: failing to GS-1963 Verify that the Alert message displayed for changing the publisher for deal,on cancel retain the filled value and accept removed the filled values.
    Given admin user login to RX UI with valid username and password
    When Click on Deals option under Sales
	And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    And Click on publisher input
  	And enter the following values
  	|publisher|PrivateAuction|DSPValue|EntDealName|Values|
	|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
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
	And Verify the buyer is "Enabled"
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
	|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
	And "Enable" the DSP buyer
	And enter the following DSP buyer details.
		|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
		|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
 	When change the DSP name to "TheTradeDesk"
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
	And Verify the buyer is "Enabled"
	When change the DSP name to "TheTradeDesk"
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
	|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
	And enter the following DSP buyer details.
		|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
		|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
 	When click on Save deal
	Then Verify deal contains copy deal id message
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
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter previously used DSP buyer details using autofill.
		When click on Save deal
		Then Verify deal contains copy deal id message
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
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat Name|
			|TestAutodSPSeatName|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter previously used DSP buyer details using autofill.
		When click on Save deal
		Then Verify deal contains copy deal id message
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
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter previously used DSP buyer details using autofill.
		And enter the following DSP buyer details with clear.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
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
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And enter the following DSP buyer details with clear.
			|DSP Seat ID|
			|TestAutoSeatID|
		When click on Save deal
		Then Verify deal contains copy deal id message
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
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|ASeatID|ASeatName|AAdvId|AAdvName|ASeatPass|ADomainAdv|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter previously used DSP buyer details using autofill.
		And enter the following DSP buyer details with clear.
			|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthrosughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
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
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the original DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
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
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the original DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
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
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the 255 character as DSP buyer details
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|||||||
		When click on Save deal
		Then Verify deal contains copy deal id message
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
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the random int as DSP buyer details
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|||||||
		When click on Save deal
		Then Verify deal contains copy deal id message
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

	Scenario: Verify that Details icon displays information properly
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Hover over deal details button
		And Get deal details data
		Then Verify deal details data is correct

	Scenario: Verify that Details icon displays information properly after editing all values
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And enter the following DSP buyer details with clear.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Hover over deal details button
		And Get deal details data
		Then Verify deal details data is correct

	Scenario: Verify that Details icon displays information properly after editing one value
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And enter the following DSP buyer details with clear.
			|DSP Seat ID|
			|TestAutoSeatID|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Hover over deal details button
		And Get deal details data
		Then Verify deal details data is correct

	Scenario: Verify that Details icon displays information properly after removing value
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And enter the original DSP buyer details with clear.
			|DSP Seat ID|
			||
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Hover over deal details button
		And Get deal details data
		Then Verify deal details data is correct

	Scenario: Verify that long values in Buyers card autofill are truncated
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the 270 character as DSP buyer details
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|||||||
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		And Verify the following buyers details with the created long data of deal are truncated
			|FieldName|
			|DSP Seat ID|
			|DSP Seat Name|
			|Advertiser ID|
			|Advertiser Name|
			|DSP Seat Passthrough String|
			|DSP Domain Advertiser Passthrough String|

	Scenario: Verify warning banner is displayed when inactive buyer is present
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		And "Disable" the DSP buyer
		And Verify the buyer is "Disabled"
		When click on Save deal
		Then Verify banner message about inactive buyers

	Scenario: 20. Verify DSP info icon displays proper information
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		When Click on publisher input
		And Select publisher by name: "Viber"
		Then The Currency field is not null
		When Select DSP by name: "RBidder"
		When Hover over on DSP info icon
		Then Verify the DSP information displays
			|Message|
			|All buyers of this deal need to be on the same DSP. If the buyer you're selling this Private Auction is buying on multiple DSPs, create multiple deals, one per DSP.|


	Scenario: 53. Verify that several active Buyers without any details are not saved empty for the deal
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And Click on Add more seats button
		And Click on Add more seats button
		Then Verify "3" seat panels are displayed
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		Then Verify no buyers entities were saved

	Scenario: 54. Verify that several inactive Buyers without any details are not saved empty for the deal
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And Click on Add more seats button
		And Click on Add more seats button
		And Click on Add more seats button
		Then Verify "4" seat panels are displayed
		When Disabled "3" added seats
		Then Verify "3" added seats are disabled
		When click on Save deal
		Then Verify deal contains copy deal id message
		And copy the deal ID
		And search the deal ID
		And Select the deal and click on edit
		Then Verify no buyers entities were saved

	Scenario: 70. Verify create Deal page is disabled Publisher while warning banner is present with data in inputs for Admin
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		When change the publisher name to "Viki"
		Then Verify the following message is displayed when the publisher changed for deal
			|Message|
			|By changing the Publisher the form will be reset and the previous changes will not be saved.|
		Then Verify entity page is disabled

	Scenario: 71. Verify create Deal page is disabled Publisher while warning banner is present with data in inputs for Cross Publisher
		Given Publisher user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		When change the publisher name to "Viki"
		Then Verify the following message is displayed when the publisher changed for deal
			|Message|
			|By changing the Publisher the form will be reset and the previous changes will not be saved.|
		Then Verify entity page is disabled

	Scenario: 72. Verify create Deal page is disabled while DSP warning banner is present with data in inputs for Admin
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And "Enable" the DSP buyer
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When change the DSP name to "TheTradeDesk"
		Then Verify the following message is displayed when the DSP changed for deal
			|Message|
			|By changing the DSP, the Buyers section will be reset and will not be saved.|
		Then Verify entity page is disabled

	Scenario: 73. Verify create Deal page is disabled while DSP warning banner is present with data in inputs for Cross Publisher
		Given Publisher user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Click on publisher input
		And enter the following values
			|publisher|PrivateAuction|DSPValue|EntDealName|Values|
			|Viber|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And "Enable" the DSP buyer
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When change the DSP name to "TheTradeDesk"
		Then Verify the following message is displayed when the DSP changed for deal
			|Message|
			|By changing the DSP, the Buyers section will be reset and will not be saved.|
		Then Verify entity page is disabled

	Scenario: 74. Verify create Deal page is disabled while DSP warning banner is present with data in inputs for Single Publisher
		Given Single Publisher user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		And Enter the following values for Single Publisher user
			|PrivateAuction|DSPValue|EntDealName|Values|
			|RTBHouse Auction|RBidder|TestAutoDeal|2|
		And "Enable" the DSP buyer
		And enter the following DSP buyer details.
			|DSP Seat ID|DSP Seat Name|Advertiser ID|Advertiser Name|DSP Seat Passthrough String|DSP Domain Advertiser Passthrough String|
			|TestAutoSeatID|TestAutodSPSeatName|TestAutoAdvertiserId|TestAutoadvertiserName|TestAutodSPSeatPassthroughString|TestAutodSPDomainAdvertiserPassthroughString|
		When change the DSP name to "TheTradeDesk"
		Then Verify the following message is displayed when the DSP changed for deal
			|Message|
			|By changing the DSP, the Buyers section will be reset and will not be saved.|
		Then Verify entity page is disabled

	Scenario: 77. Verify Deal edit page is disabled when currency banner is present for Admin
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click on any deal name in Deals list
		And Select "USD - Dollars" from Currency
		Then Verify the following message is displayed when the Currency changed for deal
			|Message|
			|Changing the currency type associated with the value of this deal may lead to its buyers bidding below the price currently set. Due to echange rate fluctuations the new value may differ dramatically. Are you sure you want to proceed? |
		Then Verify entity page is disabled

	Scenario: 77. Verify Deal edit page is disabled when currency banner is present for Cross Publisher
		Given Publisher user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click on any deal name in Deals list
		And Select "USD - Dollars" from Currency
		Then Verify the following message is displayed when the Currency changed for deal
			|Message|
			|Changing the currency type associated with the value of this deal may lead to its buyers bidding below the price currently set. Due to echange rate fluctuations the new value may differ dramatically. Are you sure you want to proceed? |
		Then Verify entity page is disabled

	Scenario: 77. Verify Deal edit page is disabled when currency banner is present for Single Publisher
		Given Single Publisher user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click on any deal name in Deals list
		And Select "USD - Dollars" from Currency
		Then Verify the following message is displayed when the Currency changed for deal
			|Message|
			|Changing the currency type associated with the value of this deal may lead to its buyers bidding below the price currently set. Due to echange rate fluctuations the new value may differ dramatically. Are you sure you want to proceed? |
		Then Verify entity page is disabled

	Scenario: 88. Verify that errors are displayed near save button on Create/update date for Deals
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		When click on Save deal
		Then Verify the validation errors display
			|Message|
			|The Name field is required|
			|The Private Auction field is required|
			|The Date Range field is required|
			|The Value field is required|
			|The Currency field is required|
			|The DSP field is required|
		When Click on publisher input
		And Select publisher by name: "Viber"
		Then The Currency field is not null
		Then Verify the validation error does not display
			|Message|
			|The Currency field is required|
		When Select Private Auction by name: "RTBHouse Auction"
		Then Verify the validation error does not display
			|Message|
			|The Private Auction field is required|
			|The Date Range field is required|
		When Select DSP by name: "RBidder"
		Then Verify the validation error does not display
			|Message|
			|The DSP field is required|
		When Enter "TestAutoDeal" into Name
		Then Verify the validation error does not display
			|Message|
			|The Name field is required|
		When Enter "1" into Floor Price
		Then Verify no validation errors display

	Scenario: 117. Verify that only one error is displayed for Date picker
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		When click on Save deal
		Then Verify only one error message displays below Date Range input
			|Message|
			|The Date Range field is required|
		When Click on publisher input
		And Select publisher by name: "Viber"
		Then The Currency field is not null
		When click on Save deal
		Then Verify only one error message displays below Date Range input
			|Message|
			|The Date Range field is required|

	Scenario: 146. Verify that clicking save button triggers dirty flag for Deals
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		When Click on publisher input
		And Select publisher by name: "Viber"
		Then The Currency field is not null
		When click on Save deal
		Then Verify the change Publisher banner message is not displayed
			|Message|
			|By changing the Publisher the form will be reset and the previous changes will not be saved.|
		When Select Private Auction by name: "RTBHouse Auction"
		When change the publisher name to "Viki"
		Then Verify the following message is displayed when the publisher changed for deal
			|Message|
			|By changing the Publisher the form will be reset and the previous changes will not be saved.|

	Scenario: 219. Verify Floor Price limits for Deals
		Given admin user login to RX UI with valid username and password
		When Click on Deals option under Sales
		And User displayed with Deals page
		And Click create a new deal
		Then Create deal menu is opened
		When Click on publisher input
		And Select publisher by name: "Viber"
		Then The Currency field is not null
		When Enter "1234567" into Floor Price
		Then Verify the error message displays below Floor Price input
			|Message|
			|A valid price is between 0.00 and 999,999.99|