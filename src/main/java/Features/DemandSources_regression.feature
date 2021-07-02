Feature: Demand Sources page regression
  @Ignore
  Scenario:  120.Verify that Activate Deactivate buttons are displayed at same time for Demand Sources list page
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    And Select one "true" Active DSP item
    Then Verify that following buttons are present in DSP list page
      |Edit|
      |Disable DSP|
      |Enable DSP|
    When Click "Edit" button in DSP list page
    Then Edit DSP pop up is present
    And Enter the following data in the general card of DSP
      | FieldName |
      | Request Adjustment Rate      |
    Then Click on Save DSP button
    Then Verify the edited DSP data is matching with its overview list values
    When Click "Enable DSP" button in DSP list page
    Then "true" is displayed for the DSP
    And Select one "true" Active DSP item
    When Click "Disable DSP" button in DSP list page
    Then "false" is displayed for the DSP
    And Select 1 "false" and 1 "true" Active DSP items
    Then Verify that following buttons are present in DSP list page
      |Disable DSPs|
      |Enable DSPs|
    When Click "Disable DSPs" button in DSP list page
    Then "false" is displayed for the DSP
    And Select 2 "false" and 2 "true" Active DSP items
    Then Verify that following buttons are present in DSP list page
      |Disable DSPs|
      |Enable DSPs|
    When Click "Disable DSPs" button in DSP list page
    Then "false" is displayed for the DSP
    And Select 1 "false" and 1 "true" Active DSP items
    Then Verify that following buttons are present in DSP list page
      |Disable DSPs|
      |Enable DSPs|
    When Click "Enable DSPs" button in DSP list page
    Then "true" is displayed for the DSP
    And Select one "false" Active DSP item
    Then Verify that following buttons are present in DSP list page
      |Edit|
      |Disable DSP|
      |Enable DSP|
    When Click "Edit" button in DSP list page
    Then Edit DSP pop up is present
    And Enter the following data in the general card of DSP
      | FieldName |
      | Request Adjustment Rate      |
    Then Click on Save DSP button
    Then Verify the edited DSP data is matching with its overview list values
    When Click "Disable DSP" button in DSP list page
    Then "false" is displayed for the DSP
    And Select one "false" Active DSP item
    When Click "Enable DSP" button in DSP list page
    Then "true" is displayed for the DSP
    And Select 2 "false" and 2 "true" Active DSP items
    Then Verify that following buttons are present in DSP list page
      |Disable DSPs|
      |Enable DSPs|
    When Click "Enable DSPs" button in Rule list page
    Then "true" is displayed for the Rule