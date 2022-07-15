package api.preconditionbuilders;


import api.core.client.HttpClient;
import api.dto.GenericResponse;
import api.dto.rx.protection.*;
import api.services.ProtectionsService;
import configurations.User;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Getter
@AllArgsConstructor
public class ProtectionsPrecondition {

    private ProtectionRequest protectionsRequest;
    private Protection protectionsResponse;
    private GenericResponse<Protection> protectionsGetAllResponse;

    private ProtectionsPrecondition(ProtectionsPreconditionBuilder builder) {

        this.protectionsRequest = builder.protectionsRequest;
        this.protectionsResponse = builder.protectionsResponse;
        this.protectionsGetAllResponse = builder.protectionsGetAllResponse;
    }

    public static ProtectionsPreconditionBuilder protection() {
        return new ProtectionsPreconditionBuilder();
    }

    public static class ProtectionsPreconditionBuilder {

        private Response response;
        private Protection protectionsResponse;
        private ProtectionRequest protectionsRequest;
        private GenericResponse protectionsGetAllResponse;
        private ProtectionsService protectionsService = new ProtectionsService();

        public ProtectionsPreconditionBuilder createNewRandomProtection() {
            this.protectionsRequest = ProtectionRequest.builder()
                    .name(captionWithSuffix("Test_Rule"))
                    .publisherId(4)
                    .typeId(2)
                    .active(true)
                    .rule(Rule.builder()
                            .adspot(AdSpot.builder()
                                    .includedAdspots(List.of(58))
                                    .excludedAdspots(List.of())
                                    .build())
                            .adFormat(AdFormat.builder()
                                    .adFormats(List.of(2, 3))
                                    .exclude(false)
                                    .build())
                            .adSize(AdSize.builder()
                                    .adSizes(List.of(10))
                                    .exclude(false)
                                    .build())
                            .media(Media.builder()
                                    .media(List.of(14))
                                    .exclude(false)
                                    .build())
                            .deviceType(DeviceType.builder()
                                    .deviceTypes(List.of(4))
                                    .exclude(false)
                                    .build())
                            .ifa(Ifa.builder()
                                    .ifas(List.of())
                                    .exclude(false)
                                    .build())
                            .geo(Geo.builder()
                                    .geos(List.of(221))
                                    .exclude(false)
                                    .build())
                            .deviceOS(DeviceOS.builder()
                                    .deviceOSs(List.of(4))
                                    .exclude(false)
                                    .build())
                            .dsp(Dsp.builder()
                                    .dsps(null)
                                    .exclude(false)
                                    .build())
                            .dspSeat(DspSeat.builder()
                                    .dspSeats(null)
                                    .exclude(false)
                                    .build())
                            .advertiser(Advertiser.builder()
                                    .advertisers(List.of(
                                            "tester-tester",
                                            "  Slotomania™ Vegas Casino Slots",
                                            " Activated Charcoal Whitening Toothpaste. | Search Ads",
                                            " Auto ibride | Ricerca annunci",
                                            " Cosphera Nahrungsergänzungsmittel ",
                                            "- 72 ANS: Test d'éligibilité gratuit en 1 clic"))
                                    .category(Category.builder()
                                            .blockUnknownCategories(false)
                                            .categories(List.of(2, 4, 6, 8))
                                            .exclude(false)
                                            .build())
                                    .build())
                            .build())
                    .build();
            this.response = protectionsService.createProtection(protectionsRequest);
            this.protectionsResponse = response.as(Protection.class);

            return this;
        }

        public ProtectionsPreconditionBuilder deleteProtection(int id) {
            this.response = protectionsService.deleteProtection(id);

            return this;
        }

        public ProtectionsPreconditionBuilder setCredentials(User user) {
            HttpClient.setCredentials(user);

            return this;
        }

        public ProtectionsPrecondition build() {

            return new ProtectionsPrecondition(this);
        }

        private List<Protection> getProtectionsResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", Protection.class));
        }

        public ProtectionsPreconditionBuilder getProtectionsWithFilter(Map<String, Object> queryParams) {
            this.response = protectionsService.getProtectionsWithFilter(queryParams);

            this.protectionsGetAllResponse = this.response.as(GenericResponse.class);

            return this;
        }

        public Response getResponse(){

            return this.response;
        }
    }
}
