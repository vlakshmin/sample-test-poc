package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BasePageElements {

    SNACKBAR( "SnackBar", ""),
    LOGO( "Logo", "//div[@class='logo']/../.."),
    TABLE_PROGRESSBAR("Table ProgressBar", "//div[@role='progressbar']"),
    NUXT_PROGRESSBAR("Page Load Progressbar", "//div[@class='nuxt-progress']");

    private String alias;
    private String selector;
}
