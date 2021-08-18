package stepDefinitions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RXBaseClass.RXBaseClass;
import RXPages.PublisherListPage;
import RXPages.RXDealsPage;
import RXPages.RXNavOptions;
import RXPages.RXUsers;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AdminUserStepDefinition extends RXBaseClass {
	RXUsers rxUserPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	RXDealsPage dealsPage;
	Logger log = Logger.getLogger(AdminUserStepDefinition.class);
	
	//Variables
	static String pName;
	static String uAcName;
	static String uEMail;
	static String uPwd;
	static ArrayList<String> tesData = new ArrayList<String>();
	static ArrayList<WebElement> userTableData = new ArrayList<WebElement>();
	static int adminId;
	static int publiId;
	static int userId;
	static int modifyUserId;
	static String roleType;
	String enteredUserName = "";
	List<String> enteredUserNameList = new ArrayList<String>();
	
	public AdminUserStepDefinition()
	{
		super();
		rxUserPage = new RXUsers();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();
		dealsPage = new RXDealsPage();
	}
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	//==============================================================================================================
	// Verify if user is displayed with list of user accounts for admin login.
	
	@When("^click on User option under accounts\\.$")
	public void click_on_User_option_under_accounts() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"
				+ pubListPgs.logodisplayed());
		Assert.assertTrue(pubListPgs.logodisplayed());
		navOptions.expandAdmin();;
		wait.until(ExpectedConditions.visibilityOf(navOptions.usersUndrAdmin));
		navOptions.usersUndrAdmin.click();
	}

	@Then("^user displayed with User page\\.$")
	public void user_displayed_with_User_page() throws Throwable {
		log.info("Seats Page Header is asserts  and it is : "+ rxUserPage.getUserPageHeading() );
		Assert.assertEquals(rxUserPage.getUserPageHeading(), "Users");
		
	}

	//==============================================================================================================
	// Verify if user is unable to create new user account without filling the fields from  admin login.
	
	@Then("^click on create user\\.$")
	public void click_on_create_user() throws Throwable {
		log.info("Clicked on create user button");
		rxUserPage.clickButtonCreateEditDisableUser("Create");
		
	}

	@Then("^Don't fill the create user page and click save$")
	public void don_t_fill_the_create_user_page_and_click_save() throws Throwable {
		log.info("Select publisher user ");
		rxUserPage.clickOnAdminOrPublisher("Publisher");
		log.info("Clicked on save button");
		rxUserPage.clkSave("Create");
	}

	@Then("^assert that the mandatory field message displayed at field in the create user page$")
	public void assert_that_the_mandatory_field_message_displayed_at_field_in_the_create_user_page() throws Throwable {
		log.info("User Page publisher field mandatory message is asserts  and it is : "+ rxUserPage.getFieldValueMandMsgFrmCreateUserPage("Publisher") );
		Assert.assertEquals(rxUserPage.getFieldValueMandMsgFrmCreateUserPage("Publisher"), "The publisher field is required");
		log.info("User Page Account Name field mandatory message is asserts  and it is : "+ rxUserPage.getFieldValueMandMsgFrmCreateUserPage("Account Name") );
		Assert.assertEquals(rxUserPage.getFieldValueMandMsgFrmCreateUserPage("Account Name"), "The account name field is required");
		log.info("User Page Email field mandatory message is asserts  and it is : "+ rxUserPage.getFieldValueMandMsgFrmCreateUserPage("Email"));
		Assert.assertEquals(rxUserPage.getFieldValueMandMsgFrmCreateUserPage("Email"), "The e-mail field is required");
	}

	//==============================================================================================================
	//  Verify if user is able to create new Admin account from admin login.

	@Then("^fill the create Admin user page$")
	public void fill_the_create_user_page() throws Throwable {
		log.info("Fetch the test data from");
		tesData=rxUserPage.getTestData("Create");
		log.info("Store test data in temp variable");
		uAcName=tesData.get(1);
		uEMail=tesData.get(2);
		uPwd=tesData.get(3);
		roleType="Admin";
		log.info("Fill the fields in create user page");
		rxUserPage.enterVlauesToCreateUser("", uAcName, uEMail, uPwd,roleType );
	}

	@Then("^Click on Save$")
	public void click_on_Save() throws Throwable {
		log.info("Click on Save and assert user created successfully");
		Assert.assertEquals(rxUserPage.clkSave("Create"), "UPDATED!");
		log.info("Create seat page has been closed ");
		rxUserPage.closeCreateUserPage();
		
	}

	@Then("^assert the new Admin user for filled values are displaying in create user page$")
	public void assert_the_new_Admin_user_for_filled_values_are_displaying_in_create_user_page() throws Throwable {
		rxUserPage.waitForUserPageToBeDisplayed();
		log.info("Click on active tab");
		rxUserPage.clickOnTab("Active");
		userId=rxUserPage.selectUserandRetunID(uAcName);
		rxUserPage.updateTheUserIdInTestData("adminUserId",userId);//Added
		adminId=userId;
		log.info("Click on created user check box in the table :"+userId);
		log.info("Click on edit");
		rxUserPage.clickButtonCreateEditDisableUser("Edit");
		tesData.clear();
		log.info("Fetch the data from create user page and put it in array list");
		tesData=rxUserPage.getTheValueFrmCreateUserPage();
		log.info("User name asserted in create User page :"+ tesData.get(0));
		Assert.assertEquals(tesData.get(0),uAcName);
		log.info("Email asserted in create user page :"+ tesData.get(1));
		Assert.assertEquals(tesData.get(1),uEMail);
		log.info("Password asserted in create user page :"+ tesData.get(2));
		Assert.assertEquals(tesData.get(2),"");
		log.info("Create usage page has been closed ");
		rxUserPage.closeCreateUserPage();
		log.info("Click on active tab");
		rxUserPage.waitForUserPageToBeDisplayed();
		rxUserPage.clickOnTab("Active");

	}

	@Then("^assert the new user details in user page table view\\.$")
	public void assert_the_new_user_details_in_user_page_table_view() throws Throwable {
		try {
			log.info("Putting rowdata into arraylist ");
			userTableData=rxUserPage.getParticularUserRowData(userId);
		    log.info("User name asserted in tab :"+ userTableData.get(1).getText());
			Assert.assertEquals(userTableData.get(1).getText(),uAcName);
			log.info("Email asserted in tab :"+ userTableData.get(2).getText());
			Assert.assertEquals(userTableData.get(2).getText(),uEMail);
			log.info("Role asserted in tab :"+ userTableData.get(3).getText());
			Assert.assertEquals(userTableData.get(3).getText(),roleType);
			
			}
			catch (org.openqa.selenium.NoSuchElementException e) 
			{
				System.out.println("There is no seats available in the seat page");
				
			
			}
	}
	

	//==============================================================================================================
	// Verify if user is able to create new publisher account from  admin login.
	
	@Then("^fill the create Publisher user page$")
	public void fill_the_create_Publisher_user_page() throws Throwable {
		log.info("Fetch the test data from");
		tesData=rxUserPage.getTestData("Create");
		log.info("Store test data in temp variable");
		pName=tesData.get(0);
		uAcName=tesData.get(1);
		uEMail=tesData.get(2);
		uPwd=tesData.get(3);
		roleType="Publisher";
		log.info("Fill the fields in create user page"+pName);
		rxUserPage.enterVlauesToCreateUser(pName, uAcName, uEMail, uPwd,roleType );
	}
	
