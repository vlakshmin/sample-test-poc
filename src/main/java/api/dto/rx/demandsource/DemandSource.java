package api.dto.rx.demandsource;

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
public class DemandSource {

    private Integer id;
    private String key;
    private String name;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
