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
    |chandrashekhara.av@rakuten.com|Test@123 |
    |xyz@gmail.com|Password2|
    |chandrashekhara.av@rakuten.com| 		  |
    | 	     |Password2|