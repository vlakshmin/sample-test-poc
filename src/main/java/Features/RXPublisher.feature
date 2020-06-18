Feature: Publisher page validation
Scenario: Verify if admin user displayed with User Info ,Dashboard,Admin,Inventory and Rules as main menu.
	Given admin user login to RX UI with valid username and password 
	When Check for main menu navigation options.
	Then User Info,Dashboard,Admin,Inventory and Rules are displayed as main menu navigation options.

#Scenario: Verify if admin user displayed with sub menu Publisher,Seats and Users for accounts in menu.
#	Given admin user login to RX UI with valid username and password 
#	When Check for option publisher ,seats and users.
#	Then Publisher ,Seats and user options are displayed under Account.


#Scenario: Verify if admin user displayed publisiher page on clicking publisher menu.
#	Given admin user login to RX UI with valid username and password 
#	When Click on publisher option under account.
#	Then Publisher page should display.


#Scenario: Verify if user is able to create the Individual publisher with admin role.
#	Given admin user login to RX UI with valid username and password 
#	When Click on create publisher and enter form for individual publisher.
#	Then Individual publisher is created successfully .
#	Then Individual publisher is reflects publisher list .



#Scenario:  Verify if user is able to create the Corporation publisher with admin role.
#	Given admin user login to RX UI with valid username and password 
#	When Click on create publisher and enter form for corporation publisher.
#	Then corporation publisher is created successfully .
#	Then corporation publisher is reflects publisher list .



#Scenario:  Verify if user is able to edit the publisher with admin role.
#	Given admin user login to RX UI with valid username and password 
#	When Edit the publisher by selecting the existing publisher .
#	Then publisher is edited successfully .
#	Then Edit details are reflecting in publisher list .


#Scenario: Verify if user is able to disable the publisher with admin role.
#	Given admin user login to RX UI with valid username and password 
#	When Click on disable by selecting publisher for disable publisher
#	Then Disabled publisher is displayed in disabled tab .

	
	
#Scenario:  Verify if user is able to enable the publisher with admin role.
#	Given admin user login to RX UI with valid username and password 
#	When Click on Enable by selecting publisher from disabled 
#	Then Enabled publisher is displayed in Active tab .
	
	
#Scenario:  Verify if user is not able to create the publisher without entering mandatory fields with admin role.
#	Given admin user login to RX UI with valid username and password 
#	When Click on create publisher and don't fill the field values. 
#	Then use should be displayed with field in-line mandatory error message.

	
#Scenario:Verify if user is able to search enabled publisher in the active tab with admin role.
#	Given admin user login to RX UI with valid username and password 
#	When Goto Active tab and search for the publisher. 
#	Then Searched result display the publisher.
	

#Scenario: Verify if user is able to search disabled publisher in the disabled tab with admin role.
#	Given admin user login to RX UI with valid username and password 
#	When Goto Disabled tab and search for the publisher. 
#	Then Searched result display the publisher.
	