package widgets.common.detailsmenu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DetailsSectionName {

    GEO("Geo"),
    DEVICE("Device"),
    AD_SIZE("Ad Size"),
    AD_FORMAT("Ad Format"),
    INVENTORY("Inventory"),
    DEMAND_SOURCES("Demand Sources"),
    OPERATING_SYSTEM("Operating System"),

//---Ad Spots
    NATIVE("Native"),
    NATIVE_FLOOR_PRICE("Native Floor Price"),
    BANNER("Banner"),
    VIDEO("Video"),
    BANNER_FLOOR_PRICE("Banner Floor Price"),
    BANNER_AD_SIZE("Banner Ad Size"),

    VIDEO_AD_SIZE("Video Ad Size"),
    VIDEO_PLACEMENT_TYPE("Video Placement Type"),
    VIDEO_PLAYBACK_METHOD("Video Playback Method"),
    VIDEO_FLOOR_PRICE("Video Floor Price"),
    VIDEO_MIN_DURATION("Video Min Duration"),
    VIDEO_MAX_DURATION("Video Max Duration");

    private final String name;
}
