Feature: Adspots page regression

  Scenario:  Verify the default column names in the adspots overview page
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then Verify the overview page contains following columns
      | ColumnName          |
      | ID                  |
      | Ad Spot Name        |
      | Publisher           |
      | Details             |
      | Related Media       |
      | Active/Inactive     |
      | Default Sizes       |
      | Default Floor Price |


  Scenario:  Verify the pagination exists for the list in the adspots page for admin
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then Verify the pagination of the listed rows in the Page with a selection of 50 rows per page with 8 columns


  Scenario:  Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    Then User displayed with Adspots page
    And User click on table options button
    Then Verify that column "Ad Spot Name" can be hidden and shown


  Scenario:  Verify onclicking relevant status from table options shows only that particular table rows with that status
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then User click on table options button
    And Verify that column "Active/Inactive" only shows relevant rows in the table with filter "Active"
    And Verify that column "Active/Inactive" only shows relevant rows in the table with filter "Inactive"

  @debug
  Scenario:  Verify searching adspots with avaiable and non available adspot name
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then Verify the search functionality with the following names
      | Name       | ColumnName   |
      | zoo test   | Ad Spot Name |
      | Top Banner | Ad Spot Name |


  Scenario:  Verify enabling and disabling of an adspot from the overview page
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then Verify the search functionality with the following names
      | Name       | ColumnName   |
      | Top Banner | Ad Spot Name |
    Then Verify enabling and disabling of an adspot from the overview page


  Scenario:  Verify enabling and disabling of multiple adspots from the overview page
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then Verify the search functionality with the following names
      | Name       | ColumnName   |
      | Top Banner | Ad Spot Name |
    And Search for adspot
      | Name    |
      | Dynamic |
    Then Verify Enable of multiple adspots from the overview page
    Then Verify Disable of multiple adspots from the overview page


  Scenario:  Verify sorting of the table's columns of the adspots overview page
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then Verify the sorting functionality with the following columns
      | ColumnName    | SortType |
      | ID            | asc      |
      | Publisher     | desc     |
      | Related Media | asc      |

  # It's a known bug in line 95
  @Ignore
  Scenario:  Verify mandatory fields in the Create Adspot Page
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    And Click on the Adspot create button
    Then Click on save button
    Then Verify following fields are mandatory for create page
      | FieldName      |
      | Publisher Name |
    And Enter the following data in the general card of adspot
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    And Click on save button
    Then Verify following fields are mandatory for create page
      | FieldName              |
      | Ad Spot Name           |
      | Related Media          |
      | Position               |
      | Default Ad Sizes       |
      | Default Floor Price    |
      | Video Placement Type   |
      | Video Playback Methods |


  Scenario:  Verify Changing publisher name alert the user and then on change every fields go to default state
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    And Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And Verify Categories filed has subcategories
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 2              |
      | Default Floor Price | 10        |                |
    And "Enable" the banner card
    And "Enable" the native card
    And "Enable" the in-banner video card
    When Enter the following data in the general card of adspot
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify the following message is displayed when the publisher changed
      | Message                                                                                      |
      | By changing the Publisher the form will be reset and the previous changes will not be saved. |
    And Select "Cancel" on the publisher change banner
    Then Verify the following columns value with the created data for the general card of adspot
      | FieldName           |
      | Ad Spot Name        |
      | Related Media       |
      | Categories          |
      | Position            |
      | Filter              |
      | Default Ad Sizes    |
      | Default Floor Price |
    And Verify the banner card is "Enabled"
    And Verify the native card is "Enabled"
    And Verify the in-banner video card is "Enabled"
    And Enter the following data in the general card of adspot
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    And Select "Accept" on the publisher change banner
    Then Verify the following columns values for the general card of adspot is empty
      | FieldName           |
      | Ad Spot Name        |
      | Related Media       |
      | Categories          |
      | Position            |
      | Filter              |
      | Default Ad Sizes    |
      | Default Floor Price |
    Then Verify the banner card is "Disabled"
    And Verify the native card is "Disabled"
    And Verify the in-banner video card is "Disabled"


  Scenario: Verify with invalid value for floor price and adsizes>1 creation is unsuccessful
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then Click on the Adspot create button
    And Enter the following data in the general card of adspot
      | FieldName           | Value      | ListValueIndex |
      | Publisher Name      | Viber      |                |
      | Ad Spot Name        | Auto_Test2 |                |
      | Related Media       | ListValue  | 1              |
      | Categories          | ListValue  | 2              |
      | Position            | ListValue  | 2              |
      | Filter              | ListValue  | 1              |
      | Default Ad Sizes    | ListValue  | 2,3            |
      | Default Floor Price | -10        |                |
    And "Enable" the banner card
    And "Expand" the banner card
    Then Enter the following data in the banner card of adspot
      | FieldName   | Value     | ListValueIndex |
      | Ad Sizes    | ListValue | 2,3            |
      | Floor Price | -10       |                |
    And "Enable" the native card
    And "Expand" the native card
    Then Enter the following data in the native card of adspot
      | FieldName   | Value |
      | Floor Price | -10   |
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    Then Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 1              |
      | Ad Sizes               | ListValue | 2,3            |
      | Floor Price            | -10       |                |
      | Minimum Video Duration | ListValue | 1              |
      | Maximum Video Duration | ListValue | 1              |
      | Playback Methods       | ListValue | 1,2,3          |
    And Click on save button
    Then Verify error messages for sizes and floor price for the following cards
      | Card          | SizeErrorMsg                       | FloorPriceErrorMsg                          |
      | General       | Default Ad Sizes must be 1 or less | A valid price is between 0.00 and 999,999.99 |
      | Banner        | Banner Ad Sizes must be 1 or less  | A valid price is between 0.00 and 999,999.99 |
      | Native        |                                    | A valid price is between 0.00 and 999,999.99 |
      | InBannerVideo | Video Ad Sizes must be 1 or less   | A valid price is between 0.00 and 999,999.99 |


  Scenario: Verify successful creation and updation of an adspot
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Active              | Active    |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 2              |
      | Default Floor Price | 10        |                |
    And "Enable" the banner card
    And "Expand" the banner card
    Then Enter the following data in the banner card of adspot
      | FieldName   | Value     | ListValueIndex |
      | Ad Sizes    | ListValue | 2              |
      | Floor Price | 5         |                |
    And "Enable" the native card
    And "Expand" the native card
    Then Enter the following data in the native card of adspot
      | FieldName   | Value |
      | Floor Price | 5     |
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    Then Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 1              |
      | Ad Sizes               | ListValue | 2              |
      | Floor Price            | 5         |                |
      | Minimum Video Duration | ListValue | 1              |
      | Maximum Video Duration | ListValue | 1              |
      | Playback Methods       | ListValue | 1,2,3          |
    And Click on save button and wait for dialog to close
    Then Verify the created adspot data is matching with its overview list values
    When Click on the created adspotname in the overview page
    And Verify following fields are disabled on create/edit adspot page
      | FieldName      |
      | Publisher Name |
      | Related Media  |
    Then Verify the following columns value with the created data for the general card of adspot
      | FieldName           |
      | Publisher Name      |
      | Active              |
      | Ad Spot Name        |
      | Related Media       |
      | Default Ad Sizes    |
      | Default Floor Price |
    And Verify the banner card is "Enabled"
    And Verify the native card is "Enabled"
    And Verify the in-banner video card is "Enabled"
    Then "Expand" the banner card
    And "Expand" the native card
    And "Expand" the in-banner video card
    Then Verify the following columns value with the created data for the banner card of adspot
      | FieldName   |
      | Ad Sizes    |
      | Floor Price |
    Then Verify the following columns value with the created data for the native card of adspot
      | FieldName   |
      | Floor Price |
    Then Verify the following columns value with the created data for the in-banner video card of adspot
      | FieldName              |
      | Ad Sizes               |
      | Floor Price            |
      | Minimum Video Duration |
      | Maximum Video Duration |
      | Playback Methods       |
      | Video Placement Type   |

	#Editing flow
    When Enter the following data in the general card of adspot
      | FieldName           | Value          | ListValueIndex |
      | Active              | Inactive       |                |
      | Ad Spot Name        | Auto_Test_Edit |                |
      | Categories          | ListValue      | 2,4            |
      | Position            | ListValue      | 1              |
      | Filter              | ListValue      | 1              |
      | Default Ad Sizes    | ListValue      | 2,4            |
      | Default Floor Price | 8              |                |
    And Enter the following data in the banner card of adspot
      | FieldName   | Value     | ListValueIndex |
      | Ad Sizes    | ListValue | 2,4            |
      | Floor Price | 6         |                |
    And Enter the following data in the native card of adspot
      | FieldName   | Value |
      | Floor Price | 6     |
    And Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 2              |
      | Ad Sizes               | ListValue | 2,4            |
      | Floor Price            | 6         |                |
      | Minimum Video Duration | ListValue | 2              |
      | Maximum Video Duration | ListValue | 2              |
      | Playback Methods       | ListValue | 1,2,4          |
    Then Click on save button and wait for dialog to close
    Then Verify the created adspot data is matching with its overview list values
    And Click on the created adspotname in the overview page
    Then Verify the following columns value with the created data for the general card of adspot
      | FieldName           |
      | Publisher Name      |
      | Active              |
      | Ad Spot Name        |
      | Related Media       |
      | Default Ad Sizes    |
      | Default Floor Price |
    Then Verify the banner card is "Enabled"
    And Verify the native card is "Enabled"
    And Verify the in-banner video card is "Enabled"
    Then "Expand" the banner card
    And "Expand" the native card
    And "Expand" the in-banner video card
    Then Verify the following columns value with the created data for the banner card of adspot
      | FieldName   |
      | Ad Sizes    |
      | Floor Price |
    Then Verify the following columns value with the created data for the native card of adspot
      | FieldName   |
      | Floor Price |
    Then Verify the following columns value with the created data for the in-banner video card of adspot
      | FieldName              |
      | Ad Sizes               |
      | Floor Price            |
      | Minimum Video Duration |
      | Maximum Video Duration |
      | Playback Methods       |
      | Video Placement Type   |


  Scenario: Verify adspot can be created without any cards enabled when is inactive but for
  editing with activing the same adspot the edit is unsuccessful
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    And Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Active              | Inactive  |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 2              |
      | Default Floor Price | 10        |                |
    And Click on save button and wait for dialog to close
    Then Verify the created adspot data is matching with its overview list values
    When Click on the created adspotname in the overview page
    And Verify following fields are disabled on create/edit adspot page
      | FieldName      |
      | Publisher Name |
      | Related Media  |
    Then Verify the following columns value with the created data for the general card of adspot
      | FieldName           |
      | Publisher Name      |
      | Active              |
      | Ad Spot Name        |
      | Related Media       |
      | Default Ad Sizes    |
      | Default Floor Price |

	#Editing flow
    When Enter the following data in the general card of adspot
      | FieldName | Value  | ListValueIndex |
      | Active    | Active |                |
    And Click on save button
    Then Verify the save is failed


  Scenario: Verify without adding any card ,creation of adspot is unsuccessful when the adspot is active
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Active              | Active    |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 2              |
      | Default Floor Price | 10        |                |
    And Click on save button
    Then Verify the save is failed


  Scenario: Verify after creation of adspot if the publisher is disabled the editing of the adspot is unsuccessful
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value           | ListValueIndex |
      | Publisher Name      | RakutenTV       |                |
      | Active              | Inactive        |                |
      | Ad Spot Name        | Auto_Test       |                |
      | Related Media       | ListValue       | 1              |
      | Categories          | ListValue       | 2              |
      | Position            | ListValue       | 1              |
      | Default Ad Sizes    | ListValue       | 2              |
      | Default Floor Price | 10              |                |
    And Click on save button and wait for dialog to close
    Then Verify the created adspot data is matching with its overview list values
    And Click on publisher option under Admin
    And Publisher page should be displayed
    Then Verify the search functionality with the following names
      | Name            | ColumnName |
      | RakutenTV       | Publisher  |
    When Select "1" publisher in list view
    And Click on "Deactivate Publisher" button in Publisher page
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    And Click on the created adspotname in the overview page
    Then Verify following fields are disabled on create/edit adspot page
      | FieldName     |
      | Related Media |
    And Click on save button
    Then Verify the save is failed
    And Close toast message
    And Close Adspot entity page
    And User displayed with Adspots page
    And Click on publisher option under Admin
