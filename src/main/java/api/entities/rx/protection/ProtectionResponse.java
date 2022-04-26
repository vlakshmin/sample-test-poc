package api.entities.rx.protection;

import api.utils.ObjectMapperUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProtectionResponse extends ProtectionRequest{

    private Integer id;
    private String createdAt;
    private String updatedAt;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
