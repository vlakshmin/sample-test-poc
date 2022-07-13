package rx.pipelines;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.media.Media;
import api.dto.rx.yield.openpricing.OpenPricing;
import api.preconditionbuilders.AdSpotPrecondition;
import api.preconditionbuilders.MediaPrecondition;
import api.preconditionbuilders.OpenPricingPrecondition;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import zutils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.List;

import static configurations.User.USER_FOR_DELETION;

public class DeleteTestData {
    private final static String PREFIX = "auto";
    private List<Media> media;
    private List<AdSpot> adSpots;
    private List<OpenPricing> openPricing;
    private List<Publisher> publishers;


    @Test
    public void delete() {
//        adSpots= getAllAdSpotsByParams();
//        for (AdSpot as: adSpots) {
//                AdSpotPrecondition.adSpot().
//                        setCredentials(USER_FOR_DELETION).
//                        deleteAdSpot(as.getId());
//                System.out.println(String.format("Ad Spot: id %s %s",as.getId(),as.getName()));
//
//        }

        media = getAllMediaByParams();
        for (Media m: media) {
            MediaPrecondition.media().
                    setCredentials(USER_FOR_DELETION).
                    deleteMedia(m.getId());
            System.out.println(String.format("Media: id %s %s",m.getId(),m.getName()));
        }
    }

    private List<Media> getAllMediaByParams() {
        HashMap<String, Object> queryParams = new HashMap();
    //    queryParams.put("search", PREFIX);
        queryParams.put("search", "ios web");
        queryParams.put("sort","id-desc");
        var mediaList = MediaPrecondition.media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(mediaList,Media.class);
    }

    private List<AdSpot> getAllAdSpotsByParams() {
        HashMap<String, Object> queryParams = new HashMap();
       // queryParams.put("search", PREFIX);
        queryParams.put("search", "auto");
        queryParams.put("sort","id-desc");
        var adSpotsList = AdSpotPrecondition.adSpot()
                .getAdSpotsWithFilter(queryParams)
                .build()
                .getAdSpotsGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(adSpotsList,AdSpot.class);
    }

    private List<AdSpot> getAllPricingByParams() {
        HashMap<String, Object> queryParams = new HashMap();
        // queryParams.put("search", PREFIX);
        queryParams.put("search", "auto");
        queryParams.put("sort","id-desc");
        var pricingList = OpenPricingPrecondition.openPricing()
              //  .getOpesWithFilter(queryParams)
                .build()
                .getOpenPricingGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(pricingList,OpenPricing.class);
    }
}
