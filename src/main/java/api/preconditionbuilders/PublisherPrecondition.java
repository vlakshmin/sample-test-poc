package api.preconditionbuilders;

import api.dto.rx.common.Currency;
import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.admin.publisher.PublisherRequest;
import api.services.PublisherService;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import static zutils.FakerUtils.*;

@Slf4j
@Getter
@AllArgsConstructor
public class PublisherPrecondition {

    private Publisher publisherResponse;
    private PublisherRequest publisherRequest;
    private List<Publisher> publisherResponseList;

    private PublisherPrecondition(PublisherPreconditionBuilder builder) {
        this.publisherRequest = builder.publisherRequest;
        this.publisherResponse = builder.publisherResponse;
        this.publisherResponseList = builder.publisherResponseList;
    }

    public static PublisherPreconditionBuilder publisher() {

        return new PublisherPreconditionBuilder();
    }

    public static class PublisherPreconditionBuilder {

        private Response response;
        private Publisher publisherResponse;
        private PublisherRequest publisherRequest;
        private List<Publisher> publisherResponseList;
        private PublisherService publisherService = new PublisherService();

        public PublisherPreconditionBuilder createNewPublisher() {
            this.publisherRequest = PublisherRequest.builder()
                    .name(captionWithSuffix("Publisher"))
                    .salesAccountName("ops_persoj")
                    .mail(randomMail())
                    .isEnabled(true)
                    .domain(randomUrl())
                    .currency(Currency.JPY.name())
                    .categoryIds(List.of(1,9))
                    .dspIds(List.of(7))
                    .build();

            this.response = publisherService.createPublisher(publisherRequest);
            this.publisherResponse = response.as(Publisher.class);

            return this;
        }

        public PublisherPreconditionBuilder getPublishersList() {
            this.response = publisherService.getAll();

            this.publisherResponseList = this.getPublisherResponseList();

            return this;
        }

        private List<Publisher> getPublisherResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", Publisher[].class));
        }

        public PublisherPrecondition build() {

            return new PublisherPrecondition(this);
        }
    }
}
