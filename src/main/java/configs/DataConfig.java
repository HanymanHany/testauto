package configs;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/other/data.properties"
})
public interface DataConfig extends Config {

    @Key("phone")
    String getPhone();

    @Key("sms_code")
    String getCode();

}
