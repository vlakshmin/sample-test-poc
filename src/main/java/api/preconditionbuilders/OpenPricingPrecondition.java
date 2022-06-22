package api.preconditionbuilders;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.yield.openPricing.*;
import api.services.OpenPricingService;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import static zutils.FakerUtils.captionWithSuffix;

@Slf4j
@Getter
@AllArgsConstructor
public class OpenPricingPrecondition {

    private OpenPricing openPricingResponse;
    private OpenPricingRequest openPricingRequest;
    private List<OpenPricing> openPricingResponseList;

    private OpenPricingPrecondition(OpenPricingPreconditionBuilder builder) {
        this.openPricingRequest = builder.openPricingRequest;
        this.openPricingResponse = builder.openPricingResponse;
        this.openPricingResponseList = builder.openPricingResponseList;
    }

    public static OpenPricingPreconditionBuilder openPricing() {

        return new OpenPricingPreconditionBuilder();
    }

    public static class OpenPricingPreconditionBuilder {

        private Response response;
        private OpenPricing openPricingResponse;
        private OpenPricingRequest openPricingRequest;
        private List<OpenPricing> openPricingResponseList;
        private OpenPricingService openPricingService = new OpenPricingService();

        public OpenPricingPreconditionBuilder createNewOpenPricing() {
            Publisher publisher = PublisherPrecondition.publisher()
                    .createNewPublisher()
                    .build()
                    .getPublisherResponse();

            this.openPricingRequest = OpenPricingRequest.builder()
                    .name(captionWithSuffix("OpenPricing"))
                    .active(true)
                    .floorPrice(5.66)
                    .notes("autotest")
                    .priority(1)
                    .publisherName(publisher.getName())
                    .publisherId(publisher.getId())
                    .rule(Rule.builder()
                            .adspot(Adspot.builder()
                                    .includedAdspots(List.of(58))
                                    .excludedAdspots(List.of())
                                    .build())
                            .adFormat(AdFormat.builder()
                                    .adFormats(List.of(2,3))
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

            this.openPricingResponseList = this.getOpenPricingResponseList();

            return this;
        }

        private List<OpenPricing> getOpenPricingResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", OpenPricing[].class));
        }

        public OpenPricingPrecondition build() {

            return new OpenPricingPrecondition(this);
        }
    }
}
