package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;

import RXBaseClass.RXBaseClass;
import RXPages.PublisherListPage;
import RXPages.RXNavOptions;
import RXPages.RXUsers;
import RXUtitities.RXUtile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RXNavOptionStepDefinitions extends RXBaseClass{
	
	RXUsers rxUserPage;
	RXNavOptions rxNavOpt;
	PublisherListPage pubListPgs;
	Logger log = Logger.getLogger(RXNavOptionStepDefinitions.class);
	
	public RXNavOptionStepDefinitions()
	{
		super();
		rxUserPage = new RXUsers();
		rxNavOpt = new RXNavOptions();
		pubListPgs = new PublisherListPage();
		
	}
	
	
	@When("^Check for main menu navigation options\\.$")
	public void check_for_main_menu_navigation_options() throws Throwable {
		 driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		log.info("User logged in to check the navigation option :"+ pubListPgs.logodisplayed());
		 Assert.assertTrue(pubListPgs.logodisplayed());
		
	}

	@Then("^User Info,Dashboard,Admin,Inventory and Rules are displayed as main menu navigation options\\.$")
	public void user_Info_Dashboard_Admin_Inventory_and_Rules_are_displayed_as_main_menu_navigation_options() throws Throwable {
		log.info("User Info main menu displayed "+ rxNavOpt.isUserInfoNavDisplayed());
		Assert.assertTrue(rxNavOpt.isUserInfoNavDisplayed());
		log.info("Dashboard main menu displayed "+ rxNavOpt.isdashBoardDisplayed());
		Assert.assertTrue(rxNavOpt.isdashBoardDisplayed());	
		log.info("Admin main menu displayed "+ rxNavOpt.isAdminNavDisplayed());
		Assert.assertTrue(rxNavOpt.isAdminNavDisplayed());	
		log.info("Inventory main menu displayed "+ rxNavOpt.isInventoryNavDisplayed());
		Assert.assertTrue(rxNavOpt.isInventoryNavDisplayed());	
		log.info("Rules main menu displayed "+ rxNavOpt.isRuleNavDisplayed());
		Assert.assertTrue(rxNavOpt.isRuleNavDisplayed());	
	}
	
	
	@When("^Check for Sub mennu option under Admin main menu\\.$")
	public void check_for_Sub_mennu_option_under_Admin_main_menu() throws Throwable {
		 driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
			log.info("User logged in to check the navigation option for Sub mennu option under Admin main menu :"+ pubListPgs.logodisplayed());
			 Assert.assertTrue(pubListPgs.logodisplayed());
			 if(!rxNavOpt.ispublisherUndrAdminDisplayed())
				{
				 log.info("Click on Admain main menu expansion ");
				 rxNavOpt.expandAdmin();
				}
	}

	@Then("^Publisher,Users,Demand Sources and Buyers are displayed as sub main menu under Admin main menu as navigation options\\.$")
	public void publisher_Users_Demand_Sources_and_Buyers_are_displayed_as_sub_main_menu_under_Admin_main_menu_as_navigation_options() throws Throwable {
		log.info("Publisher sub menu are displaying under Admin main menu : "+ rxNavOpt.ispublisherUndrAdminDisplayed());
		Assert.assertTrue(rxNavOpt.ispublisherUndrAdminDisplayed());
		log.info("Users sub menu are displaying under Admin main menu : "+ rxNavOpt.isusersUndrAdminDisplayed());
		Assert.assertTrue(rxNavOpt.isusersUndrAdminDisplayed());
		log.info("Demand Source sub menu are displaying under Admin main menu : "+ rxNavOpt.isdemandSourcesUndrAdminDisplayed());
		Assert.assertTrue(rxNavOpt.isdemandSourcesUndrAdminDisplayed());
		log.info("Buyer sub menu are displaying under Admin main menu : "+ rxNavOpt.isbuyersUndrAdminDisplayed());
		Assert.assertTrue(rxNavOpt.isbuyersUndrAdminDisplayed());
		
	}
	
	@When("^Check for Sub mennu option under Inventory main menu\\.$")
	public void check_for_Sub_mennu_option_under_Inventory_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub mennu option under Inventory main menu :"+ pubListPgs.logodisplayed());
		 Assert.assertTrue(pubListPgs.logodisplayed());
		 rxNavOpt.expandInventory();

	}

	@Then("^Media and Adspot are displayed as sub main menu under Inventory main menu as navigation options\\.$")
	public void media_and_Adspot_are_displayed_as_sub_main_menu_under_Inventory_main_menu_as_navigation_options() throws Throwable {
		log.info("Media sub menu are displaying under Inventory main menu : "+ rxNavOpt.ismediaUndrInventoryDisplayed());
		Assert.assertTrue(rxNavOpt.ismediaUndrInventoryDisplayed());
		log.info("Adspots sub menu are displaying under Inventory main menu : "+ rxNavOpt.isadspotsUndrInventoryUndrInventoryDisplayed());
		Assert.assertTrue(rxNavOpt.isadspotsUndrInventoryUndrInventoryDisplayed());
	}
	
	
	@When("^Check for Sub mennu option under Rules main menu\\.$")
	public void check_for_Sub_mennu_option_under_Rules_main_menu() throws Throwable {
		log.info("User logged in to check the navigation option for Sub mennu option under Rules main menu :"+ pubListPgs.logodisplayed());
		 Assert.assertTrue(pubListPgs.logodisplayed());
		 rxNavOpt.expandRules();
	}

	@Then("^Filters and Targeting are displayed as sub main menu under Rules main menu as navigation options\\.$")
	public void filters_and_Targeting_are_displayed_as_sub_main_menu_under_Rules_main_menu_as_navigation_options() throws Throwable {
		log.info("Filter sub menu are displaying under Rules main menu : "+ rxNavOpt.isfiltersUndrRulesDisplayed());
		Assert.assertTrue(rxNavOpt.isfiltersUndrRulesDisplayed());
		driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME, TimeUnit.SECONDS);
		log.info("Targeting sub menu are displaying under Rules main menu : "+ rxNavOpt.istargetingUndrRulesDisplayed());
		Assert.assertTrue(rxNavOpt.istargetingUndrRulesDisplayed());
	}


	
	@Then("^User Info,Dashboard,Inventory and Rules are displayed as main menu navigation options\\.$")
	public void user_Info_Dashboard_Inventory_and_Rules_are_displayed_as_main_menu_navigation_options() throws Throwable {
		log.info("User Info main menu displayed "+ rxNavOpt.isUserInfoNavDisplayed());
		Assert.assertTrue(rxNavOpt.isUserInfoNavDisplayed());
		log.info("Dashboard main menu displayed "+ rxNavOpt.isdashBoardDisplayed());
		Assert.assertTrue(rxNavOpt.isdashBoardDisplayed());	
		log.info("Inventory main menu displayed "+ rxNavOpt.isInventoryNavDisplayed());
		Assert.assertTrue(rxNavOpt.isInventoryNavDisplayed());	
		log.info("Rules main menu displayed "+ rxNavOpt.isRuleNavDisplayed());
		Assert.assertTrue(rxNavOpt.isRuleNavDisplayed());	
		
	}
	
	@When("^Check for Sub menu option under Inventory main menu of publisher\\.$")
	public void check_for_Sub_menu_option_under_Inventory_main_menu_of_publisher() throws Throwable {
		
		log.info("User logged in to check the navigation option for Sub mennu option under Publisher main menu :"+ pubListPgs.logodisplayed());
		
			 log.info("Click on Inventory main menu expansion ");
			 rxNavOpt.expandAdmin();
			
	}
	
	
	@When("^Check for Sub menu option under Rules main menu of publisher$")
	public void check_for_Sub_menu_option_under_Rules_main_menu_of_publisher() throws Throwable {
		
		log.info("User logged in to check the navigation option for Sub mennu option under Publisher main menu :"+ pubListPgs.logodisplayed());
		
		Assert.assertTrue(pubListPgs.logodisplayed());
		 rxNavOpt.expandInventory();
	}
}
