package rx;

import api.dto.rx.common.Currency;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.adspot.AdSpotRequest;
import api.preconditionbuilders.AdSpotPrecondition;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Path;
import pages.inventory.adspots.AdspotsPage;

import java.util.List;

import static com.codeborne.selenide.Condition.disappear;
import static configurations.User.TEST_USER;
import static managers.TestManager.testStart;

@Slf4j
@Listeners({ScreenShooter.class})
public class AdSpotTest extends BaseTest{

    private AdSpot adSpot;
    private AdspotsPage adspotsPage;


    public AdSpotTest(){
        adspotsPage = new AdspotsPage();
    }

    @BeforeClass
    public void createNewAdSpot(){
        //Creating ad spot to edit Using API
        adSpot = AdSpotPrecondition.adSpot()
                .createNewAdSpot()
                .build()
                .getAdSpotResponse();


    }

    @Test
    public void editAdSpotTest(){

        //Opening Browser and Edit the media created from Precondition
        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adspotsPage.getNuxtProgress())
                .and()
         .testEnd();

        //allure serve
    }

    @Ignore
    @Test
    public void createCustomAdSpotTest(){
        createCustomAdSpot();
        //Opening Browser and Edit the media created from Precondition
        testStart()
                .given()
                .openDirectPath(Path.AD_SPOT)
                .logIn(TEST_USER)
                .waitAndValidate(disappear, adspotsPage.getNuxtProgress())
                .and()
                .testEnd();

        //allure serve
    }

    private AdSpotRequest createCustomAdSpot(){

        AdSpotRequest adSpotRequest= new AdSpotRequest();
        adSpotRequest.setCategoryIds(List.of(1,2));
        adSpotRequest.setCurrency(Currency.EUR.name());
        return  adSpotRequest;
    }
}
