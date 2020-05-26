Feature: Seats page validation
Scenario:  Verify if user is displayed with seats list page on clicking seats with admin role.
	Given admin user login to RX UI with valid username and password 
	When click on Seat option under accounts.
	Then user displayed with seats page.
#	Then Close the browser

Scenario: Verify if user is able to search the Active Seat with admin role.
	Given admin user login to RX UI with valid username and password 
	When click on Active tab and search for Seat
	Then Searched result display the seat
#	Then Close the browser

Scenario: Verify if user is able to search the Inactive Seat with admin role.
	Given admin user login to RX UI with valid username and password 
	When click on Inactive tab and search for Seat
	Then Searched result display the seat
#	Then Close the browser

Scenario: Verify if user is able to search the paused Seat with admin role.
	Given admin user login to RX UI with valid username and password 
	When click on paused tab and search for Seat
	Then Searched result display the seat
#	Then Close the browser

Scenario: Verify if user is able to update the Active Seat into Inactive seat with admin role.
	Given admin user login to RX UI with valid username and password 
	When Select the active seat for modify
	Then modify the selected seat to Deactivate
#	Then Close the browser
	
Scenario:  Verify if user is able to update the Active Seat into Disabled seat with admin role.
	Given admin user login to RX UI with valid username and password 
	When Select the active seat for modify
	Then modify the selected seat to Disabled or paused
#	Then Close the browser

Scenario: Verify if user is able to update the Inactive Seat into Active seat with admin role.
	Given admin user login to RX UI with valid username and password 
	When Select the Inactive seat for modify
	Then modify the selected seat to Activate
#	Then Close the browser
	
Scenario:  Verify if user is able to update the Inactive Seat into Disabled seat with admin role.
	Given admin user login to RX UI with valid username and password 
	When Select the Inactive seat for modify
	Then modify the selected seat Disabled or paused
#	Then Close the browser
	
Scenario: Verify if user is able to update the Disabled or Paused Seat into Active seat with admin role.
	Given admin user login to RX UI with valid username and password 
	When Select the Disabled or paused seat for modify
	Then modify the selected seat to Active
#	Then Close the browser
	
Scenario:  Verify if user is able to update the Disabled or Paused Seat into Inactive or Deactivate seat with admin role.
	Given admin user login to RX UI with valid username and password 
	When Select the Disabled or paused seat for modify
	Then modify the selected seat to Inactive
#	Then Close the browser
	