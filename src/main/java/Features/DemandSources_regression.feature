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
      | Endpoint URI      |
    Then Click on Save DSP button
    Then Verify "Endpoint URI" is saved properly for the edited DSP data
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
      | Endpoint URI      |
    Then Click on Save DSP button
    Then Verify "Endpoint URI" is saved properly for the edited DSP data
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

  Scenario:  296.Verify that if DSP not active - user able to save it with empty datacenter/endpoint URI fields
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Search "DSP-Test-7" DSP item
    And Click on Bidder column for "DSP-Test-7" DSP item
    Then Edit DSP pop up is present
    When Click on Save DSP button
    Then DSP saved successfully without error message

  Scenario:  297.Verify that if DSP not active - user able to save it with data specified in datacenter/endpoint URI fields
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Search "DSP-Test-5" DSP item
    And Click on Bidder column for "DSP-Test-5" DSP item
    Then Edit DSP pop up is present
    And Enter the following data in the general card of DSP
      | FieldName |
      | Endpoint URI      |
    When Click on Save DSP button
    Then DSP saved successfully without error message

  Scenario:  298.Verify that if DSP active - user can't save it with empty datacenter/endpoint URI because of validation
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Search "Smart" DSP item
    And Click on Bidder column for "Smart" DSP item
    Then Edit DSP pop up is present
    And Erase data from endpoint input
    When Click on Save DSP button
    Then Verify following errors are displayed near save button
      |The Endpoint URI field is required|
    Then Verify error "The Endpoint URI field is required" is present for Endpoint URI