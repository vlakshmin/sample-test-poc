package rx.media;

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
import widgets.inventory.media.sidebar.EditMediaSidebar;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static java.lang.String.valueOf;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaTestExample extends BaseTest {

    private Media media;
    private MediaPage mediaPage;
    private EditMediaSidebar editMediaSidebar;
    private MediaTooltipText tooltipText;

    public MediaTestExample(){
        editMediaSidebar = new EditMediaSidebar();
        mediaPage = new MediaPage();
        tooltipText = new MediaTooltipText();
    }

    @BeforeClass
    public void createNewMedia(){
        //Creating media to edit Using API
        media = MediaPrecondition.media()
                .createNewMedia()
                .build()
                .getMediaResponse();
    }

    @Test
    public void editMediaTest(){
        var table = mediaPage.getMediaTable().getTableOptions();
        var tableData = mediaPage.getMediaTable().getTableData();

        //Opening Browser and Edit the media created from Precondition
        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and()
                .setValueWithClean(tableData.getSearch(),media.getName())
                .and()
                .clickEnterButton(tableData.getSearch())
                .then()
                .waitLoading(visible,mediaPage.getTableProgressBar())
                .waitLoading(disappear,mediaPage.getTableProgressBar())
                .then()
                .validateListContainsTextOnly(tableData.getCustomCells(ColumnNames.MEDIA_NAME),
                        media.getName())
                .and()
                .clickOnTableCellLink(tableData, ColumnNames.MEDIA_NAME, media.getName())
                .waitSideBarOpened()
                .then()
                .validateTooltip(editMediaSidebar.getHintCategories(),
                        tooltipText.getTooltip().getText(), tooltipText.getTooltipText().getAlias())
                .and()
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .and()
                .setValueWithClean(editMediaSidebar.getSiteURL(),"https://test.com")
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .then()

                .waitSideBarClosed()

                .and()
         .testEnd();

        //allure serve
    }

}
