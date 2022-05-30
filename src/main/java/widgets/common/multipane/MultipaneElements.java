package widgets.common.multipane;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MultipaneElements {

    INCLUDE_ALL_BUTTON( "'Include All' button",
            "//button/h3[text()='%s']/../../..//span/div[contains(text(),'Include All')]/..");

    private String alias;
    private String selector;
}
