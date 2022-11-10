package api.dto.rx.admin.publisher;

import java.util.List;
import zutils.ObjectMapperUtils;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Publisher {

    private Integer id;
    private String name;
    private String mail;
    private String domain;
    private String currency;
    private String createdAt;
    private String updatedAt;
    private Boolean isEnabled;
    private String salesAccountName;
    private List<Integer> dspIds;
    private List<Integer> categoryIds;

    public String toJson() {

        return ObjectMapperUtils.toJson(this);
    }
}
