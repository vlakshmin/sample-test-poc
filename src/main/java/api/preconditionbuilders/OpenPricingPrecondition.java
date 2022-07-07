package api.preconditionbuilders;

import api.dto.rx.yield.openpricing.OpenPricingRequest;
import api.dto.rx.yield.openpricing.*;
import api.dto.GenericResponse;
import api.dto.rx.inventory.adspot.Banner;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.adspot.AdSpotRequest;
import api.dto.rx.inventory.media.Media;
import api.services.OpenPricingService;
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

    private OpenPricing openPricingResponse;
    private OpenPricingRequest openPricingRequest;
    private GenericResponse<OpenPricing> openPricingGetAllResponse;

    private OpenPricingPrecondition(OpenPricingPreconditionBuilder builder) {
        this.openPricingRequest = builder.openPricingRequest;
        this.openPricingResponse = builder.openPricingResponse;
        this.openPricingGetAllResponse = builder.openPricingGetAllResponse;
    }

    public static OpenPricingPreconditionBuilder openPricing() {

        return new OpenPricingPreconditionBuilder();
    }

    public static class OpenPricingPreconditionBuilder {

        private Response response;
        private OpenPricing openPricingResponse;
        private OpenPricingRequest openPricingRequest;
        private List<OpenPricing> openPricingResponseList;
        private GenericResponse openPricingGetAllResponse;
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
                    .rule(api.dto.rx.yield.openpricing.Rule.builder()
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

            return this;
        }

        public OpenPricingPreconditionBuilder getOpenPricingList() {
            this.response = openPricingService.getAll();

            this.openPricingGetAllResponse = this.response.as(new GenericResponse<OpenPricing>().getClass());

            return this;
        }

        private List<OpenPricing> getOpenPricingResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", OpenPricing[].class));
        }

        public OpenPricingPrecondition build() {

            return new OpenPricingPrecondition(this);
        }
        public OpenPricingPreconditionBuilder getOpenPricingWithFilter(Map<String, Object> queryParams) {
            this.response = openPricingService.getOpenPricingWithFilter(queryParams);

            this.openPricingGetAllResponse = this.response.as(new GenericResponse<Media>().getClass());

            return this;
        }
    }
}
