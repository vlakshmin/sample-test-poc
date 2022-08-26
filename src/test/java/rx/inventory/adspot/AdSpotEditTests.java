package rx.inventory.adspot;

import api.dto.rx.inventory.adspot.AdSpot;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.inventory.adSpots.sidebar.EditAdSpotSidebar;

import static api.preconditionbuilders.AdSpotPrecondition.adSpot;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotEditTests extends BaseTest {
    private AdSpotsPage adSpotsPage;
    private EditAdSpotSidebar editAdSpotSidebar;
    private AdSpot adSpot;

    public AdSpotEditTests() {
        adSpotsPage = new AdSpotsPage();
        editAdSpotSidebar = new EditAdSpotSidebar();
    }


    @BeforeClass
    private void init() {

//        adSpot = adSpot()
//                .createNewAdSpot()
//                .build()
//                .getAdSpotResponse();
    }

    @BeforeMethod
    private void login() {

        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .testEnd();
    }
    @Test(description = "Save existing Ad Spot without changes", alwaysRun = true)
    public void saveAdSpotWithoutChanges(){
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();
        var tablePagination = adSpotsPage.getAdSpotsTable().getTablePagination();

        adSpot = adSpot()
                .createNewAdSpot()
                .build()
                .getAdSpotResponse();

        testStart()
                .setValueWithClean(tableData.getSearch(),adSpot.getName())
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .clickEnterButton(tableData.getSearch())
                .then("Validate that text in table footer '1-1 of 1")
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .and("Open Sidebar and check data")
                .clickOnTableCellLink(tableData, ColumnNames.AD_SPOT_NAME, adSpot.getName())
                .waitSideBarOpened()
                .and("Click Save")
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .waitAndValidate(not(visible), editAdSpotSidebar.getErrorAlert().getErrorPanel())
                .waitAndValidate(not(visible), adSpotsPage.getToasterMessage().getPanelError())
                .waitSideBarClosed()
                .testEnd();
    }

    @AfterMethod(alwaysRun = true)
    private void logout() {
        testStart()
                .and("Logout")
                .logOut()
                .testEnd();
    }

    @AfterClass(alwaysRun = true)
    private void deletePublisher() {

        if (adSpot()
                .setCredentials(USER_FOR_DELETION)
                .deleteAdSpot(adSpot.getId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted media %s", adSpot.getId()));

        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(adSpot.getPublisherId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", adSpot.getPublisherId()));
    }

}
