package stepDefinitions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import RXBaseClass.RXBaseClass;
import RXPages.PublisherListPage;
import RXPages.RXAdspotsPage;
import RXPages.RXNavOptions;
import RXUtitities.RXUtile;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AdspotsPageStepDefinition extends RXBaseClass    {
	
	RXAdspotsPage adspotsPage;
	RXNavOptions navOptions;
	PublisherListPage pubListPgs;
	Logger log = Logger.getLogger(AdspotsPageStepDefinition.class);
	
	public AdspotsPageStepDefinition()
	{
		super();
		adspotsPage = new RXAdspotsPage();
		navOptions = new RXNavOptions();
		pubListPgs = new PublisherListPage();
		
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
//=========================================================================================================	
	//Verify if user is displayed with media list page on clicking media navigation link 

@When("^Click on Adspots option under Inventory$")
public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub menu option under Inventory main menu :"+ pubListPgs.logodisplayed());
		 Assert.assertTrue(pubListPgs.logodisplayed());
		 navOptions.expandInventory();
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOf(navOptions.adspotsUndrInventory));
			 navOptions.adspotsUndrInventory.click(); 
		 

	}

@Then("^User displayed with Adspots page$")
public void user_displayed_with_seats_page() throws Throwable {
	Assert.assertEquals(adspotsPage.getPageHeading(), adspotsPage.adspotsHeaderStr);
	log.info("Adspots Page Header is asserted  and it is : "+ adspotsPage.getPageHeading() );

}
//=========================================================================================================
// Verify search functionality on the adspots overview page
@Then("^Verify the search functionality with the following adspot names$")
public void verifySearch(DataTable dt) throws InterruptedException {
	List<Map<String, String>> list = dt.asMaps(String.class, String.class);
	for(int i=0; i<list.size(); i++) {
		String adspotName = list.get(i).get("AdspotName");
		adspotsPage.searchAdspots(adspotName);
		WebDriverWait wait = new WebDriverWait(driver,45);
		WebElement elem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]"))));
		if(elem.getText().equals("No data available")) {
			log.info("The searched adspot named as " +adspotName+ "is not available");	
			Assert.assertTrue(true, "The searched element is not available, but it is not matching with the shown message");
		}else {
			 List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("AdSpot Name");
		     for(int j=0;j<coulmnData.size();j++) {
		    	 Assert.assertEquals(coulmnData.get(j).getText().trim(), adspotName);
				}
		}
		
	}
}

//Verify enabling abd disabling of an adspot from the overview page

@When("^Verify enabling and disabling of an adspot from the overview page$")
public void verifyHEnableDiableAdspot() throws InterruptedException {
	for(int i=0;i<=1;i++) {
	driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]/div//i")).click();
	List<WebElement> coulmnData = navOptions.getColumnDataMatchingHeader("Active/Inactive");
	String status = coulmnData.get(0).getText();
	switch(status) {
	  case "Active":
		     Assert.assertTrue(adspotsPage.overviewEditbutton.isDisplayed());
		     Assert.assertTrue(adspotsPage.overviewDisablebutton.isDisplayed());
		     adspotsPage.clickOverViewDisablebutton();
		     Thread.sleep(3000);
		     List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader("Active/Inactive");
		     Assert.assertEquals(coulmnData1.get(0).getText(), "Inactive");
			break;
	  case "Inactive":
		  Assert.assertTrue(adspotsPage.overviewEditbutton.isDisplayed());
		     Assert.assertTrue(adspotsPage.overviewEnablebutton.isDisplayed());
		     String enableText = adspotsPage.overviewEnablebutton.getText().replaceAll("\\s", "");
		     Assert.assertEquals(enableText, "ENABLEADSPOT");
		     adspotsPage.clickOverViewEnablebutton();
		     Thread.sleep(3000);
		     List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader("Active/Inactive");
		     Assert.assertEquals(coulmnData2.get(0).getText(), "Active");
			break;
	   
	  default:
	    Assert.assertTrue(false, "The status fields supplied does not match with the input");
	
    }
	}
}

//Verify sorting of the table list columns
@Then("^Verify the sorting functionality with the following columns$")
public void verifySort(DataTable dt) throws InterruptedException, ParseException {
	WebDriverWait wait = new WebDriverWait(driver,45);
	driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//thead//th/span[text()='ID']/parent::th")).click();
	WebElement elem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]"))));
	List dataInEachColumn;
	List dataInEachColumnSorted = new ArrayList();
	List<Map<String, String>> list = dt.asMaps(String.class, String.class);
	for(int i=0; i<list.size(); i++) {
		String columnName = list.get(i).get("ColumnName");
		String sortType = list.get(i).get("SortType");
		driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//thead//th/span[text()='"+columnName+"']/parent::th")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]"))));
		if(sortType.equalsIgnoreCase("Desc")) {
		driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//thead//th/span[text()='"+columnName+"']/parent::th")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//tbody/tr[1]/td[1]"))));
		}
		Thread.sleep(5000);
		String sorting = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']//thead//th/span[text()='"+columnName+"']/parent::th")).getAttribute("aria-sort");
		switch(sorting) {
		  case "ascending":
			    
			    List<WebElement> coulmnData1 = navOptions.getColumnDataMatchingHeader(columnName);
			    dataInEachColumnSorted = returnListOfColumnData(coulmnData1, columnName);
			    dataInEachColumn = new ArrayList(dataInEachColumnSorted);
			    System.out.println("Before sorting: " + dataInEachColumn);
			    Collections.sort(dataInEachColumnSorted);
			    System.out.println("After sorting: " + dataInEachColumnSorted);
			    Assert.assertEquals(dataInEachColumn,dataInEachColumnSorted);
				break;
		  case "descending":
			    List<WebElement> coulmnData2 = navOptions.getColumnDataMatchingHeader(columnName);
			    dataInEachColumnSorted = returnListOfColumnData(coulmnData2, columnName);
			    dataInEachColumn = new ArrayList(dataInEachColumnSorted);
			    System.out.println("Before sorting: " + dataInEachColumn);
			    Collections.sort(dataInEachColumnSorted);
			    Collections.reverse(dataInEachColumnSorted);
			    System.out.println("After sorting: " + dataInEachColumnSorted);
			    Assert.assertEquals(dataInEachColumn,dataInEachColumnSorted);
				break;
		   
		  default:
		    Assert.assertTrue(false, "The status fields supplied does not match with the input");
		
	    }
		
		
		
		
	}
}

public List returnListOfColumnData(List<WebElement> coulmnData, String columnName) throws ParseException {
	List dataInEachColumn = new ArrayList();
	for(int j=0;j<coulmnData.size();j++) {
   	 if(columnName.contains("ID")) {
   		 dataInEachColumn.add(Integer.parseInt(coulmnData.get(j).getText()));
   	 }
//   	 else if(columnName.contains("Date")) {
//   		 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//   		 dataInEachColumn.add(dateFormatter.parse(coulmnData.get(j).getText()));
//   	 }
   	 else {
   		 dataInEachColumn.add(coulmnData.get(j).getText()); 
   	 }
   	
		}
	return dataInEachColumn;
	
}

}
