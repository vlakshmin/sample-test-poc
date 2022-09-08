package api.services;

import io.restassured.response.Response;

import java.util.Map;

import static api.core.RakutenExchangeApi.*;
import static api.core.client.HttpClient.get;
import static api.core.client.HttpClient.initURL;

public class DemandSourceService extends BaseService {

    public Response getAll() {
        URL = initURL(GET_ALL_DSPS);

        return get(URL);
    }

    public Response createDSP() {
        URL = initURL(CREATE_DSP);

        return get(URL);
    }

    public Response getDSPsWithFilter(Map<String, Object> queryParams) {
        URL = initURL(GET_ALL_DSPS);

        return get(URL,queryParams);
    }
}
