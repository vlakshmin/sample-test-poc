package rx.yield.openpricing;

import api.dto.rx.yield.openpricing.OpenPricing;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.yield.openPricing.sidebar.CreateOpenPricingSidebar;

import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class CreateOpenPricingTest extends BaseTest {

    private OpenPricing openPricing;
    private OpenPricingPage openPricingPage;
    private CreateOpenPricingSidebar createOpenPricingSidebar;

    public CreateOpenPricingTest() {
        openPricingPage = new OpenPricingPage();
        createOpenPricingSidebar = new CreateOpenPricingSidebar();
    }

    @BeforeClass
    @Step("Login ToOpenPricing Page")
    public void loginToOpenPricing() {

        testStart()
                .given("Logging Directly to Open Pricing Page")
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .waitAndValidate(disappear, openPricingPage.getTableProgressBar())
                .testEnd();
    }

    @Test
    public void editOpenPricingTest() {
        var tableData = openPricingPage.getOpenPricingTable().getTableData();

        testStart()
                .given("Opening 'Create New Open Pricing sidebar'")
                .openDirectPath(Path.CREATE_OPEN_PRICING)
                .waitSideBarOpened()
                .then("Enter data to all fields of sidebar")
                .selectFromDropdownByPosition(createOpenPricingSidebar.getPublisherNameDropdown(), createOpenPricingSidebar.getPub1)
                .and()
                .clickOnTableCellLink(tableData, ColumnNames.NAME, openPricing.getName())
                .waitSideBarOpened()
                .validateAttribute(editOpenPricingSidebar.getNameInput(), "value", openPricing.getName())
                .validate(editOpenPricingSidebar.getPublisherInput(), openPricing.getPublisherName())
                .validateAttribute(editOpenPricingSidebar.getFloorPrice(), "value", openPricing.getFloorPrice().toString())

                .clickOnWebElement(editOpenPricingSidebar.getSaveButton())
                .waitSideBarClosed()

                .testEnd();

        //allure serve
    }
    //todo Uncomment it and delete this test
//
//    @Test
//    public void verifySingleDeactivatePublisherActive() {
//        var tableData = openPricingPage.getOpenPricingTable().getTableData();
//        var tableOptions = openPricingPage.getOpenPricingTable().getTableOptions();
//        var tablePagination = openPricingPage.getOpenPricingTable().getTablePagination();
//        testStart()
//                .given()
//                .openDirectPath(Path.OPEN_PRICING)
//                .logIn(TEST_USER)
//                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
//                .and()
//                .scrollIntoView(tableData.getCheckbox(1))
//                //.clickOnWebElement(tableData.getCheckbox(1))
//                //.clickOnWebElement(openPricingPage.getDeactivateButton())
//                .validateContainsText(tableData.getCustomCells(ColumnNames.ACTIVE_INACTIVE).get(0),"Active")
//                .then()
//                .testEnd();
//
//
//    }


}
