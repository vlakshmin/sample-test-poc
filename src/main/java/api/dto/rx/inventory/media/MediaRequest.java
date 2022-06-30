package api.dto.rx.inventory.media;

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
public class MediaRequest {

    private String url;
    private String name;
    private Boolean isEnabled;
    private Integer platformId;
    private Integer publisherId;
    private String publisherName;

    private List<Integer> categoryIds;

    public String toJson() {

        return ObjectMapperUtils.toJson(this);
    }
}