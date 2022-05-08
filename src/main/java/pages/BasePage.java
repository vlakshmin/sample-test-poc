package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;
import static pages.BasePageElements.*;

@Getter
public abstract class BasePage {

    private SelenideElement logo = $x(LOGO.getSelector()).as(LOGO.getAlias());
    private SelenideElement snackBar = $x(SNACKBAR.getSelector()).as(SNACKBAR.getAlias());
    private SelenideElement nuxtProgress = $x(NUXT_PROGRESSBAR.getSelector()).as(NUXT_PROGRESSBAR.getAlias());
    private SelenideElement tableProgressBar = $x(TABLE_PROGRESSBAR.getSelector()).as(TABLE_PROGRESSBAR.getAlias());

}
