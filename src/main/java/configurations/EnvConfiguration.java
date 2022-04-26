package configurations;

import lombok.Data;

@Data
public  class EnvConfiguration {
    private String baseUrlAPI;
    private String baseUrl;
    private String browser;
    private String pageLoadStrategy;
    private String browserSize;
    private Boolean holdBrowserOpen;
    private Boolean reopenBrowserOnFail;
    private Boolean screenshots;
    private Integer timeout;
    private Boolean headless;
    private Boolean enableProxy;
}
