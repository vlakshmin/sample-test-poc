package api.preconditionbuilders;

import api.dto.rx.admin.publisher.Currency;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.adspot.AdSpotRequest;
import api.dto.rx.inventory.media.Media;
import api.services.AdSpotService;
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
public class AdSpotPrecondition {

    private AdSpot adSpotResponse;
    private AdSpotRequest adSpotRequest;
    private List<AdSpot> adSpotsResponseList;

    private AdSpotPrecondition(AdSpotPrecondition.AdSpotPreconditionBuilder builder) {
        this.adSpotRequest = builder.adSpotRequest;
        this.adSpotResponse = builder.adSpotResponse;
        this.adSpotsResponseList = builder.adSpotsResponseList;
    }

    public static AdSpotPrecondition.AdSpotPreconditionBuilder adSpot() {

        return new AdSpotPrecondition.AdSpotPreconditionBuilder();
    }

    public static class AdSpotPreconditionBuilder {

        private Response response;
        private AdSpot adSpotResponse;
        private AdSpotRequest adSpotRequest;
        private List<AdSpot> adSpotsResponseList;
        private AdSpotService adSpotService = new AdSpotService();
        private Media media;

        public AdSpotPrecondition.AdSpotPreconditionBuilder createNewAdSpot() {

            media = MediaPrecondition.media()
                    .createNewMedia()
                    .build()
                    .getMediaResponse();

            this.adSpotRequest = adSpotRequest.builder()
                    .name(captionWithSuffix("ad spot"))
                    .filterId(media.getFilterId())
                    .publisherId(media.getPublisherId())
                    .currency(Currency.JPY.name())
                    .floorPrice(11)
                    .mediaId(media.getId())
                    .positionId(1)
                    .coppa(true)
                    .floorPriceAutomated(true)
                    .testMode(false)
                    .categoryIds(List.of(1, 9))


                    .build();

            this.response = adSpotService.createAdSpot(adSpotRequest);
            this.adSpotResponse = response.as(AdSpot.class);

            return this;
        }

        public AdSpotPrecondition.AdSpotPreconditionBuilder getAllAdSpotsList() {
            this.response = adSpotService.getAll();

            this.adSpotsResponseList = this.getAdSpotsResponseList();

            return this;
        }

        private List<AdSpot> getAdSpotsResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", AdSpot[].class));
        }

        public AdSpotPrecondition build() {

            return new AdSpotPrecondition(this);
        }
    }
}
