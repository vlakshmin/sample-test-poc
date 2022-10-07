package rx.admin.demand;

import api.dto.rx.demandsource.DemandSource;
import api.preconditionbuilders.DemandSourcePrecondition;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.admin.demand.DemandPage;
import rx.BaseTest;
import widgets.admin.demand.sidebar.EditDemandSidebar;
import widgets.admin.publisher.sidebar.CreatePublisherSidebar;
import widgets.admin.publisher.sidebar.EditPublisherSidebar;
import widgets.common.table.ColumnNames;
import zutils.FakerUtils;

import static api.preconditionbuilders.DemandSourcePrecondition.demandSource;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static java.lang.String.valueOf;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class EditDemandSourceTest extends BaseTest {

    private DemandPage demandPage;
    private DemandSource demandSource;
    private EditDemandSidebar editDemandSidebar;

    public EditDemandSourceTest() {
        demandPage = new DemandPage();
        editDemandSidebar = new EditDemandSidebar();
    }

    @BeforeClass
    public void createNewPublisher() {
        //Creating new demand source to edit Using API
        demandSource = createNewDsp();
        login();
    }

    @Step("Login to system")
    private void login(){
        testStart()
                .given("Login to SSP")
                .openDirectPath(Path.DEMAND)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, demandPage.getNuxtProgress())
                .waitAndValidate(disappear, demandPage.getTableProgressBar())
                .testEnd();
    }

    @Test
    public void checkDspSettingsTest() {

        testStart()
                .given("Searching Demand source")
                .setValueWithClean(demandPage.getDemandTable().getTableData().getSearch(),
                        demandSource.getCorp())
                .clickEnterButton(demandPage.getDemandTable().getTableData().getSearch())
                .clickOnTableCellLink(demandPage.getDemandTable().getTableData(), ColumnNames.BIDDER, demandSource.getCorp())
                .then("Wait till Demand Source sidebar will be opened")
                .waitSideBarOpened()
                .then("Validate All Settings of Demand Source sidebar")
                .validateAttribute(editDemandSidebar.getInactiveRadioButton(), "aria-checked", "true")
                .validateAttribute(editDemandSidebar.getActiveRadioButton(), "aria-checked", "false")
                .validateAttribute(editDemandSidebar.getOnboardingRadioButton(), "aria-checked", "false")
                .then("Check inputs value")
                .validateAttribute(editDemandSidebar.getBidderInput(), "value", demandSource.getCorp())
                .validateAttribute(editDemandSidebar.getBidderInput(), "disabled", "true")
                .validateAttribute(editDemandSidebar.getRequestAdjustmentRateField().getRequestAdjustmentRateFieldInput(),
                        "value", valueOf(demandSource.getRequestAdjustmentRate()))
                .then("Check sidebar toggles")
                .validateAttribute(editDemandSidebar.getSyncRequiredToggle(), "aria-checked", "false")
                .validateAttribute(editDemandSidebar.getIdfaRequiredToggle(), "aria-checked", "false")
                .validateAttribute(editDemandSidebar.getTokenGenerationToggle(), "aria-checked", "false")
                .validateAttribute(editDemandSidebar.getPmpSupportToggle(), "aria-checked", "true")
                .validateAttribute(editDemandSidebar.getNonProgrammaticToggle(), "aria-checked", "false")
                .then("Check values of checkBoxes")
                .scrollIntoView(editDemandSidebar.getFormatLabel())
                .validateAttribute(editDemandSidebar.getBannerCheckBox(), "aria-checked", "true")
                .validateAttribute(editDemandSidebar.getNativeCheckBox(), "aria-checked", "true")
                .validateAttribute(editDemandSidebar.getVideoCheckBox(), "aria-checked", "false")
                .validateAttribute(editDemandSidebar.getIosCheckBox(), "aria-checked", "true")
                .validateAttribute(editDemandSidebar.getAndroidCheckBox() ,"aria-checked", "true")
                .validateAttribute(editDemandSidebar.getMobileWebCheckBox(), "aria-checked", "true")
                .validateAttribute(editDemandSidebar.getPcWebCheckBox(), "aria-checked", "true")
                .then("Close sidebar")
                .clickOnWebElement(editDemandSidebar.getCloseIcon())
                .testEnd();
    }

    @AfterClass
    private void logOutAndDeleteData(){
        deleteDsp(demandSource.getId());
        logout();
    }

    @Step("Delete Demand Source")
    private void logout(){
        testStart()
                .logOut()
                .testEnd();
    }

    @Step("Create New Demand Source")
    private DemandSource createNewDsp(){

        return demandSource()
                .createDemandSource()
                .build()
                .getDemandSourceResponse();
    }

    @Step("Delete Demand Source")
    private void deleteDsp(int id){
        demandSource()
                .setCredentials(USER_FOR_DELETION)
                .deleteDemandSource(id)
                .build();
    }
}
