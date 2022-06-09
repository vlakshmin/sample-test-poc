package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Columns {

    DSP_ID("DSP ID", "DSP ID", false),
    DETAILS("Details", "Details", true),
    STATUS("Status", "Status", true),
    BIDDER("Bidder", "Bidder", true),
    CREATED_DATE("Create Date", "Create Date", true),
    UPDATED_DATE("Update Date", "Updated Date", true),
    UPDATED_BY("Updated By", "Updated By", true),

    ID("ID", "ID", true),
    MEDIA_NAME("Media Name", "Media Name", true),
    PUBLISHER("Publisher", "Publisher", true),
    PLATFORM("Platform", "Platform", true);


    private String locator;
    private String name;
    private Boolean sortable;

}
