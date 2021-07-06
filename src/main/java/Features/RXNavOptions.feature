Feature: GS-535 navigation menus validation
Scenario: Verify if admin user displayed with User Info ,Dashboard,Admin,Inventory and Rules as main menu.
	Given admin user login to RX UI with valid username and password 
	When Check for main menu navigation options.
	Then User Info,Dashboard,Admin,Inventory and Rules are displayed as main menu navigation options.

Scenario: Verify if admin user displayed with submenu Publishers,Users,Demand Sources and Buyers for Admin main menu.
	Given admin user login to RX UI with valid username and password 
	When Check for Sub menu option under Admin main menu.
	Then Publisher,Users and Demand Sources are displayed as sub main menu under Admin main menu as navigation options.

Scenario: Verify if admin user displayed with submenu Media and Adspot for Inventory main menu.
	Given admin user login to RX UI with valid username and password 
	When Check for Sub menu option under Inventory main menu.
	Then Media and Adspot are displayed as sub main menu under Inventory main menu as navigation options.

Scenario: Verify if admin user displayed with submenu Filters and Targeting for Rules main menu.
	Given admin user login to RX UI with valid username and password 
	When Check for Sub menu option under Rules main menu.
	Then Targeting is displayed as sub main menu under Rules main menu as navigation options.
	
Scenario: Verify if Publisher user displayed with User Info ,Dashboard,Inventory and Rules as main menu.
	Given Publisher user login to RX UI with valid username and password 
	When Check for main menu navigation options.
	Then User Info,Dashboard,Inventory and Rules are displayed as main menu navigation options.
	
Scenario: Verify if publisher user displayed with submenu Media and Adspot for Inventory main menu.
	Given Publisher user login to RX UI with valid username and password 
	When Check for Sub menu option under Inventory main menu of publisher.
	Then Media and Adspot are displayed as sub main menu under Inventory main menu as navigation options.
	
Scenario: Verify if publisher user displayed with submenu Filters and Targeting for Rules main menu.
	Given Publisher user login to RX UI with valid username and password 
	When Check for Sub menu option under Rules main menu of publisher
	Then Targeting is displayed as sub main menu under Rules main menu as navigation options.