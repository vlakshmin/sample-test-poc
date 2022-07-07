package api.services;

import api.dto.rx.yield.openpricing.OpenPricingRequest;
import io.restassured.response.Response;

import java.util.Map;

import static api.core.RakutenExchangeApi.*;
import static api.core.client.HttpClient.*;

public class OpenPricingService extends BaseService{

    public Response createOpenPricing(OpenPricingRequest body) {
        URL = initURL(CREATE_OPEN_PRICING);

        return post(URL, body.toJson());
    }
    public Response getOpenPricingWithFilter(Map<String, Object> queryParams) {
        URL = initURL(GET_ALL_OPEN_PRICING);

        return get(URL,queryParams);
    }

    public Response getAll(){
        URL = initURL(GET_ALL_OPEN_PRICING);

        return get(URL);
    }
}
