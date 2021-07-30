Feature: RX Publisher page Validation

  Scenario: 280.Verify the pagination exists for the list in the Publisher page for admin
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    Then Verify the pagination of the listed rows in the Page with a selection of 50 rows per page with 8 columns

  Scenario: 204. Verify that multiple demand sources can be added to publisher while creating publisher
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Click on Create Publisher button
    And Enter the following values in Create Publisher page
      | Publisher Name    | Ad Ops Person  | Ad Ops Email               | Currency      | Demand Source          |
      | TestAutoPublisher | Test Publisher | TestAutoPublisher@test.com | USD - Dollars | AppNexus,Bucksense,MTS |
    And Click on Save Publisher button
    Then Verify that save publisher is successful

  Scenario: 205. Verify that multiple demand sources can be modified to publisher while editing publisher
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Select "1" publisher in list view
    And Click on Edit Publisher button
    And Clear the Demand Source values
    And Enter the following values in Create Publisher page
      | Demand Source            |
      | Criteo,EPOM,Flurry,smart |
    And Click on Save Publisher button
    Then Verify that save publisher is successful

  Scenario: 206. Verify that demand sources field is not a mandatory field
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Click on Create Publisher button
    And Enter the following values in Create Publisher page
      | Publisher Name    | Ad Ops Person  | Ad Ops Email               | Currency      | Domain      | Categories |
      | TestAutoPublisher | Test Publisher | TestAutoPublisher@test.com | USD - Dollars | rakuten.com | Business   |
    And Click on Save Publisher button
    Then Verify that save publisher is successful

  Scenario: 211. Verify sorting for Demand sources in Publisher
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Click on Create Publisher button
    And Enter the following values in Create Publisher page
      | Publisher Name    | Ad Ops Person  | Ad Ops Email               | Currency      | Domain      | Categories |
      | TestAutoPublisher | Test Publisher | TestAutoPublisher@test.com | USD - Dollars | rakuten.com | Business   |
    And Click on Demand Source dropdown
    Then Verify that all items are sorted alphabetically
    When Enter the following values in Create Publisher page
      | Demand Source            |
      | Voluum,Flurry,Criteo,EPOM,smart,AppNexus |
    And Click on Save Publisher button
    Then Verify that save publisher is successful
    When Click on the newly created publisher in list view
    Then Verify that selected Demand Sources are sorted alphabetically

  Scenario: 212. Verify that Active toggle set to true while creation new Publisher
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Click on Create Publisher button
    Then Verify that Active toggle set to true in Create Publisher page
    And Enter the following values in Create Publisher page
      | Publisher Name    | Ad Ops Person  | Ad Ops Email               | Currency      |
      | TestAutoPublisher | Test Publisher | TestAutoPublisher@test.com | USD - Dollars |
    And Click on Save Publisher button
    Then Verify that save publisher is successful
    And Verify that "Active" as a value displayed in Active column in publisher list view

  Scenario: 93. Verify that errors are displayed near save button on Create/update date for Publishers
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Click on Create Publisher button
    And Click on Save Publisher button
    Then Verify that below errors are displayed near Save Publisher button
      | Message                              |
      | The Publisher Name field is required |
      | The Ad Ops Person field is required  |
      | The Ad Ops Email field is required   |
      | The Currency field is required       |
    When Enter the following values in Create Publisher page
      | Publisher Name    |
      | TestAutoPublisher |
    Then Verify that below errors are not displayed near Save Publisher button
      | Message                              |
      | The Publisher Name field is required |
    When Enter the following values in Create Publisher page
      | Ad Ops Person  |
      | Test Publisher |
    Then Verify that below errors are not displayed near Save Publisher button
      | Message                             |
      | The Ad Ops Person field is required |
    When Enter the following values in Create Publisher page
      | Ad Ops Email               |
      | TestAutoPublisher@test.com |
    Then Verify that below errors are not displayed near Save Publisher button
      | Message                            |
      | The Ad Ops Email field is required |
    When Enter the following values in Create Publisher page
      | Currency      |
      | USD - Dollars |
    Then Verify no validation errors display in Create Publisher page

  Scenario: 119. Verify that Activate Deactivate buttons are displayed at same time Publishers list page
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Select "1" "Active" publisher in list view
    Then Verify the following buttons are present in Publisher page
      | Button               |
      | Edit Publisher       |
      | Activate Publisher   |
      | Deactivate Publisher |
    And Click on Edit Publisher button
    Then Verify Edit Publisher page displays
    When Close Edit Publisher page
    When Click on "Deactivate Publisher" button in Publisher page
    Then Verify the selected "Active" publisher change to "Inactive" status in Publisher list view
    When Select "1" "Inactive" publisher in list view
    Then Verify the following buttons are present in Publisher page
      | Button               |
      | Edit Publisher       |
      | Activate Publisher   |
      | Deactivate Publisher |
    And Click on Edit Publisher button
    Then Verify Edit Publisher page displays
    When Close Edit Publisher page
    When Click on "Activate Publisher" button in Publisher page
    Then Verify the selected "Inactive" publisher change to "Active" status in Publisher list view
    When Select "2" "Active" publisher in list view
    Then Verify the following buttons are present in Publisher page
      | Button                |
      | Activate Publishers   |
      | Deactivate Publishers |
    When Click on "Deactivate Publishers" button in Publisher page
    Then Verify the selected "Active" publisher change to "Inactive" status in Publisher list view
    When Select "1" "Active" publisher in list view
    When Select "1" "Inactive" publisher in list view
    Then Verify the following buttons are present in Publisher page
      | Button                |
      | Activate Publishers   |
      | Deactivate Publishers |
    When Click on "Deactivate Publishers" button in Publisher page
    Then Verify the selected "Active" publisher change to "Inactive" status in Publisher list view
    Then Verify the selected "Inactive" publisher change to "Inactive" status in Publisher list view
    When Select "1" "Active" publisher in list view
    When Select "1" "Inactive" publisher in list view
    Then Verify the following buttons are present in Publisher page
      | Button                |
      | Activate Publishers   |
      | Deactivate Publishers |
    When Click on "Activate Publishers" button in Publisher page
    Then Verify the selected "Active" publisher change to "Active" status in Publisher list view
    Then Verify the selected "Inactive" publisher change to "Active" status in Publisher list view
    When Select "2" "Inactive" publisher in list view
    Then Verify the following buttons are present in Publisher page
      | Button                |
      | Activate Publishers   |
      | Deactivate Publishers |
    When Click on "Activate Publishers" button in Publisher page
    Then Verify the selected "Inactive" publisher change to "Active" status in Publisher list view

  Scenario: 165. Verify presence of active toggle button in publisher entity page
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Click on Create Publisher button
    Then Verify that Active toggle set to true in Create Publisher page
    And Enter the following values in Create Publisher page
      | Publisher Name    | Ad Ops Person  | Ad Ops Email               | Currency      |
      | TestAutoPublisher | Test Publisher | TestAutoPublisher@test.com | USD - Dollars |
    And "Disable" the Active toggle button in Create Publisher page
    And Click on Save Publisher button
    Then Verify that save publisher is successful
    And Verify that "Inactive" as a value displayed in Active column in publisher list view
    When Click on the newly created publisher in list view
    And "Enable" the Active toggle button in Create Publisher page
    And Click on Save Publisher button
    Then Verify that save publisher is successful
    And Verify that "Active" as a value displayed in Active column in publisher list view

  Scenario: 279. Verify the default column names in the Publisher overview page
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    Then Verify following columns are displayed by default in the Publishers overview page
      | Columns       |
      | ID            |
      | Publisher     |
      | Category      |
      | Active        |
      | Domain        |
      | Currency      |
      | Ad Ops Person |
      | Mail          |

  Scenario: 281. Verify hide/show columns from the table options for admin
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    And User click on table options button
    Then Verify that column "Mail" can be hidden and shown
    Then Verify that column "Domain" can be hidden and shown

  Scenario: 282. Verify searching Publisher with available and non available Publisher name
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    Then Verify the search functionality with the following names
      | Name  | ColumnName |
      | Frank | Publisher  |
      | abc   | Publisher  |

  Scenario: 284. Verify sorting of the list's columns of the Publisher overview page
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    Then Verify the sorting functionality with the following columns
      | ColumnName | SortType |
      | ID         | asc      |
      | Publisher  | desc     |
      | Mail       | asc      |

  Scenario: 287. Verify successful editing of Publisher
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Click on Create Publisher button
    And Enter the following values in Create Publisher page
      | Publisher Name    | Ad Ops Person  | Ad Ops Email               | Currency      |
      | TestAutoPublisher | Test Publisher | TestAutoPublisher@test.com | USD - Dollars |
    And Click on Save Publisher button
    Then Verify that save publisher is successful
    When Click on the newly created publisher in list view
    And Enter the following values in Create Publisher page
      | Publisher Name     | Ad Ops Person  | Ad Ops Email                |
      | TestAutoPub_update | TestPub_update | TestAutoPub_update@test.com |
    And Click on Save Publisher button
    Then Verify that save publisher is successful
    Then Verify that update was successful for edit publisher