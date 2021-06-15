Feature: RX users page Validation
#Scenario:  Verify if user is displayed with list of user accounts for admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then user displayed with User page.	
#
#Scenario:  Verify if user is unable to create new user account without filling the fields from  admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on create user.
#	Then Don't fill the create user page and click save
#	Then assert that the mandatory field message displayed at field in the create user page
#	
#Scenario:  Verify if user is able to create new Admin account from admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on create user.
#	Then fill the create Admin user page
#	Then Click on Save
#	Then assert the new Admin user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.
#	
#Scenario: Verify if user is able to edit active Admin account from admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on active tab and select one of the admin user.
#	Then Click on edit button.
#	Then Edit the Admin user
#	Then Click on Save edited user
#	Then assert the edited Admin user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.
#	
#Scenario:  Verify if user is able to modify Active Admin account into active publisher account from admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on active tab and select one of the admin user.
#	Then Click on edit button.
#	Then Edit the Admin user to modify it into Publisher
#	Then Click on Save modify admin user
#	Then assert the edited Publisher user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.		
#	
#Scenario:  Verify if user is able to modify Active Publisher account into active Admin account from admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on active tab and select one of the Publisher user.
#	Then Click on edit button.
#	Then Edit the Publisher user to modify it into Admin
#	Then Click on Save modify Publisher user
#	Then assert the edited Admin user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.		
#
#Scenario:   Verify if user is able to disable the Active Admin account with admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on active tab and select one of the admin user.
#	Then click on disable user button and navigate to disabled tab
#	Then assert the new user details in user page table view.
#
#Scenario: Verify if user is able to search disabled Admin account with admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on disabled tab and select one of the Admin user. 
#	Then search for the user
#	Then assert the new user details in user page table view.	
#
#Scenario:  Verify if user is able to edit disabled admin account from admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on disabled tab and select one of the Admin user.
#	Then Click on edit button.
#	Then Edit the Admin user
#	Then Click on Save edited user
#	Then assert the edited Admin user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.	
#	
#Scenario:  Verify if user is able to modify disabled Admin account into disabled publisher account from admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on disabled tab and select one of the Admin user.
#	Then Click on edit button.
#	Then Edit the Admin user to modify it into Publisher
#	Then Click on Save modify admin user
#	Then assert the edited Publisher user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.
#	
#Scenario: Verify if user is able to modify disabled publisher account into disabled admin account from admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on disabled tab and select one of the Publisher user.
#	Then Click on edit button.
#	Then Edit the Publisher user to modify it into Admin
#	Then Click on Save modify Publisher user
#	Then assert the edited Admin user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.	
#	
#Scenario:   Verify if user is able to Activate the disabled Admin account with admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on disabled tab and select one of the Admin user.
#	Then click on Enable user button and navigate to Activate tab
#	Then assert the new user details in user page table view.
#	
#Scenario:  Verify if user is able to create new publisher account from  admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on create user.
#	Then fill the create Publisher user page
#	Then Click on Save
#	Then assert the new Publisher user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.
#	
#Scenario: Verify if user is able to edit active publisher account from admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on active tab and select one of the Publisher user.
#	Then Click on edit button.
#	Then Edit the publisher user
#	Then Click on Save edited user
#	Then assert the edited Publisher user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.
#	
#Scenario:  Verify if user is able to disable the Active Publisher account with admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on active tab and select one of the Publisher user.
#	Then click on disable user button and navigate to disabled tab
#	Then assert the new user details in user page table view.
#	
#Scenario: Verify if user is able to search disabled publisher account with admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on disabled tab and select one of the Publisher user. 
#	Then search for the user
#	Then assert the new user details in user page table view.
#	
#Scenario:  Verify if user is able to Edit Disabled publisher account from publisher login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on disabled tab and select one of the Publisher user.
#	Then Click on edit button.
#	Then Edit the publisher user
#	Then Click on Save edited user
#	Then assert the edited Publisher user for filled values are displaying in create user page
#	Then assert the new user details in user page table view.	
#	
#Scenario:  Verify if user is able to Activate the disabled Publisher account with admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on disabled tab and select one of the Publisher user.
#	Then click on Enable user button and navigate to Activate tab
#	Then assert the new user details in user page table view.	
#	
#Scenario: Verify if user is able to search active Admin account with admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on active tab and select one of the admin user. 
#	Then search for the user
#	Then assert the new user details in user page table view.	
#	
#Scenario: Verify if user is able to search active publisher account with admin login.
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then click on active tab and select one of the Publisher user. 
#	Then search for the user
#	Then assert the new user details in user page table view.	

	
#Scenario:  Verify the pagination exists for the list in the users page for admin
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then user displayed with User page.
#	Then Verify the pagination of the listed rows in the Page with a selection of 10 rows per page with 5 columns
	
#Scenario:  Verify the enabling and disabling for user for the list in the users page for admin
#	Given admin user login to RX UI with valid username and password 
#	When click on User option under accounts.
#	Then user displayed with User page.
#	Then Verify the enabling and disabling feature of the user with index 1
#	
Scenario:  Verify the toggle option is unavailable for old UI
	Then Verify that user cannot navigate to old UI 
	
Scenario:  Verify the default column names in the users overview page
	Given admin user login to RX UI with valid username and password 
	When click on User option under accounts.
	Then user displayed with User page.
	Then Verify the overview page contains following columns
	|ColumnName|
	|ID|
	|Name|
	|Publisher|
	|Email|
	|Active/Inactive|
	|Role|
