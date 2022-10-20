package rx.pipelines;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.admin.user.UserDto;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.media.Media;
import api.dto.rx.protection.Protection;
import api.dto.rx.yield.dynamicpricing.DynamicPricing;
import api.dto.rx.yield.openpricing.OpenPricing;
import api.preconditionbuilders.*;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import rx.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static configurations.User.USER_FOR_DELETION;

@Slf4j
public class DeleteGeneratedDataTest extends BaseTest {

    private static final String PREFIX_MEDIA = "auto";
    private static final String PREFIX_ADSPOTS = "auto";
    private static final String PREFIX_PUBLISHERS = "auto";
    private static final String PREFIX_OPEN_PRICING = "auto";
  //  private static final String PREFIX_USERS = "Test Account";
    private static final String PREFIX_USERS = "user";
    private static final String PREFIX_DYNAMIC_PRICING = "auto";
    private static final String PREFIX_PROTECTIONS_1 = "api";
    private static final String PREFIX_PROTECTIONS_2 = "auto";

    @Test(priority = 1)
    public void deleteProtectionsByPrefix() {

        deleteProtections(PREFIX_PROTECTIONS_1);
        deleteProtections(PREFIX_PROTECTIONS_2);
    }

    @Step("Delete Protections")
    private void deleteProtections(String prefix){

        var protections = getAllProtectionsByParams(prefix);
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

    @Test(priority = 3)
    public void deleteAdSpots() {
        var adSpots = getAllAdSpotsByParams();
        int deleted = 0;
        for (AdSpot as : adSpots) {
            if (AdSpotPrecondition.adSpot()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteAdSpot(as.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                deleted++;
        }
        log.info(String.format("Deleted ad spots items %s of %s", deleted, adSpots.size()));
    }

    @Test(priority = 2)
    public void deleteMedia() {
        var media = getAllMediaByParams();
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
        var openPricing = getAllPricingByParams();
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
    public void deleteDynamicPricing() {
        var dynamicPricing = getAllDynamicPricingByParams();
        int deleted = 0;
        for (DynamicPricing p : dynamicPricing) {
            if (DynamicPricingPrecondition.dynamicPricing()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteDynamicPricing(p.getId())
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                deleted++;
        }
        log.info(String.format("Deleted dynamic pricing items %s of %s", deleted, dynamicPricing.size()));
    }

    @Test(priority = 6)
    public void deletePublishers() {
        var publishers = getAllPublishersByParams();
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

    @Test(priority = 8)
    public void updatePublishers() {
        var publishers = getAllPublishersByParams();
        int updated = 0;
        Publisher pub;

        for (Publisher p : publishers) {
            pub = Publisher.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .salesAccountName(p.getSalesAccountName())
                    .mail(p.getMail())
                    .isEnabled(false)
                    .domain(p.getDomain())
                    .currency(p.getCurrency())
                    .categoryIds(p.getCategoryIds())
                    .dspIds(p.getDspIds())
                    .createdAt(p.getCreatedAt())
                    .updatedAt(p.getUpdatedAt())
                    .build();

            if (PublisherPrecondition.publisher()
                    .setCredentials(USER_FOR_DELETION)
                    .updatePublisher(pub)
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT)
                updated++;
        }
        log.info(String.format("Updated publishers items %s of %s", updated, publishers.size()));
    }

    @Test(priority = 7)
    public void deleteUsers() {
        var users = getAllUsersByParams();
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
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("search", PREFIX_MEDIA);
        queryParams.put("sort", "id-desc");

        return MediaPrecondition.media()
                .getMediaWithFilter(queryParams)
                .build()
                .getMediaGetAllResponse()
                .getItems();
    }

    private List<AdSpot> getAllAdSpotsByParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("search", PREFIX_ADSPOTS);
        queryParams.put("sort", "id-desc");

        return AdSpotPrecondition.adSpot()
                .getAdSpotsWithFilter(queryParams)
                .build()
                .getAdSpotsGetAllResponse()
                .getItems();
    }

    private List<OpenPricing> getAllPricingByParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("search", PREFIX_OPEN_PRICING);
        queryParams.put("sort", "id-desc");

        return OpenPricingPrecondition.openPricing()
                .getOpenPricingWithFilter(queryParams)
                .build()
                .getOpenPricingGetAllResponse()
                .getItems();
    }

    private List<DynamicPricing> getAllDynamicPricingByParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("search", PREFIX_DYNAMIC_PRICING);
        queryParams.put("sort", "id-desc");

        return DynamicPricingPrecondition.dynamicPricing()
                .getDynamicPricingWithFilter(queryParams)
                .build()
                .getDynamicPricingGetAllResponse()
                .getItems();
    }

    private List<Protection> getAllProtectionsByParams(String prefix) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("search", prefix);
        queryParams.put("sort", "id-desc");

        return ProtectionsPrecondition.protection()
                .getProtectionsWithFilter(queryParams)
                .build()
                .getProtectionsGetAllResponse()
                .getItems();
    }

    private List<Publisher> getAllPublishersByParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("search", PREFIX_PUBLISHERS);
        queryParams.put("sort", "id-desc");

        return PublisherPrecondition.publisher()
                .getPublisherWithFilter(queryParams)
                .build()
                .getPublisherGetAllResponse()
                .getItems();
    }

    private List<UserDto> getAllUsersByParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("search", PREFIX_USERS);
        queryParams.put("sort", "id-desc");

        return UsersPrecondition.user()
                .getUsersWithFilter(queryParams)
                .build()
                .getUserGetAllResponse()
                .getItems();
    }
}
