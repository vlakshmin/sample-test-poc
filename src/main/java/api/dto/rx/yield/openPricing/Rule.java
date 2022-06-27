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
public class Rule {

    public AdSpotRule adspot;
    public AdFormat adFormat;
    public AdSize adSize;
    public MediaRule media;
    public DeviceType deviceType;
    public Geo geo;
    public DeviceOS deviceOS;
    public Dsp dsp;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
