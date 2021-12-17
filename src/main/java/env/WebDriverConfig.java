package env;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/env/browser.properties"
})
public interface WebDriverConfig  extends Config {

    @Key("baseUrl")
    String getUrl();

    @Key("timeout")
    long getBrowserTimeout();

    @Key("browser")
    String getWebBrowser();

    @Key("browser.size")
    String getBrowserSize();

    @Key("headless")
    boolean getHeadlessMode();

}
