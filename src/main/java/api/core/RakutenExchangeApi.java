package api.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RakutenExchangeApi {
    CREATE_RULE("/protections/targeting"),

    // Data Container
    GET_DATA_CONTAINER("/data-containers/%s"),
    CREATE_DATA_CONTAINER("/data-containers"),
    GET_ALL_DATA_CONTAINERS("/data-containers"),
    UPDATE_DATA_CONTAINER("/data-containers/%s"),
    DELETE_DATA_CONTAINER("/data-containers/%s"),

    // Data Container Configuration
    GET_ALL_FIELDS_BY_CONTAINER("/data-containers/%s/configuration"),
    ADD_FIELD_TO_DATA_CONTAINER("/data-containers/%s/configuration"),
    UPDATE_DATA_CONTAINER_CONFIG("/data-containers/%s/configuration/%s"),
    DELETE_DATA_CONTAINER_CONFIGURATION("/data-containers/%s/configuration/%s"),
    GET_DATA_TABLES("/data-tables");

    private String endpoint;

    public String setParameters(Object... parameters) {
        return parameters.length > 0 ? String.format(endpoint, parameters) : endpoint;
    }

}
