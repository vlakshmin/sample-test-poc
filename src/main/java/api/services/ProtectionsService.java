package api.services;

import api.dto.rx.protection.ProtectionRequest;
import io.restassured.response.Response;

import static api.core.RakutenExchangeApi.*;
import static api.core.client.HttpClient.*;

public class ProtectionsService extends BaseService {

    public Response createRule(ProtectionRequest body) {
        URL = initURL(CREATE_RULE);

        return post(URL, body.toJson());
    }
}
