package api.preconditionbuilders;

import api.dto.GenericResponse;
import api.dto.rx.common.Currency;
import api.dto.rx.inventory.adspot.*;
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
    private GenericResponse<AdSpot> adSpotsGetAllResponse;

    private AdSpotPrecondition(AdSpotPreconditionBuilder builder) {
        this.adSpotRequest = builder.adSpotRequest;
        this.adSpotResponse = builder.adSpotResponse;
        this.adSpotsGetAllResponse = builder.adSpotsGetAllResponse;
    }

    public static AdSpotPreconditionBuilder adSpot() {

        return new AdSpotPreconditionBuilder();
    }

    public static class AdSpotPreconditionBuilder {

        private Response response;
        private AdSpot adSpotResponse;
        private AdSpotRequest adSpotRequest;
        private List<AdSpot> adSpotsResponseList;
        private AdSpotService adSpotService = new AdSpotService();
        private GenericResponse adSpotsGetAllResponse;
        private Video video = new Video();
        private Banner banner = new Banner();
        private Native nativeObj = new Native();
        private NativeVideo nativeVideo = new NativeVideo();

        private Media media;

        public AdSpotPreconditionBuilder createNewAdSpot() {

            media = MediaPrecondition.media()
                    .createNewMedia()
                    .build()
                    .getMediaResponse();

            video = Video.builder()
                    .floorPrice(23.00)
                    .maxDuration(10)
                    .enabled(true)
                    .sizeIds(List.of(10))
                    .playbackMethodIds(List.of(1))
                    .build();


            this.adSpotRequest = adSpotRequest.builder()
                    .name(captionWithSuffix("ad spot"))
                    .publisherId(media.getPublisherId())
                    .currency(Currency.JPY.name())
                    .floorPrice(11.00)
                    .mediaId(media.getId())
                    .positionId(1)
                    .coppa(true)
                    .sizeIds(List.of(10))
                    .floorPriceAutomated(true)
                    .testMode(false)
                    .categoryIds(List.of(1, 9))
                    .video(video)

                    .build();

            this.response = adSpotService.createAdSpot(adSpotRequest);
            this.adSpotResponse = response.as(AdSpot.class);

            return this;
        }

        public AdSpotPreconditionBuilder createNewAdSpot(AdSpotRequest adSpotRequest) {

            this.response = adSpotService.createAdSpot(adSpotRequest);
            this.adSpotResponse = response.as(AdSpot.class);

            return this;
        }

        public AdSpotPreconditionBuilder getAllAdSpotsList() {
            this.response = adSpotService.getAll();

            this.adSpotsGetAllResponse = this.response.as(new GenericResponse<AdSpot>().getClass());

            return this;
        }


        private List<AdSpot> getAdSpotsResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", AdSpot[].class));
        }

        public AdSpotPrecondition build() {

            return new AdSpotPrecondition(this);
        }
    }
    public static void main(String[] args) {

        System.out.println(
                AdSpotPrecondition.adSpot()
                        .getAllAdSpotsList()
                        .build()
                        .getAdSpotsGetAllResponse().getTotal()
        );
    }
}
