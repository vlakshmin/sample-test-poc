package api.dto.rx.yield.openPricing;

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
public class Adspot {

    private List<Integer> includedAdspots;
    private List<Integer> excludedAdspots;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
