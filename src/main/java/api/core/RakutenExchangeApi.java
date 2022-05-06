package api.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RakutenExchangeApi {
    //Rules
    GET_RULE("/v3/protections/targeting/%s"),
    CREATE_RULE("/v3protections/targeting"),

    //Publisher
    CREATE_PUBLISHER("/v2/publishers");

    private String endpoint;

    public String setParameters(Object... parameters) {
        return parameters.length > 0 ? String.format(endpoint, parameters) : endpoint;
    }

}
