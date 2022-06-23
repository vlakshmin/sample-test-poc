package api.dto.rx.yield.openPricing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zutils.ObjectMapperUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenPricingRequest {

    private Integer id;
    private String name;
    private Boolean active;
    private Integer publisherId;
    private String publisherName;
    private String notes;
    private Double floorPrice;
    private Integer priority;
    private Rule rule;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
