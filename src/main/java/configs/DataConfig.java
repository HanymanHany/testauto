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

    @Key("phone.user2")
    String getPhoneUser2();

    @Key("sms.code")
    String getCode();

    @Key("name.merchant")
    String getNameMerchant();

    @Key("surname.merchant")
    String getSurnameMerchant();

    @Key("middle.name.merchant")
    String getMiddleNameMerchant();

    @Key("phone.merchant")
    String getPhoneMerchant();

    @Key("email.merchant")
    String getEmailMerchant();

    @Key("inn.merchant")
    String getInnMerchant();

    @Key("organization.merchant")
    String getOrganizationMerchant();

    @Key("address.merchant")
    String getAddressMerchant();
}
