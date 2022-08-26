package rx.inventory.adspot;

import api.dto.rx.admin.publisher.Publisher;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;
import pages.Path;
import pages.inventory.adspots.AdSpotsPage;
import rx.BaseTest;
import widgets.inventory.adSpots.sidebar.EditAdSpotSidebar;

import static api.preconditionbuilders.PublisherPrecondition.publisher;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.disabled;
import static configurations.User.TEST_USER;
import static configurations.User.USER_FOR_DELETION;
import static managers.TestManager.testStart;
import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotCheckFields extends BaseTest {
    private AdSpotsPage adSpotPage;
    private EditAdSpotSidebar editAdSpotSidebar;
    private Publisher publisher;

    public AdSpotCheckFields() {
        adSpotPage = new AdSpotsPage();
        editAdSpotSidebar = new EditAdSpotSidebar();
    }

    @BeforeClass
    private void init() {

        publisher = publisher()
                .createNewPublisher(captionWithSuffix("2autoPubASpot"))
                .build()
                .getPublisherResponse();
    }

    @BeforeMethod
    private void login() {

        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adSpotPage.getNuxtProgress())
                .clickOnWebElement(adSpotPage.getCreateAdSpotButton())
                .waitSideBarOpened()
                .testEnd();
    }



    @Test(description = "Check fields by default")
    private void checkDefaultFields() {
        testStart()
                .then("Validate fields by default")
                .validate(disabled, editAdSpotSidebar.getActiveToggle())
                .validateAttribute(editAdSpotSidebar.getActiveToggle(), "aria-checked", "true")
                .validate(visible, editAdSpotSidebar.getPublisherInput())
                .validate(editAdSpotSidebar.getPublisherInput(), "")
                .validate(disabled,editAdSpotSidebar.getCategoriesInput())
                .validate(disabled,editAdSpotSidebar.getNameInput())
                .validate(disabled,editAdSpotSidebar.getRelatedMediaInput())
                .validate(disabled,editAdSpotSidebar.getPosition())
                .validate(disabled,editAdSpotSidebar.getDefaultAdSizes())
                .validate(disabled,editAdSpotSidebar.getDefaultFloorPrice())
                .validate(disabled,editAdSpotSidebar.getTestModeToggle())
                .validate(disabled,editAdSpotSidebar.getContentForChildrenToggle())
                .validateAttribute(editAdSpotSidebar.getBannerCard().getBannerPanel(),"style","display: none;")
                .validateAttribute(editAdSpotSidebar.getVideoCard().getVideoPanel(),"style","display: none;")
                .validateAttribute(editAdSpotSidebar.getNativeCard().getNativePanel(),"style","display: none;")
                .and("Close Ad Spot Sidebar")
                .clickOnWebElement(editAdSpotSidebar.getCloseIcon())
                .waitSideBarClosed()
                .testEnd();
    }

    @Test(description = "Check required fields")
    private void checkRequiredFields() {
        var videoCard = editAdSpotSidebar.getVideoCard();
        var bannerCard = editAdSpotSidebar.getBannerCard();
        var nativeCard = editAdSpotSidebar.getNativeCard();

        testStart()
                .and("Expand all cards")
                .clickOnWebElement(bannerCard.getBannerCardHeader())
                .clickOnWebElement(nativeCard.getNativeCardHeader())
                .scrollIntoView(videoCard.getVideoCardHeader())
                .clickOnWebElement(videoCard.getVideoCardHeader())

                .then("Click 'Save' and check Errors")
                .scrollIntoView(editAdSpotSidebar.getSaveButton())
                .clickOnWebElement(editAdSpotSidebar.getSaveButton())
                .and("Close Ad Spot Sidebar")
                .clickOnWebElement(editAdSpotSidebar.getCloseIcon())
                .waitSideBarClosed()

              .testEnd();
    }


    @AfterMethod
    private void logout() {
        testStart()
                .and("Logout")
                .logOut()
                .testEnd();
    }

    @AfterClass
    private void deletePublisher() {
        if (publisher()
                .setCredentials(USER_FOR_DELETION)
                .deletePublisher(publisher.getId())
                .build()
                .getResponseCode() == HttpStatus.SC_NO_CONTENT)
            log.info(String.format("Deleted publisher %s", publisher.getId()));
    }
}
