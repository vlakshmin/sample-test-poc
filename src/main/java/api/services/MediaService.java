package api.services;

import api.dto.rx.inventory.media.MediaRequest;
import io.restassured.response.Response;

import static api.core.RakutenExchangeApi.CREATE_MEDIA;
import static api.core.RakutenExchangeApi.GET_ALL_MEDIA;
import static api.core.client.HttpClient.*;

public class MediaService extends BaseService {
    public Response createMedia(MediaRequest body) {
        URL = initURL(CREATE_MEDIA);

        return post(URL, body.toJson());
    }

    public Response getAll() {
        URL = initURL(GET_ALL_MEDIA);

        return get(URL);
    }

}
