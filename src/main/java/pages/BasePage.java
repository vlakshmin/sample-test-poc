package pages;

import lombok.Getter;

@Getter
public abstract class BasePage {

    private String logo = "//div[@class='logo']/../..";
    private String snackBar = "";

}
