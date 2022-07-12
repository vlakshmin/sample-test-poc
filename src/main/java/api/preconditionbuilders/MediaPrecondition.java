package api.preconditionbuilders;

import api.core.client.HttpClient;
import api.dto.GenericResponse;
import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.media.Media;
import api.dto.rx.inventory.media.MediaRequest;
import api.services.MediaService;
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
public class MediaPrecondition {

    private Media mediaResponse;
    private MediaRequest mediaRequest;
    private GenericResponse<Media> mediaGetAllResponse;


    private MediaPrecondition(MediaPreconditionBuilder builder) {
        this.mediaRequest = builder.mediaRequest;
        this.mediaResponse = builder.mediaResponse;
        this.mediaGetAllResponse = builder.mediaGetAllResponse;
    }

    public static MediaPreconditionBuilder media() {

        return new MediaPreconditionBuilder();
    }

    public static class MediaPreconditionBuilder {

        private Response response;
        private Media mediaResponse;
        private MediaRequest mediaRequest;
        private GenericResponse<Media> mediaGetAllResponse;
        private final MediaService mediaService = new MediaService();


        public MediaPreconditionBuilder createNewMedia() {

            Publisher publisher = PublisherPrecondition.publisher()
                    .createNewPublisher()
                    .build()
                    .getPublisherResponse();

            this.mediaRequest = MediaRequest.builder()
                    .name(captionWithSuffix("Media"))
                    .publisherId(publisher.getId())
                    .platformId(2)
                    .url("http://localhost:5016")
                    .isEnabled(true)
                    .categoryIds(List.of(1, 9))
                    .build();

            this.response = mediaService.createMedia(mediaRequest);
            this.mediaResponse = response.as(Media.class);

            return this;
        }

        public MediaPreconditionBuilder createNewMedia(MediaRequest mediaRequest) {

            this.response = mediaService.createMedia(mediaRequest);
            this.mediaResponse = response.as(Media.class);

            return this;
        }
        public MediaPreconditionBuilder getAllMediaList() {
            this.response = mediaService.getAll();

            this.mediaGetAllResponse = this.response.as(new GenericResponse<Media>().getClass());

            return this;
        }

        public MediaPreconditionBuilder getMediaWithFilter(Map<String, Object> queryParams) {
            this.response = mediaService.getMediaWithFilter(queryParams);

            this.mediaGetAllResponse = this.response.as(new GenericResponse<Media>().getClass());

            return this;
        }

        private List<Media> getMediaResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", Media[].class));
        }

        public MediaPreconditionBuilder deleteMedia(int id) {
            this.response = mediaService.deleteMedia(id);

            return this;
        }

        public MediaPreconditionBuilder setCredentials(User user){
            HttpClient.setCredentials(user);

            return this;
        }
        public MediaPrecondition build() {
            HttpClient.setCredentials(User.TEST_USER);

            return new MediaPrecondition(this);
        }

    }
}
