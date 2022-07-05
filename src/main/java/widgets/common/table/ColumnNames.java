package widgets.common.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColumnNames {
    //General for all tables
    ID("ID", true),
    PUBLISHER("Publisher", true),
    CREATED_DATE("Created Date", true),
    CREATED_BY("Created By", true),
    UPDATED_DATE("Updated Date", true),
    UPDATED_BY("Updated By", true),
    DETAILS("Details", true),
    ACTIVE("Active", true),
    INACTIVE("Inactive", false),
    BOTH("Both", false),

    NAME("Name", true),
    ALWAYS_ON("Always on", true),
    START_DATE("Start Date", true),
    END_DATE("End Date", true),
    STATUS("Status", true),
    ACTIVE_INACTIVE("Active/Inactive", true),

    //Demand Source
    DSP_ID("DSP ID", false),

    BIDDER("Bidder", true),


    //Publisher
    CATEGORY("Category", true),
    DOMAIN("Domain", true),
    CURRENCY("Currency", true),
    AD_OPS_PERSON("Ad Ops Person", true),
    MAIL("Mail", true),


    //Media
    MEDIA_NAME("Media Name", true),
    PLATFORM("Platform", true),
    SITE_APP_STORE_URL("Site / App Store URL", true),

    //Ad Spot Name
    AD_SPOT_NAME("Ad Spot Name", true),
    RELATED_MEDIA("Related Media", true),

    PAGE_CATEGORY("Page Category", false),
    FILTER("Filter", false),
    TEST_MODE("Test Mode", false),
    DEFAULT_SIZES("Default Sizes", false),
    DEFAULT_FLOOR_PRICE("Default Floor Price", true),

    //Private Auction
    OPTIMIZE("Optimize", false),

    //Protections
    MANAGE_BY_SYSTEM_ADMIN("Manage by system admin", false),

    //Users
    EMAIL("Email", true),
    ROLE("Role", false),

    //Deals
    PRIVATE_AUCTION("Private Auction", true),
    DSP("DSP", false),
    PRICE_VALUE("Price | Value", true),
    PRICE_CURRENCY("Price | Currency", true),

    //Open Pricing
    FLOOR_PRICE("Floor Price", true);


    private String name;
    private Boolean sortable;

}
