package widgets.common.table;

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

    Columns(String locator, String name, Boolean sortable) {
        this.locator = locator;
        this.name = name;
        this.sortable = sortable;
    }

    public String getLocator() {
        return ".v-data-table-header tr th[aria-label^='"+locator+"']";
    }

    public String getName() {
        return name;
    }

    public Boolean getSortable() {
        return sortable;
    }

    public static Columns findByAbbr(String abbr) {
        for (Columns v : values()) {
            if (v.getName().equals(abbr)) {
                return v;
            }
        }
        return null;
    }


}