//	@Then("^Click on Save Publisher$")
//	public void click_on_Save_Publisher() throws Throwable {
//		log.info("Click on Save and assert user created successfully");
//		Assert.assertEquals(rxUserPage.clkSave("Create"), "UPDATED!");
//		log.info("Create seat page has been closed ");
//		rxUserPage.closeCreateUserPage();
//		
//	}
	
	@Then("^assert the new Publisher user for filled values are displaying in create user page$")
	public void assert_the_new_Publisher_user_for_filled_values_are_displaying_in_create_user_page() throws Throwable {
		log.info("Click on active tab");
		rxUserPage.waitForUserPageToBeDisplayed();
		rxUserPage.clickOnTab("Active");
		userId=rxUserPage.selectUserandRetunID(uAcName);
		rxUserPage.updateTheUserIdInTestData("publisherUserId",userId);//Added
		publiId=userId;
		log.info("Click on created user check box in the table :"+userId);
		log.info("Click on edit");
		rxUserPage.clickButtonCreateEditDisableUser("Edit");
		tesData.clear();
		log.info("Fetch the data from create user page and put it in array list");
		tesData=rxUserPage.getTheValueFrmCreateUserPage();
		log.info("Publihser name asserted in create User page :"+tesData.size()+tesData.get(0));
		Assert.assertEquals(tesData.get(0),pName);
		log.info("Account name asserted in create User page :"+ tesData.get(1));
		Assert.assertEquals(tesData.get(1),uAcName);
		log.info("Email asserted in create user page :"+ tesData.get(2));
		Assert.assertEquals(tesData.get(2),uEMail);
		log.info("Password asserted in create user page :"+ tesData.get(3));
		Assert.assertEquals(tesData.get(3),"");
		log.info("Create usage page has been closed ");
		rxUserPage.closeCreateUserPage();
		log.info("Click on active tab");
		rxUserPage.waitForUserPageToBeDisplayed();
		rxUserPage.clickOnTab("Active");
	
}
	
	//==============================================================================================================
	//  Verify if user is able to disable the Active Admin account with admin login.
		
	@Then("^click on active tab and select one of the admin user\\.$")
	public void click_on_active_tab_and_select_one_of_the_admin_user() throws Throwable {
		log.info("Click on active tab");
		rxUserPage.waitForUserPageToBeDisplayed();
		rxUserPage.clickOnTab("Active");
		log.info("Looking for a admin role");
		roleType="Admin";
//		userId=rxUserPage.selectTheUserBasedOnRole(roleType);Commented
		userId=rxUserPage.selectUserforEdit(prop.getProperty("adminUserId"));//Added
		userTableData.clear();
		log.info("Putting rowdata into arraylist ");
		userTableData=rxUserPage.getParticularUserRowData(userId);
		uAcName=userTableData.get(1).getText();
		uEMail=userTableData.get(2).getText();
		log.info("User name asserted in tab when selected for modify :"+ userTableData.get(1).getText());
		log.info("Email asserted in tab when selected for modify :"+ userTableData.get(2).getText());
		log.info("Role asserted in tab when selected for modify :"+ userTableData.get(3).getText());
	}

	@Then("^click on disable user button and navigate to disabled tab$")
	public void click_on_disable_user_button_and_navigate_to_disabled_tab() throws Throwable {
		log.info("Click on Disabled");
		rxUserPage.clickButtonCreateEditDisableUser("Disable");
		rxUserPage.waitForUserPageToBeDisplayed();
		rxUserPage.clickOnTab("Disabled");
	}

	//==============================================================================================================
		//  Verify if user is able to disable the Active Publisher account with admin login.

		@Then("^click on active tab and select one of the Publisher user\\.$")
		public void click_on_active_tab_and_select_one_of_the_Publisher_user() throws Throwable {
			log.info("Click on active tab");
			rxUserPage.waitForUserPageToBeDisplayed();
			rxUserPage.clickOnTab("Active");
			log.info("Looking for a Publisher role");
			roleType="Publisher";
//			userId=rxUserPage.selectTheUserBasedOnRole(roleType);commented
			userId=rxUserPage.selectUserforEdit(prop.getProperty("publisherUserId"));//Added
			userTableData.clear();
			log.info("Putting rowdata into arraylist ");
			userTableData.clear();
			userTableData=rxUserPage.getParticularUserRowData(userId);
			uAcName=userTableData.get(1).getText();
			uEMail=userTableData.get(2).getText();
			log.info("User name asserted in tab when selected for modify :"+ userTableData.get(1).getText());
			log.info("Email asserted in tab when selected for modify :"+ userTableData.get(2).getText());
			log.info("Role asserted in tab when selected for modify :"+ userTableData.get(3).getText());
		}
	
		//==============================================================================================================
		//   Verify if user is able to Activate the disabled Admin account with admin login.
			
		@Then("^click on disabled tab and select one of the Admin user\\.$")
		public void click_on_disabled_tab_and_select_one_of_the_admin_user() throws Throwable {
			log.info("Click on Disabled tab");
			rxUserPage.waitForUserPageToBeDisplayed();
			rxUserPage.clickOnTab("Disabled");
			log.info("Looking for a admin role");
			roleType="Admin";
//			userId=rxUserPage.selectTheUserBasedOnRole(roleType);added
			userId=rxUserPage.selectUserforEdit(prop.getProperty("adminUserId"));//Added
			userTableData.clear();
			log.info("Putting rowdata into arraylist ");
			userTableData=rxUserPage.getParticularUserRowData(userId);
			uAcName=userTableData.get(1).getText();
			uEMail=userTableData.get(2).getText();
			log.info("User name asserted in tab when selected for modify :"+ userTableData.get(1).getText());
			log.info("Email asserted in tab when selected for modify :"+ userTableData.get(2).getText());
			log.info("Role asserted in tab when selected for modify :"+ userTableData.get(3).getText());
		}

		@Then("^click on Enable user button and navigate to Activate tab$")
		public void click_on_Enable_user_button_and_navigate_to_Activate_tab() throws Throwable {
			rxUserPage.clickButtonCreateEditDisableUser("Enable");
			rxUserPage.waitForUserPageToBeDisplayed();
			rxUserPage.clickOnTab("Active");
		}

		//==============================================================================================================
		//   Verify if user is able to Activate the disabled Publisher account with admin login.
			
		@Then("^click on disabled tab and select one of the Publisher user\\.$")
		public void click_on_disabled_tab_and_select_one_of_the_Publisher_user() throws Throwable {
			log.info("Click on Disabled tab");
			rxUserPage.waitForUserPageToBeDisplayed();
			rxUserPage.clickOnTab("Disabled");
			log.info("Looking for a Publisher role");
			roleType="Publisher";
//			userId=rxUserPage.selectTheUserBasedOnRole(roleType);Commented
			userId=rxUserPage.selectUserforEdit(prop.getProperty("publisherUserId"));//Added
			userTableData.clear();
			log.info("Putting rowdata into arraylist ");
			userTableData=rxUserPage.getParticularUserRowData(userId);
			uAcName=userTableData.get(1).getText();
			uEMail=userTableData.get(2).getText();
			log.info("User name asserted in tab when selected for modify :"+ userTableData.get(1).getText());
			log.info("Email asserted in tab when selected for modify :"+ userTableData.get(2).getText());
			log.info("Role asserted in tab when selected for modify :"+ userTableData.get(3).getText());
		}
		//==============================================================================================================
		// Verify if user is able to edit active publisher account from admin login.
		@Then("^Click on edit button\\.$")
		public void click_on_edit_button() throws Throwable {
			log.info("Clicked on edit user button");
			rxUserPage.clickButtonCreateEditDisableUser("Edit");
		}

		@Then("^Edit the publisher user$")
		public void edit_the_publisher_user() throws Throwable {
			log.info("Fetch the test data from");
			rxUserPage.deleteFieldValues();
			tesData.clear();
			tesData=rxUserPage.getTestData("Edit");
			log.info("Store test data in temp variable");
			pName=tesData.get(0);
			uAcName=tesData.get(1);
			uEMail=tesData.get(2);
			uPwd=tesData.get(3);
//			roleType="Publisher";
			log.info("Fill the fields in create user page"+pName);
			Thread.sleep(1000);
			rxUserPage.enterVlauesToCreateUser(pName, uAcName, uEMail, uPwd,"EditPublisher" );
		}
		
		@Then("^Click on Save edited user$")
		public void click_on_Save_edited_user() throws Throwable {
			log.info("Click on Save and assert user created successfully");
			Assert.assertEquals(rxUserPage.clkSave("Create"), "UPDATED!");
			log.info("Create seat page has been closed ");
			rxUserPage.closeCreateUserPage();
		}
					
		@Then("^assert the edited Publisher user for filled values are displaying in create user page$")
		public void assert_the_edited_Publisher_user_for_filled_values_are_displaying_in_create_user_page() throws Throwable {
			
//			log.info("Click on active tab");
//			rxUserPage.clickOnTab("Active");
//			userId=rxUserPage.selectUserandRetunID();
//			publiId=userId;
			log.info("Click on created user check box in the table :"+userId);
			log.info("Click on edit");
			rxUserPage.clickButtonCreateEditDisableUser("Edit");
			tesData.clear();
			log.info("Fetch the data from create user page and put it in array list");
			tesData=rxUserPage.getTheValueFrmCreateUserPage();
			log.info("Publihser name asserted in create User page :"+tesData.size()+tesData.get(0));
			Assert.assertEquals(tesData.get(0),pName);
			log.info("Account name asserted in create User page :"+ tesData.get(1));
			Assert.assertEquals(tesData.get(1),uAcName);
			log.info("Email asserted in create user page :"+ tesData.get(2));
			Assert.assertEquals(tesData.get(2),uEMail);
			log.info("Password asserted in create user page :"+ tesData.get(3));
			Assert.assertEquals(tesData.get(3),"");
			log.info("Create usage page has been closed ");
			rxUserPage.closeCreateUserPage();
			log.info("Click on active tab");
//			rxUserPage.clickOnTab("Active");
		}
		//==============================================================================================================
		// Verify if user is able to edit active Admin account from admin login.

		@Then("^Edit the Admin user$")
		public void edit_the_Admin_user() throws Throwable {
			log.info("Fetch the test data from");
			tesData.clear();
			tesData=rxUserPage.getTestData("Edit");
			log.info("Store test data in temp variable");
			pName=tesData.get(0);
			uAcName=tesData.get(1);
			uEMail=tesData.get(2);
			uPwd=tesData.get(3);
			roleType="Admin";
			log.info("Fill the fields in create user page"+pName);
			rxUserPage.deleteFieldValues();
			Thread.sleep(1000);
			rxUserPage.enterVlauesToCreateUser(pName, uAcName, uEMail, uPwd,roleType );
		}
		
					
		@Then("^assert the edited Admin user for filled values are displaying in create user page$")
		public void assert_the_edited_Admin_user_for_filled_values_are_displaying_in_create_user_page() throws Throwable {
			log.info("Click on edit");
			rxUserPage.clickButtonCreateEditDisableUser("Edit");
			tesData.clear();
			log.info("Fetch the data from create user page and put it in array list");
			tesData=rxUserPage.getTheValueFrmCreateUserPage();
			log.info("Account name asserted in create User page :"+ tesData.get(0));
			Assert.assertEquals(tesData.get(0),uAcName);
			log.info("Email asserted in create user page :"+ tesData.get(1));
			Assert.assertEquals(tesData.get(1),uEMail);
			log.info("Password asserted in create user page :"+ tesData.get(2));
			Assert.assertEquals(tesData.get(2),"");
			log.info("Create usage page has been closed ");
			rxUserPage.closeCreateUserPage();
			log.info("Click on active tab");
//			rxUserPage.clickOnTab("Active");
		}
