package api.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RakutenExchangeApi {
    //Rules
    GET_RULE("/v3/protections/targeting/%s"),
    CREATE_RULE("/v3/protections/targeting"),

    //Publisher
    GET_PUBLISHERS("/v2/publishers"),
    CREATE_PUBLISHER("/v2/publishers"),
    DELETE_PUBLISHER("/v2/publishers/%s"),

    //Users
    CREATE_USER("/v2/accounts"),
    GET_USER("/v2/accounts/%s"),
    GET_ALL_USERS("/v2/accounts"),
    UPDATE_USER("/v2/accounts/%s"),

    //Open Pricing
    CREATE_OPEN_PRICING("/v3/pricing/open"),
    UPDATE_OPEN_PRICING("/v3/pricing/open/%s"),
    GET_ALL_OPEN_PRICING("/v3/pricing/open"),
    GET_OPEN_PRICING("/v3/pricing/open/%s"),
    DELETE_OPEN_PRICING("/v3/pricing/open/%s"),

    //Media
    CREATE_MEDIA("/v2/media"),
    GET_MEDIA("/v2/media/%s"),
    GET_ALL_MEDIA("/v2/media"),
    UPDATE_MEDIA("/v2/media/%s"),
    DELETE_MEDIA("/v2/media/%s"),

    //Ad Spot
    CREATE_ADSPOT("/v2/adspots"),
    GET_ADSPOT("/v2/adspots/%s"),
    GET_ALL_ADSPOTS("/v2/adspots"),
    UPDATE_ADSPOT("/v2/adspots/%s");

    private String endpoint;

    public String setParameters(Object... parameters) {
        return parameters.length > 0 ? String.format(endpoint, parameters) : endpoint;
    }

}
