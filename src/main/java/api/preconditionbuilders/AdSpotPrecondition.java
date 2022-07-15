package api.preconditionbuilders;

import api.core.client.HttpClient;
import api.dto.GenericResponse;
import api.dto.rx.common.Currency;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.adspot.AdSpotRequest;
import api.dto.rx.inventory.adspot.Video;
import api.dto.rx.inventory.media.Media;
import api.services.AdSpotService;
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
        private final AdSpotService adSpotService = new AdSpotService();
        private GenericResponse<AdSpot> adSpotsGetAllResponse;

        public AdSpotPreconditionBuilder createNewAdSpot() {

            Media media = MediaPrecondition.media()
                    .createNewMedia()
                    .build()
                    .getMediaResponse();

            Video video = Video.builder()
                    .floorPrice(23.00)
                    .maxDuration(10)
                    .enabled(true)
                    .sizeIds(List.of(10))
                    .playbackMethodIds(List.of(1))
                    .build();


            this.adSpotRequest = AdSpotRequest.builder()
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

        public AdSpotPreconditionBuilder getAdSpotsWithFilter(Map<String, Object> queryParams) {
            this.response = adSpotService.getAdSpotsWithFilter(queryParams);

            this.adSpotsGetAllResponse = this.response.as(new GenericResponse<AdSpot>().getClass());

            return this;
        }

        public AdSpotPreconditionBuilder deleteAdSpot(int id) {
            this.response = adSpotService.deleteAdSpot(id);

            return this;
        }

        public AdSpotPreconditionBuilder setCredentials(User user){
            HttpClient.setCredentials(user);

            return this;
        }
        public AdSpotPrecondition build() {

            return new AdSpotPrecondition(this);
        }

        public Response getResponse(){

            return this.response;
        }

    }
}