//==============================================================================================================
//Verify if user is able to modify Active Admin account into active publisher account from admin login.
		
		@Then("^Edit the Admin user to modify it into Publisher$")
		public void edit_the_Admin_user_to_modify_it_into_Publisher() throws Throwable {
			log.info("Fetch the test data from");
			rxUserPage.clickOnAdminOrPublisher("Publisher");
			rxUserPage.selectPublisherFrmCreateForm();
			tesData.clear();
			tesData=rxUserPage.getTheValueFrmCreateUserPage();
			log.info("Store test data in temp variable");
			pName=tesData.get(0);
			uAcName=tesData.get(1);
			uEMail=tesData.get(2);
			uPwd=tesData.get(3);
			roleType="Publisher";
			log.info("Fill the fields in create user page"+pName);
			Thread.sleep(1000);
		}
		
		@Then("^Click on Save modify admin user$")
		public void click_on_Save_modify_admin_user() throws Throwable {
			log.info("Click on Save and assert user created successfully");
			Assert.assertEquals(rxUserPage.clkSave("Create"), "UPDATED!");
			log.info("Create seat page has been closed ");
			rxUserPage.closeCreateUserPage();
			rxUserPage.waitForUserPageToBeDisplayed();
			modifyUserId=rxUserPage.selectUserAndReturnId(uAcName);//Added
			rxUserPage.updateTheUserIdInTestData("publisherUserId",modifyUserId);
			rxUserPage.selectUserAndReturnId(uAcName);
			
		}
