package rx.openpricing;

import api.dto.rx.yield.openPricing.OpenPricing;
import api.preconditionbuilders.OpenPricingPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.yield.openpricing.OpenPricingPage;
import widgets.yield.openPricing.sidebar.EditOpenPricingSidebar;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class OpenPricingTest {
    private OpenPricing openPricing;
    private OpenPricingPage openPricingPage;
    private EditOpenPricingSidebar editOpenPricingSidebar;

    public OpenPricingTest() {
        openPricingPage = new OpenPricingPage();
        editOpenPricingSidebar = new EditOpenPricingSidebar();
    }

    @BeforeClass
    public void createOpenPricing() {
        openPricing = OpenPricingPrecondition.openPricing()
                .createNewOpenPricing()
                .build()
                .getOpenPricingResponse();
    }

    @Test
    public void checkPagination() {
        var table = openPricingPage.getOpenPricingTable().getTableOptions();
        var tableData = openPricingPage.getOpenPricingTable().getTableData();
        var tablePagenation = openPricingPage.getOpenPricingTable().getTablePagination();
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, openPricingPage.getNuxtProgress())
                .and()
                .selectFromDropdown(tablePagenation.getPageMenu(), tablePagenation.getRowNumbersList(), "10")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .validateListSize(tableData.getRows(), 10)
                .clickOnWebElement(tablePagenation.getNext())
                .selectFromDropdown(tablePagenation.getPageMenu(), tablePagenation.getRowNumbersList(), "20")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .validateListSize(tableData.getRows(), 20)
                .clickOnWebElement(tablePagenation.getNext())
                .validateListSize(tableData.getRows(), 20)
                .selectFromDropdown(tablePagenation.getPageMenu(), tablePagenation.getRowNumbersList(), "15")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .validateListSize(tableData.getRows(), 15)
                .clickOnWebElement(tablePagenation.getNext())
                .validateListSize(tableData.getRows(), 15)
                .clickOnWebElement(tablePagenation.getPrevious())
                .validateListSize(tableData.getRows(), 15)
                .selectFromDropdown(tablePagenation.getPageMenu(), tablePagenation.getRowNumbersList(), "25")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .validateListSize(tableData.getRows(), 25)
                .clickOnWebElement(tablePagenation.getNext())
                .validateListSize(tableData.getRows(), 25)
                .selectFromDropdown(tablePagenation.getPageMenu(), tablePagenation.getRowNumbersList(), "50")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .validateListSize(tableData.getRows(), 50)
                .selectFromDropdown(tablePagenation.getPageMenu(), tablePagenation.getRowNumbersList(), "100")
                .waitLoading(visible, openPricingPage.getTableProgressBar())
                .waitLoading(disappear, openPricingPage.getTableProgressBar())
                .validateListSize(tableData.getRows(), 100)
                .clickOnWebElement(tablePagenation.getNext())
                .validateListSize(tableData.getRows(), 100)
                .logOut()
                .testEnd();
    }

    @Test
    public void verifyColumnOrder(){

    }



}
