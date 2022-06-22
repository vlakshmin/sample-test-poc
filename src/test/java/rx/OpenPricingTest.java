package rx;

import api.preconditionbuilders.PublisherPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static configurations.User.TEST_USER;
import static java.lang.String.valueOf;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class OpenPricingTest extends BaseTest{


    @BeforeClass
    public void createNewPublisher(){
        //Creating publisher to edit Using API
     /*   openPricing = PublisherPrecondition.publisher()
                .createNewPublisher()
                .build()
                .getPublisherResponse();*/
    }

    @Test
    public void editPublisherTest(){

        //Opening Browser and Edit the protection created from Precondition
        testStart()
                .given()
                .openDirectPath(Path.OPEN_PRICING)
                .logIn(TEST_USER)


                .testEnd();

        //allure serve
    }
}
