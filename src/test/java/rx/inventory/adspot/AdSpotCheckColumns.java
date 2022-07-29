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

    @BeforeClass
    private void initTestData(){
          adSpotsPage = new AdSpotsPage();

        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adSpotsPage.getNuxtProgress())
                .testEnd();
    }


    @Test
    public void checkColumns() {
        var table = adSpotsPage.getAdSpotsTable().getTableOptions();
        var tableData = adSpotsPage.getAdSpotsTable().getTableData();
        testStart()
                .and("'Show' all columns")
                .scrollIntoView(table.getTableOptionsBtn())
                .clickOnWebElement(table.getTableOptionsBtn())
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.ID))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.DETAILS))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.AD_SPOT_NAME))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.RELATED_MEDIA))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.ACTIVE_INACTIVE))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.PAGE_CATEGORY))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.FILTER))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.TEST_MODE))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.DEFAULT_SIZES))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.DEFAULT_FLOOR_PRICE))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .selectCheckBox(table.getMenuItemCheckbox(ColumnNames.UPDATED_BY))
              //  .scrollIntoView(table.getTableOptionsBtn())
              //  .clickOnWebElement(table.getTableOptionsBtn())
                .then("All columns should be shown")
                .validateListSize((ElementsCollection) tableData.getColumns(),
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
                        ColumnNames.CREATED_DATE.getName(),
                        ColumnNames.UPDATED_BY.getName())
                .and("Hide all columns")
          //      .scrollIntoView(table.getTableOptionsBtn())
          //      .clickOnWebElement(table.getTableOptionsBtn())
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.ID))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.DETAILS))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.AD_SPOT_NAME))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.PUBLISHER))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.RELATED_MEDIA))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.ACTIVE_INACTIVE))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.PAGE_CATEGORY))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.FILTER))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.TEST_MODE))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.DEFAULT_SIZES))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.DEFAULT_FLOOR_PRICE))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.CREATED_DATE))
                .unSelectCheckBox(table.getMenuItemCheckbox(ColumnNames.UPDATED_BY))
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
                .validate(not(visible), tableData.getColumnHeader(ColumnNames.UPDATED_BY.getName()))
                .testEnd();
    }

    @AfterClass
    private void logout(){
        testStart()
                .logOut()
                .testEnd();
    }
}
