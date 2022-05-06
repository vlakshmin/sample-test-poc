package api.entities.rx.publisher;

import api.utils.ObjectMapperUtils;
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
public class Publisher {

    private Integer id;
    private String name;
    private String mail;
    private String domain;
    private String currency;
    private String salesAccountName;
    private List<Integer> categoryIds;
    private List<Integer> dspIds;
    private Boolean isEnabled;
    private String createdAt;
    private String updatedAt;

    public String toJson() {

        return ObjectMapperUtils.toJson(this);
    }
}
