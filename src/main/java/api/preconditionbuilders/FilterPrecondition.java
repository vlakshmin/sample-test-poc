package api.preconditionbuilders;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.filter.Filter;
import api.dto.rx.inventory.filter.FilterRequest;
import api.services.FilterService;
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
public class FilterPrecondition {

    private Filter filterResponse;
    private FilterRequest filterRequest;
    private List<Filter> filterResponseList;

    private FilterPrecondition(FilterPrecondition.FilterPreconditionBuilder builder) {
        this.filterRequest = builder.filterRequest;
        this.filterResponse = builder.filterResponse;
        this.filterResponseList = builder.filterResponseList;
    }

    public static FilterPrecondition.FilterPreconditionBuilder filter() {

        return new FilterPrecondition.FilterPreconditionBuilder();
    }

    public static class FilterPreconditionBuilder {

        private Response response;
        private Filter filterResponse;
        private FilterRequest filterRequest;
        private List<Filter> filterResponseList;
        private FilterService filterService = new FilterService();
        private Publisher publisher;

        public FilterPrecondition.FilterPreconditionBuilder createNewFilter() {

            publisher = PublisherPrecondition.publisher()
                    .createNewPublisher()
                    .build()
                    .getPublisherResponse();

            this.filterRequest = filterRequest.builder()
                    .name(captionWithSuffix("Filter"))
                    .publisherId(publisher.getId())
                    .build();

            this.response = filterService.createFilter(filterRequest);
            this.filterResponse = response.as(Filter.class);

            return this;
        }

        public FilterPrecondition.FilterPreconditionBuilder getAllFilterList() {
            this.response = filterService.getAll();

            this.filterResponseList = this.getFilterResponseList();

            return this;
        }

        private List<Filter> getFilterResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", Filter[].class));
        }

        public FilterPrecondition build() {

            return new FilterPrecondition(this);
        }
    }
}
