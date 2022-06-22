package api.dto.rx.inventory.filter;

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
public class Filter {

    private Integer id;
    private Integer publisherId;
    private String publisherName;
    private String createdAt;
    private String updatedAt;


    public String toJson() {

        return ObjectMapperUtils.toJson(this);
    }
}