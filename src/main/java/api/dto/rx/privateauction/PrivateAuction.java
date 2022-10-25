package api.dto.rx.privateauction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zutils.ObjectMapperUtils;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrivateAuction {

    private Integer id;
    private String name;
    private Boolean enabled;
    private Boolean optimized;
    private Boolean noEndDate;
    private Integer publisherId;
    private String publisherName;
    private InventoryTargeting inventoryTargeting;
    private List<RelatedPackage> relatedPackages;
    private String startDate;
    private String endDate;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String updatedBy;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
