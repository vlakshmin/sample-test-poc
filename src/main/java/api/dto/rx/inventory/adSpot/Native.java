package api.dto.rx.inventory.adSpot;

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

    private Assets assets;
    private NativeVideo video;
    private Title title;

    private Boolean required;


    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
