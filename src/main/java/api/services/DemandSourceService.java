package api.services;

import api.dto.rx.demandsource.DemandSource;
import io.restassured.response.Response;

import java.util.Map;

import static api.core.RakutenExchangeApi.*;
import static api.core.client.HttpClient.get;
import static api.core.client.HttpClient.initURL;
import static api.core.client.HttpClient.post;

public class DemandSourceService extends BaseService {

    public Response getAll() {
        URL = initURL(GET_ALL_DSPS);

        return get(URL);
    }

    public Response createDSP(DemandSource body) {
        URL = initURL(CREATE_DSP);

        return post(URL, body.toJson());
    }

    public Response getDSPsWithFilter(Map<String, Object> queryParams) {
        URL = initURL(GET_ALL_DSPS);

        return get(URL, queryParams);
    }
}
