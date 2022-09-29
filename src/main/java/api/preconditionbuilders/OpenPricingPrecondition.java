package api.preconditionbuilders;

import api.core.client.HttpClient;
import api.dto.rx.yield.openpricing.OpenPricingRequest;
import api.dto.rx.yield.openpricing.*;
import api.dto.GenericResponse;
import api.dto.rx.inventory.adspot.Banner;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.adspot.AdSpotRequest;
import api.dto.rx.inventory.media.Media;
import api.services.OpenPricingService;
import configurations.User;
import io.restassured.common.mapper.TypeRef;
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
public class OpenPricingPrecondition {

    private Integer responseCode;
    private OpenPricing openPricingResponse;
    private String openPricingExportResponse;
    private OpenPricingRequest openPricingRequest;
    private GenericResponse<OpenPricing> openPricingGetAllResponse;

    private OpenPricingPrecondition(OpenPricingPreconditionBuilder builder) {
        this.responseCode = builder.responseCode;
        this.openPricingRequest = builder.openPricingRequest;
        this.openPricingResponse = builder.openPricingResponse;
        this.openPricingExportResponse = openPricingExportResponse;
        this.openPricingGetAllResponse = builder.openPricingGetAllResponse;
    }

    public static OpenPricingPreconditionBuilder openPricing() {

        return new OpenPricingPreconditionBuilder();
    }

    public static class OpenPricingPreconditionBuilder {

        private Response response;
        private Integer responseCode;
        private OpenPricing openPricingResponse;
        private String openPricingExportResponse;
        private OpenPricingRequest openPricingRequest;
        private GenericResponse<OpenPricing> openPricingGetAllResponse;
        private OpenPricingService openPricingService = new OpenPricingService();

        public OpenPricingPreconditionBuilder createNewOpenPricing() {

            Media media = MediaPrecondition.media().createNewMedia().build().getMediaResponse();

            AdSpot adSpot = AdSpotPrecondition.adSpot().
                    createNewAdSpot(AdSpotRequest.builder()
                            .name(captionWithSuffix("AdSpot"))
                            .enabled(true)
                            .publisherId(media.getPublisherId())
                            .publisherName(media.getPublisherName())
                            .floorPrice(9.99)
                            .mediaId(media.getId())
                            .coppa(true)
                            .categoryIds(List.of(1, 2))
                            .sizeIds(List.of(10))
                            .banner(Banner.builder()
                                    .enabled(true)
                                    .floorPrice(8.88)
                                    .sizeIds(List.of(3))
                                    .build())
                            .build())
                    .build()
                    .getAdSpotResponse();

            this.openPricingRequest = OpenPricingRequest.builder()
                    .name(captionWithSuffix("OpenPricing"))
                    .active(true)
                    .floorPrice(5.66)
                    .notes("autotest")
                    .priority(1)
                    .publisherName(media.getPublisherName())
                    .publisherId(media.getPublisherId())
                    .rule(Rule.builder()
                            .adspot(AdSpotRule.builder()
                                    .includedAdspots(List.of(adSpot.getId()))
                                    .excludedAdspots(List.of())
                                    .build())
                            .adFormat(AdFormat.builder()
                                    .adFormats(List.of(2, 3))
                                    .exclude(false)
                                    .build())
                            .adSize(AdSize.builder()
                                    .adSizes(List.of(10))
                                    .exclude(false).build())
                            .media(MediaRule.builder()
                                    .media(List.of(media.getId()))
                                    .exclude(false).build())
                            .deviceType(DeviceType.builder()
                                    .deviceTypes(List.of(4))
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
                            .build())
                    .build();

            this.response = openPricingService.createOpenPricing(openPricingRequest);
            this.openPricingResponse = response.as(OpenPricing.class);
            this.responseCode = response.getStatusCode();

            return this;
        }

        public OpenPricingPreconditionBuilder export(int publisherId) {
            this.response = openPricingService.export(Map.of("publisher_id", String.valueOf(publisherId)));

            this.openPricingExportResponse = this.response.as(String.class);
            this.responseCode = response.getStatusCode();

            return this;
        }

        public OpenPricingPreconditionBuilder getOpenPricingList() {
            this.response = openPricingService.getAll();

            this.openPricingGetAllResponse = this.response.as(new TypeRef<GenericResponse<OpenPricing>>() {});
            this.responseCode = response.getStatusCode();

            return this;
        }

        private List<OpenPricing> getOpenPricingResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", OpenPricing[].class));
        }

        public OpenPricingPreconditionBuilder deleteOpenPricing(int id) {
            this.response = openPricingService.deleteOpenPricing(id);
            this.responseCode = response.getStatusCode();

            return this;
        }

        public OpenPricingPreconditionBuilder setCredentials(User user) {
            HttpClient.setCredentials(user);

            return this;
        }

        public OpenPricingPrecondition build() {

            return new OpenPricingPrecondition(this);
        }

        public OpenPricingPreconditionBuilder getOpenPricingWithFilter(Map<String, Object> queryParams) {
            this.response = openPricingService.getOpenPricingWithFilter(queryParams);

            this.openPricingGetAllResponse = this.response.as(new TypeRef<GenericResponse<OpenPricing>>() {});
            this.responseCode = response.getStatusCode();

            return this;
        }
    }
}
