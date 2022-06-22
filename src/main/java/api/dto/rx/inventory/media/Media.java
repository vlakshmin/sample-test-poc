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
public class Media {

    private Integer id;
    private String name;
    private String appBundleId;
    private String iconPath;
    private String identifier;
    private Integer filterId;
    private Integer platformId;
    private Integer publisherId;
    private String publisherName;
    private String url;

    private List<Integer> categoryIds;

    private Boolean isEnabled;
    private Boolean appReleased;
    private String createdAt;
    private String updatedAt;

    public String toJson() {

        return ObjectMapperUtils.toJson(this);
    }
}