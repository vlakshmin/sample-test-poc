package rx.inventory.adspot;

import api.dto.rx.inventory.adspot.AdSpot;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static api.preconditionbuilders.AdSpotPrecondition.adSpot;
import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotCheckPagination extends BaseTest {
    private AdSpotsPage adSpotsPage;

    private int totalAdSpots;
    private List<AdSpot> listAdSpots;

    @BeforeClass
    private void init(){
        adSpotsPage = new AdSpotsPage();

        if (getTotalAdSpots() < 100) generateAdSpots();

        totalAdSpots = getTotalAdSpots();
    }

    @BeforeMethod
    private void login(){
        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .testEnd();
    }

    @Test(description = "Verify Pagination: 100 rows per page")
    public void checkPagination100() {
        verifyPagination(100);
    }

    @Test(description = "Verify Pagination: 50 rows per page")
    public void checkPagination50() {
        verifyPagination(50);
    }

    @Test(description = "Verify Pagination: 25 rows per page")
    public void checkPagination25() {
        verifyPagination(25);
    }

    @Test(description = "Verify Pagination: 20 rows per page")
    public void checkPagination20() {
        verifyPagination(20);
    }

    @Test(description = "Verify Pagination: 15 rows per page")
    public void checkPagination15() {
        verifyPagination(15);
    }

    @Test(description = "Verify Pagination: 10 rows per page")
    public void checkPagination10() {
        verifyPagination(10);
    }

    @Step("Verify pagination {0}")
    private void verifyPagination(Integer rowsPerPage){
        var tablePagination = adSpotsPage.getAdSpotsTable().getTablePagination();
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();

        testStart()
                .and(String.format("Select %s rows per page",rowsPerPage))
                .scrollIntoView(tablePagination.getPageMenu())
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), rowsPerPage.toString())
                .waitLoading(visible, adSpotsPage.getTableProgressBar())
                .waitLoading(disappear, adSpotsPage.getTableProgressBar())
                .then(String.format(String.format("Validate that text in table footer '1-%s of %s'",rowsPerPage,
                        totalAdSpots)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format(String.format("1-%s of %s",rowsPerPage, totalAdSpots)))
                .then(String.format("Rows in table page equals %s",rowsPerPage))
                .validateListSize(tableData.getRows(),rowsPerPage)
                .and("Click on Next page")
                .scrollIntoView(tablePagination.getNext())
                .clickOnWebElement(tablePagination.getNext())
                .then(String.format(String.format("Validate that text in table footer '%s-%s of %s'",
                        rowsPerPage+1, Math.min(rowsPerPage*2,totalAdSpots), totalAdSpots)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format(String.format("%s-%s of %s",
                                rowsPerPage+1, Math.min(rowsPerPage*2,totalAdSpots), totalAdSpots)))
                .then(String.format("Rows in table page equals %s",rowsPerPage))
                .validateListSize(tableData.getRows(),rowsPerPage)
                .and("Click on Previous page")
                .scrollIntoView(tablePagination.getPrevious())
                .clickOnWebElement(tablePagination.getPrevious())
                .then(String.format(String.format("Validate that text in table footer '1-%s of %s'",
                        rowsPerPage, totalAdSpots)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format(String.format("1-%s of %s",
                                rowsPerPage, totalAdSpots)))
                .then(String.format("Rows in table page equals %s",rowsPerPage))
                .validateListSize(tableData.getRows(),rowsPerPage)
                .and("Logout")
                .logOut()
                .testEnd();
    }

    @AfterClass
    private void deleteEntities(){
        if(listAdSpots!=null) {
            for (AdSpot adSpot : listAdSpots) {
                deleteAdSpot(adSpot.getId());
                deleteMedia(adSpot.getMediaId());
                deletePublisher(adSpot.getPublisherId());
            }
        }
    }

    private void deleteAdSpot(Integer id){
        adSpot()
                .setCredentials(USER_FOR_DELETION)
                .deleteAdSpot(id);
    }

    private void deleteMedia(Integer id){
        media()
                .setCredentials(USER_FOR_DELETION)
                .deleteMedia(id);
    }

    private void deletePublisher(Integer id){
        publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(id);
    }

    private int getTotalAdSpots() {

        return adSpot()
                .getAllAdSpotsList()
                .build()
                .getAdSpotsGetAllResponse()
                .getTotal();
    }

    private void generateAdSpots() {
        listAdSpots = new ArrayList<>();
        while (getTotalAdSpots() < 110) {
            AdSpot adSpot = adSpot()
                    .createNewAdSpot(captionWithSuffix("auto"))
                    .build()
                    .getAdSpotResponse();

            listAdSpots.add(adSpot);
        }
    }
}
