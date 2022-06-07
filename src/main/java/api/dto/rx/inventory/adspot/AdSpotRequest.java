package api.dto.rx.inventory.adspot;

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
public class AdSpotRequest {

    private Integer mediaId;
    private String name;
    private String mediaName;
    private Boolean coppa;
    private Boolean customSizing;
    private Boolean enabled;
    private Boolean floorPriceAutomated;
    private Boolean testMode;
    private Integer filterId;
    private Integer floorPrice;
    private Integer ttl;
    private Integer positionId;
    private Integer prebidCacheEnabled;
    private Integer publisherId;
    private Integer publisherName;
    private String currency;
    private Video video;
    private Banner banner;
    //private Native native;

    private List<Integer> categoryIds;


    public String toJson() {

        return ObjectMapperUtils.toJson(this);
    }
}