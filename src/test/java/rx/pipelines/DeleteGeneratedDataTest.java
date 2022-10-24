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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static configurations.User.USER_FOR_DELETION;

@Slf4j
public class DeleteGeneratedDataTest extends BaseTest {

    private static final String PREFIX_MEDIA = "auto";
    private static final String PREFIX_ADSPOTS = "auto";
    private static final String PREFIX_PUBLISHERS = "auto";
    private static final String PREFIX_OPEN_PRICING = "auto";
    private static final String PREFIX_PROTECTIONS_1 = "api";
    private static final String PREFIX_PROTECTIONS_2 = "auto";
    private static final String PREFIX_USERS = "Test Account";
    private static final String PREFIX_DYNAMIC_PRICING = "auto";

    @Test(priority = 1)
    public void deleteProtectionsByPrefix() {
        deleteProtections(PREFIX_PROTECTIONS_1);
        deleteProtections(PREFIX_PROTECTIONS_2);
    }

    @Step("Delete Protections")
    private void deleteProtections(String prefix) {
        final var deleted = new AtomicInteger(0);
        getAllProtectionIdsByParams(prefix).forEach(
                protectionId -> {
                    if (ProtectionsPrecondition.protection()
                            .setCredentials(USER_FOR_DELETION)
                            .deleteProtection(protectionId)
                            .build()
                            .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                        deleted.getAndIncrement();
                    }
                });
        log.info(String.format("Deleted %s protections items", deleted);
    }

    @Test(priority = 2)
    public void deleteAdSpots() {
        final var deleted = new AtomicInteger(0);
        getAllAdSpotIdsByParams().forEach(
                adSpotId -> {
                    if (AdSpotPrecondition.adSpot()
                            .setCredentials(USER_FOR_DELETION)
                            .deleteAdSpot(adSpotId)
                            .build()
                            .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                        deleted.getAndIncrement();
                    }
                });
        log.info(String.format("Deleted ad spots items %s", deleted));
    }

    @Test(priority = 3)
    public void deleteMedia() {
        final var deleted = new AtomicInteger(0);
        getAllMediaIdsByParams().forEach(
                mediaId -> {
                    if (MediaPrecondition.media().
                            setCredentials(USER_FOR_DELETION).
                            deleteMedia(mediaId)
                            .build()
                            .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                        deleted.getAndIncrement();
                    }
                });
        log.info(String.format("Deleted media items %s", deleted);
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


    @Test(priority = 6)
    public void deletePublishers() {
        final var deleted = new AtomicInteger(0);
        getAllPublishersByParams().stream()
                .filter(pubId -> !getRelatedToPrivateAuctionsPublisherIds().contains(pubId))
                .forEach(
                        publisherId -> {
                            if (PublisherPrecondition.publisher()
                                    .setCredentials(USER_FOR_DELETION)
                                    .deletePublisher(publisherId)
                                    .build()
                                    .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                                deleted.getAndIncrement();
                            }
                        });
        log.info(String.format("Deleted publishers items %s", deleted));
    }

    @Test(priority = 8)
    public void updatePublishers() {
        var publishers = getAllPublishersByParams();
        int updated = 0;
        Publisher pub;

        for (Publisher p : publishers) {
            pub.setIsEnabled(false);
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

    private List<Integer> getAllMediaIdsByParams() {

        return MediaPrecondition.media()
                .getMediaWithFilter(Map.of("search", PREFIX_MEDIA, "sort", "id-desc"))
                .build()
                .getMediaGetAllResponse()
                .getItems().stream()
                .map(Media::getId)
                .collect(Collectors.toList());
    }

    private List<Integer> getAllAdSpotIdsByParams() {

        return AdSpotPrecondition.adSpot()
                .getAdSpotsWithFilter(Map.of("search", PREFIX_ADSPOTS, "sort", "id-desc"))
                .build()
                .getAdSpotsGetAllResponse()
                .getItems().stream()
                .map(AdSpot::getId)
                .collect(Collectors.toList());
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

    private List<Integer> getAllProtectionIdsByParams(String prefix) {

        return ProtectionsPrecondition.protection()
                .getProtectionsWithFilter(Map.of("search", prefix, "sort", "id-desc"))
                .build()
                .getProtectionsGetAllResponse()
                .getItems().stream()
                .map(Protection::getId)
                .collect(Collectors.toList());
    }

    private List<Integer> getRelatedToPrivateAuctionsPublisherIds(){

        return //Create task for Private Auction precondiiton builder
    }

    private List<Integer> getAllPublishersByParams() {

        return PublisherPrecondition.publisher()
                .getPublisherWithFilter(Map.of("search", PREFIX_PUBLISHERS, "sort", "id-desc"))
                .build()
                .getPublisherGetAllResponse()
                .getItems().stream()
                .map(Publisher::getId)
                .collect(Collectors.toList());
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
