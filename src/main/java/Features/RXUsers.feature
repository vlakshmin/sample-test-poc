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
	