package stepDefinitions;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RXBaseClass.RXBaseClass;
import RXPages.PublisherListPage;
import RXPages.RXLoginPage;
import RXPages.RXSeatsPage;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SeatsPageStepsDefinition extends RXBaseClass    {
	
	RXSeatsPage seatsPage;
	Logger log = Logger.getLogger(SeatsPageStepsDefinition.class);
	
	public SeatsPageStepsDefinition()
	{
		super();
		seatsPage = new RXSeatsPage();
		
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	// Variable used 
	static String strmisc;
	static int seatId;
	static String seatName;
	static String seatType;
	static String seatRegion;
	static ArrayList<WebElement> seatTable;
	static ArrayList<WebElement> stTable;
	static ArrayList<String> stData;
	//Variable to store data for create seat
	static String bName; 
	static String bType;
	static String eDSP;
	static String eToken; 
	static String cName;
	static String eWebsite; 
	static String eAddressOne ;
	static String eAddressTwo ; 
	static String eCity;
	static String pCode;
	static String eCountry ;
	static String eCurrency;
	static String eAuthorId;
	static String cEmail ;
//=========================================================================================================	
//Verify if user is displayed with seats list page on clicking seats with admin role.

@When("^click on Seat option under accounts\\.$")
public void click_on_Seat_option_under_accounts() throws Throwable {
	
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
}

@Then("^user displayed with seats page\\.$")
public void user_displayed_with_seats_page() throws Throwable {
	Assert.assertEquals(seatsPage.getPageHeading(), seatsPage.SeatsHeaderStr);
	log.info("Seats Page Header is asserts  and it is : "+ seatsPage.getPageHeading() );

}
//=========================================================================================================
//Verify if user is able to search the Active Seat with admin role.


@When("^click on Active tab and search for Seat$")
public void click_on_Active_tab_and_search_for_Seat() throws Throwable {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Active");
	log.info("Clicked on Active tab");
	strmisc=seatsPage.getSeatNameForSearch();
	log.info("Seat name for search on Active tab :"+ strmisc);
	seatsPage.seatNameSearch(strmisc);
	log.info("Seat name searched on Active tab :"+ strmisc);
	
}

@Then("^Searched result display the seat$")
public void searched_result_display_the_seat() throws Throwable {
	try {
	Assert.assertEquals(seatsPage.getSeatNameForSearch(),strmisc);
	log.info("Search result of Seat name asserted  :"+ seatsPage.getSeatNameForSearch());
	}
	catch (org.openqa.selenium.NoSuchElementException e) 
	{
		System.out.println("There is no seats available in the seat page");
		
	
	}
}

//=========================================================================================================
//Verify if user is able to search the Inactive Seat with admin role.

@When("^click on Inactive tab and search for Seat$")
public void click_on_Inactive_tab_and_search_for_Seat() throws Throwable {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Inactive");
	log.info("Clicked on Inactive tab");
	strmisc=seatsPage.getSeatNameForSearch();
	log.info("Seat name for search on Inactive tab :"+ strmisc);
	seatsPage.seatNameSearch(strmisc);
	log.info("Seat name searched on Inactive tab :"+ strmisc);
	
}

//=========================================================================================================
//Verify if user is able to search the paused Seat with admin role.

@When("^click on paused tab and search for Seat$")
public void click_on_paused_tab_and_search_for_Seat() throws Throwable {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("paused");
	log.info("Clicked on paused tab");
	try {
	strmisc=seatsPage.getSeatNameForSearch();
	log.info("Seat name for search on paused tab :"+ strmisc);
	seatsPage.seatNameSearch(strmisc);
	log.info("Seat name searched on paused tab :"+ strmisc);
	}
	catch (org.openqa.selenium.NoSuchElementException e) 
	{
		System.out.println("There is no seats available in the seat page");
		
	
	}
}

//=========================================================================================================
//Verify if user is able to update the Active Seat into Inactive seat with admin role.


@When("^Select the active seat for modify$")
public void select_the_active_seat_for_modify()  throws Throwable{
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Active");
	log.info("Clicked on Active tab");
	try {
//	seatId=seatsPage.selectSeatandRetunID();Commented
	seatId=seatsPage.selectSeatforEdit(prop.getProperty("activeSeatID"));//Added
	System.out.println("Seat ID select :"+ seatId);
	log.info("Clicked on Checkbox of the seat ID :"+seatId);
	seatTable=seatsPage.getParticularSeatRowData(seatId);
	log.info("Before Modify active seat assigned the particular seat row data into the array list");
	seatName=seatTable.get(1).getText();
	log.info("Before Modify active seat assigned the seat name from the array list :"+seatName);
	seatType=seatTable.get(2).getText();
	log.info("Before Modify active seat assigned the sales account name from the array list :"+seatType);
	seatRegion=seatTable.get(3).getText();
	log.info("Before Modify active seat assigned the email if from the array list :"+seatRegion);
	}
	catch (org.openqa.selenium.NoSuchElementException e) 
	{
		System.out.println("There is no seats available in the seat page");
		
	
	}
	
}

@Then("^modify the selected seat to Deactivate$")
public void modify_the_selected_seat_to_Deactivate() throws InterruptedException {
	try {
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Deactivate");
	log.info("Clicked on Deactivate button by selecting Seats from Active tab");
	seatsPage.clickTabActiveInactivePaused("Inactive");
	log.info("Clicked on Inactive tab : "+seatId);
    stTable=seatsPage.getParticularSeatRowData(seatId);
	Assert.assertEquals(stTable.get(1).getText(),seatName);
	log.info("Seat name asserted in Inactive tab :"+ stTable.get(1).getText());
	Assert.assertEquals(stTable.get(2).getText(),seatType);
	log.info("Seat name asserted in Inactive tab :"+ stTable.get(2).getText());
	Assert.assertEquals(stTable.get(3).getText(),seatRegion);
	log.info("Seat name asserted in Inactive tab :"+ stTable.get(3).getText());
	}
	catch (org.openqa.selenium.NoSuchElementException e) 
	{
		System.out.println("There is no seats available in the seat page");
		
	
	}
	
}

//=========================================================================================================
//Verify if user is able to update the Active Seat into Disabled seat with admin role.
@Then("^modify the selected seat to Disabled or paused$")
public void modify_the_selected_seat_to_Disabled_or_paused() throws InterruptedException  {
	try {
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Paused");
	log.info("Clicked on paused seat button by selecting Seats from Active tab");
	seatsPage.clickTabActiveInactivePaused("Paused");
	log.info("Clicked on paused tab : "+seatId);
    stTable=seatsPage.getParticularSeatRowData(seatId);
	Assert.assertEquals(stTable.get(1).getText(),seatName);
	log.info("Seat name asserted in paused tab :"+ stTable.get(1).getText());
	Assert.assertEquals(stTable.get(2).getText(),seatType);
	log.info("Seat name asserted in paused tab :"+ stTable.get(2).getText());
	Assert.assertEquals(stTable.get(3).getText(),seatRegion);
	log.info("Seat name asserted in paused tab :"+ stTable.get(3).getText());
	}
	catch (org.openqa.selenium.NoSuchElementException e) 
	{
		System.out.println("There is no seats available in the seat page");
		
	
	}
}

//=========================================================================================================
//Verify if user is able to update the Inactive Seat into Active seat with admin role.
@When("^Select the Inactive seat for modify$")
public void select_the_Inactive_seat_for_modify() throws Throwable {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Inactive");
	log.info("Clicked on Inactive tab");
	try {
//	seatId=seatsPage.selectSeatandRetunID();Comment
	seatId=seatsPage.selectSeatforEdit(prop.getProperty("activeSeatID"));//Added
	System.out.println("Seat ID select :"+ seatId);
	log.info("Clicked on Checkbox of the seat ID :"+seatId);
	seatTable=seatsPage.getParticularSeatRowData(seatId);
	log.info("Before Modify Inactive seat assigned the particular seat row data into the array list");
	seatName=seatTable.get(1).getText();
	log.info("Before Modify Inactive seat assigned the seat name from the array list :"+seatName);
	seatType=seatTable.get(2).getText();
	log.info("Before Modify Inactive seat assigned the sales account name from the array list :"+seatType);
	seatRegion=seatTable.get(3).getText();
	log.info("Before Modify Inactive seat assigned the email if from the array list :"+seatRegion);
	}
	catch (org.openqa.selenium.NoSuchElementException e) 
	{
		System.out.println("There is no seats available in the seat page");
		
	
	}
}

@Then("^modify the selected seat to Activate$")
public void modify_the_selected_seat_to_Activate() throws InterruptedException {
	try {
		seatsPage.clickButtonCreateEditActiveDeactivePaused("Activate");
		log.info("Clicked on Activate seat button by selecting Seats from Inactive tab");
		seatsPage.clickTabActiveInactivePaused("Active");
		log.info("Clicked on Activate tab : "+seatId);
	    stTable=seatsPage.getParticularSeatRowData(seatId);
		Assert.assertEquals(stTable.get(1).getText(),seatName);
		log.info("Seat name asserted in Activate tab :"+ stTable.get(1).getText());
		Assert.assertEquals(stTable.get(2).getText(),seatType);
		log.info("Seat name asserted in Activate tab :"+ stTable.get(2).getText());
		Assert.assertEquals(stTable.get(3).getText(),seatRegion);
		log.info("Seat name asserted in Activate tab :"+ stTable.get(3).getText());
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			System.out.println("There is no seats available in the seat page");
			
		
		}
}

//=========================================================================================================
//Verify if user is able to update the Inactive Seat into Disabled seat with admin role.
@Then("^modify the selected seat to Disabled or Paused$")
public void modify_the_selected_seat_to_Disabled_orPaused() throws InterruptedException {
	try {
		seatsPage.clickButtonCreateEditActiveDeactivePaused("Paused");
		log.info("Clicked on Paused seat button by selecting Seats from Inactive tab");
		seatsPage.clickTabActiveInactivePaused("Paused");
		log.info("Clicked on Paused tab : "+seatId);
	    stTable=seatsPage.getParticularSeatRowData(seatId);
		Assert.assertEquals(stTable.get(1).getText(),seatName);
		log.info("Seat name asserted in Paused tab :"+ stTable.get(1).getText());
		Assert.assertEquals(stTable.get(2).getText(),seatType);
		log.info("Seat name asserted in Paused tab :"+ stTable.get(2).getText());
		Assert.assertEquals(stTable.get(3).getText(),seatRegion);
		log.info("Seat name asserted in Paused tab :"+ stTable.get(3).getText());
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			System.out.println("There is no seats available in the seat page");
			
		
		}
}

//=========================================================================================================
//Verify if user is able to update the Disabled or Paused Seat into Active seat with admin role


@When("^Select the Disabled or paused seat for modify$")
public void select_the_Disabled_or_paused_seat_for_modify() throws InterruptedException {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Paused");
	log.info("Clicked on Paused tab");
	try {
//	seatId=seatsPage.selectSeatandRetunID();Commented
	seatId=seatsPage.selectSeatforEdit(prop.getProperty("activeSeatID"));//Added
	System.out.println("Seat ID select :"+ seatId);
	log.info("Clicked on Checkbox of the seat ID :"+seatId);
	seatTable=seatsPage.getParticularSeatRowData(seatId);
	log.info("Before Modify Paused seat assigned the particular seat row data into the array list");
	seatName=seatTable.get(1).getText();
	log.info("Before Modify Paused seat assigned the seat name from the array list :"+seatName);
	seatType=seatTable.get(2).getText();
	log.info("Before Modify Paused seat assigned the sales account name from the array list :"+seatType);
	seatRegion=seatTable.get(3).getText();
	log.info("Before Modify Paused seat assigned the email if from the array list :"+seatRegion);
	}
	catch (org.openqa.selenium.NoSuchElementException e) 
	{
		System.out.println("There is no seats available in the seat page");
		
	
	}
	
}

@Then("^modify the selected seat to Active$")
public void modify_the_selected_seat_to_Active() throws InterruptedException {
	try {
		seatsPage.clickButtonCreateEditActiveDeactivePaused("Activate");
		log.info("Clicked on Activate seat button by selecting Seats from paused tab");
		seatsPage.clickTabActiveInactivePaused("Active");
		log.info("Clicked on Active tab : "+seatId);
	    stTable=seatsPage.getParticularSeatRowData(seatId);
		Assert.assertEquals(stTable.get(1).getText(),seatName);
		log.info("Seat name asserted in Active tab :"+ stTable.get(1).getText());
		Assert.assertEquals(stTable.get(2).getText(),seatType);
		log.info("Seat name asserted in Active tab :"+ stTable.get(2).getText());
		Assert.assertEquals(stTable.get(3).getText(),seatRegion);
		log.info("Seat name asserted in Active tab :"+ stTable.get(3).getText());
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			System.out.println("There is no seats available in the seat page");
			
		
		}

}
//=========================================================================================================
//Verify if user is able to update the Disabled or Paused Seat into Inactive or Deactivate seat with admin role.

@Then("^modify the selected seat to Inactive$")
public void modify_the_selected_seat_to_Inactive() throws InterruptedException {
	try {
		seatsPage.clickButtonCreateEditActiveDeactivePaused("DeactivateFromPaused");
		log.info("Clicked on Deactivate seat button by selecting Seats from paused tab");
		seatsPage.clickTabActiveInactivePaused("InActive");
		log.info("Clicked on Inactivate tab : "+seatId);
	    stTable=seatsPage.getParticularSeatRowData(seatId);
		Assert.assertEquals(stTable.get(1).getText(),seatName);
		log.info("Seat name asserted in Inactivate tab :"+ stTable.get(1).getText());
		Assert.assertEquals(stTable.get(2).getText(),seatType);
		log.info("Seat name asserted in Inactivate tab :"+ stTable.get(2).getText());
		Assert.assertEquals(stTable.get(3).getText(),seatRegion);
		log.info("Seat name asserted in Inactivate tab :"+ stTable.get(3).getText());
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			System.out.println("There is no seats available in the seat page");
			
		
		}

}

//=============================================================================================================
//Verify if user is able to create the Active Seat with admin role.

@When("^Click on create seat button\\.$")
public void click_on_create_seat_button() throws Throwable {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Active");
	log.info("Click on Active tab");
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Create");
	log.info("Click on create seat");
}

@Then("^Fill the Active create seat page \\.$")
public void fill_the_Active_create_seat_page() throws Throwable {
	
	stData=seatsPage.getTestData();
	bName =stData.get(0);
	bType=stData.get(1);
	eDSP=stData.get(2);
	eToken=stData.get(3);
	cName=stData.get(4);
	eWebsite=stData.get(5); 
	eAddressOne =stData.get(6);
	eAddressTwo =stData.get(7);
	eCity=stData.get(8);
	pCode=stData.get(9);
	eCountry =stData.get(10);
	eCurrency=stData.get(11);
	eAuthorId=stData.get(12);
	cEmail =stData.get(13);
	seatsPage.enterVlauesToCreateSeats(bName, bType, "Active", eDSP, eToken, cName, eWebsite, eAddressOne, eAddressTwo, eCity, pCode, eCountry, eCurrency, eAuthorId, cEmail);
	
}

@Then("^Click on Save and close the create seat page$")
public void click_on_Save() throws Throwable {
	Assert.assertEquals(seatsPage.clkSave(), "UPDATED!");
	log.info("Seate created Successfully ");
	seatsPage.closeCreateSeatPage();
	log.info("Create seat page has been closed ");
	Thread.sleep(1000);
}

@Then("^Navigate to active create seat page and validate the filled data\\.$")
public void navigate_to_create_seat_page_and_validate_the_filled_data() throws Throwable {
	seatId=seatsPage.getTheSeatIdandClickCheckBox(bName);
	seatsPage.updateTheSeatIdInTestData("activeSeatID",seatId);//Added
	log.info("Click on created seat check box in the table :"+seatId);
	seatsPage.clickTabActiveInactivePaused("Active");
	log.info("Click on Active tab");
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Edit");
	log.info("Click on edit seat");
	stData.clear();
	stData=seatsPage.getTheValueFrmCreateSeatPage();
	log.info("Active Seat name asserted in create seat page :"+ seatsPage.getTheStatus("Active"));
	Assert.assertTrue(seatsPage.getTheStatus("Active"));
	Assert.assertEquals(stData.get(0),bName);
	log.info("Seat name asserted in create seat page :"+ stData.get(0));
	Assert.assertEquals(stData.get(1),bType);
	log.info("Seat type asserted in create seat page :"+ stData.get(1));
	Assert.assertEquals(stData.get(2),eDSP);
	log.info("Seat DSP asserted in create seat page :"+ stData.get(2));
	Assert.assertEquals(stData.get(3),eToken);
	log.info("Seat Tocken asserted in create seat page :"+ stData.get(3));
	Assert.assertEquals(stData.get(4),cName);
	log.info("Seat Corporation name asserted in create seat page :"+ stData.get(4));
	Assert.assertEquals(stData.get(5),eWebsite);
	log.info("Seat website asserted in create seat page :"+ stData.get(5));
	Assert.assertEquals(stData.get(6),eAddressOne);
//	JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView();", seatsPage.enterAuthorId);
	log.info("Seat address one asserted in create seat page :"+ stData.get(6));
	Assert.assertEquals(stData.get(7),eAddressTwo);
	log.info("Seat address two asserted in create seat page :"+ stData.get(7));
	Assert.assertEquals(stData.get(8),eCity);
	log.info("Seat name asserted in create seat page :"+ stData.get(8));
	Assert.assertEquals(stData.get(9),pCode);
	log.info("Seat postal code asserted in create seat page :"+ stData.get(9));
	log.info("Seat country asserted in create seat page :"+ stData.get(10));
	Assert.assertEquals(stData.get(10),eCountry);
	log.info("Seat currency asserted in create seat page :"+ stData.get(11));
	Assert.assertEquals(stData.get(11),eCurrency);
	log.info("Seat author id asserted in create seat page :"+ stData.get(12));
	Assert.assertEquals(stData.get(12),eAuthorId);
	log.info("Seat email asserted in create seat page :"+ stData.get(13));
	Assert.assertEquals(stData.get(13),cEmail);
	log.info("Create seat page has been closed ");
	seatsPage.closeCreateSeatPage();
	
	
}

@Then("^Validate the created seat data reflects in the Active tab\\.$")
public void validate_the_created_seat_data_in_table() throws Throwable {
	try {
		Thread.sleep(1000);
		log.info("Clicked on activate tab : "+seatId);
		seatsPage.clickTabActiveInactivePaused("Active");
	
	    stTable=seatsPage.getParticularSeatRowData(seatId);
	    log.info("Seat name asserted in activate tab :"+ stTable.get(1).getText());
		Assert.assertEquals(stTable.get(1).getText(),bName);
		log.info("Seat name asserted in activate tab :"+ stTable.get(2).getText());
		Assert.assertEquals(stTable.get(2).getText(),bType);
		log.info("Seat name asserted in activate tab :"+ stTable.get(3).getText());
		Assert.assertEquals(stTable.get(3).getText(),eCountry);
		
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			System.out.println("There is no seats available in the seat page");
			
		
		}
	
}

//=============================================================================================================
//Verify if user is able to create the Inactive Seat with admin role.

@Then("^Fill the Inactive create seat page \\.$")
public void fill_the_Inactive_create_seat_page() throws Throwable {
	
	stData=seatsPage.getTestData();
	bName =stData.get(0);
	bType=stData.get(1);
	eDSP=stData.get(2);
	eToken=stData.get(3);
	cName=stData.get(4);
	eWebsite=stData.get(5); 
	eAddressOne =stData.get(6);
	eAddressTwo =stData.get(7);
	eCity=stData.get(8);
	pCode=stData.get(9);
	eCountry =stData.get(10);
	eCurrency=stData.get(11);
	eAuthorId=stData.get(12);
	cEmail =stData.get(13);
	seatsPage.enterVlauesToCreateSeats(bName, bType, "Inactive", eDSP, eToken, cName, eWebsite, eAddressOne, eAddressTwo, eCity, pCode, eCountry, eCurrency, eAuthorId, cEmail);
	
}

@Then("^Navigate to Inactive create seat page and validate the filled data\\.$")
public void navigate_to_Inactive_create_seat_page_and_validate_the_filled_data() throws Throwable {
	seatsPage.clickTabActiveInactivePaused("Inactive");
	log.info("Click on Inactive tab");
	seatId=seatsPage.getTheSeatIdandClickCheckBox(bName);
	seatsPage.updateTheSeatIdInTestData("inActiveSeatID",seatId);//Added
	log.info("Click on created seat check box in the table :"+seatId);
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Edit");
	log.info("Click on edit seat");
	stData.clear();
	stData=seatsPage.getTheValueFrmCreateSeatPage();
	log.info("Inactive Seat name asserted in create seat page :"+ seatsPage.getTheStatus("Inactive"));
	Assert.assertTrue(seatsPage.getTheStatus("Inactive"));
	Assert.assertEquals(stData.get(0),bName);
	log.info("Seat name asserted in create seat page :"+ stData.get(0));
	Assert.assertEquals(stData.get(1),bType);
	log.info("Seat type asserted in create seat page :"+ stData.get(1));
	Assert.assertEquals(stData.get(2),eDSP);
	log.info("Seat DSP asserted in create seat page :"+ stData.get(2));
	Assert.assertEquals(stData.get(3),eToken);
	log.info("Seat Tocken asserted in create seat page :"+ stData.get(3));
	Assert.assertEquals(stData.get(4),cName);
	log.info("Seat Corporation name asserted in create seat page :"+ stData.get(4));
	Assert.assertEquals(stData.get(5),eWebsite);
	log.info("Seat website asserted in create seat page :"+ stData.get(5));
	Assert.assertEquals(stData.get(6),eAddressOne);
	log.info("Seat address one asserted in create seat page :"+ stData.get(6));
	
	 js.executeScript("arguments[0].scrollIntoView();", seatsPage.enterAuthorId);
	Assert.assertEquals(stData.get(7),eAddressTwo);
	log.info("Seat address two asserted in create seat page :"+ stData.get(7));
	Assert.assertEquals(stData.get(8),eCity);
	log.info("Seat name asserted in create seat page :"+ stData.get(8));
	Assert.assertEquals(stData.get(9),pCode);
	log.info("Seat postal code asserted in create seat page :"+ stData.get(9));
	log.info("Seat country asserted in create seat page :"+ stData.get(10));
	Assert.assertEquals(stData.get(10),eCountry);
	log.info("Seat currency asserted in create seat page :"+ stData.get(11));
	Assert.assertEquals(stData.get(11),eCurrency);
	log.info("Seat author id asserted in create seat page :"+ stData.get(12));
	Assert.assertEquals(stData.get(12),eAuthorId);
	log.info("Seat email asserted in create seat page :"+ stData.get(13));
	Assert.assertEquals(stData.get(13),cEmail);
	log.info("Create seat page has been closed ");
	seatsPage.closeCreateSeatPage();
}

@Then("^Validate the created seat data reflects in the Inactive tab\\.$")
public void validate_the_created_seat_data_reflects_in_the_Inactive_tab() throws Throwable {
	try {
		Thread.sleep(1000);
		log.info("Clicked on activate tab : "+seatId);
		seatsPage.clickTabActiveInactivePaused("Inactive");
	
	    stTable=seatsPage.getParticularSeatRowData(seatId);
	    log.info("Seat name asserted in activate tab :"+ stTable.get(1).getText());
		Assert.assertEquals(stTable.get(1).getText(),bName);
		log.info("Seat name asserted in activate tab :"+ stTable.get(2).getText());
		Assert.assertEquals(stTable.get(2).getText(),bType);
		log.info("Seat name asserted in activate tab :"+ stTable.get(3).getText());
		Assert.assertEquals(stTable.get(3).getText(),eCountry);
		
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			System.out.println("There is no seats available in the seat page");
			
		
		}
}
//=============================================================================================================
//Verify if user is able to create the disabled Seat with admin role.
@Then("^Fill the disabled create seat page \\.$")
public void fill_the_disabled_create_seat_page() throws Throwable {
	stData=seatsPage.getTestData();
	bName =stData.get(0);
	bType=stData.get(1);
	eDSP=stData.get(2);
	eToken=stData.get(3);
	cName=stData.get(4);
	eWebsite=stData.get(5); 
	eAddressOne =stData.get(6);
	eAddressTwo =stData.get(7);
	eCity=stData.get(8);
	pCode=stData.get(9);
	eCountry =stData.get(10);
	eCurrency=stData.get(11);
	eAuthorId=stData.get(12);
	cEmail =stData.get(13);
	seatsPage.enterVlauesToCreateSeats(bName, bType, "Disabled", eDSP, eToken, cName, eWebsite, eAddressOne, eAddressTwo, eCity, pCode, eCountry, eCurrency, eAuthorId, cEmail);
	
}

@Then("^Navigate to disabled create seat page and validate the filled data\\.$")
public void navigate_to_disabled_create_seat_page_and_validate_the_filled_data() throws Throwable {
	seatsPage.clickTabActiveInactivePaused("Paused");
	log.info("Click on paused tab");
	seatId=seatsPage.getTheSeatIdandClickCheckBox(bName);
	seatsPage.updateTheSeatIdInTestData("desabledSeatID",seatId);//Added
	log.info("Click on created seat check box in the table :"+seatId);
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Edit");
	log.info("Click on edit seat");
	stData.clear();
	stData=seatsPage.getTheValueFrmCreateSeatPage();
	log.info("Inactive Seat name asserted in create seat page :"+ seatsPage.getTheStatus("Disabled"));
	Assert.assertTrue(seatsPage.getTheStatus("Disabled"));
	Assert.assertEquals(stData.get(0),bName);
	log.info("Seat name asserted in create seat page :"+ stData.get(0));
	Assert.assertEquals(stData.get(1),bType);
	log.info("Seat type asserted in create seat page :"+ stData.get(1));
	Assert.assertEquals(stData.get(2),eDSP);
	log.info("Seat DSP asserted in create seat page :"+ stData.get(2));
	Assert.assertEquals(stData.get(3),eToken);
	log.info("Seat Tocken asserted in create seat page :"+ stData.get(3));
	Assert.assertEquals(stData.get(4),cName);
	log.info("Seat Corporation name asserted in create seat page :"+ stData.get(4));
	Assert.assertEquals(stData.get(5),eWebsite);
	log.info("Seat website asserted in create seat page :"+ stData.get(5));
	Assert.assertEquals(stData.get(6),eAddressOne);
	log.info("Seat address one asserted in create seat page :"+ stData.get(6));
//	JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView();", seatsPage.enterAuthorId);
	Assert.assertEquals(stData.get(7),eAddressTwo);
	log.info("Seat address two asserted in create seat page :"+ stData.get(7));
	Assert.assertEquals(stData.get(8),eCity);
	log.info("Seat name asserted in create seat page :"+ stData.get(8));
	Assert.assertEquals(stData.get(9),pCode);
	log.info("Seat postal code asserted in create seat page :"+ stData.get(9));
	log.info("Seat country asserted in create seat page :"+ stData.get(10));
	Assert.assertEquals(stData.get(10),eCountry);
	log.info("Seat currency asserted in create seat page :"+ stData.get(11));
	Assert.assertEquals(stData.get(11),eCurrency);
	log.info("Seat author id asserted in create seat page :"+ stData.get(12));
	Assert.assertEquals(stData.get(12),eAuthorId);
	log.info("Seat email asserted in create seat page :"+ stData.get(13));
	Assert.assertEquals(stData.get(13),cEmail);
	log.info("Create seat page has been closed ");
	seatsPage.closeCreateSeatPage();
}

@Then("^Validate the created seat data reflects in the disabled tab\\.$")
public void validate_the_created_seat_data_reflects_in_the_disabled_tab() throws Throwable {
	try {
		Thread.sleep(1000);
		log.info("Clicked on activate tab : "+seatId);
		seatsPage.clickTabActiveInactivePaused("Paused");
	
	    stTable=seatsPage.getParticularSeatRowData(seatId);
	    log.info("Seat name asserted in activate tab :"+ stTable.get(1).getText());
		Assert.assertEquals(stTable.get(1).getText(),bName);
		log.info("Seat name asserted in activate tab :"+ stTable.get(2).getText());
		Assert.assertEquals(stTable.get(2).getText(),bType);
		log.info("Seat name asserted in activate tab :"+ stTable.get(3).getText());
		Assert.assertEquals(stTable.get(3).getText(),eCountry);
		
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			System.out.println("There is no seats available in the seat page");
			
		
		}
}
//==============================================================================================================
//Verify if user is able to edit the Active Seat details with admin role.

@When("^Select the existing Active seat and click on edit\\.$")
public void select_the_existing_seat_and_click_on_edit() throws Throwable {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Active");
	log.info("Click on Active tab");
//	seatId=seatsPage.selectSeatandRetunID();Commented
	seatId=seatsPage.selectSeatforEdit(prop.getProperty("activeSeatID"));//Added
	System.out.println("Seat ID select :"+ seatId);
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Edit");
	log.info("Click on Edit seat");
	log.info("Delete all the field values");
	seatsPage.deleteFieldValues();
	
}

@Then("^Navigate to Active create seat and validate the filled data\\.$")
public void navigate_to_Active_create_seat_and_validate_the_filled_data() throws Throwable {
	seatsPage.clickTabActiveInactivePaused("Active");
	log.info("Click on Active tab");
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Edit");
	log.info("Click on edit seat");
	stData.clear();
	stData=seatsPage.getTheValueFrmCreateSeatPage();
	log.info("Active Seat name asserted in create seat page :"+ seatsPage.getTheStatus("Active"));
	Assert.assertTrue(seatsPage.getTheStatus("Active"));
	Assert.assertEquals(stData.get(0),bName);
	log.info("Seat name asserted in create seat page :"+ stData.get(0));
	Assert.assertEquals(stData.get(1),bType);
	log.info("Seat type asserted in create seat page :"+ stData.get(1));
	Assert.assertEquals(stData.get(2),eDSP);
	log.info("Seat DSP asserted in create seat page :"+ stData.get(2));
	Assert.assertEquals(stData.get(3),eToken);
	log.info("Seat Tocken asserted in create seat page :"+ stData.get(3));
	Assert.assertEquals(stData.get(4),cName);
	log.info("Seat Corporation name asserted in create seat page :"+ stData.get(4));
	Assert.assertEquals(stData.get(5),eWebsite);
	log.info("Seat website asserted in create seat page :"+ stData.get(5));
	Assert.assertEquals(stData.get(6),eAddressOne);
//	JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView();", seatsPage.enterAuthorId);
	log.info("Seat address one asserted in create seat page :"+ stData.get(6));
	Assert.assertEquals(stData.get(7),eAddressTwo);
	log.info("Seat address two asserted in create seat page :"+ stData.get(7));
	Assert.assertEquals(stData.get(8),eCity);
	log.info("Seat name asserted in create seat page :"+ stData.get(8));
	Assert.assertEquals(stData.get(9),pCode);
	log.info("Seat postal code asserted in create seat page :"+ stData.get(9));
	log.info("Seat country asserted in create seat page :"+ stData.get(10));
	Assert.assertEquals(stData.get(10),eCountry);
	log.info("Seat currency asserted in create seat page :"+ stData.get(11));
	Assert.assertEquals(stData.get(11),eCurrency);
	log.info("Seat author id asserted in create seat page :"+ stData.get(12));
	Assert.assertEquals(stData.get(12),eAuthorId);
	log.info("Seat email asserted in create seat page :"+ stData.get(13));
	Assert.assertEquals(stData.get(13),cEmail);
	log.info("Create seat page has been closed ");
	seatsPage.closeCreateSeatPage();
}

//==============================================================================================================
//Verify if user is able to edit the Inactive Seat details with admin role.
@When("^Select the existing Inactive seat and click on edit\\.$")
public void select_the_existing_Inactive_seat_and_click_on_edit() throws Throwable {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Inactive");
	log.info("Click on Inactive tab");
//	seatId=seatsPage.selectSeatandRetunID();Commented
	seatId=seatsPage.selectSeatforEdit(prop.getProperty("inActiveSeatID"));//Added
	System.out.println("Seat ID select :"+ seatId);
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Edit");
	log.info("Click on Edit seat");
	log.info("Delete all the field values");
	seatsPage.deleteFieldValues();
	
}

@Then("^Navigate to Inactive create seat and validate the filled data\\.$")
public void navigate_to_inactive_create_seat_and_validate_the_filled_data() throws Throwable {
	seatsPage.clickTabActiveInactivePaused("Inactive");
	log.info("Click on Inactive tab");
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Edit");
	log.info("Click on edit seat");
	stData.clear();
	stData=seatsPage.getTheValueFrmCreateSeatPage();
	log.info("Active Seat name asserted in create seat page :"+ seatsPage.getTheStatus("Inactive"));
	Assert.assertTrue(seatsPage.getTheStatus("Inactive"));
	Assert.assertEquals(stData.get(0),bName);
	log.info("Seat name asserted in create seat page :"+ stData.get(0));
	Assert.assertEquals(stData.get(1),bType);
	log.info("Seat type asserted in create seat page :"+ stData.get(1));
	Assert.assertEquals(stData.get(2),eDSP);
	log.info("Seat DSP asserted in create seat page :"+ stData.get(2));
	Assert.assertEquals(stData.get(3),eToken);
	log.info("Seat Tocken asserted in create seat page :"+ stData.get(3));
	Assert.assertEquals(stData.get(4),cName);
	log.info("Seat Corporation name asserted in create seat page :"+ stData.get(4));
	Assert.assertEquals(stData.get(5),eWebsite);
	log.info("Seat website asserted in create seat page :"+ stData.get(5));
	Assert.assertEquals(stData.get(6),eAddressOne);
//	JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView();", seatsPage.enterAuthorId);
	log.info("Seat address one asserted in create seat page :"+ stData.get(6));
	Assert.assertEquals(stData.get(7),eAddressTwo);
	log.info("Seat address two asserted in create seat page :"+ stData.get(7));
	Assert.assertEquals(stData.get(8),eCity);
	log.info("Seat name asserted in create seat page :"+ stData.get(8));
	Assert.assertEquals(stData.get(9),pCode);
	log.info("Seat postal code asserted in create seat page :"+ stData.get(9));
	log.info("Seat country asserted in create seat page :"+ stData.get(10));
	Assert.assertEquals(stData.get(10),eCountry);
	log.info("Seat currency asserted in create seat page :"+ stData.get(11));
	Assert.assertEquals(stData.get(11),eCurrency);
	log.info("Seat author id asserted in create seat page :"+ stData.get(12));
	Assert.assertEquals(stData.get(12),eAuthorId);
	log.info("Seat email asserted in create seat page :"+ stData.get(13));
	Assert.assertEquals(stData.get(13),cEmail);
	log.info("Create seat page has been closed ");
	seatsPage.closeCreateSeatPage();
}
//==============================================================================================================
//Verify if user is able to edit the Disabled Seat details with admin role.
@When("^Select the existing disabled seat and click on edit\\.$")
public void select_the_existing_disabled_seat_and_click_on_edit() throws Throwable {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Paused");
	log.info("Click on disabled or Paused tab");
//	seatId=seatsPage.selectSeatandRetunID();commented
	seatId=seatsPage.selectSeatforEdit(prop.getProperty("desabledSeatID"));//Added
	System.out.println("Seat ID select :"+ seatId);
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Edit");
	log.info("Click on Edit seat");
	log.info("Delete all the field values");
	seatsPage.deleteFieldValues();
	
}

@Then("^Navigate to disabled create seat and validate the filled data\\.$")
public void navigate_to_disabled_create_seat_and_validate_the_filled_data() throws Throwable {
	seatsPage.clickTabActiveInactivePaused("Paused");
	log.info("Click on Inactive tab");
	seatsPage.clickButtonCreateEditActiveDeactivePaused("Edit");
	log.info("Click on edit seat");
	stData.clear();
	stData=seatsPage.getTheValueFrmCreateSeatPage();
	log.info("Active Seat name asserted in create seat page :"+ seatsPage.getTheStatus("Disabled"));
	Assert.assertTrue(seatsPage.getTheStatus("Disabled"));
	Assert.assertEquals(stData.get(0),bName);
	log.info("Seat name asserted in create seat page :"+ stData.get(0));
	Assert.assertEquals(stData.get(1),bType);
	log.info("Seat type asserted in create seat page :"+ stData.get(1));
	Assert.assertEquals(stData.get(2),eDSP);
	log.info("Seat DSP asserted in create seat page :"+ stData.get(2));
	Assert.assertEquals(stData.get(3),eToken);
	log.info("Seat Tocken asserted in create seat page :"+ stData.get(3));
	Assert.assertEquals(stData.get(4),cName);
	log.info("Seat Corporation name asserted in create seat page :"+ stData.get(4));
	Assert.assertEquals(stData.get(5),eWebsite);
	log.info("Seat website asserted in create seat page :"+ stData.get(5));
	Assert.assertEquals(stData.get(6),eAddressOne);
//	JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView();", seatsPage.enterAuthorId);
	log.info("Seat address one asserted in create seat page :"+ stData.get(6));
	Assert.assertEquals(stData.get(7),eAddressTwo);
	log.info("Seat address two asserted in create seat page :"+ stData.get(7));
	Assert.assertEquals(stData.get(8),eCity);
	log.info("Seat name asserted in create seat page :"+ stData.get(8));
	Assert.assertEquals(stData.get(9),pCode);
	log.info("Seat postal code asserted in create seat page :"+ stData.get(9));
	log.info("Seat country asserted in create seat page :"+ stData.get(10));
	Assert.assertEquals(stData.get(10),eCountry);
	log.info("Seat currency asserted in create seat page :"+ stData.get(11));
	Assert.assertEquals(stData.get(11),eCurrency);
	log.info("Seat author id asserted in create seat page :"+ stData.get(12));
	Assert.assertEquals(stData.get(12),eAuthorId);
	log.info("Seat email asserted in create seat page :"+ stData.get(13));
	Assert.assertEquals(stData.get(13),cEmail);
	log.info("Create seat page has been closed ");
	seatsPage.closeCreateSeatPage();
}


}
