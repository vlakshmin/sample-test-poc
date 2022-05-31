package api.dto.rx.admin.publisher;

import zutils.ObjectMapperUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublisherRequest {

    private String name;
    private String mail;
    private String domain;
    private String currency;
    private Boolean isEnabled;
    private List<Integer> dspIds;
    private String salesAccountName;
    private List<Integer> categoryIds;

    public String toJson() {

        return ObjectMapperUtils.toJson(this);
    }
}