//==============================================================================================================
//Verify if user is able to modify Active Publisher account into active Admin account from admin login.
		@Then("^Edit the Publisher user to modify it into Admin$")
		public void edit_the_Publisher_user_to_modify_it_into_Admin() throws Throwable {
			log.info("Fetch the test data from");
			rxUserPage.clickOnAdminOrPublisher("Admin");
			tesData.clear();
			tesData=rxUserPage.getTheValueFrmCreateUserPage();
			log.info("Store test data in temp variable");
			uAcName=tesData.get(0);
			uEMail=tesData.get(1);
			uPwd=tesData.get(2);
			roleType="Admin";
			log.info("Fill the fields in create user page"+pName);
			Thread.sleep(1000);
		}
		@Then("^Click on Save modify Publisher user$")
		public void click_on_Save_modify_Publisher_user() throws Throwable {
			log.info("Click on Save and assert user created successfully");
			Assert.assertEquals(rxUserPage.clkSave("Create"), "UPDATED!");
			log.info("Create seat page has been closed ");
			rxUserPage.closeCreateUserPage();
			rxUserPage.waitForUserPageToBeDisplayed();
			modifyUserId=rxUserPage.selectUserAndReturnId(uAcName);//Added
			rxUserPage.updateTheUserIdInTestData("adminUserId",modifyUserId);
			rxUserPage.selectUserAndReturnId(uAcName);
			
		}

