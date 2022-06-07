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
public class Video {

    private List<Integer> playbackMethodIds;
    private List<Integer> sizeIds;
    private Boolean enabled;
    private Integer floorPrice;
    private Integer maxDuration;
    private Integer minDuration;
    private Integer placementType;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
