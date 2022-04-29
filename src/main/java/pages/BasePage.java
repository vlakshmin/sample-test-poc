package pages;

import lombok.Getter;

@Getter
public abstract class BasePage {

    private String logo = "//div[@class='logo']/../..";
    private String snackBar = "";
    private String tableProgressBar = "//div[@role='progressbar']";
    private String nuxtPogress = "//div[@class='nuxt-progress']";

}