//==============================================================================================================
//Verify if user is able to search active Admin account with admin login.

		@Then("^search for the user$")
		public void search_for_the_user() throws Throwable {
			rxUserPage.seatNameSearch(uAcName);
		}
		
		@Then("^Verify the enabling and disabling feature of the user with index (.*)$")
		public void userEnableDisable(int index) throws InterruptedException {
			rxUserPage.clickUserCheckBox(index);
			String status = rxUserPage.getActiveFieldText(index);
			switch(status) {
			  case "Active":
				     Assert.assertEquals(rxUserPage.getEnableDisableBtnText(), "DISABLEUSER");
				     rxUserPage.clickEnableDisableButton();
				     wait.until(ExpectedConditions.invisibilityOf(rxUserPage.enableDisableButton));
				     Thread.sleep(5000);
					 Assert.assertEquals(rxUserPage.getActiveFieldText(index), "Inactive");
					 rxUserPage.clickUserCheckBox(index);
					 Assert.assertEquals(rxUserPage.getEnableDisableBtnText(), "ENABLEUSER");
					 rxUserPage.clickEnableDisableButton();
					 wait.until(ExpectedConditions.invisibilityOf(rxUserPage.enableDisableButton));
					 Thread.sleep(5000);
					 Assert.assertEquals(rxUserPage.getActiveFieldText(index), "Active");
					break;
			  case "Inactive":
				     Assert.assertEquals(rxUserPage.getEnableDisableBtnText(), "ENABLEUSER");
				     rxUserPage.clickEnableDisableButton();
				     wait.until(ExpectedConditions.invisibilityOf(rxUserPage.enableDisableButton));
				     Thread.sleep(5000);
					 Assert.assertEquals(rxUserPage.getActiveFieldText(index), "Active");
					 rxUserPage.clickUserCheckBox(index);
					 Assert.assertEquals(rxUserPage.getEnableDisableBtnText(), "DISABLEUSER");
					 rxUserPage.clickEnableDisableButton();
					 wait.until(ExpectedConditions.invisibilityOf(rxUserPage.enableDisableButton));
					 Thread.sleep(5000);
					 Assert.assertEquals(rxUserPage.getActiveFieldText(index), "Inactive");
					break;
			   
			  default:
			    Assert.assertTrue(false, "The status fields supplied does not match with the input");
			}
			
		}
		
		@When("^Click on SAVE USER button$")
		public void click_on_SAVE_USER_button() {
			wait.until(ExpectedConditions.visibilityOf(rxUserPage.saveButton));
			rxUserPage.saveButton.click();
			
		}
		
		@Then("^Create User page should be closed$")
		public void create_User_page_should_be_closed() {
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
		}


		@When("^Enter the following data in the Create User page$")
		public void enter_the_following_data_in_the_Create_User_page(DataTable dt) throws Throwable {
			List<String> list = dt.asList(String.class);
			log.info("Fetch the test data from");
			tesData=rxUserPage.getTestData("Create");
			for (int i = 0; i < list.size(); i++) {
				String fieldName = list.get(i);
				switch (fieldName) {
				case "Publisher":
					rxUserPage.dropDwonSelect.click();
					dealsPage.selectPublisherByName(tesData.get(0));
					wait.until(ExpectedConditions.visibilityOf(rxUserPage.userName));
					break;
				case "Username":
					while (!rxUserPage.userName.getAttribute("value").equals("")) {
						rxUserPage.userName.sendKeys(Keys.BACK_SPACE);
					}
					uAcName=tesData.get(1);
					rxUserPage.userName.sendKeys(uAcName);
					enteredUserName = uAcName;
//					System.out.println("enteredUserName>>>>"+rxUserPage.userName.getAttribute("value"));
					break;
				case "Email":
					while (!rxUserPage.userEmail.getAttribute("value").equals("")) {
						rxUserPage.userEmail.sendKeys(Keys.BACK_SPACE);
					}
					uEMail=tesData.get(2)+"@test.com";
					rxUserPage.userEmail.sendKeys(uEMail);
//					System.out.println("entereduserEmail>>>>"+rxUserPage.userEmail.getAttribute("value"));
					break;
				case "Password":
					while (!rxUserPage.userPassword.getAttribute("value").equals("")) {
						rxUserPage.userPassword.sendKeys(Keys.BACK_SPACE);
					}
					uPwd=tesData.get(3);
					rxUserPage.userPassword.sendKeys(uPwd);
//					System.out.println("entereduserPassword>>>>"+rxUserPage.userPassword.getAttribute("value"));
					break;
				default:
					Assert.fail(fieldName + " is not present.");
				}
			}
		}
		
		@Then("^Select one \"([^\"]*)\" User item$")
		public void select_one_User_item(String active) {
			enteredUserNameList.clear();
			List<WebElement> listActives = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[6]"));
			for (int k = 0; k < listActives.size(); k++) {
					String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
					if (active.equals(reqActive)) {
						rxUserPage.userCheckBox(k+1).click();
						enteredUserNameList.add(rxUserPage.userName(k+1));
						break;
					}
			}
		}

		@Then("^Verify that following buttons are present in User list page$")
		public void verify_that_following_buttons_are_present_in_User_list_page(DataTable dt) {
			List<String> buttons = dt.asList(String.class);
			buttons.forEach(e -> Assert.assertTrue(rxUserPage.toolbarButton(e).isDisplayed(), e + " is not present."));
		}

		@When("^Click \"([^\"]*)\" button in User list page$")
		public void click_button_in_User_list_page(String buttonName) {
			rxUserPage.toolbarButton(buttonName).click();
			if(!buttonName.equals("Edit User")) {
				wait.until(ExpectedConditions.invisibilityOf(rxUserPage.toolbarButton(buttonName)));
			}
		}

		@Then("^Edit User pop up is present$")
		public void edit_User_pop_up_is_present()  {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//aside[@class='dialog']"))));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(
					By.xpath("//aside[@class='dialog']/header//div[contains(text(),'" + enteredUserNameList.get(0) + "')]"))));
		}

		@Then("^Verify the edited User data is matching with its overview list values$")
		public void verify_the_edited_User_data_is_matching_with_its_overview_list_values()  {
			String userName = "";
			String enteredName = enteredUserName.replaceAll("\\s", "");
			List<WebElement> listNames = driver
					.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[3]/a"));
			for (int k = 0; k < listNames.size(); k++) {
				userName = listNames.get(k).getText().replaceAll("\\s", "");
				if (enteredName.equals(userName)) {
					break;
				}
			}
			Assert.assertEquals(userName, enteredName);
		}

		@Then("^\"([^\"]*)\" is displayed for the created User$")
		public void is_displayed_for_the_created_User(String arg1)  {
			List<WebElement> listOfNames = driver
					.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[4]/a"));
			for(int i=0;i<enteredUserNameList.size();i++) {
				for (int k = 0; k < listOfNames.size(); k++) {
					String reqName = listOfNames.get(k).getText().replaceAll("\\s", "");
					if (enteredUserNameList.get(i).equals(reqName)) {
						Assert.assertEquals(rxUserPage.userActive(k+1).getText(), arg1, rxUserPage.userActive(k+1).getText() +" is displayed for the created private auction");
						break;
					}
				}
			}
		}

		@Then("^Select (\\d+) \"([^\"]*)\" and (\\d+) \"([^\"]*)\" User items$")
		public void select_and_User_items(int num1, String inactive, int num3, String active){
			enteredUserNameList.clear();
			List<WebElement> listActives = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr/td[6]"));
			for (int k = 0; k < listActives.size(); k++) {
					String reqActive = listActives.get(k).getText().replaceAll("\\s", "");
					if (active.equals(reqActive) && num3 > 0) {
						rxUserPage.userCheckBox(k+1).click();
						enteredUserNameList.add(rxUserPage.userName(k+1));
						num3--;
					}
					if(inactive.equals(reqActive) && num1 > 0) {
						rxUserPage.userCheckBox(k+1).click();
						enteredUserNameList.add(rxUserPage.userName(k+1));
						num1--;
					}
					if(num1 == 0 && num3 == 0) {
						break;
					}
			}
			Assert.assertEquals(num1,0,num1 +" Inactive User is not selected.");
			Assert.assertEquals(num3,0,num3 +" Active User is not selected.");
		}

		@Then("^Verify that active toggle button is present in the top left corner of the form and is enabled by default$")
		public void verify_that_active_toggle_button_is_present_in_the_top_left_corner_of_the_form_and_is_enabled_by_default() {
			Assert.assertTrue(rxUserPage.toggleField("Active").isDisplayed());
			Assert.assertEquals(rxUserPage.toggleFieldIsEnabledForCreatePage("Active"), "true");
		}

		@Then("^Verify that the user created/edited should be \"([^\"]*)\"$")
		public void verify_that_the_user_created_edited_should_be(String active) {
			Assert.assertEquals(rxUserPage.getActiveWithName(enteredUserName), active);
		}
		
		@Then("^Verify following default columns in user list page$")
		public void verify_following_default_columns_in_user_list_page(DataTable dt) {
		    List<String> headers = dt.asList(String.class);
		    headers.forEach(e -> Assert.assertTrue(rxUserPage.getColumnHeader(e).isDisplayed()));
		}
		
		@When("^Click on Table Options button in users list page$")
		public void click_on_Table_Options_button_in_users_list_page() {
			rxUserPage.tableOptions.click();
			wait.until(ExpectedConditions.visibilityOf(rxUserPage.tableOptionsMenu));
		}

		@When("^Select the columns from Users Table Options$")
		public void select_the_columns_from_Users_Table_Options(DataTable dt) throws Throwable {
			List<String> columns = dt.asList(String.class);
		    columns.forEach(e -> rxUserPage.selectColumnInTableOptions(e));
		}
}
