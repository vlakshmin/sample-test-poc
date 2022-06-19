package widgets.common.multipane.item;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static widgets.common.multipane.item.MultipaneItemElements.*;

/**
 * Keep Selectors of UI elements in {@link MultipaneItemElements}
 */
@Getter
public class MultipaneItem {

    private int position;
    private String multipaneName;

    private SelenideElement publisherId;
    private SelenideElement publisherMail;

    private static final String MULTIPANE_ITEM = "tbody/tr";
    //descendant::h3[text()='%s']/../..//table[@class='select-table fixed']/tbody[22]/tr/td[2]/div
    ////descendant::h3[text()='Inventory']/../..//table[@class='select-table fixed']/tbody/tr[26]//td[2]/div

    public MultipaneItem(int position, String multipaneName) {
        this.position = position;
        this.multipaneName = multipaneName;
        this.publisherId =  $x(buildXpath(ID.getSelector())).as(ID.getAlias());
        this.publisherMail = $x(buildXpath(MAIL.getSelector())).as(MAIL.getAlias());
    }

    public String buildXpath(String elementXpath) {

        return (format("//descendant::%s[%s]%s", MULTIPANE_ITEM, position, elementXpath));
    }
}
