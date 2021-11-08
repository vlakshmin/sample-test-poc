#noinspection CucumberUndefinedStep
Feature: Protections page regression

  Scenario: 7.Verify left nav menu intem Protections for Single Publisher
    Given Single Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page

  Scenario: 7.Verify left nav menu intem Protections for admin
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page

  Scenario: 7.Verify left nav menu intem Protections for cross Publisher
    Given Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page

  Scenario: 8.Verify pagination on Protections list page for Single Publisher
    Given Single Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    Then Verify that default value is 20 items per page
    Then Verify that each items value displayes proper amount of items
    Then Verify that next page/prev page displays proper amount of items

  Scenario: 8.Verify pagination on Protections list page for admin
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    Then Verify that default value is 20 items per page
    Then Verify that each items value displayes proper amount of items
    Then Verify that next page/prev page displays proper amount of items

  Scenario: 8.Verify pagination on Protections list page for cross Publisher
    Given Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    Then Verify that default value is 20 items per page
    Then Verify that each items value displayes proper amount of items
    Then Verify that next page/prev page displays proper amount of items

  Scenario: 9.Verify Search on Protections page for Single Publisher
    Given Single Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Search Protections item with "full name"
    Then Verify that exicting Protections item is displayed via "full name" search
    When Search Protections item with "partly name"
    Then Verify that exicting Protections item is displayed via "partly name" search
    When Search Protections item with "single letter"
    Then Verify that exicting Protections item is displayed via "single letter" search
    When Click on Search clear button
    Then Verify that all Protections items are displayed

  Scenario: 9.Verify Search on Protections page for admin
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Search Protections item with "full name"
    Then Verify that exicting Protections item is displayed via "full name" search
    When Search Protections item with "partly name"
    Then Verify that exicting Protections item is displayed via "partly name" search
    When Search Protections item with "single letter"
    Then Verify that exicting Protections item is displayed via "single letter" search
    When Click on Search clear button
    Then Verify that all Protections items are displayed

  Scenario: 9.Verify Search on Protections page for cross Publisher
    Given Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Search Protections item with "full name"
    Then Verify that exicting Protections item is displayed via "full name" search
    When Search Protections item with "partly name"
    Then Verify that exicting Protections item is displayed via "partly name" search
    When Search Protections item with "single letter"
    Then Verify that exicting Protections item is displayed via "single letter" search
    When Click on Search clear button
    Then Verify that all Protections items are displayed

  Scenario: 10.Verify items can be sorted by columns for Single Publisher
    Given Single Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    Then "ID" column should be sorted in "descending" order
    When Click on "ascending" sorting symbol for "ID" column
    Then "ID" column should be sorted in "ascending" order
    When Click on "ascending" sorting symbol for "Publisher" column
    Then "Publisher" column should be sorted in "ascending" order
    When Click on "descending" sorting symbol for "Publisher" column
    Then "Publisher" column should be sorted in "descending" order

  Scenario: 10.Verify items can be sorted by columns for admin
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    Then "ID" column should be sorted in "descending" order
    When Click on "ascending" sorting symbol for "ID" column
    Then "ID" column should be sorted in "ascending" order
    When Click on "ascending" sorting symbol for "Publisher" column
    Then "Publisher" column should be sorted in "ascending" order
    When Click on "descending" sorting symbol for "Publisher" column
    Then "Publisher" column should be sorted in "descending" order

  Scenario: 10.Verify items can be sorted by columns for cross Publisher
    Given Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    Then "ID" column should be sorted in "descending" order
    When Click on "ascending" sorting symbol for "ID" column
    Then "ID" column should be sorted in "ascending" order
    When Click on "ascending" sorting symbol for "Publisher" column
    Then "Publisher" column should be sorted in "ascending" order
    When Click on "descending" sorting symbol for "Publisher" column
    Then "Publisher" column should be sorted in "descending" order

  Scenario: 11.Verify date format in items for cross Publisher
    Given Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on Table Options button
    And Select the columns from Table Options
      | Create Date |
      | Update Date |
    Then Should see the header in Protections table
      | Create Date |
      | Update Date |
    Then Date format of "Create Date" column should be "yyyy-MM-dd"
    Then Date format of "Update Date" column should be "yyyy-MM-dd"

  Scenario: 11.Verify date format in items for admin
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on Table Options button
    And Select the columns from Table Options
      | Create Date |
      | Update Date |
    Then Should see the header in Protections table
      | Create Date |
      | Update Date |
    Then Date format of "Create Date" column should be "yyyy-MM-dd"
    Then Date format of "Update Date" column should be "yyyy-MM-dd"

  Scenario: 11.Verify date format in items for admin for Single Publisher
    Given Single Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on Table Options button
    And Select the columns from Table Options
      | Create Date |
      | Update Date |
    Then Should see the header in Protections table
      | Create Date |
      | Update Date |
    Then Date format of "Create Date" column should be "yyyy-MM-dd"
    Then Date format of "Update Date" column should be "yyyy-MM-dd"

  Scenario: 12.Verify columns selected by default for cross Publisher
    Given Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    Then Should see the header in Protections table
      | ID              |
      | Publisher       |
      | Active/Inactive |
      | Name            |
    Then Should not see the header in Protections table
      | Updated by |

  Scenario: 12.Verify columns selected by default for admin
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    Then Should see the header in Protections table
      | ID              |
      | Publisher       |
      | Active/Inactive |
      | Name            |
    Then Should not see the header in Protections table
      | Updated by |

  Scenario: 12.Verify columns selected by default for Single Publisher
    Given Single Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    Then Should see the header in Protections table
      | ID              |
      | Publisher       |
      | Active/Inactive |
      | Name            |
    Then Should not see the header in Protections table
      | Updated by |

  Scenario: 13.Verify statys sorting option for cross Publisher
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on Table Options button
    And Select the columns from Table Options
      | Active |
    Then "Active" items are displayed for Active/Inactive column
    And Select the columns from Table Options
      | Inactive |
    Then "Inactive" items are displayed for Active/Inactive column
    And Select the columns from Table Options
      | Both |
    Then Both "Active" and "Inactive" items are displayed for Active/Inactive column

  Scenario: 13.Verify statys sorting option for admin
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on Table Options button
    And Select the columns from Table Options
      | Active |
    Then "Active" items are displayed for Active/Inactive column
    And Select the columns from Table Options
      | Inactive |
    Then "Inactive" items are displayed for Active/Inactive column
    And Select the columns from Table Options
      | Both |
    Then Both "Active" and "Inactive" items are displayed for Active/Inactive column

  Scenario: 13.Verify statys sorting option for Single Publisher
    Given Single Publisher user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on Table Options button
    And Select the columns from Table Options
      | Active |
    Then "Active" items are displayed for Active/Inactive column
    And Select the columns from Table Options
      | Inactive |
    Then "Inactive" items are displayed for Active/Inactive column
    And Select the columns from Table Options
      | Both |
    Then Both "Active" and "Inactive" items are displayed for Active/Inactive column

