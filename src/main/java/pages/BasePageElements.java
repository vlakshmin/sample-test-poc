package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BasePageElements {

    SNACKBAR( "'SnackBar' element", ""),
    LOGO( "'Logo' Icon", "//div[@class='logo']/../.."),
    SIDEBAR("'Sidebar'", "//div[@class='overlay']"),
    TABLE_PROGRESSBAR("'Table ProgressBar'", "//div[@role='progressbar']"),
    NUXT_PROGRESSBAR("'Page Load Progressbar'", "//div[@class='nuxt-progress']"),
    BUTTON("Button with Caption", "//span[@class='v-btn__content' and text()='%s']/..");

    private String alias;
    private String selector;
}
