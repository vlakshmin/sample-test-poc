package RXPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RXBaseClass.RXBaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProfilePage extends RXBaseClass {

	public String msgPwd;


	//page objects
	@FindBy(xpath = "//div[1]/div[2]/a/div[2]/div[2]") WebElement userProfile;
	@FindBy(xpath = "//div[2]/div[1]/div/div/div[1]/div/div[1]") WebElement profileEmailId;
	@FindBy(css = "span.role") WebElement profileRole;
	@FindBy(css = "div.v-card__title") WebElement changePasswordTitle;
	@FindBy(xpath = "//label[text()='Current password']") WebElement currentPassword;
	@FindBy(xpath = "//label[text()='New password']") WebElement newPassword;
	@FindBy(xpath = "//label[text()='Confirm new password']") WebElement confirmationPassword;
	@FindBy(xpath = "//span[text()='Update password']") WebElement updatePassword;
	@FindBy(xpath = "//form/div[2]/span[1]/div/div/div[2]/div/div/div") WebElement mandatMSforCurrentPwd;
	@FindBy(xpath = "//form/div[2]/span[2]/div/div/div[2]/div/div/div") WebElement mandatMSforNewPwd;
	@FindBy(xpath = "//form/div[2]/span[3]/div/div/div[2]/div/div/div") WebElement mandatMSforconfirmPwd;
	@FindBy(xpath = "//*[@id=\"app\"]/div/header/div/div[1]/button") WebElement logoutButton;
	@FindBy(xpath = "//span[text()='Failed!']") WebElement failedtoChangePwd;
	public @FindBy(xpath = "//div/span/form/div[3]/button/span") WebElement updatedTheChangePwd;
	public @FindBy(xpath = "//span/form/div[2]/span[3]/div/div/div[2]/div/div/div") WebElement newPwdConfpwdMismatch;
	@FindBy(xpath = "//label[contains(text(),'Current password')]/following-sibling::input[@type='password']") WebElement enterCurPwd;
	@FindBy(xpath = "//label[text()='New password']/following-sibling::input[@type='password']") WebElement enterNewPwd;
	@FindBy(xpath = "//label[text()='Confirm new password']/following-sibling::input[@type='password']") WebElement enterConfPwd;

	WebDriverWait wait = new WebDriverWait(driver, 10000);

	public ProfilePage()
	{

		PageFactory.initElements(driver,this);

	}



	//Method to return Mandatory message of the fields on entering blank
	public String getMandatoryFieldMessage(String madPwd)
	{

		if(madPwd.equalsIgnoreCase("Current Passsword"))
		{
			msgPwd=mandatMSforCurrentPwd.getText();
		}else
			if(madPwd.equalsIgnoreCase("New Passsword"))
			{
				msgPwd=mandatMSforNewPwd.getText();
			}else
			{
				msgPwd=mandatMSforconfirmPwd.getText();
			}

		return msgPwd;

	}
	//Enter the field values for change password
	public void enterChangePassword(String currentPwd,String newPwd,String confirmationPwd)
	{
		enterCurPwd.sendKeys(currentPwd);
		enterNewPwd.sendKeys(newPwd);
		enterConfPwd.sendKeys(confirmationPwd);

	}

	//Click on profile
	public void clickProfile()
	{

		
		
		userProfile.click();

	}
	//Enter the field values for change password
	public void clickUpdatePassword()
	{
		updatePassword.click();

	}

	//Check for update password displayed
	public boolean checkForUpdatePwdDisplayed()
	{
		return updatePassword.isDisplayed();

	}
	//Check for email displayed
	public boolean checkForEmailDisplayed()
	{
		return profileEmailId.isDisplayed();

	}
	//Check for role displayed
	public boolean  checkForRoleDisplayed()
	{
		return profileRole.isDisplayed();

	}	
	//Check for change password title displayed
	public boolean  checkForChangePwdTitleDisplayed()
	{
		return changePasswordTitle.isDisplayed();

	}	

	//Get for email displayed
	public String getEmailDisplayed()
	{
		return profileEmailId.getText();

	}
	//Get for role displayed
	public String  getRoleDisplayed()
	{
		return profileRole.getText();

	}	
	//Get for change password title displayed
	public String getChangePwdTitleDisplayed()
	{
		return changePasswordTitle.getText();

	}	
	//Get for change password title displayed
	public void clickOnLogOut()
	{
		 wait.until(ExpectedConditions.visibilityOf(changePasswordTitle));
		
		logoutButton.click();

	}	

	//Get for Failed text
	public String getFailedtoChangePwdDisplayed()
	{
		return failedtoChangePwd.getText();

	}

	//Get for Updated text
	public String getUpdatedTheChangePwdDisplayed()
	{
		return updatedTheChangePwd.getText();



	}

}