#  Scenario: 79.Verify Protection Targeting section elements
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value             | ListValueIndex |
#      | Publisher Name | Viber |                |
#    And Click Add Protections Targeting button
#    Then Verify that following items are present
#      | Advertiser  |
#      | Ad Category |
#      | All Ads     |
#    Then tooltip "Block/whitelist advertisers" displays when mouse hovered on "Advertiser"
#    Then tooltip "Block/whitelist ads content categories" displays when mouse hovered on "Ad Category"
#    Then tooltip "Block all ads from serving" displays when mouse hovered on "All Ads"

#  Scenario: 80.Verify Protection Targeting can be selected All Ads or Advertsier/Ad Category
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value             | ListValueIndex |
#      | Publisher Name | Viber |                |
#    And Click Add Protections Targeting button
#    Then Verify that following items are present
#      | Advertiser  |
#      | Ad Category |
#      | All Ads     |
#    When Select "Advertiser" from Add Protections Targeting
#    Then Verify that "Ad Category" is enabled
#    Then Verify that "All Ads" is disabled
#    Then Delete "Advertiser" in Create Protections page
#    When Click Add Protections Targeting button
#    Then Verify that following items are present
#      | Advertiser  |
#      | Ad Category |
#      | All Ads     |
#    And Select "All Ads" from Add Protections Targeting
#    Then Verify that "Advertiser" is disabled
#    Then Verify that "Ad Category" is disabled

