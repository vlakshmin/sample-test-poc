package api.dto.rx.inventory.adspot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AdSpot {

    private Integer id;
    private Integer mediaId;
    private String name;
    private String mediaName;
    private Boolean coppa;
    private Boolean customSizing;
    private Boolean enabled;
    private Boolean floorPriceAutomated;
    private Boolean testMode;
    private Integer filterId;
    private Double floorPrice;
    private Integer ttl;
    private Integer positionId;
    private Integer prebidCacheEnabled;
    private Integer publisherId;
    private String publisherName;
    private String currency;
    private List<Integer> sizeIds;
    @JsonProperty(value="native")
    private Native anative;

    private List<Integer> categoryIds;

    private String createdAt;
    private String updatedAt;

    public String toJson() {

        return ObjectMapperUtils.toJson(this);
    }
}