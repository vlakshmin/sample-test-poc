
package widgets.common.tooltip;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaTooltipTextElements {


    TOOLTIP("Tooltip","//span"),
    CATEGORY_TOOLTIP_TEXT("'Category' Tooltip Text", "Category/ies set in the Media level are indicated " +
            "in bid requests coming from its ad spots as the site.cat in web media types, and as the app.cat in mobile, respectively to its set Media Type.");

    private String alias;
    private String selector;
}
