package rx.admin.publishers;

import api.dto.rx.admin.publisher.Publisher;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.admin.publisher.PublishersPage;
import rx.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class PublisherCheckPaginationTests extends BaseTest {

    private PublishersPage publishersPage;

    private int totalPublishers;
    private List<Publisher> listOfPublishers;

    Publisher publisher;


    public PublisherCheckPaginationTests() {
        publishersPage = new PublishersPage();
    }

    @BeforeClass
    private void init() {
        if (getTotalPublishers() < 150) generatePublishers();

        totalPublishers = getTotalPublishers();
    }

    @BeforeMethod(alwaysRun = true)
    private void login() {
        testStart()
                .given()
                .openDirectPath(Path.PUBLISHER)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, publishersPage.getNuxtProgress())
                .testEnd();
    }

    @Test(description = "Verify Pagination: 100 rows per page", alwaysRun = true, priority = 1)
    public void checkPagination100() {
        verifyPagination(100);
    }

    @Test(description = "Verify Pagination: 50 rows per page", alwaysRun = true, priority = 4)
    public void checkPagination50() {
        verifyPagination(50);
    }

    @Test(description = "Verify Pagination: 25 rows per page", alwaysRun = true, priority = 5)
    public void checkPagination25() {
        verifyPagination(25);
    }

    @Test(description = "Verify Pagination: 20 rows per page", alwaysRun = true, priority = 6)
    public void checkPagination20() {
        verifyPagination(20);
    }

    @Test(description = "Verify Pagination: 15 rows per page", alwaysRun = true, priority = 3)
    public void checkPagination15() {
        verifyPagination(15);
    }

    @Test(description = "Verify Pagination: 10 rows per page", alwaysRun = true, priority = 2)
    public void checkPagination10() {
        verifyPagination(10);
    }

    @Step("Verify pagination {0}")
    private void verifyPagination(Integer rowsPerPage) {
        var tablePagination = publishersPage.getTable().getTablePagination();
        var tableData = publishersPage.getTable().getTableData();

        testStart()
                .and(String.format("Select %s rows per page", rowsPerPage))
                .scrollIntoView(tablePagination.getPageMenu())
                .selectFromDropdown(tablePagination.getPageMenu(),
                        tablePagination.getRowNumbersList(), rowsPerPage.toString())
                .waitLoading(disappear, publishersPage.getTableProgressBar())
                .then(String.format(String.format("Validate that text in table footer '1-%s of %s'", rowsPerPage,
                        totalPublishers)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format(String.format("1-%s of %s", rowsPerPage, totalPublishers)))
                .then(String.format("Rows in table page equals %s", rowsPerPage))
                .validateListSize(tableData.getRows(), rowsPerPage)
                .and("Click on Next page")
                .scrollIntoView(tablePagination.getNext())
                .clickOnWebElement(tablePagination.getNext())
                .then(String.format(String.format("Validate that text in table footer '%s-%s of %s'",
                        rowsPerPage + 1, Math.min(rowsPerPage * 2, totalPublishers), totalPublishers)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format(String.format("%s-%s of %s",
                                rowsPerPage + 1, Math.min(rowsPerPage * 2, totalPublishers), totalPublishers)))
                .then(String.format("Rows in table page equals %s", rowsPerPage))
                .validateListSize(tableData.getRows(), rowsPerPage)
                .and("Click on Previous page")
                .scrollIntoView(tablePagination.getPrevious())
                .clickOnWebElement(tablePagination.getPrevious())
                .then(String.format(String.format("Validate that text in table footer '1-%s of %s'",
                        rowsPerPage, totalPublishers)))
                .validateContainsText(tablePagination.getPaginationPanel(),
                        String.format(String.format("1-%s of %s",
                                rowsPerPage, totalPublishers)))
                .then(String.format("Rows in table page equals %s", rowsPerPage))
                .validateListSize(tableData.getRows(), rowsPerPage)
                .testEnd();
    }

    @AfterMethod(alwaysRun = true)
    public void logout() {
        testStart()
                .and("Logout")
                .logOut()
                .testEnd();
    }

    @AfterClass(alwaysRun = true)
    private void deleteEntities() {

        if (listOfPublishers != null) {
            for (Publisher publisher : listOfPublishers) {
                deletePublisher(publisher.getId());
            }
        }
    }

    private void deletePublisher(Integer id) {
        publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(id);
    }

    private int getTotalPublishers() {

        return publisher()
                .getPublishersList()
                .build()
                .getPublisherGetAllResponse()
                .getTotal();
    }

    private void generatePublishers() {

        publisher = publisher()
                .createNewPublisher(captionWithSuffix("autoPub"))
                .build()
                .getPublisherResponse();

        listOfPublishers = new ArrayList<>();
        while (getTotalPublishers() < 210) {
            Publisher publisher1 = publisher()
                    .createNewPublisher()
                    .build()
                    .getPublisherResponse();
        }
    }
}
