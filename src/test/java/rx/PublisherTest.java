package rx;

import api.dto.rx.publisher.Publisher;
import api.preconditionbuilders.PublisherPrecondition;
import zutils.FakerUtils;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.PublishersPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;
import static java.lang.String.valueOf;

@Slf4j
@Listeners({ScreenShooter.class})
public class PublisherTest extends BaseTest{

    private MainPage mainPage;
    private Publisher publisher;
    private PublishersPage publishersPage;

    private static final String PUBLISHER_NAME_EDITED = FakerUtils.captionWithSuffix("Pub_Edited");
    private static final String PUBLISHER_AD_OPS_EDITED = FakerUtils.captionWithSuffix("Ad_Ops_Edited");

    public PublisherTest(){
        mainPage = new MainPage();
        publishersPage = new PublishersPage();
    }

    @BeforeClass
    public void createNewPublisher(){
        //Creating publisher to edit Using API
        publisher = PublisherPrecondition.publisher()
                .createNewPublisher()
                .build()
                .getPublisherResponse();
    }

    @Test
    public void editPublisherTest(){

        //Opening Browser and Edit the protection created from Precondition
        testStart()
                .given()
                .openUrl()
                .logIn(TEST_USER)
                .validate(visible, mainPage.getLogo())
                .validate(TEST_USER.getMail())
                .waitAndValidate(disappear, mainPage.getNuxtProgress())
                .and()
                .clickOnText("Admin")
                .clickOnText("Publisher")
                .waitAndValidate(disappear, mainPage.getTableProgressBar())
                .clickOnWebElement(publishersPage.getPublisherItemByName(publisher.getName()).getPublisherName())
                .waitSideBarOpened()
                .then()
                .validate(publisher.getName()
                        , publisher.getMail()
                        , publisher.getCurrency())
                .validateAttribute(publishersPage.getEditPublisherSidebar().getCurrency(), "disabled", "true")
                .and()
                .setValueWithClean(publishersPage.getEditPublisherSidebar().getNameInput(), PUBLISHER_NAME_EDITED)
                .setValueWithClean(publishersPage.getEditPublisherSidebar().getAdOpsInput(), PUBLISHER_AD_OPS_EDITED)
                .clickOnWebElement(publishersPage.getEditPublisherSidebar().getSaveButton())
                .waitSideBarClosed()
                .then()
                .validate(publishersPage.getPublisherItemByPositionInList(0).getPublisherName(), PUBLISHER_NAME_EDITED)
                .validate(publishersPage.getPublisherItemByPositionInList(0).getPublisherAdOps(), PUBLISHER_AD_OPS_EDITED)
                .validate(publishersPage.getPublisherItemByPositionInList(0).getPublisherId(), valueOf(publisher.getId()))
        .testEnd();

        //allure serve
    }
}
