package rx;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.media.Media;
import api.preconditionbuilders.MediaPrecondition;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.admin.publisher.PublishersPage;
import pages.dashbord.DashboardPage;
import pages.inventory.media.MediaPage;
import widgets.common.table.ColumnNames;
import widgets.inventory.media.sidebar.EditMediaSidebar;
import zutils.FakerUtils;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static java.lang.String.valueOf;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaTest extends BaseTest{

    private Media media;
    private MediaPage mediaPage;
    private EditMediaSidebar editMediaSidebar;

    public MediaTest(){
        editMediaSidebar = new EditMediaSidebar();
        mediaPage = new MediaPage();
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
                .clickEnterButton(tableData.getSearch())
                .waitLoading(visible,mediaPage.getTableProgressBar())
                .waitLoading(disappear,mediaPage.getTableProgressBar())
                .then()
                .validateListContainsTextOnly(tableData.getCustomCells(ColumnNames.MEDIA_NAME),
                        media.getName())
                .and()
                .clickOnTableCellLink(tableData, ColumnNames.MEDIA_NAME, media.getName())
                .waitSideBarOpened()
                .clickOnWebElement(editMediaSidebar.getSaveButton())
                .then()
               // .validateTooltip(,editMediaSidebar.getHintCategories(),"test tooltip")
                .waitSideBarClosed()

                .and()
         .testEnd();

        //allure serve
    }

}
