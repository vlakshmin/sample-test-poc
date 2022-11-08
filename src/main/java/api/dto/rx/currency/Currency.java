package api.dto.rx.currency;

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
public class Currency {

    private Integer code;
    private String name;
    private String symbol;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
