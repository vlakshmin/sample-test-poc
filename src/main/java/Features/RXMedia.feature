Feature: Targeting page validation

  Scenario:  267.Verify the pagination exists for the list in the Media page for admin
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    Then Verify the pagination of the listed rows in the Page with a selection of 50 rows per page with 5 columns

  Scenario:  Verify the pagination exists for the list in the targeting page for publisher
    Given Publisher user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    Then Verify the pagination of the listed rows in the Page with a selection of 10 rows per page with 5 columns

  Scenario: 75. Verify create Media page is disabled while warning banner is present with data in inputs for Admin
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    And Enter the following values in Create Media page
      | Publisher | Media Name    | Media Type | Site URL         | Categories |
      | Viber     | TestAutoMedia | Mobile Web | http://app.store | Automotive |
    And Enter the following values in Create Media page
      | Publisher |
      | Viki      |
    Then Verify the following message is displayed when the publisher changed for Media
      | Message                                                                                      |
      | By changing the Publisher the form will be reset and the previous changes will not be saved. |
    Then Verify the Create Media entity page is disabled
    Then Verify the Create Media page is filled with data

  Scenario: 76. Verify create Media page is disabled while warning banner is present with data in inputs for Cross Publisher
    Given Publisher user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    And Enter the following values in Create Media page
      | Publisher | Media Name    | Media Type | Site URL         | Categories |
      | Viber     | TestAutoMedia | Mobile Web | http://app.store | Automotive |
    And Enter the following values in Create Media page
      | Publisher |
      | Viki      |
    Then Verify the following message is displayed when the publisher changed for Media
      | Message                                                                                      |
      | By changing the Publisher the form will be reset and the previous changes will not be saved. |
    Then Verify the Create Media entity page is disabled
    Then Verify the Create Media page is filled with data

  Scenario: 90. Verify that errors are displayed near save button on Create/update date for Media
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    And Click on Save Media button
    Then Verify the validation errors display near Save Media button
      | Message                          |
      | The Publisher field is required  |
      | The Media Name field is required |
      | The Media Type field is required |
      | The Site URL field is required   |
    When Select publisher by name: "Viber" in Create Media page
    Then Verify the validation error does not display in Create Media page
      | Message                        |
      | The Currency field is required |
    When Enter "TestAutoMedia" into Media Name in Create Media page
    Then Verify the validation error does not display in Create Media page
      | Message                          |
      | The Media Name field is required |
    When Select Media Type by value: "PC Web" in Create Media page
    Then Verify the validation error does not display in Create Media page
      | Message                          |
      | The Media Type field is required |
    When Enter "http://web.com" into Site URL in Create Media page
    Then Verify no validation errors display in Create Media page

  Scenario: 123. Verify that Activate Deactivate buttons are displayed at same time for Media list page
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Select "1" "Active" media in list view
    Then Verify the following buttons are present in Media page
      | Button           |
      | Edit Media       |
      | Activate Media   |
      | Deactivate Media |
    When Click on Edit Media button
    Then Verify Edit Media page displays
    When Close Edit Media page
    When Click on "Deactivate" Media button
    Then Verify the selected "Active" media change to "Inactive" status in Media list view
    When Select "1" "Inactive" media in list view
    Then Verify the following buttons are present in Media page
      | Button           |
      | Edit Media       |
      | Activate Media   |
      | Deactivate Media |
    When Click on Edit Media button
    Then Verify Edit Media page displays
    When Close Edit Media page
    When Click on "Activate" Media button
    Then Verify the selected "Inactive" media change to "Active" status in Media list view
    When Select "1" "Active" media in list view
    When Select "1" "Inactive" media in list view
    Then Verify the following buttons are present in Media page
      | Button           |
      | Activate Media   |
      | Deactivate Media |
    When Click on "Deactivate" Media button
    Then Verify the selected "Active" media change to "Inactive" status in Media list view
    Then Verify the selected "Inactive" media change to "Inactive" status in Media list view
    When Select "1" "Active" media in list view
    When Select "1" "Inactive" media in list view
    Then Verify the following buttons are present in Media page
      | Button           |
      | Activate Media   |
      | Deactivate Media |
    When Click on "Activate" Media button
    Then Verify the selected "Active" media change to "Active" status in Media list view
    Then Verify the selected "Inactive" media change to "Active" status in Media list view
    When Select "2" "Active" media in list view
    Then Verify the following buttons are present in Media page
      | Button           |
      | Activate Media   |
      | Deactivate Media |
    When Click on "Deactivate" Media button
    Then Verify the selected "Active" media change to "Inactive" status in Media list view
    When Select "2" "Inactive" media in list view
    Then Verify the following buttons are present in Media page
      | Button           |
      | Activate Media   |
      | Deactivate Media |
    When Click on "Activate" Media button
    Then Verify the selected "Inactive" media change to "Active" status in Media list view

  Scenario: 157. Verify that searching with publisher name retrieve results for Media
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Enter "France" into Search input in Media page
    Then Verify that search results displayed and publisher in search results "Raktuen France"

  Scenario: 167. Verify only required columns are displayed by default in media list page
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    Then Verify following columns are displayed by default in the Media list
      | Columns    |
      | ID         |
      | Media Name |
      | Publisher  |
      | Platform   |
      | Status     |

  Scenario: 213. Verify that Active toggle set to true while creation new Media
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    Then Verify that Active toggle set to true
    When Enter the following values in Create Media page
      | Publisher | Media Name    | Media Type | Site URL         | Categories |
      | Viber     | TestAutoMedia | Mobile Web | http://app.store | Automotive |
    And Click on Save Media button
    Then Verify that Active as a value displayed in Status column

  Scenario: 268.Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    And User click on table options button
    Then Verify that column "Created Date" can be hidden and shown
    Then Verify that column "Modified Date" can be hidden and shown

  Scenario: 269.Verify onclicking relevant status from table options shows only that particular table rows with that status
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    Then User click on table options button
    And Verify that column "Status" only shows relevant rows in the table with filter "Active"
    And Verify that column "Status" only shows relevant rows in the table with filter "Inactive"

  Scenario: 270.Verify searching Media with available and non available Media name
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Search Media with name "blockchains LLC Media"
    Then Verify that Media "blockchains LLC Media" is displayed
    When Search Media with name "blockchains LLC Media a"
    Then Verify that no results are displayed

  Scenario: 271.Verify enabling and disabling of Media from the overview page
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    Then Verify that Media can be Enabled and Disabled from list

  Scenario: 272.Verify sorting of the list's columns of the Media overview page
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    Then Verify the sorting functionality with the following columns
      | ColumnName | SortType |
      | ID         | desc      |
      | Publisher  | asc     |
      | Media Name     | asc      |

  Scenario: 273.Verify mandatory fields in the Create Media Page
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    Then Click on Save Media button
    Then Verify following fields are mandatory for create page
      | FieldName      |
      | Publisher|
    When Select publisher by name: "Viber" in Create Media page
    Then Verify following fields are mandatory for create page
      | FieldName  |
      | Media Name       |
      | Media Type |
    |  Site URL  |

  Scenario: 274.Verify on single publisher user login, publisher field is disabled in the Create Media Page
    Given Single Publisher user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    Then Verify Publisher name field is disabled on Create Media page

  Scenario: 275.Verify without selecting publisher the card is not enabled to fill in the Create Median Page
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    Then Verify following fields are not enabled for create Media page
      | FieldName  |
      | Media Name       |
      | Media Type |
      | Site URL     |
      | Categories  |

  Scenario: 276.Verify Changing publisher name alert the user and then on change every fields go to default state
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    And Enter the following values in Create Media page
      | Publisher | Media Name    | Media Type | Site URL         | Categories |
      | Viber     | TestAutoMedia | Mobile Web | http://app.store | Automotive |
    And Enter the following values in Create Media page
      | Publisher |
      | Viki      |
    Then Verify the following message is displayed when the publisher changed for Media
      | Message                                                                                      |
      | By changing the Publisher the form will be reset and the previous changes will not be saved. |
    When Select "Accept" on the publisher change banner
    Then Verify every fields go to default state

  Scenario: 277.Verify successful creation of Media on clicking Save Media button
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    And Enter the following values in Create Media page
      | Publisher | Media Name    | Media Type | Site URL         | Categories |
      | Viber     | TestAutoMedia | Mobile Web | http://app.store | Automotive |
    And Click on Save Media button
    Then Verify the created media data is matching with its overview list values
  
  Scenario: 278.Verify successful editing of Media
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    And Enter the following values in Create Media page
      | Publisher | Media Name    | Media Type | Site URL         | Categories |
      | Viber     | TestAutoMedia | Mobile Web | http://app.store | Automotive |
    And Click on Save Media button
    Then Verify the created media data is matching with its overview list values
    Then Click on the created media name in the overview page
    Then Verify Publisher name field is disabled on Create Media page
    Then Verify the Create Media page is filled with data
    And Enter the following values in Create Media page
      | Media Name    |
      | TestAutoMedia |
    And Click on Save Media button
    Then Verify the created media data is matching with its overview list values
    Then Click on the created media name in the overview page
    Then Verify the Create Media page is filled with data
