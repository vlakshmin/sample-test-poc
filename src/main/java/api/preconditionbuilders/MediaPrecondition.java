package api.preconditionbuilders;

import api.dto.rx.inventory.filter.Filter;
import api.dto.rx.inventory.media.Media;
import api.dto.rx.inventory.media.MediaRequest;
import api.services.MediaService;
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
public class MediaPrecondition {

    private Media mediaResponse;
    private MediaRequest mediaRequest;
    private List<Media> mediaResponseList;

    private MediaPrecondition(MediaPreconditionBuilder builder) {
        this.mediaRequest = builder.mediaRequest;
        this.mediaResponse = builder.mediaResponse;
        this.mediaResponseList = builder.mediaResponseList;
    }

    public static MediaPreconditionBuilder media() {

        return new MediaPreconditionBuilder();
    }

    public static class MediaPreconditionBuilder {

        private Response response;
        private Media mediaResponse;
        private MediaRequest mediaRequest;
        private List<Media> mediaResponseList;
        private MediaService mediaService = new MediaService();


        public MediaPreconditionBuilder createNewMedia() {

            this.mediaRequest = mediaRequest.builder()
                    .name(captionWithSuffix("Media"))
                 //   .publisherId(getPublisherId())
                    .platformId(2)
                    .url("http://localhost:5016")
                    .isEnabled(true)

                    .categoryIds(List.of(1, 9))

                    .build();

            this.response = mediaService.createMedia(mediaRequest);
            this.mediaResponse = response.as(Media.class);

            return this;
        }

        public MediaPreconditionBuilder getAllMediaList() {
            this.response = mediaService.getAll();

            this.mediaResponseList = this.getMediaResponseList();

            return this;
        }

        private List<Media> getMediaResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", Media[].class));
        }

        public MediaPrecondition build() {

            return new MediaPrecondition(this);
        }
    }
}
