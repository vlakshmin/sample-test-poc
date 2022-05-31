package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import widgets.common.table.TableOptionsElements;

import static com.codeborne.selenide.Selenide.$x;
import static pages.BasePageElements.*;

/**
 * Keep Selectors of UI elements in {@link BasePageElements}
 */
@Getter
public class BasePage {

    private SelenideElement logo = $x(LOGO.getSelector()).as(LOGO.getAlias());
    private SelenideElement sidebar = $x(SIDEBAR.getSelector()).as(SIDEBAR.getAlias());
    private SelenideElement snackBar = $x(SNACKBAR.getSelector()).as(SNACKBAR.getAlias());
    private SelenideElement nuxtProgress = $x(NUXT_PROGRESSBAR.getSelector()).as(NUXT_PROGRESSBAR.getAlias());
    private SelenideElement tableProgressBar = $x(TABLE_PROGRESSBAR.getSelector()).as(TABLE_PROGRESSBAR.getAlias());

    public SelenideElement getButtonWithCaption(String caption){

       return  $x(String.format(BUTTON.getSelector(),caption)).as(BUTTON.getAlias());
    }
}
