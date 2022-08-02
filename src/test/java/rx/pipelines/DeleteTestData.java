package rx.pipelines;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.admin.user.UserDto;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.media.Media;
import api.dto.rx.protection.Protection;
import api.dto.rx.yield.openpricing.OpenPricing;
import api.preconditionbuilders.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import zutils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static configurations.User.USER_FOR_DELETION;

@Slf4j
public class DeleteTestData {
    private List<Media> media;
    private List<UserDto> users;
    private List<AdSpot> adSpots;
    private List<Publisher> publishers;
    private List<Protection> protections;
    private List<OpenPricing> openPricing;

    private static final String PREFIX_MEDIA = "auto";
    private static final String PREFIX_ADSPOTS = "auto";
    private static final String PREFIX_PUBLISHERS = "auto";
    private static final String PREFIX_OPEN_PRICING = "auto";
    private static final String PREFIX_USERS = "Test Account";
    private static final String PREFIX_PROTECTIONS = "api protection";


    @Test(priority = 1)
    public void deleteProtections() {
        protections = getAllProtectionsByParams();
        int deleted = 0;
        for (Protection pr : protections) {
            if (ProtectionsPrecondition.protection()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteProtection(pr.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                deleted++;
        }
        log.info(String.format("Deleted protections items %s of %s", deleted, protections.size()));
    }

    @Test(priority = 2)
    public void deleteAdSpots() {
        adSpots = getAllAdSpotsByParams();
        int deleted = 0;
        for (AdSpot as : adSpots) {
            if ( AdSpotPrecondition.adSpot()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteAdSpot(as.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                deleted++;
        }
        log.info(String.format("Deleted ad spots items %s of %s", deleted, adSpots.size()));
    }

    @Test(priority = 3)
    public void deleteMedia() {
        media = getAllMediaByParams();
        int deleted = 0;
        for (Media m : media) {
            if (MediaPrecondition.media().
                    setCredentials(USER_FOR_DELETION).
                    deleteMedia(m.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                deleted++;
        }
        log.info(String.format("Deleted media items %s of %s", deleted, media.size()));
    }

    @Test(priority = 4)
    public void deleteOpenPricing() {
        openPricing = getAllPricingByParams();
        int deleted = 0;
        for (OpenPricing p : openPricing) {
            if (OpenPricingPrecondition.openPricing()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteOpenPricing(p.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                deleted++;
        }
        log.info(String.format("Deleted open pricing items %s of %s", deleted, openPricing.size()));
    }

    @Test(priority = 5)
    public void deletePublishers() {
        publishers = getAllPublishersByParams();
        int deleted = 0;
        for (Publisher p : publishers) {
            if (PublisherPrecondition.publisher()
                    .setCredentials(USER_FOR_DELETION)
                    .deletePublisher(p.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                deleted++;
        }
        log.info(String.format("Deleted publishers items %s of %s", deleted, publishers.size()));
    }

    @Test(priority = 6)
    public void deleteUsers() {
        users = getAllUsersByParams();
        int deleted = 0;
        for (UserDto user : users) {
            if (UsersPrecondition.user()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteUser(user.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                deleted++;
        }
        log.info(String.format("Deleted users items %s of %s", deleted, users.size()));
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

        return AdSpotPrecondition.adSpot()
                .getAdSpotsWithFilter(queryParams)
                .build()
                .getAdSpotsGetAllResponse()
                .getItems();
    }

    private List<OpenPricing> getAllPricingByParams() {
        Map<String, Object> queryParams = new HashMap();
        queryParams.put("search", PREFIX_OPEN_PRICING);
        queryParams.put("sort", "id-desc");

        return OpenPricingPrecondition.openPricing()
                .getOpenPricingWithFilter(queryParams)
                .build()
                .getOpenPricingGetAllResponse()
                .getItems();
    }

    private List<Protection> getAllProtectionsByParams() {
        Map<String, Object> queryParams = new HashMap();
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
