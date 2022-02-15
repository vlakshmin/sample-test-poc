Feature: Demand Sources page regression

  @Ignore
  Scenario:  120.Verify that Activate Deactivate buttons are displayed at same time for Demand Sources list page
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    And Select one "true" Active DSP item
    Then Verify that following buttons are present in DSP list page
      | Edit        |
      | Disable DSP |
      | Enable DSP  |
    When Click "Edit" button in DSP list page
    Then Edit DSP pop up is present
    And Enter the following data in the general card of DSP
      | FieldName    |
      | Endpoint URI |
    Then Click on Save DSP button
    Then Verify "Endpoint URI" is saved properly for the edited DSP data
    When Click "Enable DSP" button in DSP list page
    Then "true" is displayed for the DSP
    And Select one "true" Active DSP item
    When Click "Disable DSP" button in DSP list page
    Then "false" is displayed for the DSP
    And Select 1 "false" and 1 "true" Active DSP items
    Then Verify that following buttons are present in DSP list page
      | Disable DSPs |
      | Enable DSPs  |
    When Click "Disable DSPs" button in DSP list page
    Then "false" is displayed for the DSP
    And Select 2 "false" and 2 "true" Active DSP items
    Then Verify that following buttons are present in DSP list page
      | Disable DSPs |
      | Enable DSPs  |
    When Click "Disable DSPs" button in DSP list page
    Then "false" is displayed for the DSP
    And Select 1 "false" and 1 "true" Active DSP items
    Then Verify that following buttons are present in DSP list page
      | Disable DSPs |
      | Enable DSPs  |
    When Click "Enable DSPs" button in DSP list page
    Then "true" is displayed for the DSP
    And Select one "false" Active DSP item
    Then Verify that following buttons are present in DSP list page
      | Edit        |
      | Disable DSP |
      | Enable DSP  |
    When Click "Edit" button in DSP list page
    Then Edit DSP pop up is present
    And Enter the following data in the general card of DSP
      | FieldName    |
      | Endpoint URI |
    Then Click on Save DSP button
    Then Verify "Endpoint URI" is saved properly for the edited DSP data
    When Click "Disable DSP" button in DSP list page
    Then "false" is displayed for the DSP
    And Select one "false" Active DSP item
    When Click "Enable DSP" button in DSP list page
    Then "true" is displayed for the DSP
    And Select 2 "false" and 2 "true" Active DSP items
    Then Verify that following buttons are present in DSP list page
      | Disable DSPs |
      | Enable DSPs  |
    When Click "Enable DSPs" button in Rule list page
    Then "true" is displayed for the Rule

  Scenario:  296.Verify that if DSP not active - user able to save it with empty datacenter/endpoint URI fields
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Search "DistrictM" DSP item
    And Click on Bidder column for "DistrictM" DSP item
    Then Edit DSP pop up is present
    When Click on Save DSP button
    Then DSP saved successfully without error message

  Scenario:  297.Verify that if DSP not active - user able to save it with data specified in datacenter/endpoint URI fields
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Search "Flurry" DSP item
    And Click on Bidder column for "Flurry" DSP item
    Then Edit DSP pop up is present
    And Enter the following data in the general card of DSP
      | FieldName    |
      | Endpoint URI |
    When Click on Save DSP button
    Then DSP saved successfully without error message

  Scenario:  298.Verify that if DSP active - user can't save it with empty datacenter/endpoint URI because of validation
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Search "Criteo" DSP item
    And Click on Bidder column for "Criteo" DSP item
    Then Edit DSP pop up is present
    And Erase data from endpoint input
    When Click on Save DSP button
    Then Verify following errors are displayed near save button
      | The Endpoint URI field is required |
    Then Verify error "The Endpoint URI field is required" is present for Endpoint URI
  @Ignore
  Scenario:  353.Check required Status / PMPs / NonProgramatic
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    And Click on Bidder column for "DumbBidder" DSP item
    Then Edit DSP pop up is present
    Then Verify following radio buttons displayed in the header Status
      | Inactive   |
      | Onboarding |
      | Active     |
    Then Verify following DSP Toggles is displayed
      | PMP support      |
      | Non-Programmatic |
  @Ignore
  Scenario:  354.Check option "Non Programatic" has status "read only"
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    And Click on Bidder column for "DumbBidder" DSP item
    Then Edit DSP pop up is present
    Then Verify following DSP Toggles is Inactive
    |Non-Programmatic|
    Then Verify following DSP Toggles is Disable
    |Non-Programmatic|
  @Ignore
  Scenario:  355.Check statuses for demand source
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    And Click on Bidder column for "DumbBidder" DSP item
    Then Edit DSP pop up is present
    When Set Status as "Active" in Edit DSP page
    When Click on Save DSP button
    Then DSP saved successfully without error message
    And Click on Bidder column for "DumbBidder" DSP item
    Then Edit DSP pop up is present
    Then Status is set as "Active" in Edit DSP page
    When Set Status as "Inactive" in Edit DSP page
    When Click on Save DSP button
    Then DSP saved successfully without error message
    And Click on Bidder column for "DumbBidder" DSP item
    Then Edit DSP pop up is present
    Then Status is set as "Inactive" in Edit DSP page
    When Set Status as "Onboarding" in Edit DSP page
    When Click on Save DSP button
    Then DSP saved successfully without error message
    And Click on Bidder column for "DumbBidder" DSP item
    Then Edit DSP pop up is present
    Then Status is set as "Onboarding" in Edit DSP page
  @Ignore
  Scenario:  356.Check Check that "Status" "PMPs" and "Non programatic" in the table options
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When User click on table options button
    Then Verify the following checkboxes are exist in DSP table options
    |Status|
    |PMP support|
    |Non-Programmatic|
  @Ignore
  Scenario:  357.Check that columns "Status" "PMPs" and "Non programatic" are displayed on the Demand Source page
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When User click on table options button
    And Select the following checkbox in DSP table options
      |Status|
      |PMP support|
      |Non-Programmatic|
    Then Check that the following columns are displayed on the Demand sources page
      |Status|
      |PMP support|
      |Non-Programmatic|

  Scenario: 446. Check that there are no country selector for Demand
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Click on Bidder column for the first DSP item
    Then Edit DSP pop up is present
    Then Verify that there are no country selector in Edit Demand page

  Scenario: 447. Check Including "All countries"
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Click on Bidder column for the first DSP item
    Then Edit DSP pop up is present
    When Click on "Include All" button in Allowed Countries panel
    Then Verify that all countries are displayed in Included list in Allowed Countries panel
    When Click on Save DSP button
    Then DSP saved successfully without error message
    When Click on Bidder column for the first DSP item
    Then Edit DSP pop up is present
    Then Verify that all countries are displayed in Included list in Allowed Countries panel

  Scenario: 448. Check Clear "All countries"
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Click on Bidder column for the first DSP item
    Then Edit DSP pop up is present
    When Click on "Clear All" button in Allowed Countries panel
    Then Verify that no country is displayed in Included list in Allowed Countries panel
    When Click on Save DSP button
    Then DSP saved successfully without error message
    When Click on Bidder column for the first DSP item
    Then Edit DSP pop up is present
    Then Verify that no country is displayed in Included list in Allowed Countries panel

  Scenario: 449. Check some countries to the Geo list
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Click on Bidder column for the first DSP item
    Then Edit DSP pop up is present
    When Click on "Clear All" button in Allowed Countries panel
    When Include the below countries in Allowed Countries panel
      | Canada  |
      | Vanuatu |
      | Russia  |
    When Click on Save DSP button
    Then DSP saved successfully without error message
    When Click on Bidder column for the first DSP item
    Then Edit DSP pop up is present
    Then Verify the below countries are displayed in Included list in Allowed Countries panel
      | Canada  |
      | Vanuatu |
      | Russia  |

  Scenario: 450. Check some countries to the Geo list
    Given admin user login to RX UI with valid username and password
    And Click on Demand Sources option under Admin
    Then User displayed with Demand Sources page
    When Click on Bidder column for the first DSP item
    Then Edit DSP pop up is present
    When Remove the below countries from Included list in Allowed Countries panel
      | Canada  |
      | Vanuatu |
    When Click on Save DSP button
    Then DSP saved successfully without error message
    When Click on Bidder column for the first DSP item
    Then Edit DSP pop up is present
    Then Verify the below countries are displayed in Included list in Allowed Countries panel
      | Russia  |