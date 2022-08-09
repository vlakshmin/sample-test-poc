package widgets.common.tooltip;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaTooltipText {

    APP_STORE_URL("'App Store App' Tooltip Text",
            "App Store URL is indicated in bid requests as the app.bundle or app.storeurl"),

    CATEGORIES("'Categories' Tooltip Text",
            "Category/ies set in the Media level are indicated in bid requests coming from its ad spots as the " +
                    "site.cat in web media types, and as the app.cat in mobile, respectively to its set Media Type."),

    BUNDLE("'Bundle' Tooltip Text",
            "Category/ies set in the Media level are indicated in bid requests coming from its ad spots as the " +
                    "site.cat in web media types, and as the app.cat in mobile, respectively to its set Media Type."),

    SITE_URL("'Site URL' Tooltip Text",
            "URL set in the Media level is indicated in bid requests coming from its ad spots as the site.domain " +
                    "in web media types, and as app.bundle or app.storeurl in mobile app, respectively to the media type set.");


    private String alias;
    private String text;
}
