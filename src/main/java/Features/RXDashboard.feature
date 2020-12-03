Feature: RX Dashboard page Validation

  @Test
  Scenario: Verify dashboard page elements and default values
    Given Admin user login by entering valid username and password
    When Click on Dashboard option in Menu
    Then Viber is selected as Publisher
    Then Date range has default value
    Then Graph are displayed properly

  @Test
  Scenario: Verify dashboard page publisher change
    Given Admin user login by entering valid username and password
    When Click on Dashboard option in Menu
    Then Select Viki as Publisher
    Then Date range has default value
    Then Graph are displayed properly