package widgets.errormessages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    BAD_REQUEST("Bad Request Message","400 Bad Request"),
    PUBLISHER_IS_DISABLED("Publisher is Disabled message","Publisher is disabled and cannot be used"),
    //Media
    MEDIA_NAME_ERROR_ALERT("'Media Name' Error Alert Text", "The Media Name field is required"),
    MEDIA_TYPE_ERROR_ALERT("'Media Type' Error Alert Text", "The Media Type field is required"),
    SITE_URL_ERROR_ALERT("'Site URL' Error Alert Text", "The Site URL field must be a valid url"),
    SITE_URL_REQUIRED_ERROR_ALERT("'Site URL' Error Alert Text", "The Site URL field is required"),
    PUBLISHER_NAME_ERROR_ALERT("'Publisher Name' Error Alert Text", "The Publisher Name field is required"),
    APP_STORE_URL_ERROR_ALERT("'App Store URL' Error Alert Text", "The App Store URL field must be a valid url"),
    APP_STORE_URL_REQUIRED_ERROR_ALERT("'App Store URL' Error Alert Text", "The App Store URL field is required"),

    //Ad Spot
    POSITION_ERROR_ALERT("'Position' Error Alert Text", "The Position field is required"),
    AD_SPOT_NAME_ERROR_ALERT("'Ad Spot Name' Error Alert Text", "The Ad Spot Name field is required"),
    RELATED_MEDIA_TYPE_ERROR_ALERT("'Related Media' Error Alert Text", "The Related Media field is required"),
    DEFAULT_AD_SIZE_TYPE_ERROR_ALERT("'Default Ad size' Error Alert Text", "The Default Ad Sizes field is required"),
    DEFAULT_FLOOR_PRICE_ERROR_ALERT("'Default Floor Price' Error Alert Text", "The Default Floor Price field is required"),

    //Private Auction / Deal
    DSP_ERROR_ALERT("DSP Error Alert Text", "The DSP field is required"),
    NAME_ERROR_ALERT("Name Error Alert Text", "The Name field is required"),
    VALUE_ERROR_ALERT("Value Error Alert Text", "The Value field is required"),
    CURRENCY_ERROR_ALERT("Currency Error Alert Text", "The Currency field is required"),
    DATE_RANGE_ERROR_ALERT("'Date Range' Error Alert Text", "The Date Range field is required"),
    PRIVATE_AUCTION_ERROR_ALERT("Private Auction Error Alert Text", "The Private Auction field is required"),

    //Protections
    FLOOR_PRICE_ERROR_ALERT("Floor Price Error Alert Text", "The Floor Price field is required"),
    PROTECTION_TYPE_ERROR_ALERT("Protection Type Error Alert Text", "Protection Type field is required"),

    //Publisher
    ADD_OPS_EMAIL_ERROR_ALERT("Ad Ops Email Error Alert Text", "The Ad Ops Email field is required"),
    ADD_OPS_PERSON_ERROR_ALERT("Ad Ops Person Error Alert Text", "The Ad Ops Person field is required"),

    //Users
    EMAIL_ERROR_ALERT("Email Error Alert Text", "The Email field is required"),
    USERNAME_ERROR_ALERT("Username Error Alert Text", "The Username field is required"),
    PUBLISHER_ERROR_ALERT("Publisher Error Alert Text", "The Publisher field is required"),

    //DSP
    FORMAT_ERROR_ALERT("Format Error Alert Text", "The Format field is required"),
    PLATFORM_ERROR_ALERT("Platform Error Alert Text", "The Platform field is required"),
    ENDPOINT_ERROR_ALERT("Endpoint Error Alert Text", "Endpoint URI has no selections");

    private String alias;
    private String text;
}
