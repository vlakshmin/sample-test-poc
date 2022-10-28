package api.services;

import api.dto.rx.sales.deals.Deal;
import api.dto.rx.sales.deals.DealRequest;
import io.restassured.response.Response;

import static api.core.RakutenExchangeApi.CREATE_DEAL;
import static api.core.RakutenExchangeApi.UPDATE_DEAL;
import static api.core.client.HttpClient.*;

public class DealService extends BaseService {

    public Response getDealById(int id) {
        URL = initURL(CREATE_DEAL.setParameters(id));

        return get(URL);
    }

    public Response createDeal(DealRequest body) {
        URL = initURL(CREATE_DEAL);

        return post(URL, body.toJson());
    }

    public Response getAll() {
        URL = initURL(CREATE_DEAL);

        return get(URL);
    }

    public Response updateDeal(Deal deal) {
        URL = initURL(UPDATE_DEAL.setParameters(deal.getId()));

        return put(URL, deal.toJson());
    }

}
