package rx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MultipaneConstants {

    ONE_GEO_IS_INCLUDED("%s geo(s) are included"),
    ONE_MEDIA_IS_INCLUDED("%s media are included"),
    ONE_AD_SIZE_IS_INCLUDED("%s size(s) are included"),
    ONE_DEVICE_IS_INCLUDED("%s device(s) are included"),
    ONE_AD_FORMAT_IS_INCLUDED("%s format(s) are included"),
    ONE_DEMAND_SOURCE_IS_INCLUDED("%s demand source(s) are included"),
    ONE_OPERATING_SYSTEM_IS_INCLUDED("%s operating system(s) are included");

    private final String value;

    public String setQuantity(Object... parameters) {

        return parameters.length > 0 ? String.format(value, parameters) : value;
    }
}
