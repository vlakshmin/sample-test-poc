package rx.inventory.adspot;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotCheckColumns extends BaseTest {
    private AdSpotsPage adSpotsPage;

    public AdSpotCheckColumns() {
        adSpotsPage = new AdSpotsPage();
    }

    @BeforeClass
    private void login() {
        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .testEnd();
    }


    @Test
    public void checkColumns() {
        var tableOptions = adSpotsPage.getAdSpotsTable().getTableOptions();
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();
        testStart()
                .and("'Show' all columns")
                .scrollIntoView(tableOptions.getTableOptionsBtn())
                .clickOnWebElement(tableOptions.getTableOptionsBtn())
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ID))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.DETAILS))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.AD_SPOT_NAME))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.RELATED_MEDIA))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ACTIVE_INACTIVE))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.PAGE_CATEGORY))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.FILTER))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.TEST_MODE))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.DEFAULT_SIZES))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.DEFAULT_FLOOR_PRICE))
                .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                // TODO:GS-2748 .selectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.UPDATED_DATE))
                .then("All columns should be shown")
                .validateListSize(tableData.getColumns(),
                        ColumnNames.ID.getName(),
                        ColumnNames.DETAILS.getName(),
                        ColumnNames.AD_SPOT_NAME.getName(),
                        ColumnNames.PUBLISHER.getName(),
                        ColumnNames.RELATED_MEDIA.getName(),
                        ColumnNames.ACTIVE_INACTIVE.getName(),
                        ColumnNames.PAGE_CATEGORY.getName(),
                        ColumnNames.FILTER.getName(),
                        ColumnNames.TEST_MODE.getName(),
                        ColumnNames.DEFAULT_SIZES.getName(),
                        ColumnNames.DEFAULT_FLOOR_PRICE.getName(),
                        ColumnNames.CREATED_DATE.getName())
                // TODO:GS-2748  ColumnNames.UPDATED_DATE.getName())
                .and("Hide all columns")
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ID))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.DETAILS))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.AD_SPOT_NAME))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.RELATED_MEDIA))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.ACTIVE_INACTIVE))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.PAGE_CATEGORY))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.FILTER))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.TEST_MODE))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.DEFAULT_SIZES))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.DEFAULT_FLOOR_PRICE))
                .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                // TODO:GS-2748   .unSelectCheckBox(tableOptions.getMenuItemCheckbox(ColumnNames.UPDATED_DATE))
                .then("All columns should be hidden")
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.ID.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.DETAILS.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.AD_SPOT_NAME.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.PUBLISHER.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.RELATED_MEDIA.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.ACTIVE_INACTIVE.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.PAGE_CATEGORY.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.FILTER.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.TEST_MODE.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.DEFAULT_SIZES.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.DEFAULT_FLOOR_PRICE.getName()))
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.CREATED_DATE.getName()))
                // TODO:GS-2748      .validate(not(visible), tableData.getColumnHeader(ColumnNames.UPDATED_DATE.getName()))
                .testEnd();
    }

    @AfterClass
    private void logout() {
        testStart()
                .logOut()
                .testEnd();
    }
}
