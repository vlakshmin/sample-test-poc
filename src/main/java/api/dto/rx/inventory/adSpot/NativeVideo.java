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
public class NativeVideo {

    private List<String> mimes;
    private Integer maxDuration;
    private Integer minDuration;


    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
