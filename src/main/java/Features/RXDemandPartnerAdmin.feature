Feature: RX Demand Partner page Validation
Scenario: Verify if user is able to search Active Bidder by name  with admin login.
	Given admin user login to RX UI with valid username and password 
	When Click on Demand Partner option
	Then User is displayed with Demand page	
	
Scenario:  Verify if user is able to search Active Bidder by name  with admin login
	Given admin user login to RX UI with valid username and password 
	When Click on Demand Partner option
	Then Click on Active tab of demand page 
	Then Search for the Bidder
	Then Assert the bidder details in user page table view
	
Scenario:  Verify if user is able to search Disabled Bidder by name  with admin login
	Given admin user login to RX UI with valid username and password 
	When Click on Demand Partner option
	Then Click on disabled tab of demand page 
	Then Search for the Bidder
	Then Assert the bidder details in user page table view

#Scenario:   Verify if user is able to modify active Bidder to disabled bidder with admin login
#	Given admin user login to RX UI with valid username and password 
#	When Click on Demand Partner option
#	Then Click on Active tab of demand page 
#	Then Select one of the Bidder 
#	Then Click on disable dsp button 
#	Then Click on disabled tab of demand page
#	Then Assert the bidder details in the table view
	
#Scenario:  Verify if user is able to modify disabled Bidder to active bidder with admin login
#	Given admin user login to RX UI with valid username and password 
#	When Click on Demand Partner option
#	Then Click on disabled tab of demand page 
#	Then Select one of the Bidder 
#	Then Click on active dsp button 
#	Then Click on active tab of demand page
#	Then Assert the bidder details in the table view