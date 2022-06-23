package api.services;

import api.dto.rx.inventory.filter.FilterRequest;
import io.restassured.response.Response;

import static api.core.RakutenExchangeApi.CREATE_FILTER;
import static api.core.RakutenExchangeApi.GET_ALL_FILTERS;
import static api.core.client.HttpClient.*;

public class FilterService extends BaseService {
    public Response createFilter(FilterRequest body) {
        URL = initURL(CREATE_FILTER);

        return post(URL, body.toJson());
    }

    public Response getAll() {
        URL = initURL(GET_ALL_FILTERS);

        return get(URL);
    }

}
