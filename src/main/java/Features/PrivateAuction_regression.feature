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

  Scenario: Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And User click on table options button
    Then Verify that column "Name" can be hidden and shown

  Scenario: Verify onclicking relevant status from table options shows only that particular table rows with that status
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    Then User click on table options button
    And Verify that column "Active" only shows relevant rows in the table with filter "Active"
    And Verify that column "Active" only shows relevant rows in the table with filter "Inactive"

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
    And Click on the Pricate Auction create button
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
    And Click on the Pricate Auction create button
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
    And Click on the Pricate Auction create button
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
    And Click on the Pricate Auction create button
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
    And Click on the Pricate Auction create button
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
    And Click on the Pricate Auction create button
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
    And Click on the Pricate Auction create button
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
    And Click on the Pricate Auction create button
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

  Scenario: 40.Verify Details if all targeting are empty
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

  Scenario: 41.Verify Details if all targeting have every item included
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | Viki      |                |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Select targeting options items
      | Inventory        | Viki Android                 |
      | Device           | Phone                        |
      | Operating System | Windows                      |
      | Geo              | Afghanistan                  |
      | Ad Format        | Banner                       |
      | Ad Size          | 120x60                       |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    When Hover on the Details column of the created private auction data
    Then Verify that Details display the following data for each targeting
      | Inventory                    | Devices | Operating Systems | Geos        | Ad Format | Ad Sizes           |
      | Viki Android                 | Phone   | Windows           | Afghanistan | Banner    | Financial (120x60) |

  Scenario: 42.Verify Details display properly Inventory parent items
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | Viki      |                |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Select targeting options items
      | Inventory | Viki Android   |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    When Hover on the Details column of the created private auction data
    Then Verify that Details display the following data for each targeting
      | Inventory                    | Devices                  | Operating Systems                  | Geos                  | Ad Format                  | Ad Sizes                  |
      | Viki Android                 | All Devices are included | All Operating Systems are included | All Geos are included | All Ad Format are included | All Ad Sizes are included |

  Scenario: 43.Verify Details display properly Inventory child items
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And Open create New Private Auction page
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | Viki      |                |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Select targeting options items
      | Inventory Child| Viki Android > Viki Android PreRoll |
    Then Click on Save and wait for dialog to close
    Then Verify the created private auction data is matching with its overview list values
    When Hover on the Details column of the created private auction data
    Then Verify that Details display the following data for each targeting
      | Inventory  | Devices                  | Operating Systems                  | Geos                  | Ad Format                  | Ad Sizes                  |
      | Viki Android PreRoll | All Devices are included | All Operating Systems are included | All Geos are included | All Ad Format are included | All Ad Sizes are included |

      Scenario: 44.Verify Details display properly Inventory parent and child items
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

      Scenario: 67.Verify that warning banner is displayed under Publisher name
      Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
      Then Verify that warning banner is under Publisher name

    Scenario: 68.Veridy create Private Auction page is disabled while warning banner is present with empty inputs for Admin
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
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

      Scenario: 69.Veridy create Private Auction page is disabled while warning banner is present with empty inputs for Cross Publisher
      Given Publisher user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
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

  Scenario: 87.Verify that errors are displayed near save button on Create/update date for Private Auction
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
    And Click on Save Private Auction & Close button
    Then Verify following errors are displayed near save button
    |The Publisher Name field is required|
    |The Name field is required|
    |The Date Range field is required|
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    Then Verify that error disapear according to fields filled
    |The Publisher Name field is required|
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Name           | Test      |                |
    Then Verify that error disapear according to fields filled
    |The Name field is required|
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Date Range     | Future    |                |
    Then Verify that error disapear according to fields filled
    |The Date Range field is required|

  Scenario: 116.Verify that only one error is displayed for Date picker
  Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
    And Click on Save Private Auction & Close button
    Then Check only one error "The Date Range field is required" is present for date
     And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
     Then Check only one error "The Date Range field is required" is present for date

  Scenario: 121.Verify that Activate Deactivate buttons are displayed at same time for Private Auction list page
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Select one "Active" Private Auctions item
    Then Verify that following buttons are present in Private Auctions list page
    |Edit Private Auction|
    |Deactivate Private Auction|
    |Activate Private Auction|
    When Click "Edit Private Auction" button in Private Auctions list page
    Then Edit Private Auction pop up is present
    And Enter the following data in the general card of private auction
      | FieldName | Value       | ListValueIndex |
      | Name      | TestUpdated |                |
    Then Click on Save and wait for dialog to close
    Then Verify the edited private auction data is matching with its overview list values
    When Click "Activate Private Auction" button in Private Auctions list page
    Then "Active" is displayed for the created private auctions
    And Select one "Active" Private Auctions item
    When Click "Deactivate Private Auction" button in Private Auctions list page
    Then "Inactive" is displayed for the created private auctions
    And Select 1 "Inactive" and 1 "Active" Private Auctions items
    Then Verify that following buttons are present in Private Auctions list page
    |Deactivate Private Auction|
    |Activate Private Auction|
    When Click "Deactivate Private Auction" button in Private Auctions list page
    Then "Inactive" is displayed for the created private auctions
     And Select 2 "Inactive" and 2 "Active" Private Auctions items
    Then Verify that following buttons are present in Private Auctions list page
    |Deactivate Private Auction|
    |Activate Private Auction|
    When Click "Deactivate Private Auction" button in Private Auctions list page
    Then "Inactive" is displayed for the created private auctions
    And Select 1 "Inactive" and 1 "Active" Private Auctions items
    Then Verify that following buttons are present in Private Auctions list page
    |Deactivate Private Auction|
    |Activate Private Auction|
    When Click "Activate Private Auction" button in Private Auctions list page
    Then "Active" is displayed for the created private auctions
    And Select one "Inactive" Private Auctions item
    Then Verify that following buttons are present in Private Auctions list page
    |Edit Private Auction|
    |Deactivate Private Auction|
    |Activate Private Auction|
    When Click "Edit Private Auction" button in Private Auctions list page
    Then Edit Private Auction pop up is present
    And Enter the following data in the general card of private auction
      | FieldName | Value       | ListValueIndex |
      | Name      | TestUpdated |                |
    Then Click on Save and wait for dialog to close
    Then Verify the edited private auction data is matching with its overview list values
    When Click "Deactivate Private Auction" button in Private Auctions list page
    Then "Inactive" is displayed for the created private auctions
    And Select one "Inactive" Private Auctions item
     When Click "Activate Private Auction" button in Private Auctions list page
    Then "Active" is displayed for the created private auctions
    And Select 2 "Inactive" and 2 "Active" Private Auctions items
    Then Verify that following buttons are present in Private Auctions list page
    |Deactivate Private Auction|
    |Activate Private Auction|
    When Click "Activate Private Auction" button in Private Auctions list page
    Then "Active" is displayed for the created private auctions

  Scenario: 135.Verify that Publisher warning banned apears only if any forms were modified on Private Auctions page
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    Then Verify that warning banner is not under Publisher name
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Name           | Test      |                |
      | Date Range     | Future    |                |
    And "Disable" following toggle fields in create page
      | FieldName |
      | Active    |
      | Optimize  |
    And "Enable" following toggle fields in create page
      | FieldName |
      | Always on |
    And Select targeting options items
      | Inventory        | supply-chains and Sons Media |
      | Device           | Phone                        |
      | Operating System | Android                      |
      | Geo              | Afghanistan                  |
      | Ad Format        | Banner                       |
      | Ad Size          | 120x60                       |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
     And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Name           | Test      |                |
       And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Date Range     | Future    |                |
     And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              3 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
     And "Disable" following toggle fields in create page
      | FieldName |
      | Active    |
      And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              4 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    And "Disable" following toggle fields in create page
      | FieldName |
      | Optimize  |
     And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              5 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    And "Enable" following toggle fields in create page
      | FieldName |
      | Always on |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    And Select targeting options items
      | Inventory        | supply-chains and Sons Media |
     And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              3 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    And Select targeting options items
      | Device           | Phone                        |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              4 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    And Select targeting options items
      | Operating System | Android                      |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              5 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    And Select targeting options items
      | Geo              | Afghanistan                  |
      And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              3 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    And Select targeting options items
      | Ad Format        | Banner                       |
     And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    And Select targeting options items
      | Ad Size          | 120x60                       |
      And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner

     Scenario: 143.Verify that changing "Optimize" checkbox triggers dirty flag for Private Auctions
     Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              1 |
    And "Disable" following toggle fields in create page
      | FieldName |
      | Optimize  |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
      Then Verify that warning banner is under Publisher name