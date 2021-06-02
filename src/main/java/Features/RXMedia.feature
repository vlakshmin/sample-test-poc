Feature: Targeting page validation

  Scenario:  Verify the pagination exists for the list in the targeting page for admin
    Given admin user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    Then Verify the pagination of the listed rows in the Page with a selection of 10 rows per page with 5 columns

  Scenario:  Verify the pagination exists for the list in the targeting page for publisher
    Given Publisher user login to RX UI with valid username and password
    When Click on Media option under Inventory
    Then User displayed with media page
    Then Verify the pagination of the listed rows in the Page with a selection of 10 rows per page with 5 columns
