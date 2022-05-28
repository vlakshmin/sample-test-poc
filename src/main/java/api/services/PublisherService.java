package api.services;

import api.dto.rx.publisher.PublisherRequest;
import io.restassured.response.Response;

import static api.core.RakutenExchangeApi.CREATE_PUBLISHER;
import static api.core.client.HttpClient.initURL;
import static api.core.client.HttpClient.post;

public class PublisherService extends BaseService {

    public Response createPublisher(PublisherRequest body) {
        URL = initURL(CREATE_PUBLISHER);

        return post(URL, body.toJson());
    }
}
