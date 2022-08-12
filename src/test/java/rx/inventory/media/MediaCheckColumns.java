package rx.inventory.media;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.media.MediaPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;

import static com.codeborne.selenide.Condition.*;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class MediaCheckColumns extends BaseTest {
    private MediaPage mediaPage;

    public MediaCheckColumns() {
        mediaPage = new MediaPage();
    }

    @BeforeClass
    private void login() {

        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .testEnd();
    }


    @Test
    public void checkColumns() {
        var table = mediaPage.getMediaTable().getTableOptions();
        var tableData = mediaPage.getMediaTable().getTableData();
        testStart()
                .and("'Show' all columns")
                .scrollIntoView(table.getTableOptionsBtn())
                .clickOnWebElement(table.getTableOptionsBtn())
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.ID))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.MEDIA_NAME))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.SITE_APP_STORE_URL))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.STATUS))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.PLATFORM))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.UPDATED_DATE))
                .then("All columns should be shown")
                .validateListSize((ElementsCollection) tableData.getColumns(),
                        ColumnNames.ID.getName(),
                        ColumnNames.MEDIA_NAME.getName(),
                        ColumnNames.SITE_APP_STORE_URL.getName(),
                        ColumnNames.PUBLISHER.getName(),
                        ColumnNames.STATUS.getName(),
                        ColumnNames.PLATFORM.getName(),
                        ColumnNames.CREATED_DATE.getName(),
                        ColumnNames.UPDATED_DATE.getName())
                .and("Hide all columns")

                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.ID))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.MEDIA_NAME))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.PLATFORM))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.SITE_APP_STORE_URL))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.STATUS))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.UPDATED_DATE))
                .then("All columns should be hidden")
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.ID.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.MEDIA_NAME.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.PLATFORM.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.STATUS.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.SITE_APP_STORE_URL.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.CREATED_DATE.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.UPDATED_DATE.getName()))
                .testEnd();
    }

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}
