package rx.inventory.adspot;

import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.adspot.AdSpotRequest;
import api.dto.rx.inventory.adspot.Banner;
import api.dto.rx.inventory.media.Media;
import api.preconditionbuilders.AdSpotPrecondition;
import api.preconditionbuilders.MediaPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.common.table.ColumnNames;
import widgets.inventory.adSpots.sidebar.EditAdSpotSidebar;

import java.util.List;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotTest extends BaseTest {

    private AdSpot adSpot;
    private AdSpotsPage adspotsPage;
    private EditAdSpotSidebar editAdSpotSidebar;

    public AdSpotTest() {
        adspotsPage = new AdSpotsPage();
        editAdSpotSidebar = new EditAdSpotSidebar();
    }

    @BeforeClass
    public void createNewAdSpot() {
        //Creating ad spot to edit Using API
        adSpot = AdSpotPrecondition.adSpot()
                .createNewAdSpot()
                .build()
                .getAdSpotResponse();
    }


    @Test
    public void createCustomAdSpotTest() {
        var tableData = adspotsPage.getAdSpotsTable().getTableData();
        String adSpotName = captionWithSuffix("AdSpot");

        AdSpot adSpot = AdSpotPrecondition.adSpot()
                .createNewAdSpot(createCustomAdSpot(adSpotName))
                .build()
                .getAdSpotResponse();

        //Opening Browser and check the ad spot created
        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adspotsPage.getNuxtProgress())
                .setValueWithClean(tableData.getSearch(), adSpotName)
                .waitLoading(visible, adspotsPage.getTableProgressBar())
                .waitLoading(disappear, adspotsPage.getTableProgressBar())
                .validateListContainsTextOnly(tableData.getCustomCells(ColumnNames.AD_SPOT_NAME),
                        adSpot.getName())
                .and()
                .clickOnTableCellLink(tableData, ColumnNames.AD_SPOT_NAME, adSpot.getName())
                .waitSideBarOpened()
                .validateAttribute(editAdSpotSidebar.getNameInput(), "value", adSpotName)
                .validate(editAdSpotSidebar.getPublisherInput(), adSpot.getPublisherName())
                .validate(editAdSpotSidebar.getRelatedMediaInput(), adSpot.getMediaName())
                .validateAttribute(editAdSpotSidebar.getDefaultFloorPrice(), "value", adSpot.getFloorPrice().toString())
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .waitSideBarClosed()
                .and()
                .testEnd();
    }

    private AdSpotRequest createCustomAdSpot(String name) {

        Media media = MediaPrecondition.media()
                .createNewMedia()
                .build()
                .getMediaResponse();

        return AdSpotRequest.builder()
                .name(name)
                .enabled(true)
                .publisherId(media.getPublisherId())
                .publisherName(media.getPublisherName())
                .floorPrice(9.99)
                .mediaId(media.getId())
                .coppa(true)
                .categoryIds(List.of(1, 2))
                .sizeIds(List.of(10))
                .banner(Banner.builder()
                        .enabled(true)
                        .floorPrice(8.88)
                        .sizeIds(List.of(3))
                        .build())
                .build();
    }
}
