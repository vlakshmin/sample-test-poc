package api.services;

import api.dto.rx.inventory.adSpot.AdSpotRequest;
import io.restassured.response.Response;

import static api.core.RakutenExchangeApi.CREATE_ADSPOT;
import static api.core.RakutenExchangeApi.GET_ALL_ADSPOTS;
import static api.core.client.HttpClient.*;

public class AdSpotService extends BaseService {
    public Response createAdSpot(AdSpotRequest body) {
        URL = initURL(CREATE_ADSPOT);

        return post(URL, body.toJson());
    }

    public Response getAll() {
        URL = initURL(GET_ALL_ADSPOTS);

        return get(URL);
    }

}
