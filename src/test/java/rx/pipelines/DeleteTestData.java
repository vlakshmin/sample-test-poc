package rx.pipelines;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.admin.user.UserDto;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.media.Media;
import api.dto.rx.protection.Protection;
import api.dto.rx.yield.openpricing.OpenPricing;
import api.preconditionbuilders.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import zutils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static configurations.User.USER_FOR_DELETION;

@Slf4j
public class DeleteTestData {
    private final static String PREFIX_MEDIA = "auto";
    private final static String PREFIX_ADSPOTS = "auto";
    private final static String PREFIX_PUBLISHERS = "auto";
    private final static String PREFIX_OPEN_PRICING = "auto";
    private final static String PREFIX_PROTECTIONS = "API Protection";
    private final static String PREFIX_USERS = "Test Account";

    private List<Media> media;
    private List<AdSpot> adSpots;
    private List<UserDto> users;
    private List<Publisher> publishers;
    private List<Protection> protections;
    private List<OpenPricing> openPricing;

    @Test(priority = 1)
    public void deleteProtections() {
        protections = getAllProtectionsByParams();
        int deleted = 0;
        for (Protection pr : protections) {
            if (ProtectionsPrecondition.protection()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteProtection(pr.getId())
                    .getResponse().getStatusCode() == 204)
                deleted++;
        }
        log.info(String.format("Deleted protections %s of %s", deleted, protections.size()));
    }

    @Test(priority = 2)
    public void deleteAdSpots() {
        adSpots = getAllAdSpotsByParams();
        int deleted = 0;
        for (AdSpot as : adSpots) {
            if (AdSpotPrecondition.adSpot()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteAdSpot(as.getId())
                    .getResponse().getStatusCode() == 204)
                deleted++;
        }
        log.info(String.format("Deleted ad spots %s of %s", deleted, adSpots.size()));
    }

    @Test(priority = 3)
    public void deleteMedia() {
        media = getAllMediaByParams();
        int deleted = 0;
        for (Media m : media) {
            if (MediaPrecondition.media().
                    setCredentials(USER_FOR_DELETION).
                    deleteMedia(m.getId()).
                    getResponse().getStatusCode() == 204)
                deleted++;
        }
        log.info(String.format("Deleted media %s of %s", deleted, media.size()));
    }

    @Test(priority = 4)
    public void deleteOpenPricing() {
        openPricing = getAllPricingByParams();
        int deleted = 0;
        for (OpenPricing p : openPricing) {
            if (OpenPricingPrecondition.openPricing()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteOpenPricing(p.getId())
                    .getResponse().getStatusCode() == 204)
                deleted++;
        }
        log.info(String.format("Deleted open pricing %s of %s", deleted, openPricing.size()));
    }

    @Test(priority = 5)
    public void deletePublishers() {
        publishers = getAllPublishersByParams();
        int deleted = 0;
        for (Publisher p : publishers) {
            if (PublisherPrecondition.publisher()
                    .setCredentials(USER_FOR_DELETION)
                    .deletePublisher(p.getId())
                    .getResponse().getStatusCode() == 204)
                deleted++;
        }
        log.info(String.format("Deleted publishers %s of %s", deleted, publishers.size()));
    }

    @Test(priority = 6)
    public void deleteUsers() {
        users = getAllUsersByParams();
        int deleted = 0;
        for (UserDto user : users) {
            if (UsersPrecondition.user()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteUser(user.getId())
                    .getResponse().getStatusCode() == 204)
                deleted++;
        }
        log.info(String.format("Deleted users %s of %s", deleted, users.size()));
    }

    private List<Media> getAllMediaByParams() {
        Map<String, Object> queryParams = new HashMap();
        queryParams.put("search", PREFIX_MEDIA);
        queryParams.put("sort", "id-desc");
        var mediaList = MediaPrecondition.media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(mediaList, Media.class);
    }

    private List<AdSpot> getAllAdSpotsByParams() {
        Map<String, Object> queryParams = new HashMap();
        queryParams.put("search", PREFIX_ADSPOTS);
        queryParams.put("sort", "id-desc");
        var adSpotsList = AdSpotPrecondition.adSpot()
                .getAdSpotsWithFilter(queryParams)
                .build()
                .getAdSpotsGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(adSpotsList, AdSpot.class);
    }

    private List<OpenPricing> getAllPricingByParams() {
        Map<String, Object> queryParams = new HashMap();
        queryParams.put("search", PREFIX_OPEN_PRICING);
        queryParams.put("sort", "id-desc");
        var pricingList = OpenPricingPrecondition.openPricing()
                .getOpenPricingWithFilter(queryParams)
                .build()
                .getOpenPricingGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(pricingList, OpenPricing.class);
    }

    private List<Protection> getAllProtectionsByParams() {
        HashMap<String, Object> queryParams = new HashMap();
        queryParams.put("search", PREFIX_PROTECTIONS);
        queryParams.put("sort", "id-desc");
        var protectionsList = ProtectionsPrecondition.protection()
                .getProtectionsWithFilter(queryParams)
                .build()
                .getProtectionsGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(protectionsList, Protection.class);
    }

    private List<Publisher> getAllPublishersByParams() {
        Map<String, Object> queryParams = new HashMap();
        queryParams.put("search", PREFIX_PUBLISHERS);
        queryParams.put("sort", "id-desc");
        var publishersList = PublisherPrecondition.publisher()
                .getPublisherWithFilter(queryParams)
                .build()
                .getPublisherGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(publishersList, Publisher.class);
    }

    private List<UserDto> getAllUsersByParams() {
        Map<String, Object> queryParams = new HashMap();
        queryParams.put("search", PREFIX_USERS);
        queryParams.put("sort", "id-desc");
        var usersList = UsersPrecondition.user()
                .getUsersWithFilter(queryParams)
                .build()
                .getUserGetAllResponse()
                .getItems();

        return ObjectMapperUtils.getCollectionType(usersList, UserDto.class);
    }
}
