package stepDefinitions;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
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

public class SeatsPageStepsDefinition extends RXBaseClass   {
	
	RXSeatsPage seatsPage;
	Logger log = Logger.getLogger(ProfileStepDefinition.class);
	
	public SeatsPageStepsDefinition()
	{
		super();
		seatsPage = new RXSeatsPage();
		
	}
	
	// Variable used 
	static String strmisc;
	static int seatId;
	static String seatName;
	static String seatType;
	static String seatRegion;
	static ArrayList<WebElement> seatTable;
	static ArrayList<WebElement> stTable;
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
public void select_the_active_seat_for_modify() {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Active");
	log.info("Clicked on Active tab");
	try {
	seatId=seatsPage.selectSeatandRetunID();
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
public void select_the_Inactive_seat_for_modify() {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Inactive");
	log.info("Clicked on Inactive tab");
	try {
	seatId=seatsPage.selectSeatandRetunID();
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
@Then("^modify the selected seat Disabled or paused$")
public void modify_the_selected_seat_Disabled_or_paused() throws InterruptedException {
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
public void select_the_Disabled_or_paused_seat_for_modify() {
	seatsPage.clickOnSeatOptionUnAcc();
	log.info("Clicked on Seats option under Account");
	seatsPage.clickTabActiveInactivePaused("Paused");
	log.info("Clicked on Paused tab");
	try {
	seatId=seatsPage.selectSeatandRetunID();
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



}
