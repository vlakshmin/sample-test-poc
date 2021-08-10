package MyRunner;


import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;



@CucumberOptions(


features = {"src/main/java/Features/Targeting_regression.feature",
    "src/main/java/Features/DemandSources_regression.feature",
    "src/main/java/Features/PrivateAuction_regression.feature",
    "src/main/java/Features/RXNavOptions.feature",
    "src/main/java/Features/Adspots_regression.feature",
    "src/main/java/Features/Deals_regression.feature",
    "src/main/java/Features/Login.feature",
    "src/main/java/Features/RXPublishers.feature",
    "src/main/java/Features/UserRoles.feature",
    "src/main/java/Features/Protections_regression.feature},
        glue = {"stepDefinitions"},
        tags = {"~@Ignore"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
        		"json:target/cucumber-reports/CucumberTestReport.json",
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        })

public class TestRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
 
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
 
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
 
    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }
 
    @AfterSuite(alwaysRun = true)
    public void tearDownClass() throws Exception {
    	
        testNGCucumberRunner.finish();
    }
    
    @AfterClass
    public static void extentReportGen() throws Exception {
    Reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
    }
}
