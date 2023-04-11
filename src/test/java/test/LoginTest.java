package test;

//import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

//import static io.qameta.allure.SeverityLevel.BLOCKER;
//import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.assertEquals;
import static util.driver.DriverHolder.getDriver;

//@Epic("User Management")
//@Feature("Login")
public class LoginTest extends BaseTest {

    private HomePage homepage;

    @BeforeMethod
    public void loginBeforeMethod() {
        homepage = new HomePage(getDriver());
    }

    @Test(description = "Click Trials And Demo")
    //@Description("Click Trials and Demo")
    public void testValidLogin() {
        assertEquals(getDriver().getTitle(), "Automated Software Testing Tools | Tricentis");
    }
}
