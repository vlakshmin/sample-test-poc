package widgets.errormessages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    //Media
    SITE_URL_ERROR_ALERT("'Site URL' Error Alert Text", "The Site URL field must be a valid url"),
    PUBLISHER_NAME_ERROR_ALERT("'Publisher Name' Error Alert Text", "The Publisher Name field is required"),
    MEDIA_NAME_ERROR_ALERT("'Media Name' Error Alert Text", "The Media Name field is required"),
    MEDIA_TYPE_ERROR_ALERT("'Media Type' Error Alert Text", "The Media Type field is required"),
    APP_STORE_URL_ERROR_ALERT("'App Store URL' Error Alert Text", "The App Store URL field must be a valid url"),

    //Private Auction / Deal
    PRIVATE_AUCTION_ERROR_ALERT("Private Auction Error Alert Text", "The Private Auction field is required"),
    VALUE_ERROR_ALERT("Value Error Alert Text", "The Value field is required"),
    NAME_ERROR_ALERT("Name Error Alert Text", "The Name field is required"),
    CURRENCY_ERROR_ALERT("Currency Error Alert Text", "The Currency field is required"),
    DSP_ERROR_ALERT("DSP Error Alert Text", "The DSP field is required"),
    DATE_RANGE_ERROR_ALERT("'Date Range' Error Alert Text", "The Date Range field is required"),

    //Protections
    PROTECTION_TYPE_ERROR_ALERT("Protection Type Error Alert Text", "Protection Type field is required"),
    FLOOR_PRICE_ERROR_ALERT("Floor Price Error Alert Text", "The Floor Price field is required"),

    //Publisher
    ADD_OPS_PERSON_ERROR_ALERT("Ad Ops Person Error Alert Text", "The Ad Ops Person field is required"),
    ADD_OPS_EMAIL_ERROR_ALERT("Ad Ops Email Error Alert Text", "The Ad Ops Email field is required"),

    //Users
    PUBLISHER_ERROR_ALERT("Publisher Error Alert Text", "The Publisher field is required"),
    USERNAME_ERROR_ALERT("Username Error Alert Text", "The Username field is required"),
    EMAIL_ERROR_ALERT("Email Error Alert Text", "The Email field is required"),

    //DSP
    FORMAT_ERROR_ALERT("Format Error Alert Text", "The Format field is required"),
    PLATFORM_ERROR_ALERT("Platform Error Alert Text", "The Platform field is required"),
    ENDPOINT_ERROR_ALERT("Endpoint Error Alert Text", "Endpoint URI has no selections");

    private String alias;
    private String text;
}
