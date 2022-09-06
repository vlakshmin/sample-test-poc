package api.preconditionbuilders;

import api.core.client.HttpClient;
import api.dto.GenericResponse;
import api.dto.rx.demandsource.DemandSource;
import api.services.DemandSourceService;
import configurations.User;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public class DemandSourcePrecondition {

    private Integer responseCode;
    private GenericResponse<DemandSource> demandSourceGetAllResponse;

    private DemandSourcePrecondition(DemandSourcePreconditionBuilder builder) {
        this.responseCode = builder.responseCode;
        this.demandSourceGetAllResponse = builder.demandSourceGetAllResponse;
    }

    public static DemandSourcePreconditionBuilder demandSource() {

        return new DemandSourcePreconditionBuilder();
    }

    public static class DemandSourcePreconditionBuilder {

        private Response response;
        private Integer responseCode;
        private GenericResponse<DemandSource> demandSourceGetAllResponse;
        private DemandSourceService demandSourceService = new DemandSourceService();

        public DemandSourcePreconditionBuilder getDemandSourceList() {
            this.response = demandSourceService.getAll();

            this.demandSourceGetAllResponse = this.response.as(new TypeRef<GenericResponse<DemandSource>>() {});
            this.responseCode = response.getStatusCode();

            return this;
        }

        public DemandSourcePreconditionBuilder createDemandSource() {
            this.response = demandSourceService.createDSP();

            this.demandSourceGetAllResponse = this.response.as(new TypeRef<GenericResponse<DemandSource>>() {});
            this.responseCode = response.getStatusCode();

            return this;
        }

        public DemandSourcePreconditionBuilder setCredentials(User user) {
            HttpClient.setCredentials(user);

            return this;
        }

        public DemandSourcePrecondition build() {

            return new DemandSourcePrecondition(this);
        }
    }
}
