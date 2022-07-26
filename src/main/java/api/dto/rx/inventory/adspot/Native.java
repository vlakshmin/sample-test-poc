package api.dto.rx.inventory.adspot;

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
public class Native {

//    private Title title;
//    private Assets assets;
//    private Boolean required;
//    private NativeVideo video;
    private Boolean enabled;
    private Double floorPrice;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
