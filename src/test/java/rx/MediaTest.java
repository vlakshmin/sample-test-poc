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
    private DashboardPage dashboardsPage;
    private MediaPage mediaPage;

    private static final String PUBLISHER_NAME_EDITED = FakerUtils.captionWithSuffix("Pub_Edited");
    private static final String PUBLISHER_AD_OPS_EDITED = FakerUtils.captionWithSuffix("Ad_Ops_Edited");

    public MediaTest(){
        dashboardsPage = new DashboardPage();
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

        //Opening Browser and Edit the media created from Precondition
        testStart()
                .given()
                .openDirectPath(Path.MEDIA)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, mediaPage.getNuxtProgress())
                .and()
         .testEnd();

        //allure serve
    }

}
