Feature: GS-535 navigation menus validation

  Scenario: Verify if admin user displayed with User Info ,Dashboard,Admin,Inventory and Rules as main menu.
    Given admin user login to RX UI with valid username and password
    When Check for main menu navigation options.
    Then User Info,Dashboard,Admin,Inventory and Rules are displayed as main menu navigation options.

  Scenario: Verify if admin user displayed with submenu Publishers,Users,Demand Sources and Buyers for Admin main menu.
    Given admin user login to RX UI with valid username and password
    When Check for Sub menu option under Admin main menu.
    Then Publisher,Users and Demand Sources are displayed as sub main menu under Admin main menu as navigation options.

  Scenario: Verify if admin user displayed with submenu Media and Adspot for Inventory main menu.
    Given admin user login to RX UI with valid username and password
    When Check for Sub menu option under Inventory main menu.
    Then Media and Adspot are displayed as sub main menu under Inventory main menu as navigation options.

  Scenario: Verify if admin user displayed with submenu Filters and Targeting for Rules main menu.
    Given admin user login to RX UI with valid username and password
    When Check for Sub menu option under Rules main menu.
    Then Targeting is displayed as sub main menu under Rules main menu as navigation options.

  Scenario: Verify if Publisher user displayed with User Info ,Dashboard,Inventory and Rules as main menu.
    Given Publisher user login to RX UI with valid username and password
    When Check for main menu navigation options.
    Then User Info,Dashboard,Inventory and Rules are displayed as main menu navigation options.

  Scenario: Verify if publisher user displayed with submenu Media and Adspot for Inventory main menu.
    Given Publisher user login to RX UI with valid username and password
    When Check for Sub menu option under Inventory main menu of publisher.
    Then Media and Adspot are displayed as sub main menu under Inventory main menu as navigation options.

  Scenario: Verify if publisher user displayed with submenu Filters and Targeting for Rules main menu.
    Given Publisher user login to RX UI with valid username and password
    When Check for Sub menu option under Rules main menu of publisher
    Then Targeting is displayed as sub main menu under Rules main menu as navigation options.

  #Pricing Rules are Yield -> Open Pricing ( also not implemented yet)
  Scenario: 153.Verify that Details is second column right on ID
    Given admin user login to RX UI with valid username and password
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    Then Verify that Details is second column right on ID
    When Click on Deals option under Sales
    And User displayed with Deals page
    Then Verify that Details is second column right on ID
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    Then Verify that Details is second column right on ID
    When Click on Protections option in Menu
    And User displayed with Protections page
    Then Verify that Details is second column right on ID

  Scenario: 110.For the front-end, search for the result that is on another page should return the result
    Given Admin user click on Login by entering valid username and password
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Update "Publisher" column value in the first row to a unique value in "Publishers" page
    And Click on next page button
    Then Check the "Publisher" value that is noted in first row does not exist in second page
    When Enter the "Publisher" value that is noted in first row into search textbox
    Then Verify the "Publisher" value that is noted in first row is returned
    When click on User option under accounts.
    Then user displayed with User page.
    When Update "Name" column value in the first row to a unique value in "Users" page
    And Click on next page button
    Then Check the "Name" value that is noted in first row does not exist in second page
    When Enter the "Name" value that is noted in first row into search textbox
    Then Verify the "Name" value that is noted in first row is returned
    When Click on Media option under Inventory
    Then User displayed with media page
    When Update "Media Name" column value in the first row to a unique value in "Media" page
    And Click on next page button
    Then Check the "Media Name" value that is noted in first row does not exist in second page
    When Enter the "Media Name" value that is noted in first row into search textbox
    Then Verify the "Media Name" value that is noted in first row is returned
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Update "Ad Spot Name" column value in the first row to a unique value in "Ad Spots" page
    And Click on next page button
    Then Check the "Ad Spot Name" value that is noted in first row does not exist in second page
    When Enter the "Ad Spot Name" value that is noted in first row into search textbox
    Then Verify the "Ad Spot Name" value that is noted in first row is returned
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    When Update "Name" column value in the first row to a unique value in "Private Auctions" page
    And Click on next page button
    Then Check the "Name" value that is noted in first row does not exist in second page
    When Enter the "Name" value that is noted in first row into search textbox
    Then Verify the "Name" value that is noted in first row is returned
    When Click on Deals option under Sales
    And User displayed with Deals page
    When Update "Name" column value in the first row to a unique value in "Deals" page
    And Click on next page button
    Then Check the "Name" value that is noted in first row does not exist in second page
    When Enter the "Name" value that is noted in first row into search textbox
    Then Verify the "Name" value that is noted in first row is returned
    When Click on Targeting option under Rules
    Then User displayed with targeting page
    When Update "Name" column value in the first row to a unique value in "Targeting Rules" page
    And Click on next page button
    Then Check the "Name" value that is noted in first row does not exist in second page
    When Enter the "Name" value that is noted in first row into search textbox
    Then Verify the "Name" value that is noted in first row is returned