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

//    private static final String PUBLISHER_NAME_EDITED = FakerUtils.captionWithSuffix("Pub_Auto_Edited");
//    private static final String PUBLISHER_AD_OPS_EDITED = FakerUtils.captionWithSuffix("Ad_Ops_Edited");

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
                .testEnd();
    }

    @Test
    public void checkDspSettingsTest() {

        testStart()
                .given("Open newly created Demand Source")
                .openDirectPath(Path.DEMAND)
                .waitAndValidate(disappear, demandPage.getTableProgressBar())
                .when("Searching Demand source")
                .setValueWithClean(demandPage.getDemandTable().getTableData().getSearch(),
                        demandSource.getCorp())
                .clickEnterButton(demandPage.getDemandTable().getTableData().getSearch())
                .clickOnTableCellLink(demandPage.getDemandTable().getTableData(), ColumnNames.BIDDER, demandSource.getCorp())
                .waitSideBarOpened()
                .then("Validate Settings of Demand Source")
                .validateAttribute(editDemandSidebar.getInactiveRadioButton(), "aria-checked", "false")
//                .validateAttribute(createPublisherSidebar.getCurrency(), "disabled", "true")
//                .and()
//                .setValueWithClean(createPublisherSidebar.getNameInput(), PUBLISHER_NAME_EDITED)
//                .setValueWithClean(createPublisherSidebar.getAdOpsInput(), PUBLISHER_AD_OPS_EDITED)
//                .clickOnWebElement(createPublisherSidebar.getSaveButton())
//                .waitSideBarClosed()
//                .then()
//                .validate(demandPage.getPublisherItemByPositionInList(0).getPublisherName(), PUBLISHER_NAME_EDITED)
//                .validate(demandPage.getPublisherItemByPositionInList(0).getPublisherAdOps(), PUBLISHER_AD_OPS_EDITED)
//                .validate(demandPage.getPublisherItemByPositionInList(0).getPublisherId(), valueOf(demandSource.getId()))
//                .and()
//                .clickOnWebElement(demandPage.getPublisherItemByPositionInList(0).getPublisherName())
//                .waitSideBarOpened()
//                .then()
//                .validateAttribute(editDemandSidebar.getNameInput(), "value", PUBLISHER_NAME_EDITED)
//                .validateAttribute(editDemandSidebar.getAdOpsInput(), "value", PUBLISHER_AD_OPS_EDITED)
                .testEnd();
    }

//    @AfterClass
//    private void deleteDemandSource(){
//        if (publisher()
//                .setCredentials(USER_FOR_DELETION)
//                .deletePublisher(demandSource.getId())
//                .build()
//                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
//            log.info(String.format("Deleted publisher %s", demandSource.getId()));
//    }

    @Step("Create New Demand Source")
    private DemandSource createNewDsp(){

        return DemandSourcePrecondition.demandSource()
                .createDemandSource()
                .build()
                .getDemandSourceResponse();
    }
}
