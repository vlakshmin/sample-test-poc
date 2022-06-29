package widgets.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ToasterMessageElements {

    ERROR_MESSAGE( "'Error' Label", " ");

    private String alias;
    private String selector;
}
