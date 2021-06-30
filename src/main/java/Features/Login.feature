Feature: RX Login page Validation
Scenario: Verify if an admin user will be able to login with a valid username and valid password
	Given Admin user click on Login by entering valid username and password 
	Then login should be successful and user is displayed the publisher page with Rakuten Exchange


Scenario Outline: Verify if an admin user is not allowed to login with a invalid credential
	Given Admin user click on Login by entering "<username>" & "<password>"
	Then  login should not be success
	
Examples:
    |username|password|
    | 	     | 		  |
    |adminuser@test.com|Test@123 |
    |xyz@gmail.com|Password2|
    |adminuser@test.com| 		  |
    | 	     |Password2|

  Scenario: 45.Verify that Admin user is redirected to the page he directly navigates to
    Given admin user login to RX "admin/users" UI with valid username and password
    Then user displayed with User page.

  Scenario: 46.Verify that Cross Publisher user is redirected to the page he directly navigates to
    Given Cross Publisher user login to RX "media" UI with valid username and password
    Then User displayed with media page

  Scenario: 47.Verify that Single Publisher user is redirected to the page he directly navigates to
    Given Single Publisher user login to RX "sales/private-auctions" UI with valid username and password
    Then User displayed with Private Auctions page
