package configs;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/other/orderData.properties"
})
public interface DataOrderConfig extends Config {

    @Key("flat")
    String getFlat();

    @Key("porch")
    String getPorch();

    @Key("floor")
    String getFloor();

    @Key("intercom")
    String getIntercom();

    @Key("comments")
    String getComments();

}
