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
import zutils.StringUtils;

import java.util.List;
import java.util.Map;

import static api.preconditionbuilders.ProtectionsPrecondition.protection;
import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static java.lang.String.format;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
@Feature(value = "Components")
public class SinglepaneFilterProtectionTest extends BaseTest {
    private ProtectionsPage protectionPage;

    private Integer countItems;

    private Integer countPublishers;
    private Integer countFilteredPublishers;

    private static final String PUBLISHER_NAME = "RakutenTV";

    public SinglepaneFilterProtectionTest() {
        protectionPage = new ProtectionsPage();
    }

    @BeforeClass
    private void login() {

        countItems = getTotalProtectionItems();
        countPublishers = getTotalPublishers();
        countFilteredPublishers = getTotalFilteredPublishers("rakuten");

        testStart()
                .given()
                .openDirectPath(Path.PROTECTIONS)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, protectionPage.getNuxtProgress())
                .testEnd();
    }

    @Test(description = "Check Singlepane widget", enabled = false)
    public void testSinglepaneWidgetComponent() {
        var filter = protectionPage.getProtectionsTable().getColumnFiltersBlock();

        testStart()
                .and("click on Columns Filter button")
                .clickOnWebElement(filter.getColumnsFilterButton())
                .then("Validate Columns Filter List by default")
                .validateList(filter.getFilterOptionItems(), List.of(ColumnNames.PUBLISHER.getName(),
                        ColumnNames.ACTIVE_INACTIVE.getName(),
                        ColumnNames.MANAGED_BY_SYSTEM_ADMIN.getName()))
                .clickOnWebElement(filter.getFilterOptionByName(ColumnNames.PUBLISHER))
                .then("Validate Column Filter Header")
                .validate(filter.getSinglepane().getFilterHeaderLabel(), StringUtils.getFilterHeader(ColumnNames.PUBLISHER.getName()))
                .then("Validate Include All Count items")
                .validate(filter.getSinglepane().getItemsTotalQuantityLabel(), format("(%s)", countPublishers))
                .then("Validate Included Count items")
                .validate(filter.getSinglepane().getItemsIncludedQuantityLabel(), format("(%s)", 0))
                .and("Search publisher")
                .setValueWithClean(filter.getSinglepane().getSearchInput(), "rakuten")
                .validate(not(visible), protectionPage.getTableProgressBar())
                .validate(filter.getSinglepane().getItemsTotalQuantityLabel(),format("(%s)",countFilteredPublishers))
                .clickOnWebElement(filter.getSinglepane().getFilterItemByName(PUBLISHER_NAME).getName())
                .then("Included Icon should be visible")
                .validate(visible, filter.getSinglepane().getFilterItemByName(PUBLISHER_NAME).getIncludedIcon())
                .then("Validate Include All Filtered items")
                .validate(filter.getSinglepane().getItemsTotalQuantityLabel(), format("(%s)", countFilteredPublishers))
                .then("Validate Included Number items")
                .validate(filter.getSinglepane().getItemsIncludedQuantityLabel(), "(1)")
                .and("Click on Include All")
                .clickOnWebElement(filter.getSinglepane().getIncludeAllButton())
                .and("Included Icon should be visible")
                .validate(visible, filter.getSinglepane().getFilterItemByPositionInList(1).getIncludedIcon())
                .then("Validate Include All Count items")
                .validate(filter.getSinglepane().getItemsTotalQuantityLabel(), format("(%s)", countFilteredPublishers))
                .then("Validate Included Count items")
                .validate(filter.getSinglepane().getItemsIncludedQuantityLabel(), format("(%s)", countFilteredPublishers))
                .clickOnWebElement(filter.getSinglepane().getClearAllButton())
                .then("Validate Include All Count items")
                .validate(filter.getSinglepane().getItemsTotalQuantityLabel(), format("(%s)", countFilteredPublishers))
                .then("Validate Included Count items")
                .validate(filter.getSinglepane().getItemsIncludedQuantityLabel(), format("(%s)", 0))
                .clickOnWebElement(filter.getSinglepane().getSubmitButton())
                .then("ColumnsFilter widget is closed")
                .validate(not(visible), filter.getFilterOptionsMenu())
                .testEnd();
    }

    private Integer getTotalProtectionItems() {

        return protection()
                .setCredentials(TEST_USER)
                .getAllProtectionsList()
                .build()
                .getProtectionsGetAllResponse()
                .getTotal();
    }

    private Integer getTotalPublishers() {

        return publisher()
                .setCredentials(TEST_USER)
                .getPublishersList()
                .build()
                .getPublisherGetAllResponse()
                .getTotal();
    }

    private Integer getTotalFilteredPublishers(String publishersName) {

        return publisher()
                .setCredentials(TEST_USER)
                .getPublisherWithFilter(Map.of("name", publishersName))
                .build()
                .getPublisherGetAllResponse()
                .getTotal();
    }

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}
