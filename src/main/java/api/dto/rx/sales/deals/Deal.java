package api.dto.rx.sales.deals;

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
public class Deal {
    private Integer id;
    private String token;
    private Boolean enabled;
    private Double floorPrice;
    private String currency;
    private Integer publisherId;
    private String publisherName;
    private Integer dspId;
    private String dspName;
    private Integer privateAuctionId;
    private String privateAuctionName;
    private String startDate;
    private String endDate;
    private String createdBy;
    private String updatedBy;
    private List<Buyers> buyers;
    private String name;
    private Boolean noEndDate;

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
