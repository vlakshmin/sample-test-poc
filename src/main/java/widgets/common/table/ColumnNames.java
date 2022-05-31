package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColumnNames {

    DSP_ID("DSP ID", false),
    DETAILS( "Details", true),
    STATUS("Status", true),
    BIDDER( "Bidder", true),
    CREATED_DATE( "Create Date", true),
    UPDATED_DATE("Updated Date", true),
    UPDATED_BY("Updated By", true),

    ID("ID", true),
    MEDIA_NAME( "Media Name", true),
    PUBLISHER( "Publisher", true),
    PLATFORM( "Platform", true);


    private String name;
    private Boolean sortable;


    public static ColumnNames findByAbbr(String abbr) {
        for (ColumnNames v : values()) {
            if (v.getName().equals(abbr)) {
                return v;
            }
        }
        return null;
    }


}
