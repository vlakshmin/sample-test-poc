Feature: RX Publisher page Validation

  Scenario: Verify if an admin user will be able to login with a valid username and valid password
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    Then Verify the pagination of the listed rows in the Page with a selection of 10 rows per page with 8 columns

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
    And Verify that Active as a value displayed in Active column in publisher list view