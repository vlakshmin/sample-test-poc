package api.preconditionbuilders;

import api.core.client.HttpClient;
import api.dto.GenericResponse;
import api.dto.rx.privateauction.PrivateAuction;
import api.services.PrivateAuctionService;
import configurations.User;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Getter
@AllArgsConstructor
public class PrivateAuctionPrecondition {

    private Integer responseCode;
    private GenericResponse<PrivateAuction> privateAuctionsGetAllResponse;

    private PrivateAuctionPrecondition(PrivateAuctionPreconditionBuilder builder) {
        this.responseCode = builder.responseCode;
        this.privateAuctionsGetAllResponse = builder.privateAuctionsGetAllResponse;
    }

    public static PrivateAuctionPreconditionBuilder privateAuction() {

        return new PrivateAuctionPreconditionBuilder();
    }

    public static class PrivateAuctionPreconditionBuilder {

        private Response response;
        private Integer responseCode;
        private GenericResponse<PrivateAuction> privateAuctionsGetAllResponse;

        private final PrivateAuctionService privateAuctionService = new PrivateAuctionService();

        public PrivateAuctionPreconditionBuilder getAllPrivateAuctions() {
            this.response = privateAuctionService.getAll();
            this.privateAuctionsGetAllResponse = this.response.as(new TypeRef<GenericResponse<PrivateAuction>>() {});
            this.responseCode = response.getStatusCode();

            return this;
        }

        public PrivateAuctionPreconditionBuilder getAllGeosWithFilter(Map<String, Object> queryParams) {
            this.response = privateAuctionService.getPrivateAuctionsWithFilter(queryParams);
            this.privateAuctionsGetAllResponse = this.response.as(new TypeRef<GenericResponse<PrivateAuction>>() {});
            this.responseCode = response.getStatusCode();

            return this;
        }

        public PrivateAuctionPreconditionBuilder setCredentials(User user) {
            HttpClient.setCredentials(user);

            return this;
        }

        public PrivateAuctionPrecondition build() {

            return new PrivateAuctionPrecondition(this);
        }
    }
}
