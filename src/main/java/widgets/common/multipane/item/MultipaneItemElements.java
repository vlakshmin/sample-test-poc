package widgets.common.multipane.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MultipaneItemElements {

    ID("'Publisher id' Label", "/td[2]"),
    MAIL("'Publisher Mail' Label", "/td[9]");

    private String alias;
    private String selector;
}
