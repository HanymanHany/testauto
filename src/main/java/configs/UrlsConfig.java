package configs;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/env/urls.properties"
})
public interface UrlsConfig extends Config {

    @Key("market.url")
    String getMarketUrl();

    @Key("merchant.url")
    String getMerchantUrl();

    @Key("admin.url")
    String getAdminUrl();

    @Key("admin.login")
    String getAdminLogin();

    @Key("admin.password")
    String getAdminPassword();

}
