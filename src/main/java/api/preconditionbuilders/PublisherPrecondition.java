package api.preconditionbuilders;

import api.core.client.HttpClient;
import api.dto.GenericResponse;
import api.dto.rx.common.Currency;
import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.admin.publisher.PublisherRequest;
import api.services.PublisherService;
import configurations.User;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static zutils.FakerUtils.*;

@Slf4j
@Getter
@AllArgsConstructor
public class PublisherPrecondition {

    private Integer responseCode;
    private Publisher publisherResponse;
    private PublisherRequest publisherRequest;
    private GenericResponse<Publisher> publisherGetAllResponse;

    private PublisherPrecondition(PublisherPreconditionBuilder builder) {
        this.responseCode = builder.responseCode;
        this.publisherRequest = builder.publisherRequest;
        this.publisherResponse = builder.publisherResponse;
        this.publisherGetAllResponse = builder.publisherGetAllResponse;
    }

    public static PublisherPreconditionBuilder publisher() {

        return new PublisherPreconditionBuilder();
    }

    public static class PublisherPreconditionBuilder {

        private Response response;
        private Integer responseCode;
        private Publisher publisherResponse;
        private PublisherRequest publisherRequest;
        private GenericResponse<Publisher> publisherGetAllResponse;
        private PublisherService publisherService = new PublisherService();

        public PublisherPreconditionBuilder createNewPublisher() {
            this.publisherRequest = PublisherRequest.builder()
                    .name(captionWithSuffix("Publisher_Auto"))
                    .salesAccountName("ops_persoj")
                    .mail(randomMail())
                    .isEnabled(true)
                    .domain(randomUrl())
                    .currency(Currency.JPY.name())
                    .categoryIds(List.of(1, 9))
                    .dspIds(List.of(7))
                    .build();

            this.response = publisherService.createPublisher(publisherRequest);
            this.publisherResponse = response.as(Publisher.class);
            this.responseCode = response.getStatusCode();

            return this;
        }

        public PublisherPreconditionBuilder createNewPublisher(PublisherRequest publisherRequest) {
            this.response = publisherService.createPublisher(publisherRequest);
            this.publisherResponse = response.as(Publisher.class);
            this.responseCode = response.getStatusCode();

            return this;
        }

        public PublisherPreconditionBuilder updatePublisher(PublisherRequest publisherRequest, int id) {
            this.response = publisherService.updatePublisher(publisherRequest, id);
            this.responseCode = response.getStatusCode();

            return this;
        }

        public PublisherPreconditionBuilder getPublishersList() {
            this.response = publisherService.getAll();

            this.publisherGetAllResponse = this.response.as(new GenericResponse<Publisher>().getClass());
            this.responseCode = response.getStatusCode();

            return this;
        }

        private List<Publisher> getPublisherResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", Publisher[].class));
        }

        public PublisherPreconditionBuilder deletePublisher(int id) {
            this.response = publisherService.deletePublisher(id);
            this.responseCode = response.getStatusCode();

            return this;
        }

        public PublisherPreconditionBuilder setCredentials(User user) {
            HttpClient.setCredentials(user);

            return this;
        }

        public PublisherPrecondition build() {
            HttpClient.setCredentials(User.TEST_USER);

            return new PublisherPrecondition(this);
        }

        public PublisherPreconditionBuilder getPublisherWithFilter(Map<String, Object> queryParams) {
            this.response = publisherService.getPublisherWithFilter(queryParams);

            this.publisherGetAllResponse = this.response.as(GenericResponse.class);
            this.responseCode = response.getStatusCode();

            return this;
        }
    }
}
