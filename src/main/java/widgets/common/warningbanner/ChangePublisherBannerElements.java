
package widgets.common.warningbanner;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.card.AdSpotBannerCardSidebarElements.ERROR_ALERT_BY_FIELD_NAME;

@Getter
@AllArgsConstructor   ////div[contains(@class,'pa-4 container--fluid')]/div[contains(@class,'v-alert customAlert v-sheet')]
public enum ChangePublisherBannerElements {

    //WARNING_ICON("'Delete Buyer Card' Icon in 'Change Publisher' Banner", "//i[@icon='mdi-alert']"),
    WARNING_ICON("Warning Icon", "//div[contains(text(),'%s')]/../div[@class='v-avatar v-banner__icon']"),
  //  CANCEL_BUTTON("'Cancel' Button in 'Change Publisher' Banner", "//div[@class='v-banner__actions']//span[text()='CANCEL']/.."),
    CANCEL_BUTTON("Cancel Button","//div[contains(text(),'%s')]//../.." +
          "/div[@class='v-banner__actions']//span[contains(text(),'CANCEL')]/.."),
 //   ACCEPT_BUTTON("'Accept' Button in 'Change Publisher' Banner","//div[@class='v-banner__actions']//span[contains(text(),'ACCEPT')]/.."),
    ACCEPT_BUTTON("Accept Button","//div[contains(text(),'%s')]/../.." +
         "/div[@class='v-banner__actions']//span[contains(text(),'ACCEPT')]/.."),
   // CHANGE_PUBLISHER_LABEL("'Change publisher banner' textLabel in 'Change Publisher' Banner", "//descendant::div[@class='v-banner__text']");
   BANNER_TEXT("Banner Text","//div[contains(text(),'%s')]");

    private String alias;
    private String selector;
}
