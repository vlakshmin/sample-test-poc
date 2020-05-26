package RXPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RXBaseClass.RXBaseClass;
import RXUtitities.RXUtile;

public class RXLoginPage extends RXBaseClass {

	public @FindBy(xpath ="//input[@autofocus=\"autofocus\"]") WebElement userName;
	public @FindBy(xpath ="//input[@type=\"password\"]") WebElement password;
	public @FindBy(xpath = "//span[text()='Login']") WebElement loginButton;
	public @FindBy(xpath = "//div[text()='The e-mail field is required']") WebElement emailMandt;
	public @FindBy(xpath = "//div[text()='The password field is required']") WebElement passwordMandt;
	
	
	// Initilize page factory
	public  RXLoginPage()
	{
		
		PageFactory.initElements(driver,this);
	}
	// Provide user name and password hence click Login
	public PublisherListPage clickLogin(String uname,String pwd)
	{
		userName.sendKeys(uname);
		password.sendKeys(pwd);
		driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		loginButton.click();
		return new PublisherListPage();
	}
	//Return mandatory Email fields error message.
	public String mandatoryEmailMessage()
	{
		
		return emailMandt.getText();
	}
	
	//Return mandatory Password fields error message.
		public String mandatoryPasswordMessage()
		{
			
			return passwordMandt.getText();
		}
	
}
