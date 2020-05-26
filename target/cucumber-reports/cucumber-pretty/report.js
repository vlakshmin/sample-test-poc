$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/main/java/Features/login.feature");
formatter.feature({
  "line": 1,
  "name": "RX Launch Feature",
  "description": "",
  "id": "rx-launch-feature",
  "keyword": "Feature"
});
formatter.before({
  "duration": 11123866961,
  "status": "passed"
});
formatter.scenario({
  "line": 2,
  "name": "Verify if an admin user will be able to login with a valid username and valid password",
  "description": "",
  "id": "rx-launch-feature;verify-if-an-admin-user-will-be-able-to-login-with-a-valid-username-and-valid-password",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 3,
  "name": "Admin user click on Login by entering valid username and password",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "login should be successful and user is displayed the publisher page with Rakuten Exchange",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDefinition.admin_user_click_on_Login_by_entering_valid_username_and_password()"
});
formatter.result({
  "duration": 726110130,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.user_is_displayed_the_publisher_page_with_Rakuten_Exchange()"
});
formatter.result({
  "duration": 5049913860,
  "status": "passed"
});
formatter.after({
  "duration": 647892677,
  "status": "passed"
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 5,
      "value": "#\tThen Close the browser"
    }
  ],
  "line": 7,
  "name": "Verify if an admin user is not allowed to login with a invalid credential",
  "description": "",
  "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 8,
  "name": "Admin user click on Login by entering \"\u003cusername\u003e\" \u0026 \"\u003cpassword\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "login should not be success",
  "keyword": "Then "
});
formatter.examples({
  "line": 11,
  "name": "",
  "description": "",
  "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ],
      "line": 12,
      "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;1"
    },
    {
      "cells": [
        "",
        ""
      ],
      "line": 13,
      "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;2"
    },
    {
      "cells": [
        "chandrashekhara.av@rakuten.com",
        "Test@123"
      ],
      "line": 14,
      "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;3"
    },
    {
      "cells": [
        "xyz@gmail.com",
        "Password2"
      ],
      "line": 15,
      "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;4"
    },
    {
      "cells": [
        "chandrashekhara.av@rakuten.com",
        ""
      ],
      "line": 16,
      "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;5"
    },
    {
      "cells": [
        "",
        "Password2"
      ],
      "line": 17,
      "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;6"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 8297955783,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Verify if an admin user is not allowed to login with a invalid credential",
  "description": "",
  "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 8,
  "name": "Admin user click on Login by entering \"\" \u0026 \"\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "login should not be success",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "",
      "offset": 39
    },
    {
      "val": "",
      "offset": 44
    }
  ],
  "location": "LoginStepDefinition.admin_user_click_on_Login_by_entering(String,String)"
});
formatter.result({
  "duration": 204933016,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.login_should_not_be_success()"
});
formatter.result({
  "duration": 57860180,
  "status": "passed"
});
formatter.after({
  "duration": 1558611592,
  "status": "passed"
});
formatter.before({
  "duration": 8108074135,
  "status": "passed"
});
formatter.scenario({
  "line": 14,
  "name": "Verify if an admin user is not allowed to login with a invalid credential",
  "description": "",
  "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 8,
  "name": "Admin user click on Login by entering \"chandrashekhara.av@rakuten.com\" \u0026 \"Test@123\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "login should not be success",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "chandrashekhara.av@rakuten.com",
      "offset": 39
    },
    {
      "val": "Test@123",
      "offset": 74
    }
  ],
  "location": "LoginStepDefinition.admin_user_click_on_Login_by_entering(String,String)"
});
formatter.result({
  "duration": 477708980,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.login_should_not_be_success()"
});
formatter.result({
  "duration": 44859666,
  "status": "passed"
});
formatter.after({
  "duration": 1510823997,
  "status": "passed"
});
formatter.before({
  "duration": 7403194697,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Verify if an admin user is not allowed to login with a invalid credential",
  "description": "",
  "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;4",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 8,
  "name": "Admin user click on Login by entering \"xyz@gmail.com\" \u0026 \"Password2\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "login should not be success",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "xyz@gmail.com",
      "offset": 39
    },
    {
      "val": "Password2",
      "offset": 57
    }
  ],
  "location": "LoginStepDefinition.admin_user_click_on_Login_by_entering(String,String)"
});
formatter.result({
  "duration": 401713969,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.login_should_not_be_success()"
});
formatter.result({
  "duration": 24489768,
  "status": "passed"
});
formatter.after({
  "duration": 1535467670,
  "status": "passed"
});
formatter.before({
  "duration": 7623967242,
  "status": "passed"
});
formatter.scenario({
  "line": 16,
  "name": "Verify if an admin user is not allowed to login with a invalid credential",
  "description": "",
  "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;5",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 8,
  "name": "Admin user click on Login by entering \"chandrashekhara.av@rakuten.com\" \u0026 \"\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "login should not be success",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "chandrashekhara.av@rakuten.com",
      "offset": 39
    },
    {
      "val": "",
      "offset": 74
    }
  ],
  "location": "LoginStepDefinition.admin_user_click_on_Login_by_entering(String,String)"
});
formatter.result({
  "duration": 427617145,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.login_should_not_be_success()"
});
formatter.result({
  "duration": 55502796,
  "status": "passed"
});
formatter.after({
  "duration": 1598771922,
  "status": "passed"
});
formatter.before({
  "duration": 8134843356,
  "status": "passed"
});
formatter.scenario({
  "line": 17,
  "name": "Verify if an admin user is not allowed to login with a invalid credential",
  "description": "",
  "id": "rx-launch-feature;verify-if-an-admin-user-is-not-allowed-to-login-with-a-invalid-credential;;6",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 8,
  "name": "Admin user click on Login by entering \"\" \u0026 \"Password2\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "login should not be success",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "",
      "offset": 39
    },
    {
      "val": "Password2",
      "offset": 44
    }
  ],
  "location": "LoginStepDefinition.admin_user_click_on_Login_by_entering(String,String)"
});
formatter.result({
  "duration": 309349037,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.login_should_not_be_success()"
});
formatter.result({
  "duration": 23081994,
  "status": "passed"
});
formatter.after({
  "duration": 1583513888,
  "status": "passed"
});
});