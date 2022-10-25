package rx.pipelines;

import api.dto.rx.admin.publisher.Publisher;
import api.dto.rx.admin.user.UserDto;
import api.dto.rx.inventory.adspot.AdSpot;
import api.dto.rx.inventory.media.Media;
import api.dto.rx.privateauction.PrivateAuction;
import api.dto.rx.protection.Protection;
import api.dto.rx.yield.dynamicpricing.DynamicPricing;
import api.dto.rx.yield.openpricing.OpenPricing;
import api.preconditionbuilders.*;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import rx.BaseTest;

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
    private static final String PREFIX_PRIVATE_AUCTIONS = "auto";

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
        log.info(String.format("Deleted %s protections items", deleted));
    }

    @Test(priority = 3)
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

    @Test(priority = 4)
    public void deleteMedia() {
        var deleted = new AtomicInteger(0);
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
        log.info(String.format("Deleted media items %s", deleted));
    }

    @Test(priority = 1)
    public void deleteOpenPricing() {
        var deleted = new AtomicInteger(0);
        getAllPricingIdsByParams().forEach(pricingId -> {
            if (OpenPricingPrecondition.openPricing()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteOpenPricing(pricingId)
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                deleted.getAndIncrement();
            }
        });
        log.info(String.format("Deleted open pricing items %s", deleted));
    }

    @Test(priority = 2)
    public void deleteDynamicPricing() {
        var deleted = new AtomicInteger(0);
        getAllDynamicPricingIdsByParams().forEach(dynamicPricingId -> {
            if (DynamicPricingPrecondition.dynamicPricing()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteDynamicPricing(dynamicPricingId)
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                deleted.getAndIncrement();
            }
        });
        log.info(String.format("Deleted dynamic pricing items %s", deleted));
    }

    @Test(priority = 6)
    public void deleteUsers() {
        var deleted = new AtomicInteger(0);
        getAllUserIdsByParams().forEach(userId -> {
            if (UsersPrecondition.user()
                    .setCredentials(USER_FOR_DELETION)
                    .deleteUser(userId)
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                deleted.getAndIncrement();
            }
        });
        log.info(String.format("Deleted users items %s", deleted));
    }


    @Test(priority = 6)
    public void deletePublishers() {
        var deleted = new AtomicInteger(0);
        var relatedToPrivateAuctionsPublisherIds = getRelatedToPrivateAuctionsPublisherIds();
        getAllPublisherIdsByParams().stream()
                .filter(pubId -> !relatedToPrivateAuctionsPublisherIds.contains(pubId))
                .collect(Collectors.toList())
                .forEach(publisherId -> {
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
        var updated = new AtomicInteger(0);
        getAllPublishersByParams().forEach(publisher -> {
            publisher.setIsEnabled(false);
            if (PublisherPrecondition.publisher()
                    .updatePublisher(publisher)
                    .build()
                    .getResponseCode() == HttpStatus.SC_NO_CONTENT) {
                updated.getAndIncrement();
            }
        });

        log.info(String.format("Updated publishers items %s ", updated));
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

    private List<Integer> getAllPricingIdsByParams() {

        return OpenPricingPrecondition.openPricing()
                .getOpenPricingWithFilter(Map.of("search", PREFIX_OPEN_PRICING, "sort", "id-desc"))
                .build()
                .getOpenPricingGetAllResponse()
                .getItems().stream()
                .map(OpenPricing::getId)
                .collect(Collectors.toList());
    }

    private List<Integer> getAllDynamicPricingIdsByParams() {

        return DynamicPricingPrecondition.dynamicPricing()
                .getDynamicPricingWithFilter(Map.of("search", PREFIX_DYNAMIC_PRICING, "sort", "id-desc"))
                .build()
                .getDynamicPricingGetAllResponse()
                .getItems().stream()
                .map(DynamicPricing::getId)
                .collect(Collectors.toList());
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

    private List<Integer> getRelatedToPrivateAuctionsPublisherIds() {

        return PrivateAuctionPrecondition.privateAuction()
                .getAllPrivateAuctions()
                .build()
                .getPrivateAuctionsGetAllResponse()
                .getItems().stream()
                .filter(privateAuction -> privateAuction.getName().contains(PREFIX_PRIVATE_AUCTIONS))
                .map(PrivateAuction::getPublisherId)
                .collect(Collectors.toList());
    }

    private List<Integer> getAllPublisherIdsByParams() {

        return getAllPublishersByParams().stream()
                .map(Publisher::getId)
                .collect(Collectors.toList());
    }

    private List<Publisher> getAllPublishersByParams() {

        return PublisherPrecondition.publisher()
                .getPublisherWithFilter(Map.of("search", PREFIX_PUBLISHERS, "sort", "id-desc"))
                .build()
                .getPublisherGetAllResponse()
                .getItems();
    }

    private List<Integer> getAllUserIdsByParams() {

        return UsersPrecondition.user()
                .getUsersWithFilter(Map.of("search", PREFIX_USERS, "sort", "id-desc"))
                .build()
                .getUserGetAllResponse()
                .getItems().stream()
                .map(UserDto::getId)
                .collect(Collectors.toList());
    }
}
