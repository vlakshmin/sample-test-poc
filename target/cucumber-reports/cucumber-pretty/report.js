$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/main/java/Features/RXDashboard.feature");
formatter.feature({
  "line": 1,
  "name": "RX Dashboard page Validation",
  "description": "",
  "id": "rx-dashboard-page-validation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 3858291046,
  "status": "passed"
});
formatter.scenario({
  "line": 33,
  "name": "Verify today date or further can\u0027t be selected",
  "description": "",
  "id": "rx-dashboard-page-validation;verify-today-date-or-further-can\u0027t-be-selected",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 34,
  "name": "Admin user login by entering valid username and password",
  "keyword": "Given "
});
formatter.step({
  "line": 35,
  "name": "Click on Dashboard option in Menu",
  "keyword": "When "
});
formatter.step({
  "line": 36,
  "name": "Change Publisher selection to unexisting publisher",
  "keyword": "Then "
});
formatter.match({
  "location": "DashboardStepDefinition.admin_login_by_entering_valid_username_and_password()"
});
formatter.result({
  "duration": 726519867,
  "status": "passed"
});
formatter.match({
  "location": "DashboardStepDefinition.check_for_Dashboard_navigation_option_in_main_menu()"
});
formatter.result({
  "duration": 1477499218,
  "status": "passed"
});
formatter.match({
  "location": "DashboardStepDefinition.check_change_publisher_to_unexisting()"
});
formatter.result({
  "duration": 1401940455,
  "status": "passed"
});
formatter.after({
  "duration": 737390653,
  "status": "passed"
});
});