#  Scenario: 81.Verify Protection Targeting section elements can be deleted
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value             | ListValueIndex |
#      | Publisher Name | Viber |                |
#    And Click Add Protections Targeting button
#    Then Verify that following items are present
#      | Advertiser  |
#      | Ad Category |
#      | All Ads     |
#    When Select "Advertiser" from Add Protections Targeting
#    Then Delete "Advertiser" in Create Protections page
#    And Click Add Protections Targeting button
#    Then Verify that following items are present
#      | Advertiser  |
#      | Ad Category |
#      | All Ads     |
#    When Select "Ad Category" from Add Protections Targeting
#    Then Delete "Ad Category" in Create Protections page
#    And Click Add Protections Targeting button
#    Then Verify that following items are present
#      | Advertiser  |
#      | Ad Category |
#      | All Ads     |
#    When Select "All Ads" from Add Protections Targeting
#    Then Delete "All Ads" in Create Protections page

#  Scenario: 82.Verify Protection Targeting each card can be added only one at a time
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value             | ListValueIndex |
#      | Publisher Name | Viber |                |
#    And Click Add Protections Targeting button
#    Then Verify that following items are present
#      | Advertiser  |
#      | Ad Category |
#      | All Ads     |
#    When Select "Advertiser" from Add Protections Targeting
#    Then Verify that each card can be added only one at a time

#  Scenario: 100.Verify Protection entity page default values
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    Then Verify that all elements are present and have proper default value

#  Scenario: 101.Verify Protection entity page change Publisher resets all data
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value | ListValueIndex |
#      | Publisher Name | Viber |                |
#      | Name           | Test  |                |
#    And "Disable" following toggle fields in create page
#      | FieldName |
#      | Active    |
#    And Select targeting options items
#      | Inventory        | Viber Desktop App |
#      | Device           | Phone                |
#      | Operating System | MacOSX              |
#      | Geo              | Afghanistan          |
#      | Ad Size          | 120x60               |
#    And Click Add Protections Targeting button
#    Then Verify that following items are present
#      | Advertiser  |
#      | Ad Category |
#      | All Ads     |
#    When Select "Advertiser" from Add Protections Targeting
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 3              |
#    Then Verify that warning banner is under Publisher name
#    And Select "Accept" on the publisher change banner
#    Then Verify that all data is reseted

  Scenario: 102.Verify Protection entity search in Publisher dropdown
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    Then Verify that search with "R" works properly for Publisher dropdown
    And Click on the Create Protections button
    Then Verify that search with "Viki" works properly for Publisher dropdown

#  Scenario: 103.Verify Protection entity page name input vorks properly
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Click on Save Protection button
#    Then Verify that error apear for Name
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value             | ListValueIndex |
#      | Publisher Name | Viber |                |
#      | Name           | 1234567              |                |
#    And Click on Save Protection button
#    Then Verify the created Protection data is matching with its overview list values
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value             | ListValueIndex |
#      | Publisher Name | Viber |                |
#      | Name           | Long value              |                |
#    And Click on Save Protection button
#    Then Verify the created Protection data is matching with its overview list values

#  Scenario: 104.Verify Protection entity page multipaneselect items
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value | ListValueIndex |
#      | Publisher Name | Viber |                |
#      | Name           | Test  |                |
#    And Select targeting options items
#      | Inventory        | Viber Desktop App |
#      | Device           | Phone                |
#      | Operating System | MacOSX               |
#      | Geo              | Afghanistan          |
#      | Ad Size          | 120x60               |
#    And Click on Save Protection button
#    Then Verify the created Protection data is matching with its overview list values

