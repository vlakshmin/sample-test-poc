
package widgets.sales.deals.warningbanner;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChangePublisherBannerElements {

    WARNING_ICON("'Delete Buyer Card' Icon in 'Change Publisher' Banner", "//i[@icon='mdi-alert']"),
    CANCEL_BUTTON("'Cancel' Button in 'Change Publisher' Banner", "//div[@class='v-banner__actions']//span[text()='CANCEL']/.."),
    ACCEPT_BUTTON("'Accept' Button in 'Change Publisher' Banner","//div[@class='v-banner__actions']//span[contains(text(),'ACCEPT')]/.."),
    CHANGE_PUBLISHER_TEXT("'Change publisher banner' textLabel in 'Change Publisher' Banner", "//descendant::div[@class='v-banner__text'][2]");

    private String alias;
    private String selector;
}
