Feature: Publisher page validation
Scenario: Verify if admin user displayed with sub menu Publisher,Seats and Users for accounts in menu.
	Given admin user login to RX UI with valid username and password 
	When Check for option publisher ,seats and users.
	Then Publisher ,Seats and user options are displayed under Account.
#	Then Close the browser

Scenario: Verify if admin user displayed publisiher page on clicking publisher menu.
	Given admin user login to RX UI with valid username and password 
	When Click on publisher option under account.
	Then Publisher page should display.
#	Then Close the browser

Scenario: Verify if user is able to create the Individual publisher with admin role.
	Given admin user login to RX UI with valid username and password 
	When Click on create publisher and enter form for individual publisher.
	Then Individual publisher is created successfully .
	Then Individual publisher is reflects publisher list .
#	Then Close the browser


Scenario:  Verify if user is able to create the Corporation publisher with admin role.
	Given admin user login to RX UI with valid username and password 
	When Click on create publisher and enter form for corporation publisher.
	Then corporation publisher is created successfully .
	Then corporation publisher is reflects publisher list .
#	Then Close the browser


Scenario:  Verify if user is able to edit the publisher with admin role.
	Given admin user login to RX UI with valid username and password 
	When Edit the publisher by selecting the existing publisher .
	Then publisher is edited successfully .
	Then Edit details are reflecting in publisher list .
#	Then Close the browser

Scenario: Verify if user is able to disable the publisher with admin role.
	Given admin user login to RX UI with valid username and password 
	When Click in disable select publisher for disable publisher
	Then Disabled publisher is displayed in disabled tab .
#	Then Close the browser
	
	
Scenario:  Verify if user is able to enable the publisher with admin role.
	Given admin user login to RX UI with valid username and password 
	When Click on Enable by selecting publisher from disabled 
	Then Enabled publisher is displayed in Active tab .
#	Then Close the browser	
	
Scenario:  Verify if user is not able to create the publisher without entering mandatory fields with admin role.
	Given admin user login to RX UI with valid username and password 
	When Click on create publisher and don't fill the field values. 
	Then use should be displayed with field in-line mandatory error message.
#	Then Close the browser	
	
Scenario:Verify if user is able to search enabled publisher in the active tab with admin role.
	Given admin user login to RX UI with valid username and password 
	When Goto Active tab and search for the publisher. 
	Then Searched result display the publisher.
#	Then Close the browser		

Scenario: Verify if user is able to search disabled publisher in the disabled tab with admin role.
	Given admin user login to RX UI with valid username and password 
	When Goto Disabled tab and search for the publisher. 
	Then Searched result display the publisher.
#	Then Close the browser	