#  Scenario: 106.Verify Protection entity page can be saved
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value | ListValueIndex |
#      | Publisher Name | Viber |                |
#      | Name           | Test  |                |
#    And Click on Save Protection button
#    Then Verify the created Protection data is matching with its overview list values
#    When Select the created Protection
#    And Click on "Edit Protections" button in Protections list page
#    And Enter the following data in the Create Protections page
#      | FieldName | Value      | ListValueIndex |
#      | Name      | TestUpdate |                |
#    And Click on Save Protection button
#    Then Verify the created Protection data is matching with its overview list values

  Scenario: 125.Verify that Activate Deactivate buttons are displayed at same time for Protections list page
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Select one "Active" Protections item
    Then Verify that following buttons are present in Protections list page
      | Edit Protections |
      | Deactivate       |
      | Activate         |
    When Click "Edit Protections" button in Protections list page
    Then Edit Protections pop up is present
    And Enter the following data in the Create Protections page
      | FieldName | Value       | ListValueIndex |
      | Name      | TestUpdated |                |
    Then Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values
    When Click "Activate" button in Protections list page
    Then "Active" is displayed for the created Protections
    And Select one "Active" Protections item
    When Click "Deactivate" button in Protections list page
    Then "Inactive" is displayed for the created Protections
    And Select 1 "Inactive" and 1 "Active" Protections items
    Then Verify that following buttons are present in Protections list page
      | Deactivate |
      | Activate   |
    When Click "Deactivate" button in Protections list page
    Then "Inactive" is displayed for the created Protections
    And Select 2 "Inactive" and 2 "Active" Protections items
    Then Verify that following buttons are present in Protections list page
      | Deactivate |
      | Activate   |
    When Click "Deactivate" button in Protections list page
    Then "Inactive" is displayed for the created Protections
    And Select 1 "Inactive" and 1 "Active" Protections items
    Then Verify that following buttons are present in Protections list page
      | Deactivate |
      | Activate   |
    When Click "Activate" button in Protections list page
    Then "Active" is displayed for the created Protections
    And Select one "Inactive" Protections item
    Then Verify that following buttons are present in Protections list page
      | Edit Protections |
      | Deactivate       |
      | Activate         |
    When Click "Edit Protections" button in Protections list page
    Then Edit Protections pop up is present
    And Enter the following data in the Create Protections page
      | FieldName | Value       | ListValueIndex |
      | Name      | TestUpdated |                |
    Then Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values
    When Click "Deactivate" button in Protections list page
    Then "Inactive" is displayed for the created Protections
    And Select one "Inactive" Protections item
    When Click "Activate" button in Protections list page
    Then "Active" is displayed for the created Protections
    And Select 2 "Inactive" and 2 "Active" Protections items
    Then Verify that following buttons are present in Protections list page
      | Deactivate |
      | Activate   |
    When Click "Activate" button in Protections list page
    Then "Active" is displayed for the created Protections

