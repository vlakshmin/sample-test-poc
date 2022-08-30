package widgets.inventory.adSpots.sidebar;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.adSizes.AdSizesListPanel;
import widgets.common.categories.CategoriesListPanel;
import widgets.common.validationalert.ValidationBottomAlert;
import widgets.common.warningbanner.ChangePublisherBanner;
import widgets.inventory.adSpots.sidebar.card.AdSpotBannerCardSidebar;
import widgets.inventory.adSpots.sidebar.card.AdSpotNativeCardSidebar;
import widgets.inventory.adSpots.sidebar.card.AdSpotVideoCardSidebar;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static widgets.inventory.adSpots.sidebar.AdSpotSidebarElements.*;

/**
 * Keep Selectors of UI elements in {@link AdSpotSidebarElements}
 */
@Getter
public abstract class AdSpotSidebar {

    private AdSpotVideoCardSidebar videoCard = new AdSpotVideoCardSidebar();
    private AdSpotBannerCardSidebar bannerCard = new AdSpotBannerCardSidebar();
    private AdSpotNativeCardSidebar nativeCard = new AdSpotNativeCardSidebar();
    private SelenideElement filter = $x(FILTER.getSelector()).as(FILTER.getAlias());
    private SelenideElement position = $x(POSITION.getSelector()).as(POSITION.getAlias());
    private SelenideElement closeIcon = $x(CLOSE_ICON.getSelector()).as(CLOSE_ICON.getAlias());
    private SelenideElement categories = $x(CATEGORIES.getSelector()).as(CATEGORIES.getAlias());
    private SelenideElement saveButton = $x(SAVE_BUTTON.getSelector()).as(SAVE_BUTTON.getAlias());
    private SelenideElement testModeToggle = $x(TEST_MODE.getSelector()).as(TEST_MODE.getAlias());
    private SelenideElement nameInput = $x(AD_SPOT_NAME.getSelector()).as(AD_SPOT_NAME.getAlias());
    private SelenideElement activeToggle = $x(ACTIVE_TOGGLE.getSelector()).as(ACTIVE_TOGGLE.getAlias());
    private SelenideElement positionInput = $x(POSITION_INPUT.getSelector()).as(POSITION_INPUT.getAlias());
    private SelenideElement publisherInput = $x(PUBLISHER_NAME.getSelector()).as(PUBLISHER_NAME.getAlias());
    private SelenideElement relatedMedia = $x(RELATED_MEDIA.getSelector()).as(RELATED_MEDIA.getAlias());
    private ElementsCollection positionItems = $$x(POSITION_ITEMS.getSelector()).as(POSITION_ITEMS.getAlias());
    private SelenideElement defaultAdSizes = $x(DEFAULT_AD_SIZES.getSelector()).as(DEFAULT_AD_SIZES.getAlias());
    private SelenideElement categoriesInput = $x(CATEGORIES_INPUT.getSelector()).as(CATEGORIES_INPUT.getAlias());
    private ElementsCollection publisherItems = $$x(PUBLISHER_ITEMS.getSelector()).as(PUBLISHER_ITEMS.getAlias());
    private SelenideElement relatedMediaInput = $x(RELATED_MEDIA_INPUT.getSelector()).as(RELATED_MEDIA_INPUT.getAlias());
    private SelenideElement defaultFloorPrice = $x(DEFAULT_FLOOR_PRICE.getSelector()).as(DEFAULT_FLOOR_PRICE.getAlias());
    private SelenideElement publisherNameInput = $x(PUBLISHER_NAME_INPUT.getSelector()).as(PUBLISHER_NAME_INPUT.getAlias());
    private ElementsCollection relatedMediaItems = $$x(RELATED_MEDIA_ITEMS.getSelector()).as(RELATED_MEDIA_ITEMS.getAlias());
    private SelenideElement defaultAdSizesInput = $x(DEFAULT_AD_SIZES_INPUT.getSelector()).as(DEFAULT_AD_SIZES_INPUT.getAlias());
    private SelenideElement contentForChildrenToggle = $x(CONTENT_FOR_CHILDREN.getSelector()).as(CONTENT_FOR_CHILDREN.getAlias());
    private AdSizesListPanel adSizesPanel = new AdSizesListPanel();
    private ValidationBottomAlert errorAlert = new ValidationBottomAlert();
    private ChangePublisherBanner changePublisherBanner = new ChangePublisherBanner();
    private CategoriesListPanel categoriesPanel = new CategoriesListPanel();

    public SelenideElement getErrorAlertByFieldName(String fieldName){

        return $x(String.format(ERROR_ALERT_BY_FIELD_NAME.getSelector(),fieldName))
                .as(String.format(ERROR_ALERT_BY_FIELD_NAME.getAlias(),fieldName));
    }
}
