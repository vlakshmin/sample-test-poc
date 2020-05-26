Feature: Profile page Validation
Scenario: Verify if admin user is displayed with e-mail,Role,change password and option update password.
	Given admin user login to RX UI with valid username and password 
	When navigated to profile
	Then admin user displayed with his e-mail,Role,change password and option Update password
#	Then Close the browser
	
Scenario: Verify if admin user can change password by providing valid current password
	Given admin user login to RX UI with valid username and password 
	When User is allowed to Change password 
	Then Change password should be success
	Then User is able to login with new password
#	Then Close the browser
	
Scenario: Verify if admin user can't change password by providing invalid current password.
	Given admin user login to RX UI with valid username and password 
	When user is enter invalid current password to Change password 
	Then Change password should fail
#	Then Close the browser
	
Scenario: Verify if admin user can't change password by providing miss-match of New Password and Confirmation password.
	Given admin user login to RX UI with valid username and password 
	When user is enter miss-match of New Password and Confirmation password 
	Then user is displayed with miss-match of New Password and Confirmation password
#	Then Close the browser