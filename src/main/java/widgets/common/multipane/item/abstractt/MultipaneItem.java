package widgets.common.multipane.item.multipane;

import com.codeborne.selenide.SelenideElement;
import lombok.AccessLevel;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static widgets.common.multipane.item.multipane.MultipaneItemElements.*;

/**
 * Keep Selectors of UI elements in {@link MultipaneItemElements}
 */
@Getter
public abstract class MultipaneItem {

    private SelenideElement name;
    private SelenideElement type;

    @Getter(AccessLevel.NONE)
    private int position;
    @Getter(AccessLevel.NONE)
    private String multipaneName;
    @Getter(AccessLevel.NONE)
    protected String multipaneItem;
    //descendant::h3[text()='%s']/../..//table[@class='select-table fixed']/tbody[22]/tr/td[2]/div
    ////descendant::h3[text()='%s']/../..//table[@class='select-table fixed']/tbody/tr[contains(@class,'children')][%s]//td[2]/div

    public MultipaneItem(int position,  String  multipaneItem, String multipaneName) {
        this.position = position;
        this.multipaneItem = multipaneItem;
        this.multipaneName = multipaneName;

        this.name = $x(buildXpath(NAME.getSelector())).as(format("%s%s", NAME.getAlias(), position));
        this.type = $x(buildXpath(TYPE.getSelector())).as(format("%s%s", TYPE.getAlias(), position));
    }

    public String buildXpath(String elementXpath) {

        return format("%s[%s]%s",
                format("//descendant::%s",
                        format(multipaneItem, multipaneName)),
                position,
                elementXpath);
    }
}
