package api.dto.rx.inventory.adSpot;

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
public class Assets {

    private List<Integer> playbackMethodIds;
    private List<Integer> sizeIds;
    private String assetType;
    private Integer id;
    private Img img;
    private DataObj data;


    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
