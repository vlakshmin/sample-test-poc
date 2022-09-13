package rx.inventory.media;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.media.Media;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.media.*;
import rx.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static api.preconditionbuilders.MediaPrecondition.media;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaCheckPaginationTests extends BaseTest {
    private MediaPage mediaPage;

    private int totalMedia;
    private List<Media> listMedia;

    Publisher publisher;

    public MediaCheckPaginationTests() {
        mediaPage = new MediaPage();
    }

    @BeforeClass
    private void init() {
        if (getTotalMedia() < 100) generateMedia();

        totalMedia = getTotalMedia();
    }

    @BeforeMethod
    private void login() {
        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .testEnd();
    }

    @Test(description = "Verify Pagination: 100 rows per page",alwaysRun = true)
    public void checkPagination100() {
        verifyPagination(100);
    }

    @Test(description = "Verify Pagination: 50 rows per page",alwaysRun = true)
    public void checkPagination50() {
        verifyPagination(50);
    }

    @Test(description = "Verify Pagination: 25 rows per page",alwaysRun = true)
    public void checkPagination25() {
        verifyPagination(25);
    }

    @Test(description = "Verify Pagination: 20 rows per page",alwaysRun = true)
    public void checkPagination20() {
        verifyPagination(20);
    }

    @Test(description = "Verify Pagination: 15 rows per page",alwaysRun = true)
    public void checkPagination15() {
        verifyPagination(15);
    }

    @Test(description = "Verify Pagination: 10 rows per page",alwaysRun = true)
    public void checkPagination10() {
        verifyPagination(10);
    }

    @Step("Verify pagination {0}")
    private void verifyPagination(Integer rowsPerPage) {
        var tablePagination = mediaPage.getMediaTable().getTablePagination();
        var tableData = mediaPage.getMediaTable().getTableData();

        testStart()
                .and(String.format("Select %s rows per page", rowsPerPage))
                .scrollIntoView(tablePagination.getPageMenu())
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), rowsPerPage.toString())
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format(String.format("Validate that text in table footer '1-%s of %s'", rowsPerPage,
                        totalMedia)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format(String.format("1-%s of %s", rowsPerPage, totalMedia)))
                .then(String.format("Rows in table page equals %s", rowsPerPage))
                .validateListSize(tableData.getRows(), rowsPerPage)
                .and("Click on Next page")
                .scrollIntoView(tablePagination.getNext())
                .clickOnWebElement(tablePagination.getNext())
                .then(String.format(String.format("Validate that text in table footer '%s-%s of %s'",
                        rowsPerPage + 1, Math.min(rowsPerPage * 2, totalMedia), totalMedia)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format(String.format("%s-%s of %s",
                                rowsPerPage + 1, Math.min(rowsPerPage * 2, totalMedia), totalMedia)))
                .then(String.format("Rows in table page equals %s", rowsPerPage))
                .validateListSize(tableData.getRows(), rowsPerPage)
                .and("Click on Previous page")
                .scrollIntoView(tablePagination.getPrevious())
                .clickOnWebElement(tablePagination.getPrevious())
                .then(String.format(String.format("Validate that text in table footer '1-%s of %s'",
                        rowsPerPage, totalMedia)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format(String.format("1-%s of %s",
                                rowsPerPage, totalMedia)))
                .then(String.format("Rows in table page equals %s", rowsPerPage))
                .validateListSize(tableData.getRows(), rowsPerPage)
                .and("Logout")
                .logOut()
                .testEnd();
    }

    @AfterClass(alwaysRun = true)
    private void deleteEntities() {
        if (listMedia != null) {
            for (Media media : listMedia) {
                deleteMedia(media.getId());
            }
            deletePublisher(publisher.getId());
        }
    }

    private void deleteMedia(Integer id) {
        media()
                .setCredentials(USER_FOR_DELETION)
                .deleteMedia(id);
    }

    private void deletePublisher(Integer id) {
        publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(id);
    }

    private int getTotalMedia() {

        return media()
                .getAllMediaList()
                .build()
                .getMediaGetAllResponse()
                .getTotal();
    }

    private void generateMedia() {

        publisher = publisher()
                .createNewPublisher("autoPub")
                .build()
                .getPublisherResponse();

        listMedia = new ArrayList<>();
        while (getTotalMedia() < 110) {
            Media media = media()
                    .createNewMedia(captionWithSuffix("auto"), publisher.getId(), true)
                    .build()
                    .getMediaResponse();

            listMedia.add(media);
        }
    }
}
