$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/main/java/Features/RXNavOptions.feature");
formatter.feature({
  "line": 1,
  "name": "GS-535 navigation menus validation",
  "description": "",
  "id": "gs-535-navigation-menus-validation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 18736706208,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 2,
      "value": "#Scenario: Verify if admin user displayed with User Info ,Dashboard,Admin,Inventory and Rules as main menu."
    },
    {
      "line": 3,
      "value": "#\tGiven admin user login to RX UI with valid username and password"
    },
    {
      "line": 4,
      "value": "#\tWhen Check for main menu navigation options."
    },
    {
      "line": 5,
      "value": "#\tThen User Info,Dashboard,Admin,Inventory and Rules are displayed as main menu navigation options."
    },
    {
      "line": 7,
      "value": "#Scenario: Verify if admin user displayed with submenu Publishers,Users,Demand Sources and Buyers for Admin main menu."
    },
    {
      "line": 8,
      "value": "#\tGiven admin user login to RX UI with valid username and password"
    },
    {
      "line": 9,
      "value": "#\tWhen Check for Sub mennu option under Admin main menu."
    },
    {
      "line": 10,
      "value": "#\tThen Publisher,Users,Demand Sources and Buyers are displayed as sub main menu under Admin main menu as navigation options."
    },
    {
      "line": 12,
      "value": "#Scenario: Verify if admin user displayed with submenu Media and Adspot for Inventory main menu."
    },
    {
      "line": 13,
      "value": "#\tGiven admin user login to RX UI with valid username and password"
    },
    {
      "line": 14,
      "value": "#\tWhen Check for Sub mennu option under Inventory main menu."
    },
    {
      "line": 15,
      "value": "#\tThen Media and Adspot are displayed as sub main menu under Inventory main menu as navigation options."
    },
    {
      "line": 17,
      "value": "#Scenario: Verify if admin user displayed with submenu Filters and Targeting for Rules main menu."
    },
    {
      "line": 18,
      "value": "#\tGiven admin user login to RX UI with valid username and password"
    },
    {
      "line": 19,
      "value": "#\tWhen Check for Sub mennu option under Rules main menu."
    },
    {
      "line": 20,
      "value": "#\tThen Filters and Targeting are displayed as sub main menu under Rules main menu as navigation options."
    },
    {
      "line": 22,
      "value": "#Scenario: Verify if Publisher user displayed with User Info ,Dashboard,Inventory and Rules as main menu."
    },
    {
      "line": 23,
      "value": "#\tGiven Publisher user login to RX UI with valid username and password"
    },
    {
      "line": 24,
      "value": "#\tWhen Check for main menu navigation options."
    },
    {
      "line": 25,
      "value": "#\tThen User Info,Dashboard,Inventory and Rules are displayed as main menu navigation options."
    },
    {
      "line": 27,
      "value": "#Scenario: Verify if publisher user displayed with submenu Media and Adspot for Inventory main menu."
    },
    {
      "line": 28,
      "value": "#\tGiven Publisher user login to RX UI with valid username and password"
    },
    {
      "line": 29,
      "value": "#\tWhen Check for Sub menu option under Inventory main menu of publisher."
    },
    {
      "line": 30,
      "value": "#\tThen Media and Adspot are displayed as sub main menu under Inventory main menu as navigation options."
    }
  ],
  "line": 32,
  "name": "Verify if publisher user displayed with submenu Filters and Targeting for Rules main menu.",
  "description": "",
  "id": "gs-535-navigation-menus-validation;verify-if-publisher-user-displayed-with-submenu-filters-and-targeting-for-rules-main-menu.",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 33,
  "name": "Publisher user login to RX UI with valid username and password",
  "keyword": "Given "
});
formatter.step({
  "line": 34,
  "name": "Check for Sub menu option under Rules main menu of publisher",
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "Filters and Targeting are displayed as sub main menu under Rules main menu as navigation options.",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDefinition.publisher_user_login_to_RX_UI_with_valid_username_and_password()"
});
formatter.result({
  "duration": 1902703847,
  "status": "passed"
});
formatter.match({
  "location": "RXNavOptionStepDefinitions.check_for_Sub_menu_option_under_Rules_main_menu_of_publisher()"
});
formatter.result({
  "duration": 5582656103,
  "status": "passed"
});
formatter.match({
  "location": "RXNavOptionStepDefinitions.filters_and_Targeting_are_displayed_as_sub_main_menu_under_Rules_main_menu_as_navigation_options()"
});
formatter.result({
  "duration": 211757732,
  "status": "passed"
});
formatter.after({
  "duration": 2959549480,
  "status": "passed"
});
});