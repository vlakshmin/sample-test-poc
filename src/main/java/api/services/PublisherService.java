package api.services;

import api.dto.rx.admin.publisher.PublisherRequest;
import io.restassured.response.Response;

import static api.core.RakutenExchangeApi.CREATE_PUBLISHER;
import static api.core.RakutenExchangeApi.GET_PUBLISHERS;
import static api.core.client.HttpClient.*;

public class PublisherService extends BaseService {

    public Response createPublisher(PublisherRequest body) {
        URL = initURL(CREATE_PUBLISHER);

        return post(URL, body.toJson());
    }

    public Response getAll(){
        URL = initURL(GET_PUBLISHERS);

        return get(URL);
    }
}
