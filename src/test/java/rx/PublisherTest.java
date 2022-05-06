package rx;

import api.entities.rx.publisher.Publisher;
import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.MainPage;

@Slf4j
@Listeners({ScreenShooter.class})
public class PublisherTest extends BaseTest{

    private MainPage mainPage;
    private Publisher publisher;

    public PublisherTest(){
        mainPage = new MainPage();
    }

    @Test
    public void editPublisherTest(){

        //Creating publisher to edit Using API
        publisher = PublisherPrecondition.publisher()
                .createNewPublisher()
                .build()
                .getPublisherResponse();

        //Opening Browser and Edit the protection created from Precondition
//        testStart()
//                .given()
//                .openUrl()
//                .logIn(TEST_USER)
//                .validate(visible, $x(mainPage.getLogo()))
//                .validate(TEST_USER.getMail())
//                .waitAndValidate(disappear, $x(mainPage.getNuxtPogress()))
//                .then()
//                .clickOnText("Protections")
//                .waitAndValidate(disappear, $x(mainPage.getTableProgressBar()))
//                .clickOnText("Rows per page:")
//                .clickOnWebElement( $x( "//div[text()='20']"))
//                .clickOnWebElement( $x( "//div[text()='10']"))
//                .clickOnText(protectionResponse.getName())
//                .and()
//                .validate(visible, String.format("Edit Protections: %s", protectionResponse.getName()))
//                .validate(disappear, $x("//div[@class='v-progress-circular__info']"))
//                .validate(visible, $x("//input[@role='switch']"))
//                .clickOnWebElement($x("//input[@role='switch']"))
//                .validateAttribute($x("//input[@role='switch']"), "aria-checked", "false")
//        .testEnd();

        //allure serve
    }

}
