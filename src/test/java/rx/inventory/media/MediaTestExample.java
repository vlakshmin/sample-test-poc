package rx.inventory.media;

import api.dto.rx.inventory.media.Media;
import api.preconditionbuilders.MediaPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.media.MediaPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.common.tooltip.MediaTooltipText;
import widgets.errormessages.ErrorMessages;
import widgets.inventory.media.sidebar.EditMediaSidebar;
import widgets.inventory.media.sidebar.MediaSidebarElements;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaTestExample extends BaseTest {

    private Media media;
    private MediaPage mediaPage;
    private EditMediaSidebar editMediaSidebar;

    public MediaTestExample() {
        editMediaSidebar = new EditMediaSidebar();
        mediaPage = new MediaPage();
    }

    @BeforeClass
    public void createNewMedia() {
        //Creating media to edit Using API
        media = MediaPrecondition.media()
                .createNewMedia()
                .build()
                .getMediaResponse();
    }

    @Test
    public void mediaTest() {
        var tableData = mediaPage.getMediaTable().getTableData();
        var tablePagination = mediaPage.getMediaTable().getTablePagination();

        //Opening Browser and Edit the media created from Precondition
        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and(String.format("Search Media by name '%s'", media.getName()))
                .setValueWithClean(tableData.getSearch(), media.getName())
                .clickEnterButton(tableData.getSearch())
                .then("Wait table data loading")
                .waitLoading(visible, mediaPage.getTableProgressBar())
                .waitLoading(disappear, mediaPage.getTableProgressBar())
                .then(String.format("Validate that table contains uniq Media with random name '%s'", media.getName()))
                .validateContainsText(tablePagination.getPaginationPanel(), "1-1 of 1")
                .then("Validate list values in 'Media Name' column")
                .validateListContainsTextOnly(tableData.getCustomCells(ColumnNames.MEDIA_NAME),
                        media.getName())
                .and()
                .waitLoading(disappear, mediaPage.getNuxtProgress())
                .clickOnTableCellLink(tableData, ColumnNames.MEDIA_NAME, media.getName())
                .and("Wait SideBar is opened")
                .waitSideBarOpened()
                .then("Validate Categories tooltip text")
                .validateTooltip(editMediaSidebar.getTooltipIconByFieldName("Categories"),
                        MediaSidebarElements.TOOLTIP_PLACEHOLDER.getSelector(),
                        MediaTooltipText.CATEGORY_TOOLTIP_TEXT.getText())
                .and("Click on 'Save' button")
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .then("Validate bottom panel with errors")
                .scrollIntoView(editMediaSidebar.getErrorAlert().getErrorPanel())
                .validateContainsText(editMediaSidebar.getErrorAlert().getErrorsList(),
                        ErrorMessages.SITE_URL_ERROR_ALERT.getText())
                .then("Validate error message under the 'Site URL' field")
                .validate(visible,editMediaSidebar.getErrorAlert().getErrorPanel())
                .validate(visible,editMediaSidebar.getErrorAlert().getIconError())
                .scrollIntoView(editMediaSidebar.getErrorAlertByFieldName("Site URL"))
                .validateContainsText(editMediaSidebar.getErrorAlertByFieldName("Site URL"),
                        ErrorMessages.SITE_URL_ERROR_ALERT.getText())
                .and("Set valid value 'Site URL' and click Save")
                .setValueWithClean(editMediaSidebar.getSiteURL(), "https://test.com")
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .then("Errors should be disappeared")
                .validate(disappear,editMediaSidebar.getErrorAlert().getErrorPanel())
                .then("Wait until SideBar closed")
                .waitSideBarClosed()
                .and("End")
                .testEnd();
        //allure serve
    }

}