#  Scenario: 139.Verify that Publisher warning banned apears only if any forms were modified on Protections page
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 1              |
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 2              |
#    Then Verify that warning banner is not displayed under Publisher name
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value             | ListValueIndex |
#      | Publisher Name | Viber |                |
#      | Name           | Test              |                |
#    And "Disable" following toggle fields in create page
#      | FieldName |
#      | Active    |
#    And Select targeting options items
#      | Inventory        | Viber Desktop App |
#      | Device           | Phone                |
#      | Operating System | MacOSX               |
#      | Geo              | Afghanistan          |
#      | Ad Size          | 120x60               |
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 3              |
#    Then Verify that warning banner is under Publisher name
#    And Select "Accept" on the publisher change banner
#    When Close Create Protections page
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value             | ListValueIndex |
#      | Publisher Name | ListValue | 3              |
#      | Name           | Test              |                |
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 4              |
#    Then Verify that warning banner is under Publisher name
#    And Select "Accept" on the publisher change banner
#    When Close Create Protections page
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 4              |
#    And "Disable" following toggle fields in create page
#      | FieldName |
#      | Active    |
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 1              |
#    Then Verify that warning banner is under Publisher name
#    And Select "Accept" on the publisher change banner
#    When Close Create Protections page
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | Viber |                |
#    And Select targeting options items
#      | Inventory        | Viber Desktop App |
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 2              |
#    Then Verify that warning banner is under Publisher name
#    And Select "Accept" on the publisher change banner
#    When Close Create Protections page
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 4              |
#    And Select targeting options items
#      | Device           | Phone                        |
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 3              |
#    Then Verify that warning banner is under Publisher name
#    And Select "Accept" on the publisher change banner
#    When Close Create Protections page
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 4              |
#    And Select targeting options items
#      | Operating System | MacOSX |
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 3              |
#    Then Verify that warning banner is under Publisher name
#    And Select "Accept" on the publisher change banner
#    When Close Create Protections page
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 4              |
#    And Select targeting options items
#      | Geo              | Afghanistan                  |
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 1              |
#    Then Verify that warning banner is under Publisher name
#    And Select "Accept" on the publisher change banner
#    When Close Create Protections page
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 4              |
#    And Select targeting options items
#      | Ad Size          | 120x60                       |
#    And Enter the following data in the general card of private auction
#      | FieldName      | Value     | ListValueIndex |
#      | Publisher Name | ListValue | 2              |
#    Then Verify that warning banner is under Publisher name
#    And Select "Accept" on the publisher change banner

#  Scenario: 301.Verify that appropriate information displayed in details column for created protection
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value | ListValueIndex |
#      | Publisher Name | Viber |                |
#      | Name           | Test  |                |
#    And Click on Save Protection button
#    Then Verify the created Protection data is matching with its overview list values
#    When Hover over the Details icon in Protections page
#    Then Verify the protections details data is correct

#  Scenario: 302.Verify that blocked advertisers displayed in details column as Blocking X Advertisers
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value | ListValueIndex |
#      | Publisher Name | Viber |                |
#      | Name           | Test  |                |
#    And Click Add Protections Targeting button
#    And Select "Advertiser" from Add Protections Targeting
#    And Enable "Target away from the following advertiser (block)" radio button in Add Protections Targeting section
#    And Select the following advertisers from left panel in Add Protections Targeting section
#      | Advertiser                        |
#      | Cosphera Nahrungsergänzungsmittel |
#      | ICM                               |
#    And Click on Save Protection button
#    Then Verify the created Protection data is matching with its overview list values
#    When Hover over the Details icon in Protections page
#    Then Verify that details popup contain Advertisers section and advertisers that were selected before displayed in "Blocking" X Advertisers section

#  Scenario: 303.Verify that blocked advertisers displayed in details column as Allowing X Advertisers
#    Given admin user login to RX UI with valid username and password
#    Then Protections is present in the left nav menu
#    When Click on Protections option in Menu
#    Then User displayed with Protections page
#    And Click on the Create Protections button
#    And Enter the following data in the Create Protections page
#      | FieldName      | Value | ListValueIndex |
#      | Publisher Name | Viber |                |
#      | Name           | Test  |                |
#    And Click Add Protections Targeting button
#    And Select "Advertiser" from Add Protections Targeting
#    And Enable "Target towards the following advertiser (whitelist)" radio button in Add Protections Targeting section
#    And Select the following advertisers from left panel in Add Protections Targeting section
#      | Advertiser                        |
#      | Cosphera Nahrungsergänzungsmittel |
#      | ICM                               |
#    And Click on Save Protection button
#    Then Verify the created Protection data is matching with its overview list values
#    When Hover over the Details icon in Protections page
#    Then Verify that details popup contain Advertisers section and advertisers that were selected before displayed in "Allowing" X Advertisers section