package stepDefinitions;

import java.io.FileOutputStream;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import RXBaseClass.RXBaseClass;
import RXPages.ProfilePage;
import RXPages.PublisherListPage;
import RXPages.RXLoginPage;
import RXUtitities.RXUtile;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProfileStepDefinition extends RXBaseClass {
	String newPassword;
	ProfilePage proPage;
	RXLoginPage logain;
	PublisherListPage pubListPgs;
	Logger log = Logger.getLogger(ProfileStepDefinition.class);

	public ProfileStepDefinition() {
		super();
		
		logain = new RXLoginPage();
		
		proPage = new ProfilePage();
		
		
	}


	@Given("^admin user login to RX UI with valid username and password$")
	public void admin_user_login_to_RX_UI_with_valid_username_and_password() throws Throwable {
//		Reporter.addStepLog("The user has logged in "+prop.getProperty("url")+" as admin");
		driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		pubListPgs = logain.clickLogin(prop.getProperty("username"), prop.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
//		 Thread.sleep(6000);
		Assert.assertTrue(pubListPgs.logodisplayed());
	}

	@When("^navigated to profile$")
	public void navigated_to_profile() throws Throwable {
		proPage.clickProfile();
	}

	@Then("^admin user displayed with his e-mail,Role,change password and option Update password$")
	public void admin_user_displayed_with_his_e_mail_Role_change_password_and_option_Update_password()
			throws Throwable {
		log.info("Email displayed " + proPage.getEmailDisplayed());
		Assert.assertTrue(proPage.checkForEmailDisplayed());

		log.info("Role" + proPage.getRoleDisplayed());
		Assert.assertTrue(proPage.checkForRoleDisplayed());

		log.info("Title :" + proPage.getChangePwdTitleDisplayed());
		Assert.assertTrue(proPage.checkForChangePwdTitleDisplayed());

		log.info("Update password displayed displayed");
		Assert.assertTrue(proPage.checkForUpdatePwdDisplayed());

	}

	@When("^User is allowed to Change password$")
	public void user_is_allowed_to_Change_password() throws Throwable {
		proPage.clickProfile();
		Random rand = new Random();
		int n = rand.nextInt(50);
		newPassword = "Passwrd" + n;
		System.out.println("Before change pwd a new pass" + newPassword);
		System.out.println("Old Password" + prop.getProperty("password"));
		proPage.enterChangePassword(prop.getProperty("password"), newPassword, newPassword);
		System.out.println("After change pwd a new pass" + newPassword);

	}

	@Then("^Change password should be success$")
	public void change_password_should_be_success() throws Throwable {
		proPage.clickUpdatePassword();
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement element =wait.until(ExpectedConditions.visibilityOf(proPage.updatedTheChangePwd));
		Assert.assertEquals(element.getText(), "UPDATED!");
//		boolean element =wait.until(ExpectedConditions.(proPage.updatedTheChangePwd, "Updated!"));
		
		System.out.println("Updated Text " +proPage.getUpdatedTheChangePwdDisplayed());
		
//		Assert.assertEquals(proPage.getUpdatedTheChangePwdDisplayed(), "Updated!");
		
		  String propFile = "src/main/java/RXConfig/RX.properties"; 
		  FileOutputStream out = new FileOutputStream(propFile);
		  prop.setProperty("password",newPassword);
		  prop.store(out, null); 
		  out.close();
		 
		driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		System.out.println("Password Changed");
		proPage.clickOnLogOut();
		System.out.println("Logout Successfull");
//		driver.close();
	}

	@Then("^User is able to login with new password$")
	public void user_is_able_to_login_with_new_password() throws Throwable {
		Thread.sleep(2000);
		System.out.println("User name :" + prop.getProperty("username") + " Password :" + newPassword);
		driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		pubListPgs = logain.clickLogin(prop.getProperty("username"), prop.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		Assert.assertTrue(pubListPgs.logodisplayed());
	}
	
	
	@When("^user is enter invalid current password to Change password$")
	public void user_is_enter_invalid_current_password_to_Change_password() throws Throwable {
		Random rand = new Random();
		int n = rand.nextInt(50);
		String newPwd = "Passwd1" + n;
		proPage.clickProfile();
		proPage.enterChangePassword(prop.getProperty("invalidPwd"), newPwd, newPwd);
	}

	@Then("^Change password should fail$")
	public void change_password_should_not_be_success() throws Throwable {
		proPage.clickUpdatePassword();
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement element =wait.until(ExpectedConditions.visibilityOf(proPage.updatedTheChangePwd));
		Assert.assertEquals(element.getText(), "FAILED!");
		
	}
	
	@Then("^Verify that user cannot navigate to old UI$")
	public void veriyOldUIToggleNA() throws Throwable {
		Assert.assertTrue(driver.findElements(By.xpath("//img/following-sibling::div//div[@class='v-input--selection-controls__input']")).size()==0);
		
		
	}
	
	@When("^user is enter miss-match of New Password and Confirmation password$")
	public void user_is_enter_miss_match_of_New_Password_and_Confirmation_password() throws Throwable {
		Random rand = new Random();
		int n = rand.nextInt(50);
		String newPwd = "Passwd" + n;
		proPage.clickProfile();
		proPage.enterChangePassword(prop.getProperty("invalidPwd"), newPwd+1, newPwd+2);
		
	}

	@Then("^user is displayed with miss-match of New Password and Confirmation password$")
	public void user_is_displayed_with_miss_match_of_New_Password_and_Confirmation_password() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement element =wait.until(ExpectedConditions.visibilityOf(proPage.newPwdConfpwdMismatch));
		Assert.assertEquals(element.getText(), "The confirmPassword field confirmation does not match");
	}


	/*
	 * @Then("^Close the browser$") public void close_the_browser() { driver.quit();
	 * 
	 * }
	 */
}
