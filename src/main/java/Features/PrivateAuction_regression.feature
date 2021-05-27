Feature: Private Auctions page regression
@Ignore
  Scenario: Verify the default column names in the private auctions overview page
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    Then Verify the overview page contains following columns
      | ColumnName    |
      | ID            |
      | Name          |
      | Publisher     |
      | Optimize      |
      | Active        |
      | Related Deals |
@Ignore
  Scenario: Verify the pagination exists for the list in the private auctions page for admin
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    Then Verify the pagination of the listed rows in the Page with a selection of 50 rows per page with 6 columns
@Ignore
  Scenario: Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And User click on table options button
    Then Verify that column "Name" can be hidden and shown
@Ignore
  Scenario: Verify onclicking relevant status from table options shows only that particular table rows with that status
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    Then User click on table options button
    And Verify that column "Active" only shows relevant rows in the table with filter "Active"
    And Verify that column "Active" only shows relevant rows in the table with filter "Inactive"
@Ignore
  Scenario: Verify searching private auction with available and non available private auction name
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    Then Verify the search functionality with the following names
      | Name      | CoumnName |
      | reg_test  | Name      |
      | reg_testa | Name      |
@Ignore
  Scenario: Verify enabling and disabling of a private auction from the overview page
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    Then Verify the search functionality with the following names
      | Name        | CoumnName |
      | TestUpdated | Name      |
    Then Verify enabling and disabling of an auction from the overview page
@Ignore
  Scenario: Verify sorting of the list's columns of the private auctions overview page
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    Then Verify the sorting functionality with the following columns
      | ColumnName | SortType |
      | ID         | asc      |
      | Publisher  | desc     |
      | Active     | asc      |
@Ignore
  Scenario: Verify mandatory fields in the Create private auction Page
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    Then Click on Save Private Auction & Close button
    Then Verify following fields are mandatory for create page
      | FieldName      |
      | Publisher Name |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    Then Click on Save Private Auction & Close button
    Then Verify following fields are mandatory for create page
      | FieldName  |
      | Name       |
      | Date Range |
@Ignore
  Scenario: Verify on publisher user login, publisher field is disabled in the Create private auction Page
    Given Publisher user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    Then Verify publisher field is disabled on create/edit page
@Ignore
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
@Ignore
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
@Ignore
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
@Ignore
  Scenario: Verify without selecting publisher the card is not enabled to fill in the Create private auction Page
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    Then Verify following fields are not enabled for create page
      | FieldName  |
      | Name       |
      | Date Range |
      | Active     |
      | Always on  |
      | Optimize   |
@Ignore
  Scenario: Verify default values for toggle fields in the Create private auction Page
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    Then Verify input values for following toggle fields in create page
      | FieldName | Active |
      | Active    | Yes    |
      | Always on | No     |
      | Optimize  | Yes    |
@Ignore
  Scenario: Verify Changing publisher name alert the user and then on change every fields go to default state
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And "Disable" following toggle fields in create page
      | FieldName |
      | Active    |
      | Always on |
      | Optimize  |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    Then Verify the following message is displayed when the publisher changed
      | Message                                                                                      |
      | By changing the Publisher the form will be reset and the previous changes will not be saved. |
    And Select "Cancel" on the publisher change banner
    Then Verify the following columns value with the created data for the general card of private auction
      | FieldName  |
      | Name       |
      | Date Range |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    And Select "Accept" on the publisher change banner
    Then Verify input values for following toggle fields in create page
      | FieldName | Active |
      | Active    | Yes    |
      | Always on | No     |
      | Optimize  | Yes    |
    Then Verify the following columns values for the general card of private auction is empty
      | FieldName  |
      | Name       |
      | Date Range |
