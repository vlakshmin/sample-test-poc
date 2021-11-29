Feature: User Roles

  Scenario: 161.Verify the admin user role and level permissions to pages
    Given admin user login to RX UI with valid username and password
    When Click on user info main menu
    Then Verify user info page displays the following label
      | Label  |
      | E-mail |
      | Role   |
    Then Verify user info page displays the following title
      | Title           |
      | Settings        |
      | Change Password |
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
    When Click on publisher input
    Then Verify all RX publishers are displayed in Publisher Name dropdown
    When Click on Deals option under Sales
    And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    When Click on publisher input
    Then Verify all RX publishers are displayed in Publisher Name dropdown
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    When Click on publisher input
    Then Verify all RX publishers are displayed in Publisher Name dropdown
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    When Click on publisher input
    Then Verify all RX publishers are displayed in Publisher Name dropdown
    When Click on publisher option under Admin
    Then Publisher page should be displayed
    When Click on Create Publisher button
    Then Verify the Publisher Name input displays
    When Click on Close button
    When click on User option under accounts.
    Then user displayed with User page.
    When click on create user.
    Then Verify the following radio button is displayed in Create User page
      | Radio button     |
      | Admin            |
      | Cross-Publishers |
      | Single-Publisher |
    When Click on publisher input
    Then Verify all RX publishers are displayed in Publisher Name dropdown
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    And Select one "true" Active DSP item
    Then Verify that following buttons are present in DSP list page
      |Edit|
      |Disable DSP|
      |Enable DSP|

  Scenario: 162.Verify the cross publisher user role and level permissions to pages
    Given Publisher user login to RX UI with valid username and password
    When Click on user info main menu
    Then Verify user info page displays the following label
      | Label  |
      | E-mail |
      | Role   |
    Then Verify user info page displays the following title
      | Title           |
      | Settings        |
      | Change Password |
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
    When Click on publisher input
    Then Verify all RX publishers are displayed in Publisher Name dropdown
    When Click on Deals option under Sales
    And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    When Click on publisher input
    Then Verify all RX publishers are displayed in Publisher Name dropdown
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    When Click on publisher input
    Then Verify all RX publishers are displayed in Publisher Name dropdown
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    When Click on publisher input
    Then Verify all RX publishers are displayed in Publisher Name dropdown
    When Click on Publishers main menu
    Then Publisher page should be displayed
    When Click on Create Publisher button
    Then Verify the Publisher Name input displays
    When Click on Close button
    Then Verify "Users" sub menu is not displayed
    Then Verify "Demand Sources" sub menu is not displayed

  Scenario: 163.Verify for a single publisher user login, in the profile page, the user cannot edit the publisher details
    Given Single Publisher user login to RX UI with valid username and password
    When Click on user info main menu
    Then Verify user info page displays the following label
      | Label     |
      | E-mail    |
      | Role      |
      | Publisher |
    Then Verify user info page displays the following title
      | Title           |
      | Settings        |
      | Change Password |
    When Click on Private Auctions option under Sales
    And User displayed with Private Auctions page
    And Click on the Pricate Auction create button
    Then Verify the Publisher Name input only display my publisher
    When Click on Deals option under Sales
    And User displayed with Deals page
    And Click create a new deal
    Then Create deal menu is opened
    Then Verify the Publisher Name input only display my publisher
    When Click on Media option under Inventory
    Then User displayed with media page
    When Click on Create Media button
    Then Verify the Publisher Name input only display my publisher
    When Click on Adspots option under Inventory
    And User displayed with Adspots page
    When Click on the Adspot create button
    Then Verify the Publisher Name input only display my publisher
    Then Verify "Publishers" sub menu is not displayed
    Then Verify "Users" sub menu is not displayed
    Then Verify "Demand Sources" sub menu is not displayed

  Scenario: 164.Verify for a single publisher user login, in the profile page, the user cannot edit the publisher details
    Given Single Publisher user login to RX UI with valid username and password
    When Click on user info main menu
    And Click on the publisher name link in user info panel
    Then Verify Edit Publisher page displays
    Then Verify that publisher info with a readonly form or publisher card in the profile