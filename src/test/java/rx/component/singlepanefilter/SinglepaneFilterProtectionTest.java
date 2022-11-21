package rx.component.singlepanefilter;

import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.protections.ProtectionsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature(value = "Checking Singlepane Filter Component in Protections Tab")
public class SinglepaneFilterProtectionTest extends BaseTest {
    private ProtectionsPage protectionPage;

    public SinglepaneFilterProtectionTest() {
        protectionPage = new ProtectionsPage();
    }

    @BeforeClass
    private void login(){

        testStart()
                .given()
                .openDirectPath(Path.PROTECTIONS)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, protectionPage.getNuxtProgress())
                .testEnd();
    }

    @Test
    public void filterByPublisher(){
        var filter = protectionPage.getProtectionsTable().getFilterOptions();

        testStart()
                .and("")
                .clickOnWebElement(filter.getColumnFiltersButton())
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.PUBLISHER))
                .setValueWithClean(filter.getSinglepane().getSearchInput(), "Viber")
                .clickOnWebElement(filter.getSinglepane().getFilterItemByName("Viber").getName())
                .setValueWithClean(filter.getSinglepane().getSearchInput(), "Viki")
                //TODO waiting search result
                .waitAndValidate(disappear, protectionPage.getTableProgressBar())
                .clickOnWebElement(filter.getSinglepane().getFilterItemByName("Viki").getName())

                .clickOnWebElement(filter.getSinglepane().getSubmitButton())
                .testEnd();

    }

    @AfterClass
    private void logout(){
        testStart()
                .logOut()
                .testEnd();
    }
}
