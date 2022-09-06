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

//
//                "id": 1,
//                        "corp": "RBidder",
//                        "currency": "USD",
//                        "country": "US",
//                        "bidEndpoints": [

//                    "syncUrl": "//cm-eu.rd.linksynergy.com/rx?ssp_id={{user_id}}",
//                    "requestAdjustmentRate": 100,
//                    "idfaRequired": false,
//                    "syncRequired": false,
//                    "nativeAllowed": true,
//                    "bannerAllowed": true,
//                    "videoAllowed": false,
//                    "platformIosAppAllowed": true,
//                    "platformAndroidAppAllowed": true,
//                    "platformMobileWebAllowed": true,
//                    "platformPcWebAllowed": true,
//                    "tokenGeneration": false,
//                    "timeout": 500,
//                    "debugAdspotIds": "",
//                    "status": 3,
//                    "allowedCountries": "USA",
//                    "dspTypeId": 3,
//                    "pmpSupported": true,
//                    "ecpmSupported": false,
//                    "nonProgrammatic": false,
//                    "createdAt": "2018-12-20T22:17:54Z",
//                    "createdBy": "mike.potter@rakuten.com",
//                    "updatedAt": "2022-02-08T21:25:27Z",
//                    "updatedBy": "mike.potter@rakuten.com"
//
//

    public String toJson() {
        return ObjectMapperUtils.toJson(this);
    }
}
