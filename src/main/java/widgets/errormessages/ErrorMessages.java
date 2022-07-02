package widgets.errormessages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    SITE_URL_ERROR_ALERT("'Site URL' Error Alert Text", "The Site URL field must be a valid url"),
    PUBLISHER_NAME_ERROR_ALERT("'Publisher Name' Error Alert Text", "The Publisher Name field is required"),
    APP_STORE_URL_ERROR_ALERT("'App Store URL' Error Alert Text", "The App Store URL field must be a valid url");


    private String alias;
    private String text;
}
