package rx.inventory.adspot;

import api.dto.rx.inventory.adspot.AdSpot;
import api.preconditionbuilders.AdSpotPrecondition;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotSearchTableTests extends BaseTest {

    private AdSpot adSpot;
    private AdSpotsPage adSpotsPage;

    private List<Integer> adSpotIds;
    private List<Integer> publishersIds;
    private List<String> adSpotNamesByAsc;

    private static final String AD_SPOT_NAME = "autoSSSDD1";

    public AdSpotSearchTableTests() {
        adSpotsPage = new AdSpotsPage();
    }

    @BeforeClass
    public void loginAndCreateExpectedResuts() {
        adSpotIds = new ArrayList<>();
        publishersIds = new ArrayList<>();

        adSpot = createCustomAdSpot(captionWithSuffix("adspot_auto"), captionWithSuffix("pub_auto"), true);
        adSpotIds.add(adSpot.getId());
        publishersIds.add(adSpot.getPublisherId());

        adSpot = createCustomAdSpot(AD_SPOT_NAME, captionWithSuffix("pub_auto"), true);
        adSpotIds.add(adSpot.getId());
        publishersIds.add(adSpot.getPublisherId());

        adSpotNamesByAsc = getAllItemsByParams(AD_SPOT_NAME).stream()
                .map(AdSpot::getName)
                .collect(Collectors.toList());
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

    @AfterMethod(alwaysRun = true)
    private void logOut() {
        testStart()
                .given()
                .logOut()
                .testEnd();
    }

    @Test(testName = "Search by 'Ad Spot Name'")
    public void adSpotsSearchByAdSpotName() {
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();

        testStart()
                .given()
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), AD_SPOT_NAME)
                .clickEnterButton(tableData.getSearch())
                .waitAndValidate(disappear, adSpotsPage.getTableProgressBar())
                .and("Sort column 'Ad spot Name'")
                .clickOnWebElement(tableData.getColumnHeader(ColumnNames.NAME.getName()))
                .then(String.format("Validate data in column 'Ad spot Name' should contain '%s'", AD_SPOT_NAME))
                .validateAttribute(tableData.getColumnHeader(ColumnNames.NAME.getName()),
                        "aria-sort", "ascending")
                .validateList(tableData.getCustomCells(ColumnNames.NAME), adSpotNamesByAsc)
                .and("End Test")
                .testEnd();
    }

    //TODO: need to add scenarios Search Ad Spot with filters applying
    //https://rakutenadvertising.atlassian.net/browse/GS-3462

    @AfterTest(alwaysRun = true)
    private void deleteEntities() {

        for (Integer adSpotId : adSpotIds) {
            AdSpotPrecondition.adSpot()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteAdSpot(adSpotId);
        }

        for (Integer publisherId : publishersIds) {
            PublisherPrecondition.publisher().
                    setCredentials(USER_FOR_DELETION).
                    deletePublisher(publisherId);
        }
    }

    private AdSpot createCustomAdSpot(String name, String publisherName, Boolean isEnabled) {

        return AdSpotPrecondition.adSpot()
                .createNewAdSpot(name, publisherName, isEnabled)
                .build()
                .getAdSpotResponse();
    }

    private List<AdSpot> getAllItemsByParams(String strParams) {

        return AdSpotPrecondition.adSpot()
                .getAdSpotsWithFilter(Map.of("search", strParams))
                .build()
                .getAdSpotsGetAllResponse()
                .getItems();
    }
}
