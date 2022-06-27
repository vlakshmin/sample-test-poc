package widgets.inventory.media.sidebar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaSidebarElements {

    MEDIA_NAME("'Media Name' Input", "//label[text()='Media Name']/../input"),
    MEDIA_TYPE("'Media Type' Input", "//label[text()='Media Type']/../div"),
    PUBLISHER_NAME( "'Publisher Name' Input", "//label[text()='Publisher Name']/../div"),
    CATEGORIES("'Categories' Input", "//label[text()='Categories']/../input"),
    HINT_CATEGORIES("'Hint Categories' Input", "//label[text()='Categories']/../../div[@class='v-input__append-inner']"),
    SITE_URL( "'Site URL' Input", "//label[text()='Site URL']/../input"),
    HINT_SITE_URL("Hint Site URL","//label[text()='Site URL']/../../div[@class='v-input__append-inner']"),
    APP_STORE_URL( "'App Store URL' Input", "//label[text()='App Store URL']/../input"),
    HINT_APP_STORE_URL("Hint App Store URL","//label[text()='App Store URL']/../../div[@class='v-input__append-inner']"),
    SAVE_BUTTON("'Save Media' Button",  "//button/span[contains(text(),'Save Media')]");

    private String alias;
    private String selector;
}
