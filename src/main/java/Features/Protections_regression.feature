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
    When Get pagination value in Protections page
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
    When Get pagination value in Protections page
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
    When Get pagination value in Protections page
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


  Scenario: 100.Verify Protection entity page default values
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
#    Then User displayed with Protections page
    And Click on the Create Protections button
    Then Verify that all elements are present and have proper default value

  Scenario: 101.Verify Protection entity page change Publisher resets all data
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And "Disable" following toggle fields in create page
      | FieldName |
      | Active    |
    And "Include" targeting options items in Inventory Targeting section
      | Inventory        | Viber Desktop App |
      | Device           | Phone             |
      | Operating System | MacOSX            |
      | Geo              | Afghanistan       |
      | Ad Size          | 120x60            |
    When Click on the Protection Type button
    And Select "Advertiser" from Protection Type dropdown
    And Select below Advertisers from list in Protection targeting section
      | Advertiser Name |
      | ICM             |
    And Enter the following data in the general card of private auction
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 3              |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all data is reseted

  Scenario: 102.Verify Protection entity search in Publisher dropdown
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    Then Verify that search with "R" works properly for Publisher dropdown
    And Click on the Create Protections button
    Then Verify that search with "Viki" works properly for Publisher dropdown


  Scenario: 103.Verify Protection entity page name input vorks properly
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Click on Save Protection button
    Then Verify that error apear for Name
    And Enter the following data in the Create Protections page
      | FieldName      | Value             | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | 1234567              |                |
    When Click on the Protection Type button
    And Select "Supply Exclusions" from Protection Type dropdown
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value             | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Long value              |                |
    When Click on the Protection Type button
    And Select "Supply Exclusions" from Protection Type dropdown
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values

  Scenario: 104.Verify Protection entity page multipaneselect items
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And "Include" targeting options items in Inventory Targeting section
      | Inventory        | Viber Desktop App |
      | Device           | Phone                |
      | Operating System | MacOSX               |
      | Geo              | Afghanistan          |
      | Ad Size          | 120x60               |
    When Click on the Protection Type button
    And Select "Ad Categories" from Protection Type dropdown
    And Select below Category from list in Protection targeting section
      | Category   |
      | Automotive |
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values

  Scenario: 106.Verify Protection entity page can be saved
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    When Click on the Protection Type button
    And Select "Supply Exclusions" from Protection Type dropdown
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values
    When Select the created Protection
    And Click on "Edit Protections" button in Protections list page
    And Enter the following data in the Create Protections page
      | FieldName | Value      | ListValueIndex |
      | Name      | TestUpdate |                |
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values

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

  Scenario: 139.Verify that Publisher warning banned apears only if any forms were modified on Protections page
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 1              |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 2              |
    Then Verify that warning banner is not displayed under Publisher name
    And Enter the following data in the Create Protections page
      | FieldName      | Value             | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test              |                |
    And "Disable" following toggle fields in create page
      | FieldName |
      | Active    |
    And "Include" targeting options items in Inventory Targeting section
      | Inventory        | Viber Desktop App |
      | Device           | Phone                |
      | Operating System | MacOSX               |
      | Geo              | Afghanistan          |
      | Ad Size          | 120x60               |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 3              |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    When Click on Close button
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value             | ListValueIndex |
      | Publisher Name | ListValue | 3              |
      | Name           | Test              |                |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 4              |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    When Click on Close button
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 4              |
    And "Disable" following toggle fields in create page
      | FieldName |
      | Active    |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 1              |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    When Click on Close button
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | Viber |                |
    And "Include" targeting options items in Inventory Targeting section
      | Inventory        | Viber Desktop App |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 2              |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    When Click on Close button
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 4              |
    And "Include" targeting options items in Inventory Targeting section
      | Device           | Phone                        |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 3              |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    When Click on Close button
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 4              |
    And "Include" targeting options items in Inventory Targeting section
      | Operating System | MacOSX |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 3              |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    When Click on Close button
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 4              |
    And "Include" targeting options items in Inventory Targeting section
      | Geo              | Afghanistan                  |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 1              |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    When Click on Close button
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 4              |
    And "Include" targeting options items in Inventory Targeting section
      | Ad Size          | 120x60                       |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue | 2              |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner

  Scenario: 301.Verify that appropriate information displayed in details column for created protection
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    When Click on the Protection Type button
    And Select "Ad Categories" from Protection Type dropdown
    And Select below Category from list in Protection targeting section
      | Category   |
      | Automotive |
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values
    When Hover over the Details icon in Protections page
    Then Verify the protections details data is correct

  Scenario: 302.Verify that blocked advertisers displayed in details column as Blocking X Advertisers
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    When Click on the Protection Type button
    And Select "Advertiser" from Protection Type dropdown
    And Enable "Target away from the following advertiser (block)" radio button in Protections Targeting section
    And Select below Advertisers from list in Protection targeting section
      | Advertiser Name |
      | ICM             |
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values
    When Hover over the Details icon in Protections page
    Then Verify that details popup contain Advertisers section and advertisers that were selected before displayed in "Blocking" X Advertisers section

  Scenario: 303.Verify that blocked advertisers displayed in details column as Allowing X Advertisers
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    When Click on the Protection Type button
    And Select "Advertiser" from Protection Type dropdown
    And Enable "Target towards the following advertiser (whitelist)" radio button in Protections Targeting section
    And Select below Advertisers from list in Protection targeting section
      | Advertiser Name |
      | ICM             |
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values
    When Hover over the Details icon in Protections page
    Then Verify that details popup contain Advertisers section and advertisers that were selected before displayed in "Allowing" X Advertisers section

  Scenario: 313.Verify that "Protection Type" displayed under Protection name field
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    Then Verify that Protection Type displayed under the Name field

  Scenario: 314.Verify that "Protection Type" has "Advertiser", "AD Categories", "Supply Exclusions"
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And Click on the Protection Type button
    Then Verify that below values display in Protection Type
      | Values            |
      | Advertiser        |
      | Ad Categories     |
      | Supply Exclusions |

  Scenario: 315.Verify that there is an ability to create protection with  "Protection Type" = "Advertiser"
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And Click on the Protection Type button
    And Select "Advertiser" from Protection Type dropdown
    And Select below Advertisers from list in Protection targeting section
      | Advertiser Name                |
      | Microlorge                     |
      | https                          |
      | Slotomania™ Vegas Casino Slots |
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values

  Scenario: 316.Verify that there is an ability to create protection with  "Protection Type" = "AD Categories"
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And Click on the Protection Type button
    And Select "Ad Categories" from Protection Type dropdown
    And Select below Category from list in Protection targeting section
      | Category               |
      | News                   |
      | Automotive             |
      | Technology & Computing |
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values

  Scenario: 317.Verify that there is an ability to create protection with  "Protection Type" = "Supply Exclusions"
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And Click on the Protection Type button
    And Select "Supply Exclusions" from Protection Type dropdown
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values

  Scenario: 318.Verify that error message displayed in case value from "Protection Type" not selected
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And Click on Save Protection button
    Then Verify the error message "Protection Type is required" displays in Create Protections page

  Scenario: 319.Verify that there is no ability to change "Protection Type" while editing existing protection
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Select one "Active" Protections item
    And Click on "Edit Protections" button in Protections list page
    Then Edit Protections pop up is present
    And Verify the Protection Type value is disabled
    When Click on Close button
    When Uncheck all protections items
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And "Disable" following toggle fields in create page
      | FieldName |
      | Active    |
    And Click on the Protection Type button
    And Select "Supply Exclusions" from Protection Type dropdown
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values
    When Select one "Inactive" Protections item
    And Click on "Edit Protections" button in Protections list page
    Then Edit Protections pop up is present
    And Verify the Protection Type value is disabled

  Scenario: 320.Verify that next error message displayed “Advertiser has no selections." displayed in case no Advertiser selected
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And Click on the Protection Type button
    And Select "Advertiser" from Protection Type dropdown
    And Click on Save Protection button
    Then Verify the error message "Advertisers has no selections" displays in Create Protections page

  Scenario: 321.Verify that next error message displayed “Ad Categories has no selections." displayed in case no Ad Categories selected
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And Click on the Protection Type button
    And Select "Ad Categories" from Protection Type dropdown
    And Click on Save Protection button
    Then Verify the error message "Ad Categories has no selections" displays in Create Protections page

  Scenario: 322.Include/Exclude buttons displaying for parent entities
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    Then Verify that Include/Exclude buttons displayed for focused entitiy when put mouse over the below entity in Inventory Targeting section
      | Inventory        | Viber Desktop App |
      | Device           | Phone             |
      | Operating System | MacOSX            |
      | Geo              | Afghanistan       |
      | Ad Size          | 120x60            |

  Scenario: 323.Include/Exclude buttons displaying for child entities
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    Then Verify that Include/Exclude buttons displayed for focused entitiy when put mouse over the below entity in Inventory Targeting section
      | Inventory | Viber iOS App Prod > Viber iOS Explore Screen Prod |

  Scenario: 324."Clear all" link availability for Include operation
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App |
    Then Verify that Clear All link available in "Inventory" panel
    When "Include" targeting options items in Inventory Targeting section
      | Device | Phone |
    Then Verify that Clear All link available in "Device" panel
    When "Include" targeting options items in Inventory Targeting section
      | Operating System | MacOSX |
    Then Verify that Clear All link available in "Operating System" panel
    When "Include" targeting options items in Inventory Targeting section
      | Geo | Afghanistan |
    Then Verify that Clear All link available in "Geo" panel
    When "Include" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    Then Verify that Clear All link available in "Ad Size" panel

  Scenario: 325."Clear all" link availability for Exclude operation
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber iOS App Prod > Viber iOS Explore Screen Prod |
    Then Verify that Clear All link available in "Inventory" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Device | Tablet |
    Then Verify that Clear All link available in "Device" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Operating System | Windows |
    Then Verify that Clear All link available in "Operating System" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
    Then Verify that Clear All link available in "Geo" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    Then Verify that Clear All link available in "Ad Size" panel

  Scenario: 326.Include objects in multi-pane select list
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App                              |
      | Inventory | Viber iOS App Prod > Viber iOS BCI 320x50 Prod |
    And Click "Include All" button in "Inventory" panel
    Then Verify that all items are displayed in "Included" list in "Inventory" panel
    When "Include" targeting options items in Inventory Targeting section
      | Device | Tablet |
    And Click "Include All" button in "Device" panel
    Then Verify that all items are displayed in "Included" list in "Device" panel
    When "Include" targeting options items in Inventory Targeting section
      | Operating System | Windows |
    And Click "Include All" button in "Operating System" panel
    Then Verify that all items are displayed in "Included" list in "Operating System" panel
    When "Include" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
    And Click "Include All" button in "Geo" panel
    Then Verify that all items are displayed in "Included" list in "Geo" panel
    When "Include" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    And Click "Include All" button in "Ad Size" panel
    Then Verify that all items are displayed in "Included" list in "Ad Size" panel

  Scenario: 327.Exclude objects in multi-pane select list
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App                              |
      | Inventory | Viber iOS App Prod > Viber iOS BCI 320x50 Prod |
    And Click "Exclude All" button in "Inventory" panel
    Then Verify that all items are displayed in "Excluded" list in "Inventory" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Device | Tablet |
    And Click "Exclude All" button in "Device" panel
    Then Verify that all items are displayed in "Excluded" list in "Device" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Operating System | Windows |
    And Click "Exclude All" button in "Operating System" panel
    Then Verify that all items are displayed in "Excluded" list in "Operating System" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
    And Click "Exclude All" button in "Geo" panel
    Then Verify that all items are displayed in "Excluded" list in "Geo" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    And Click "Exclude All" button in "Ad Size" panel
    Then Verify that all items are displayed in "Excluded" list in "Ad Size" panel

  Scenario: 328.Sorting in right and left list should be consistent
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App      |
      | Inventory | Viber iOS App Prod     |
      | Inventory | Viber Android App Prod |
    Then Verify that the "included" items in right list are sorted in the same order as in left list in "Inventory" panel
    When "Include" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
      | Device | Connected TV  |
      | Device | Phone  |
    Then Verify that the "included" items in right list are sorted in the same order as in left list in "Device" panel
    When "Include" targeting options items in Inventory Targeting section
      | Operating System | Windows |
      | Operating System | Android |
      | Operating System | MacOSX  |
    Then Verify that the "included" items in right list are sorted in the same order as in left list in "Operating System" panel
    When "Include" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
      | Geo | Albania   |
      | Geo | Yemen   |
    Then Verify that the "included" items in right list are sorted in the same order as in left list in "Geo" panel
    When "Include" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
      | Ad Size | In-Feed Native (1x1)  |
      | Ad Size | 300x50  |
    Then Verify that the "included" items in right list are sorted in the same order as in left list in "Ad Size" panel
    When Click on the Protection Type button
    And Select "Advertiser" from Protection Type dropdown
    And Select below Advertisers from list in Protection targeting section
      | Advertiser Name                |
      | ICM                            |
      | Microlorge                     |
      | Slotomania™ Vegas Casino Slots |
    Then Verify that the included items in right list are sorted in the same order as in left list in "Advertiser" panel
    When Click on Close button
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App      |
      | Inventory | Viber iOS App Prod     |
      | Inventory | Viber Android App Prod |
    Then Verify that the "excluded" items in right list are sorted in the same order as in left list in "Inventory" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
      | Device | Connected TV  |
      | Device | Phone  |
    Then Verify that the "excluded" items in right list are sorted in the same order as in left list in "Device" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Operating System | Windows |
      | Operating System | Android |
      | Operating System | MacOSX  |
    Then Verify that the "excluded" items in right list are sorted in the same order as in left list in "Operating System" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
      | Geo | Albania   |
      | Geo | Yemen   |
    Then Verify that the "excluded" items in right list are sorted in the same order as in left list in "Geo" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
      | Ad Size | In-Feed Native (1x1)  |
      | Ad Size | 300x50  |
    Then Verify that the "excluded" items in right list are sorted in the same order as in left list in "Ad Size" panel
    When Click on the Protection Type button
    And Select "Ad Categories" from Protection Type dropdown
    And Select below Category from list in Protection targeting section
      | Category               |
      | News                   |
      | Automotive             |
      | Technology & Computing |
    Then Verify that the included items in right list are sorted in the same order as in left list in "Ad Categories" panel

  Scenario: 329.Indication of the number of objects selected
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App      |
      | Inventory | Viber iOS App Prod     |
      | Inventory | Viber Android App Prod |
    Then Verify that "3 Media included" are displayed in "Inventory" panel
    When "Include" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
      | Device | Connected TV  |
      | Device | Phone  |
    Then Verify that "3 device(s) included" are displayed in "Device" panel
    When "Include" targeting options items in Inventory Targeting section
      | Operating System | Windows |
      | Operating System | Android |
      | Operating System | MacOSX  |
    Then Verify that "3 operating system(s) are included" are displayed in "Operating System" panel
    When "Include" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
      | Geo | Albania   |
      | Geo | Yemen   |
    Then Verify that "3 geo(s) included" are displayed in "Geo" panel
    When "Include" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
      | Ad Size | In-Feed Native (1x1)  |
      | Ad Size | 300x50  |
    Then Verify that "3 size(s) included" are displayed in "Ad Size" panel
    When Click on the Protection Type button
    And Select "Advertiser" from Protection Type dropdown
    And Select below Advertisers from list in Protection targeting section
      | Advertiser Name                |
      | ICM                           |
      | Microlorge                     |
      | Slotomania™ Vegas Casino Slots |
    Then Verify that "Block 3 advertisers" are displayed in "Advertiser" panel
    When Enable "Target towards the following advertiser (whitelist)" radio button in Protections Targeting section
    Then Verify that "Whitelist 3 advertisers" are displayed in "Advertiser" panel
    When Click on Close button
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App      |
      | Inventory | Viber iOS App Prod     |
      | Inventory | Viber Android App Prod |
    Then Verify that "3 Media excluded" are displayed in "Inventory" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
      | Device | Connected TV  |
      | Device | Phone  |
    Then Verify that "3 device(s) excluded" are displayed in "Device" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Operating System | Windows |
      | Operating System | Android |
      | Operating System | MacOSX  |
    Then Verify that "3 operating system(s) are excluded" are displayed in "Operating System" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
      | Geo | Albania   |
      | Geo | Yemen   |
    Then Verify that "3 geo(s) excluded" are displayed in "Geo" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
      | Ad Size | In-Feed Native (1x1)  |
      | Ad Size | 300x50  |
    Then Verify that "3 size(s) excluded" are displayed in "Ad Size" panel
    When Click on the Protection Type button
    And Select "Ad Categories" from Protection Type dropdown
    And Select below Category from list in Protection targeting section
      | Category               |
      | News                   |
      | Automotive             |
      | Technology & Computing |
    Then Verify that "Block 3 Ad Categories" are displayed in "Ad Categories" panel
    When Enable "Target towards the following ad categories (whitelist)" radio button in Protections Targeting section
    Then Verify that "Whitelist 3 Ad Categories" are displayed in "Ad Categories" panel

  Scenario: 330.Speedy swap to Exclude all
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Inventory" panel
    And "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Inventory" panel
    When "Include" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Device" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Device" panel
    When "Include" targeting options items in Inventory Targeting section
      | Operating System | Windows |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Operating System" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Operating System | Windows |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Operating System" panel
    When "Include" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Geo" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Geo" panel
    When "Include" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Ad Size" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Ad Size" panel

  Scenario: 331.Speedy swap to Include all
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Inventory" panel
    And "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Inventory" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Device" panel
    When "Include" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Device" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Operating System | Windows |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Operating System" panel
    When "Include" targeting options items in Inventory Targeting section
      | Operating System | Windows |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Operating System" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Geo" panel
    When "Include" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Geo" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Ad Size" panel
    When "Include" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    Then Verify that objects can now be added as "Included" and "INCLUDE ALL" is available in "Ad Size" panel

  Scenario: 332.Child can be Excluded separate from Parent
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Calls Tab 320x50 Prod |
    Then Verify that child object "Viber Android Calls Tab 320x50 Prod" is displayed properly with parent name "Media > Viber Android App Prod" in right list
    Then Verify that "1 Ad Spot excluded" are displayed in "Inventory" panel

  Scenario: 334.Selecting Parent as Excluded will unselect all it's childs as Excluded
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Calls Tab 320x50 Prod |
    And "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod |
    Then Verify that only item "Viber Android App Prod" is displayed as "Excluded" in right list in "Inventory" panel

  Scenario: 335.Include all under parent, Excluding one of its children
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod |
    And "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Calls Tab 320x50 Prod |
    Then Verify that "Viber Android App Prod" is displayed as "Included" in right list in "Inventory" panel
    Then Verify that "Viber Android Calls Tab 320x50 Prod" is displayed as "Excluded" in right list in "Inventory" panel

  Scenario: 336.Changing Publisher should reset right and left columns for include Parent and exclude Child
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod |
    And "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Calls Tab 320x50 Prod |
    And Enter the following data in the Create Protections page
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | Viki |               |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Inventory" Panel

  Scenario: 337.Changing Publisher should reset right and left columns for include
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App |
    And "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Calls Tab 320x50 Prod |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Inventory" Panel
    When "Include" targeting options items in Inventory Targeting section
      | Device | Connected TV |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Device" Panel
    When "Include" targeting options items in Inventory Targeting section
      | Operating System | MacOSX |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Operating System" Panel
    When "Include" targeting options items in Inventory Targeting section
      | Geo | Angola |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Geo" Panel
    When "Include" targeting options items in Inventory Targeting section
      | Ad Size | 160x600 |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Ad Size" Panel

  Scenario: 338.Changing Publisher should reset right and left columns for exclude
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App |
    And "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Calls Tab 320x50 Prod |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Inventory" Panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Device | Connected TV |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Device" Panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Operating System | MacOSX |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Operating System" Panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Geo | Angola |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Geo" Panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Ad Size | 160x600 |
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify that warning banner is under Publisher name
    And Select "Accept" on the publisher change banner
    Then Verify that all changes in left and right columns are reseted in "Ad Size" Panel

  Scenario: 339.Exclude all button is available if first object is selected as Exclude
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Desktop App |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Inventory" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Device" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Operating System | Windows |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Operating System" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Geo" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    Then Verify that objects can now be added as "Excluded" and "EXCLUDE ALL" is available in "Ad Size" panel

  Scenario: 340.Selecting Include all display only parents selected and unselect all individual selected children Excluded
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Chat Ext 320x50 Prod |
    And Click "Include All" button in "Inventory" panel
    Then Verify that all items are displayed in "Included" list in "Inventory" panel

  Scenario: 341.Selecting Exclude all display only parents selected and unselect all individual selected children Excluded
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Chat Ext 320x50 Prod |
    And Click "Exclude All" button in "Inventory" panel
    Then Verify that all items are displayed in "Excluded" list in "Inventory" panel

  Scenario: 342.Exclude all
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And Expand the "Inventory" panel in Inventory Targeting section
    Then Verify that Include all link displayed under Show inactive toggle
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod |
    Then Verify that Include All link changed to Exclude All in "Inventory" panel
    Then Verify that Exclude All is disabled if more than 1000 items are displayed below in "Inventory" panel
    Then Verify that Exclude All is enabled if less than 1000 items are displayed below in "Inventory" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Device | Set Top Box |
    Then Verify that Include All link changed to Exclude All in "Device" panel
    Then Verify that Exclude All is disabled if more than 1000 items are displayed below in "Device" panel
    Then Verify that Exclude All is enabled if less than 1000 items are displayed below in "Device" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Operating System | Windows |
    Then Verify that Include All link changed to Exclude All in "Operating System" panel
    Then Verify that Exclude All is disabled if more than 1000 items are displayed below in "Operating System" panel
    Then Verify that Exclude All is enabled if less than 1000 items are displayed below in "Operating System" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Geo | Bolivia |
    Then Verify that Include All link changed to Exclude All in "Geo" panel
    Then Verify that Exclude All is disabled if more than 1000 items are displayed below in "Geo" panel
    Then Verify that Exclude All is enabled if less than 1000 items are displayed below in "Geo" panel
    When "Exclude" targeting options items in Inventory Targeting section
      | Ad Size | 120x60 |
    Then Verify that Include All link changed to Exclude All in "Ad Size" panel
    Then Verify that Exclude All is disabled if more than 1000 items are displayed below in "Ad Size" panel
    Then Verify that Exclude All is enabled if less than 1000 items are displayed below in "Ad Size" panel

  Scenario: 344.Selecting a child object, doesn't select its parent as Excluded
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Chat Ext 320x50 Prod |
    Then Verify that only item "Viber Android Chat Ext 320x50 Prod" is displayed as "Excluded" in right list in "Inventory" panel

  Scenario: 345.Children of already included parents are able to be excluded
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber iOS App Prod  |
    Then Verify that child can be "Excluded" while Parent "Viber iOS App Prod" is included

  Scenario: 346.Children of already excluded parents are not able to be also excluded
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber iOS App Prod  |
    Then Verify that no child can be selected while Parent "Viber iOS App Prod" is excluded

  Scenario: 347.If a parent got removed from the excluded entities in the excluded pane, the children will then be able to be excluded
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    When Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod  |
    Then Verify that no child can be selected while Parent "Viber Android App Prod" is excluded
    When Remove item "Viber Android App Prod" from the right list
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Chat Ext 320x50 Prod |
    Then Verify that only item "Viber Android Chat Ext 320x50 Prod" is displayed as "Excluded" in right list in "Inventory" panel

  Scenario: 348.Details popup display both included and excluded for Inventory
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    When Click on the Protection Type button
    And Select "Supply Exclusions" from Protection Type dropdown
    When "Include" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod |
    When "Exclude" targeting options items in Inventory Targeting section
      | Inventory | Viber Android App Prod > Viber Android Chat Ext 320x50 Prod |
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values
    When Hover over the Details icon in Protections page
    Then Verify that Details popup display below values for "Inventory"
      | Viber Android App Prod             |
      | Viber Android Chat Ext 320x50 Prod |

  Scenario: 349.Selected Advertiser list shold show an error if it's above the limit
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    When Click on the Protection Type button
    And Select "Advertiser" from Protection Type dropdown
    And Add "advertisers" until validation error triggers
    Then Verify that error "The selected advertiser list is too large. Please un-select some advertisers to continue." is displayed in Protection targeting section
    When Unselect "advertisers" until validation error disapears
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values

  Scenario: 350.Selected Ad Category list shold show an error if it's above the limit
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    When Click on the Protection Type button
    And Select "Ad Categories" from Protection Type dropdown
