package widgets.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ToasterMessageElements {

    ERROR_ICON("Error Icon", "//*[@class='toast-wrapper']/i"),
    ERROR_PANEL("Error Panel", "//*[@class='v-snack__content']"),
    ERROR_MESSAGE( "'Error' Message", "//*[@class='toast-wrapper']/div[@class='content']/div[@class='hidden']"),
    ERROR_STATUS( "'Error' Status", "//*[@class='toast-wrapper']/div[@class='content']/div[@class='inline']/h2"),
    REMOVE_ICON("Remove Icon","//*[@class='toast-wrapper']/div[@class='content']/div[@class='inline']/a[@class='remove']"),
    VIEW_ERROR_DETAILS( "'View Error Details Link", "//*[@class='toast-wrapper']/div[@class='content']/div[@class='inline']/a[1]");

    private String alias;
    private String selector;
}