Scenario:  Verify hide/show columns from the table options for admin
    Given admin user login to RX UI with valid username and password 
	When click on User option under accounts.
	Then user displayed with User page.
    And User click on table options button
    Then Verify that column "Create Date" can be hidden and shown
    
Scenario:  Verify non default column names in the users overview page
	Given admin user login to RX UI with valid username and password 
	When click on User option under accounts.
	Then user displayed with User page.
	And User click on table options button
	Then Verify non default columns in list page
	|ColumnName|
	|Create Date|
	|Update Date|
	@Ignore
Scenario:  94.Verify that errors are displayed near save button on Create/update date for Users
  Given admin user login to RX UI with valid username and password 
	When click on User option under accounts.
	Then user displayed with User page.
	When click on create user.
	And Click on SAVE USER button
	Then Verify following errors are displayed near save button
	|The Publisher field is required|
  |The Username field is required|
  |The Email field is required|
  When Enter the following data in the Create User page
  |Publisher|
  Then Verify that error disapear according to fields filled
  |The Publisher field is required|
  When Enter the following data in the Create User page
  | Username           | 
  Then Verify that error disapear according to fields filled
    |The Username field is required|
  When Enter the following data in the Create User page
  |Email|
  Then Verify that error disapear according to fields filled
  |The Email field is required|
  
Scenario:  118.Verify that Activate Deactivate buttons are displayed at same time for Users list page
  Given admin user login to RX UI with valid username and password 
	When click on User option under accounts.
	Then user displayed with User page.
	And Select one "Active" User item
  Then Verify that following buttons are present in User list page
    |Edit User|
    |Deactivate User|
    |Activate User|
  When Click "Edit User" button in User list page
  Then Edit User pop up is present
  When Enter the following data in the Create User page
    | Username           | 
  Then Click on SAVE USER button
  Then Create User page should be closed
  Then Verify the edited User data is matching with its overview list values
  When Click "Activate User" button in User list page
  Then "Active" is displayed for the created User
  And Select one "Active" User item
  When Click "Deactivate User" button in User list page
  Then "Inactive" is displayed for the created User
  And Select 1 "Inactive" and 1 "Active" User items
  Then Verify that following buttons are present in User list page
    |Deactivate User|
    |Activate User|
  When Click "Deactivate User" button in User list page
  Then "Inactive" is displayed for the created User
  And Select 2 "Inactive" and 2 "Active" User items
  Then Verify that following buttons are present in User list page
    |Deactivate User|
    |Activate User|
  When Click "Deactivate User" button in User list page
  Then "Inactive" is displayed for the created User
  And Select 1 "Inactive" and 1 "Active" User items
  Then Verify that following buttons are present in User list page
    |Deactivate User|
    |Activate User|
  When Click "Activate User" button in User list page
  Then "Active" is displayed for the created User
  And Select one "Inactive" User item
  Then Verify that following buttons are present in User list page
    |Edit User|
    |Deactivate User|
    |Activate User|
  When Click "Edit User" button in User list page
  Then Edit User pop up is present
  And Enter the following data in the Create User page
      | Username           | 
  Then Click on SAVE USER button
  Then Create User page should be closed
  Then Verify the edited User data is matching with its overview list values
  When Click "Deactivate User" button in User list page
  Then "Inactive" is displayed for the created User
  And Select one "Inactive" User item
  When Click "Activate User" button in User list page
  Then "Active" is displayed for the created User
  And Select 2 "Inactive" and 2 "Active" User items
  Then Verify that following buttons are present in User list page
    |Deactivate User|
    |Activate User|
  When Click "Activate User" button in User list page
  Then "Active" is displayed for the created User
   
Scenario:  166.Verify presence of active toggle button in users entity page
  Given admin user login to RX UI with valid username and password 
	When click on User option under accounts.
	Then user displayed with User page.
	When click on create user.
	Then Verify that active toggle button is present in the top left corner of the form and is enabled by default 
	When "Enable" following toggle fields in create page
      | FieldName |
      | Active    |
	When Enter the following data in the Create User page
   |Publisher|
   | Username  | 
   |Email|
   |Password|
  And Click on SAVE USER button
  Then Create User page should be closed
  Then Verify that the user created/edited should be "Active"
  And Select one "Active" User item
  When Click "Edit User" button in User list page
  Then Edit User pop up is present
  When "Disable" following toggle fields in create page
  | FieldName |
  | Active    |
  When Enter the following data in the Create User page
  | Username           | 
  Then Click on SAVE USER button
  Then Create User page should be closed
  Then Verify that the user created/edited should be "Inactive"
	
Scenario:  202.Verify default columns for users list page
  Given admin user login to RX UI with valid username and password 
	When click on User option under accounts.
	Then user displayed with User page.
	Then Verify following default columns in user list page
	|ID|
  |Name|                                                                
  |Publisher|
  |Role|
  |Email|
  |Active/Inactive|
 
Scenario:  203.Verify non-default columns for users list page
  Given admin user login to RX UI with valid username and password 
	When click on User option under accounts.
	Then user displayed with User page.
	When Click on Table Options button in users list page
	And Select the columns from Users Table Options
	|Create Date|                                                                    
  |Update Date|
	Then Verify following default columns in user list page
	|Create Date|                                                                    
  |Update Date|
	