#    And Click on publisher option from left menu
    And Publisher page should be displayed
    Then Verify the search functionality with the following names
      | Name            | ColumnName |
      | RakutenTV       | Publisher  |
    When Select "1" publisher in list view
    And Click on "Activate Publisher" button in Publisher page


  Scenario: Verify creation of adspot is successful if the related media is disabled
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    And User displayed with media page
    Then Verify the search functionality with the following names
      | Name                 | ColumnName |
      | Mark Mc Desktop Site | Media Name |
    And "Disable" a media from the media overview page
    Then Click on Adspots sub menu
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value                | ListValueIndex |
      | Publisher Name      | Mark Mceachran       |                |
      | Active              | Inactive             |                |
      | Ad Spot Name        | Auto_Test            |                |
      | Related Media       | Mark Mc Desktop Site |                |
      | Categories          | ListValue            | 2              |
      | Position            | ListValue            | 2              |
      | Default Ad Sizes    | ListValue            | 2              |
      | Default Floor Price | 10                   |                |
    And Click on save button and wait for dialog to close
    And User displayed with Adspots page
    When Click on Media sub menu
    And User displayed with media page
    Then Verify the search functionality with the following names
      | Name                 | ColumnName |
      | Mark Mc Desktop Site | Media Name |
    And "Enable" a media from the media overview page


  Scenario: Verify after creation of adspot if the related media is disabled the editing of the adspot is successful
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value                | ListValueIndex |
      | Publisher Name      | Mark Mceachran       |                |
      | Active              | Inactive             |                |
      | Ad Spot Name        | Auto_Test            |                |
      | Related Media       | Mark Mc Desktop Site |                |
      | Categories          | ListValue            | 2              |
      | Position            | ListValue            | 2              |
      | Default Ad Sizes    | ListValue            | 2              |
      | Default Floor Price | 10                   |                |
    Then Click on save button and wait for dialog to close
    Then Verify the created adspot data is matching with its overview list values
    Then Click on Media sub menu
    Then User displayed with media page
    Then Verify the search functionality with the following names
      | Name                 | ColumnName |
      | Mark Mc Desktop Site | Media Name |
    Then "Disable" a media from the media overview page
    Then Click on Adspots sub menu
    And User displayed with Adspots page
    And Click on the created adspotname in the overview page
    And Verify following fields are disabled on create/edit adspot page
      | FieldName      |
      | Publisher Name |
      | Related Media  |
    And Click on save button and wait for dialog to close
    And User displayed with Adspots page
    Then Click on Media sub menu
    Then User displayed with media page
    Then Verify the search functionality with the following names
      | Name                 | ColumnName |
      | Mark Mc Desktop Site | Media Name |
    Then "Enable" a media from the media overview page


  Scenario: Verify adspot cannot be created with defalut sizeless adsize and banner as same as default
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value                | ListValueIndex |
      | Publisher Name      | Mark Mceachran       |                |
      | Active              | Inactive             |                |
      | Ad Spot Name        | Auto_Test            |                |
      | Related Media       | Mark Mc Desktop Site |                |
      | Categories          | ListValue            | 2              |
      | Position            | ListValue            | 2              |
      | Default Ad Sizes    | ListValue            | 1              |
      | Default Floor Price | 10                   |                |
    And "Enable" the banner card
    And "Expand" the banner card
    Then Enter the following data in the banner card of adspot
      | FieldName   | Value   | ListValueIndex |
      | Ad Sizes    | Default |                |
      | Floor Price | 2       |                |
    And Click on save button
    Then Verify the save is failed
    And Close toast message
    And "Disable" the banner card
    And Click on save button
    Then Verify the save is failed


  Scenario: Verify adspot cannot be created with defalut sizeless adsize and video as same as default
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value                | ListValueIndex |
      | Publisher Name      | Mark Mceachran       |                |
      | Active              | Inactive             |                |
      | Ad Spot Name        | Auto_Test            |                |
      | Related Media       | Mark Mc Desktop Site |                |
      | Categories          | ListValue            | 2              |
      | Position            | ListValue            | 2              |
      | Default Ad Sizes    | ListValue            | 1              |
      | Default Floor Price | 10                   |                |
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    And Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 2              |
      | Ad Sizes               | Default   |                |
      | Floor Price            | Default   |                |
      | Minimum Video Duration | ListValue | 2              |
      | Maximum Video Duration | ListValue | 2              |
      | Playback Methods       | ListValue | 1,2,4          |
    And Click on save button
    Then Verify the save is failed
    And Close toast message
    And "Disable" the in-banner video card
    And Click on save button
    Then Verify the save is failed


  Scenario: Verify successful creation and updation of an adspot for a default size of 1*1 with native as same as default
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Active              | Active    |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 1              |
      | Default Floor Price | 10        |                |
    And "Enable" the native card
    And "Expand" the native card
    Then Enter the following data in the native card of adspot
      | FieldName   | Value   |
      | Floor Price | Default |
    And Click on save button and wait for dialog to close
    Then Verify the created adspot data is matching with its overview list values
    When Click on the created adspotname in the overview page
    And Verify the native card is "Enabled"
    And "Expand" the native card
    Then Verify the following columns value with the created data for the native card of adspot
      | FieldName   |
      | Floor Price |

	#Editing flow

    And "Enable" the banner card
    And "Expand" the banner card
    And Enter the following data in the banner card of adspot
      | FieldName   | Value     | ListValueIndex |
      | Ad Sizes    | ListValue | 2              |
      | Floor Price | Default   |                |
    And Enter the following data in the native card of adspot
      | FieldName   | Value |
      | Floor Price | 6     |
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    And Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 2              |
      | Ad Sizes               | ListValue | 2              |
      | Floor Price            | Default   |                |
      | Minimum Video Duration | ListValue | 2              |
      | Maximum Video Duration | ListValue | 2              |
      | Playback Methods       | ListValue | 1,2,4          |
    Then Click on save button and wait for dialog to close
    Then Verify the created adspot data is matching with its overview list values
    And Click on the created adspotname in the overview page
    Then Verify the following columns value with the created data for the general card of adspot
      | FieldName           |
      | Publisher Name      |
      | Active              |
      | Ad Spot Name        |
      | Related Media       |
      | Default Floor Price |
    Then Verify the banner card is "Enabled"
    And Verify the native card is "Enabled"
    And Verify the in-banner video card is "Enabled"
    Then "Expand" the banner card
    And "Expand" the native card
    And "Expand" the in-banner video card
    Then Verify the following columns value with the created data for the banner card of adspot
      | FieldName   |
      | Ad Sizes    |
      | Floor Price |
    Then Verify the following columns value with the created data for the native card of adspot
      | FieldName   |
      | Floor Price |
    Then Verify the following columns value with the created data for the in-banner video card of adspot
      | FieldName              |
      | Ad Sizes               |
      | Floor Price            |
      | Minimum Video Duration |
      | Maximum Video Duration |
      | Playback Methods       |
      | Video Placement Type   |


  Scenario: Verify successful creation and updation of an adspot for a default size along with 1*1 and in editing check with default size as 1*1 alone and a size alone
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Active              | Active    |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 1,2            |
      | Default Floor Price | 10        |                |
    And "Enable" the banner card
    And "Expand" the banner card
    And Enter the following data in the banner card of adspot
      | FieldName   | Value   | ListValueIndex |
      | Ad Sizes    | Default |                |
      | Floor Price | Default |                |
    And "Enable" the native card
    And "Expand" the native card
    Then Enter the following data in the native card of adspot
      | FieldName   | Value   |
      | Floor Price | Default |
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    And Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 2              |
      | Ad Sizes               | Default   |                |
      | Floor Price            | Default   |                |
      | Minimum Video Duration | ListValue | 2              |
      | Maximum Video Duration | ListValue | 2              |
      | Playback Methods       | ListValue | 1,2,4          |
    And Click on save button and wait for dialog to close
    Then Verify the created adspot data is matching with its overview list values
    When Click on the created adspotname in the overview page
    Then Verify the following columns value with the created data for the general card of adspot
      | FieldName        |
      | Default Ad Sizes |

	#Editing flow
    Then Enter the following data in the general card of adspot
      | FieldName        | Value     | ListValueIndex |
      | Default Ad Sizes | ListValue | 2              |
    And Click on save button
    Then Verify the save is failed
    And Close toast message
    Then Enter the following data in the general card of adspot
      | FieldName        | Value     | ListValueIndex |
      | Default Ad Sizes | ListValue | 1,2            |
    And "Expand" the banner card
    And Enter the following data in the banner card of adspot
      | FieldName   | Value     | ListValueIndex |
      | Ad Sizes    | ListValue | 2              |
      | Floor Price | Default   |                |
    And "Expand" the native card
    And Enter the following data in the native card of adspot
      | FieldName   | Value |
      | Floor Price | 6     |
    And "Expand" the in-banner video card
    And Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 2              |
      | Ad Sizes               | Default   |                |
      | Floor Price            | Default   |                |
      | Minimum Video Duration | ListValue | 2              |
      | Maximum Video Duration | ListValue | 2              |
    Then Click on save button and wait for dialog to close
    Then Verify the created adspot data is matching with its overview list values
    And Click on the created adspotname in the overview page
    Then Verify the following columns value with the created data for the general card of adspot
      | FieldName           |
      | Publisher Name      |
      | Active              |
      | Ad Spot Name        |
      | Related Media       |
      | Default Floor Price |
    Then Verify the banner card is "Enabled"
    And Verify the native card is "Enabled"
    And Verify the in-banner video card is "Enabled"
    Then "Expand" the banner card
    And "Expand" the native card
    And "Expand" the in-banner video card
    Then Verify the following columns value with the created data for the banner card of adspot
      | FieldName   |
      | Ad Sizes    |
      | Floor Price |
    Then Verify the following columns value with the created data for the native card of adspot
      | FieldName   |
      | Floor Price |
    Then Verify the following columns value with the created data for the in-banner video card of adspot
      | FieldName              |
      | Ad Sizes               |
      | Floor Price            |
      | Minimum Video Duration |
      | Maximum Video Duration |
      | Playback Methods       |
      | Video Placement Type   |


  Scenario: 1.5.6.Verify pop-up Banner/Native/Video enabled/disabled and modify
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Active              | Inactive  |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 2              |
      | Default Floor Price | 10        |                |
    Then "Disable" the banner card
    And "Disable" the native card
    And "Disable" the in-banner video card
    And Click on save button and wait for dialog to close
    And Hover over adspot details button
    Then Verify adspot details data is correct
    When Click on the created adspotname in the overview page
    And "Enable" the banner card
    And "Enable" the native card
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    Then Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 1              |
      | Ad Sizes               | ListValue | 2              |
      | Minimum Video Duration | ListValue | 1              |
      | Maximum Video Duration | ListValue | 1              |
      | Playback Methods       | ListValue | 1              |
    And Click on save button and wait for dialog to close
    And Hover over adspot details button
    Then Verify adspot details data is correct


  Scenario: 2.3.4.Verify adspot pop-up floor prices/ad sizes/video playback method and duration
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 2              |
      | Default Floor Price | 10        |                |
    And "Enable" the banner card
    And "Expand" the banner card
    And Enter the following data in the banner card of adspot
      | FieldName   | Value     | ListValueIndex |
      | Ad Sizes    | ListValue | 2              |
      | Floor Price | 3         |                |
    And "Enable" the native card
    And "Expand" the native card
    And Enter the following data in the native card of adspot
      | FieldName   | Value |
      | Floor Price | 6     |
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    Then Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 1              |
      | Floor Price            | 2         |                |
      | Ad Sizes               | ListValue | 2              |
      | Minimum Video Duration | ListValue | 1              |
      | Maximum Video Duration | ListValue | 1              |
      | Playback Methods       | ListValue | 1              |
    And Click on save button and wait for dialog to close
    And Hover over adspot details button
    Then Verify adspot details data is correct

  Scenario: 58.61.Verily that disabled items are displayed as "Inactive"
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Active              | Inactive  |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 2              |
      | Default Floor Price | 10        |                |
    Then "Disable" the banner card
    And "Disable" the native card
    And "Disable" the in-banner video card
    And Click on save button and wait for dialog to close
    And Hover over adspot details button
    Then Verify adspot details data is correct


  Scenario: 59.60.Verily that intems with out Floor price and Ad size display "Same as default" and
  Min and Max Duration has int value with "seconds"
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Categories          | ListValue | 2              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 2              |
      | Default Floor Price | 10        |                |
    And "Enable" the banner card
    And "Enable" the native card
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    Then Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 1              |
      | Ad Sizes               | ListValue | 2              |
      | Minimum Video Duration | ListValue | 2              |
      | Maximum Video Duration | ListValue | 2              |
      | Playback Methods       | ListValue | 1              |
    And Click on save button and wait for dialog to close
    And Hover over adspot details button
    Then Verify adspot details data is correct

  Scenario: 207.Verify that error is not displayed for optional floor price
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
      | Ad Spot Name        | Auto_Test |                |
      | Related Media       | ListValue | 1              |
      | Position            | ListValue | 2              |
      | Filter              | ListValue | 1              |
      | Default Ad Sizes    | ListValue | 2              |
      | Default Floor Price | 10        |                |
    And "Enable" the banner card
    And "Expand" the banner card
    And Click in Floor Price input in "Banner" card
    And Click outside Floor Price input in "Banner" card
    Then verify that no error is displayed below Floor Price input in "Banner" card
    And "Enable" the native card
    And "Expand" the native card
    And Click in Floor Price input in "Native" card
    And Click outside Floor Price input in "Native" card
    Then verify that no error is displayed below Floor Price input in "Native" card
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    And Click in Floor Price input in "Video" card
    And Click outside Floor Price input in "Video" card
    Then verify that no error is displayed below Floor Price input in "Video" card
    Then Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Video Placement Type   | ListValue | 1              |
      | Playback Methods       | ListValue | 1              |
    When Click on save button and wait for dialog to close

  Scenario: 208.Verify that Floor Price not duplicating while creation AdSpot
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value     | ListValueIndex |
      | Publisher Name      | Viber     |                |
    And "Enable" the banner card
    And "Expand" the banner card
    And Enter the following data in the banner card of adspot
      | FieldName   | Value     | ListValueIndex |
      | Floor Price | 3         |                |
    Then Verify that specified floor price not duplicated in "Banner" card
    And "Enable" the native card
    And "Expand" the native card
    And Enter the following data in the native card of adspot
      | FieldName   | Value |
      | Floor Price | 6     |
    Then Verify that specified floor price not duplicated in "Native" card
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    Then Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Floor Price            | 2         |                |
    Then Verify that specified floor price not duplicated in "Video" card

  Scenario: 209.Verify that Floor Price not duplicating while editing existing AdSpot
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on Ad Spot Name in first row in list view
    And "Enable" the banner card
    And "Expand" the banner card
    And Enter the following data in the banner card of adspot
      | FieldName   | Value     | ListValueIndex |
      | Floor Price | 3         |                |
    Then Verify that specified floor price not duplicated in "Banner" card
    And "Enable" the native card
    And "Expand" the native card
    And Enter the following data in the native card of adspot
      | FieldName   | Value |
      | Floor Price | 6     |
    Then Verify that specified floor price not duplicated in "Native" card
    And "Enable" the in-banner video card
    And "Expand" the in-banner video card
    Then Enter the following data in the in-banner video card of adspot
      | FieldName              | Value     | ListValueIndex |
      | Floor Price            | 2         |                |
    Then Verify that specified floor price not duplicated in "Video" card

    Scenario: 214.Verify that Active toggle set to true while creation new Ad Spot
      Given admin user login to RX UI with valid username and password
      When Click on Adspots option under Inventory
      And User displayed with Adspots page
      When Click on the Adspot create button
      Then Verify that Active toggle set to true in Create Ad Spot page
      Then Enter the following data in the general card of adspot
        | FieldName           | Value       | ListValueIndex |
        | Publisher Name      | Viber       |                |
        | Ad Spot Name        | Auto_Test   |                |
        | Related Media       | ListValue   | 1              |
        | Position            | ListValue   | 2              |
        | Filter              | ListValue   | 1              |
        | Default Ad Sizes    | ListValue   | 2              |
        | Default Floor Price | 10          |                |
      And "Enable" the banner card
      When Click on save button and wait for dialog to close
      Then Verify that Active as a value displayed in Active column for the created adspotname

  Scenario: 217.Verify Floor Price limits for Adspot
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value   | ListValueIndex |
      | Publisher Name      | Viber   |                |
      | Default Floor Price | 1234567 |                |
    Then Verify the error message "A valid price is between 0.00 and 999,999.99" displays below Floor Price in Create Ad Spot page

  Scenario: 218.Verify Floor Price limits for Adspot Native/Banner/Video
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Enter the following data in the general card of adspot
      | FieldName           | Value       | ListValueIndex |
      | Publisher Name      | Alena Grant |                |
    When "Enable" the banner card
    And "Expand" the banner card
    And Enter the following data in the banner card of adspot
      | FieldName   | Value   | ListValueIndex |
      | Floor Price | 1234567 |                |
    Then Verify the error message "A valid price is between 0.00 and 999,999.99" displays below Floor Price in "Banner" card
    When "Enable" the native card
    And "Expand" the native card
    And Enter the following data in the native card of adspot
      | FieldName   | Value   |
      | Floor Price | 1234567 |
    Then Verify the error message "A valid price is between 0.00 and 999,999.99" displays below Floor Price in "Native" card
    When "Enable" the in-banner video card
    And "Expand" the in-banner video card
    Then Enter the following data in the in-banner video card of adspot
      | FieldName   | Value   | ListValueIndex |
      | Floor Price | 1234567 |                |
    Then Verify the error message "A valid price is between 0.00 and 999,999.99" displays below Floor Price in "Video" card

  Scenario: 158.Verify that searching with publisher name retrieve results for Ad Spots
    Given admin user login to RX UI with valid username and password
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then Verify the search functionality with the following names
      | Name | ColumnName |
      | Kobo | Publisher  |