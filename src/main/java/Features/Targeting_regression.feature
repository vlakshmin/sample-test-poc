Feature: Targeting page regression
#
#Scenario:  Verify the pagination exists for the list and table options filter in the targeting page for admin
#	Given admin user login to RX UI with valid username and password 
#	When Click on Targeting option under Rules
#	Then User displayed with targeting page
##	Then Verify the pagination of the listed rows in the Page with a selection of 5 rows per page with 2 columns
#	When User click on table options button
#    Then Verify that column Name can be hidden and shown
#    And Verify that column Active only shows relevant rows in the table
#    And Verify that column Inactive only shows relevant rows in the table
#
##Scenario:  Verify hide/show columns from the table options for admin
##    Given User click on table options button
##    Then Verify that column Name can be hidden and shown
## 
##Scenario:  Verify onclicking relevant status from table options shows only status in table rows

  Scenario:  89.Verify that errors are displayed near save button on Create/update date for Targeting
    Given admin user login to RX UI with valid username and password
	When Click on Targeting option under Rules
	Then User displayed with targeting page
    And Click on the Create Rule button
    And Click on Save Rule button
    Then Verify following errors are displayed near save button
    |The Publisher Name field is required|
      |The Rule Name field is required|
      |The Advertisers field is required|
    And Enter the following data in the general card of Rule
      | FieldName      | Value     | ListValueIndex |
      | Publisher Name | ListValue |              2 |
    Then Verify that error disapear according to fields filled
      |The Publisher Name field is required|
    And Enter the following data in the general card of Rule
      | FieldName      | Value     | ListValueIndex |
      | Name           | Test      |                |
    Then Verify that error disapear according to fields filled
      |The Name field is required|
    And Add Advertiser in the general card of Rule
    Then Verify that error disapear according to fields filled
      |The Advertisers field is required|

  Scenario:  126.Verify that Activate Deactivate buttons are displayed at same time for Targeting list page
    Given admin user login to RX UI with valid username and password
    When Click on Targeting option under Rules
    Then User displayed with targeting page
    And Select one "Active" Rule item
    Then Verify that following buttons are present in Rule list page
      |Edit Rule|
      |Deactivate Rule|
      |Activate Rule|
    When Click "Edit Rule" button in Rule list page
    Then Edit Rule pop up is present
    And Enter the following data in the general card of Rule
      | FieldName | Value       | ListValueIndex |
      | Name      | TestUpdated |                |
    Then Click on Save Rule button
    Then Verify the edited Rule data is matching with its overview list values
    When Click "Activate Rule" button in Rule list page
    Then "Active" is displayed for the Rule
    And Select one "Active" Rule item
    When Click "Deactivate Rule" button in Rule list page
    Then "Inactive" is displayed for the Rule
    And Select 1 "Inactive" and 1 "Active" Rule items
    Then Verify that following buttons are present in Rule list page
      |Deactivate Rules|
      |Activate Rules|
    When Click "Deactivate Rules" button in Rule list page
    Then "Inactive" is displayed for the Rule
    And Select 2 "Inactive" and 2 "Active" Rule items
    Then Verify that following buttons are present in Rule list page
      |Deactivate Rules|
      |Activate Rules|
    When Click "Deactivate Rules" button in Rule list page
    Then "Inactive" is displayed for the Rule
    And Select 1 "Inactive" and 1 "Active" Rule items
    Then Verify that following buttons are present in Rule list page
      |Deactivate Rules|
      |Activate Rules|
    When Click "Activate Rules" button in Rule list page
    Then "Active" is displayed for the Rule
    And Select one "Inactive" Rule item
    Then Verify that following buttons are present in Rule list page
      |Edit Rule|
      |Deactivate Rule|
      |Activate Rule|
    When Click "Edit Rule" button in Rule list page
    Then Edit Rule pop up is present
    And Enter the following data in the general card of Rule
      | FieldName | Value       | ListValueIndex |
      | Name      | TestUpdated |                |
    Then Click on Save Rule button
    Then Verify the edited Rule data is matching with its overview list values
    When Click "Deactivate Rule" button in Rule list page
    Then "Inactive" is displayed for the Rule
    And Select one "Inactive" Rule item
    When Click "Activate Rule" button in Rule list page
    Then "Active" is displayed for the Rule
    And Select 2 "Inactive" and 2 "Active" Rule items
    Then Verify that following buttons are present in Rule list page
      |Deactivate Rules|
      |Activate Rules|
    When Click "Activate Rules" button in Rule list page
    Then "Active" is displayed for the Rule

  Scenario: 140.Verify that Publisher warning banned apears only if any forms were modified on Targeting page
    Given admin user login to RX UI with valid username and password
    When Click on Targeting option under Rules
    Then User displayed with targeting page
    When Click on the Create Rule button
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    Then Verify the following message is not displayed when the publisher changed for targeting rule
      | Message                                                                                      |
      | By Changing the Publisher the form will be reset and the previous changes will no be saved. |
    When Close "Create" Rule page
    Then User displayed with targeting page
    When Click on the Create Rule button
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And Enable "Protect specific inventory" checkbox in Inventory section
    And Select "Angola" from Protect specific inventory popup
    And Add Advertiser in the general card of Rule
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify the following message is displayed when the publisher changed for targeting rule
      | Message                                                                                      |
      | By Changing the Publisher the form will be reset and the previous changes will no be saved. |
    When Select "Accept" on the publisher change banner
    When Close "Create" Rule page
    Then User displayed with targeting page
    When Click on the Create Rule button
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
      | Name           | Test  |                |
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify the following message is displayed when the publisher changed for targeting rule
      | Message                                                                                      |
      | By Changing the Publisher the form will be reset and the previous changes will no be saved. |
    When Select "Accept" on the publisher change banner
    When Close "Create" Rule page
    Then User displayed with targeting page
    When Click on the Create Rule button
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And Enable "Protect specific inventory" checkbox in Inventory section
    And Select "Angola" from Protect specific inventory popup
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify the following message is displayed when the publisher changed for targeting rule
      | Message                                                                                      |
      | By Changing the Publisher the form will be reset and the previous changes will no be saved. |
    When Select "Accept" on the publisher change banner
    When Close "Create" Rule page
    Then User displayed with targeting page
    When Click on the Create Rule button
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viber |                |
    And Add Advertiser in the general card of Rule
    And Enter the following data in the general card of Rule
      | FieldName      | Value | ListValueIndex |
      | Publisher Name | Viki  |                |
    Then Verify the following message is displayed when the publisher changed for targeting rule
      | Message                                                                                      |
      | By Changing the Publisher the form will be reset and the previous changes will no be saved. |
    When Select "Accept" on the publisher change banner
    When Close "Create" Rule page
    Then User displayed with targeting page