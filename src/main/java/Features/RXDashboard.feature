Feature: RX Dashboard page Validation

  Scenario: Verify dashboard page elements and default values
    Given Admin user login by entering valid username and password
    When Click on Dashboard option in Menu
    Then Viber is selected as Publisher
    Then Date range has default value
    Then Graph are displayed properly

  Scenario: Verify dashboard page publisher change
    Given Admin user login by entering valid username and password
    When Click on Dashboard option in Menu
    Then Select Viki as Publisher
    Then Date range has default value
    Then Graph are displayed properly
  
  Scenario: Verify dashboard page date range change
    Given Admin user login by entering valid username and password
    When Click on Dashboard option in Menu
    Then Change date range for previous month
    Then Graph are displayed properly