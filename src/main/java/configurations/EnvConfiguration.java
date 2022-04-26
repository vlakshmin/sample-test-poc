package configurations;

import lombok.Data;

@Data
public  class EnvConfiguration {
    private String baseUrlAPI;
    private String baseUrl;
    private String browser;
    private Boolean enableSelenoid;
    private String pageLoadStrategy;
    private String browserSize;
    private Boolean holdBrowserOpen;
    private Boolean reopenBrowserOnFail;
    private Boolean screenshots;
    private Integer timeout;
    private Boolean headless;
    private String selenoidHost;
    private Integer selenoidPort;
    private Boolean enableProxy;
}