@Ignore
  Scenario: Verify successful creation of private auction on clicking \Save and close\
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    Then Verify clicking on Create a deal banner opens create deal entity page
@Ignore
  Scenario: Verify creation of private auction and navigating to create deal page on clicking \Save and create deal\
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    Then Click on Save Private Auction & Create Deal button and verify create deal page is opened
@Ignore
  Scenario: Verify successful editing of private auction
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    Then Click on the created auction name in the overview page
    Then Verify publisher field is disabled on create/edit page
    Then Verify the following columns value with the created data for the general card of private auction
      | FieldName      |
      | Name           |
      | Publisher Name |
    And Enter the following data in the general card of private auction
      | FieldName | Value       | ListValueIndex |
      | Name      | TestUpdated |                |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    Then Click on the created auction name in the overview page
    Then Verify the following columns value with the created data for the general card of private auction
      | FieldName      |
      | Name           |
      | Publisher Name |

  Scenario: Verify Details if all targeting are empty
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    When Hover on the Details column of the created private auction data
    Then Verify that Details display the following data for each targeting
      | Inventory                  | Devices                  | Operating Systems                  | Geos                  | Ad Format                  | Ad Sizes                  |
      | All Inventory are included | All Devices are included | All Operating Systems are included | All Geos are included | All Ad Format are included | All Ad Sizes are included |

  Scenario: Verify Details if all targeting have every item included
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Select targeting options items
      | Inventory        | supply-chains and Sons Media |
      | Device           | Phone                        |
      | Operating System | Android                      |
      | Geo              | Afghanistan                  |
      | Ad Format        | Banner                       |
      | Ad Size          | 120x60                       |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    When Hover on the Details column of the created private auction data
    Then Verify that Details display the following data for each targeting
      | Inventory                    | Devices | Operating Systems | Geos        | Ad Format | Ad Sizes           |
      | supply-chains and Sons Media | Phone   | Android           | Afghanistan | Banner    | Financial (120x60) |

  Scenario: Verify Details display properly Inventory parent items
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Select targeting options items
      | Inventory | supply-chains and Sons Media |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    When Hover on the Details column of the created private auction data
    Then Verify that Details display the following data for each targeting
      | Inventory                    | Devices                  | Operating Systems                  | Geos                  | Ad Format                  | Ad Sizes                  |
      | supply-chains and Sons Media | All Devices are included | All Operating Systems are included | All Geos are included | All Ad Format are included | All Ad Sizes are included |

  Scenario: Verify Details display properly Inventory child items
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Select targeting options items
      | Inventory Child| supply-chains and Sons Media > TCP orchid |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    When Hover on the Details column of the created private auction data
    Then Verify that Details display the following data for each targeting
      | Inventory  | Devices                  | Operating Systems                  | Geos                  | Ad Format                  | Ad Sizes                  |
      | TCP orchid | All Devices are included | All Operating Systems are included | All Geos are included | All Ad Format are included | All Ad Sizes are included |

      Scenario: Verify Details display properly Inventory parent and child items
      Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name |Mark Mceachran |      31         |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Select targeting options items
    | Inventory | Mark Mc Mobile Site |
      | Inventory Child| Mark Mc Desktop Site > 160x600 |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    When Hover on the Details column of the created private auction data
    Then Verify that Details display the following data for each targeting
      | Inventory  | Devices                  | Operating Systems                  | Geos                  | Ad Format                  | Ad Sizes                  |
      | Mark Mc Mobile Site,160x600 | All Devices are included | All Operating Systems are included | All Geos are included | All Ad Format are included | All Ad Sizes are included |
   
      Scenario: Verify that warning banner is displayed under Publisher name
      Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
      Then Verify that warning banner is under Publisher name
   
    Scenario: Veridy create Private Auction page is disabled while warning banner is present with empty inputs for Admin
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
      Then Verify that warning banner is under Publisher name
      Then Verify following fields are not enabled for create page
      | FieldName      |
      |Publisher Name|
      | Name           |
      | Date Range     |
       | Active    |
      | Always on |
      | Optimize  |
      Then Verify following Targeting in create Private Auction page is disabled 
      | FieldName      |
       | Inventory        | 
      | Device           | 
      | Operating System | 
      | Geo              | 
      | Ad Format        | 
      | Ad Size          | 
      Then Verify "Save Private Auction & Close" button in create Private Auction page is disabled
      Then Verify "Save Private Auction & Create Deal" button in create Private Auction page is disabled
      
      Scenario: Veridy create Private Auction page is disabled while warning banner is present with empty inputs for Cross Publisher
      Given Publisher user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the following create button
      | CreateButtonName       |
      | Create Private Auction |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
      Then Verify that warning banner is under Publisher name
      Then Verify following fields are not enabled for create page
      | FieldName      |
      |Publisher Name|
      | Name           |
      | Date Range     |
       | Active    |
      | Always on |
      | Optimize  |
      Then Verify following Targeting in create Private Auction page is disabled 
      | FieldName      |
       | Inventory        | 
      | Device           | 
      | Operating System | 
      | Geo              | 
      | Ad Format        | 
      | Ad Size          | 
      Then Verify "Save Private Auction & Close" button in create Private Auction page is disabled
      Then Verify "Save Private Auction & Create Deal" button in create Private Auction page is disabled    
       