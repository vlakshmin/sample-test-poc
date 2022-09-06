package api.services;

import io.restassured.response.Response;

import static api.core.RakutenExchangeApi.CREATE_DSP;
import static api.core.RakutenExchangeApi.GET_ALL_DSPS;
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
}