#    And Add "Category" until validation error triggers
    And Click on Include All button in Ad Categories section
    Then Verify that error "The selected category list is too large. Please un-select some categories to continue." is displayed in Protection targeting section
    When Unselect "Category" until validation error disapears
    And Click on Save Protection button
    Then Verify the created Protection data is matching with its overview list values

  Scenario: 359.Searching result for Protection -> Inventory is displayed correctly if "Show inactive" toggle is enabled
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And Expand the "Inventory" panel in Inventory Targeting section
    And Set Show Inactive as "active" in the Inventory panel
    Then Verify Active and Inactive media and ad spot are displayed

  Scenario: 362.Active media and adspot should be displayed if "Show inactive" toggle is disabled for Protection targeting
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And Expand the "Inventory" panel in Inventory Targeting section
    And Expand the parent item "Viber Desktop App" in select table
    Then Verify Active media and adspot are displayed only

  Scenario: 363.Searching result for Protection targeting is displayed correctly
    Given admin user login to RX UI with valid username and password
    Then Protections is present in the left nav menu
    When Click on Protections option in Menu
    Then User displayed with Protections page
    And Click on the Create Protections button
    And Enter the following data in the Create Protections page
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    When Click on the Protection Type button
    And Select "Ad Categories" from Protection Type dropdown
    And Types "m" in search box in Protection Targeting section
    Then Verify Parent and childs including "m" are displayed