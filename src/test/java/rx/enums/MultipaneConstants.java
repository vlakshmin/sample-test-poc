package rx.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MultipaneConstants {

    ALL_DEVICES_ARE_INCLUDED("All devices are included"),
    ALL_INVENTORY_ARE_INCLUDED("All inventory are included"),

    ONE_GEO_IS_INCLUDED("%s geo is included"),
    ONE_MEDIA_IS_INCLUDED("%s media is included"),
    ONE_DEVICE_IS_INCLUDED("%s device is included"),
    ONE_AD_SPOT_IS_INCLUDED("%s ad spot is included"),
    ONE_AD_SIZE_IS_INCLUDED("%s ad size is included"),
    ONE_AD_FORMAT_IS_INCLUDED("%s ad format is included"),
    ONE_DEMAND_SOURCE_IS_INCLUDED("%s demand source is included"),
    ONE_OPERATING_SYSTEM_IS_INCLUDED("%s operating system is included"),

    GEOS_ARE_INCLUDED("%s geos are included"),
    MEDIA_ARE_INCLUDED("%s media are included"),
    DEVICES_ARE_INCLUDED("%s devices are included"),
    AD_SPOTS_ARE_INCLUDED("%s ad spots are included"),
    AD_SIZES_ARE_INCLUDED("%s ad sizes are included"),
    AD_FORMATS_ARE_INCLUDED("%s ad formats are included"),
    DEMAND_SOURCES_ARE_INCLUDED("%s demand sources are included"),
    MEDIA_AD_SPOTS_ARE_INCLUDED("%s media, %s ad spots are included"),
    MEDIA_ONE_AD_SPOT_ARE_INCLUDED("%s media, %s ad spot are included"),
    OPERATING_SYSTEMS_ARE_INCLUDED("%s operating systems are included"),
    MEDIA_IS_INCLUDED_AD_SPOT_IS_EXCLUDED("%s media is included and %s ad spot is excluded"),
    MEDIA_ARE_INCLUDED_AD_SPOT_IS_EXCLUDED("%s media are included and %s ad spot is excluded"),
    MEDIA_ARE_INCLUDED_AD_SPOTS_ARE_EXCLUDED("%s media are included and %s ad spots are excluded");

    private final String value;

    public String setQuantity(Object... parameters) {

        return parameters.length > 0 ? String.format(value, parameters) : value;
    }
}