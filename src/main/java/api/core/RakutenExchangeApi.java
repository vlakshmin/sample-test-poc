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

    //Users
    CREATE_USER("/v2/accounts"),
    GET_USER("/v2/accounts/%s"),
    GET_ALL_USERS("/v2/accounts"),
    UPDATE_USER("/v2/accounts/%s");

    private String endpoint;

    public String setParameters(Object... parameters) {
        return parameters.length > 0 ? String.format(endpoint, parameters) : endpoint;
    }

}
