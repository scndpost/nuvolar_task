package configuration;

import org.aeonbits.owner.Config;

@Config.Sources({"file:.env"})
public interface Configuration extends Config {

    @Key("BROWSER")
    @DefaultValue("CHROME")
    SupportedBrowser browser();
    @Key("BASE_PETSTORE_URI")
    String basePetstoreUri();

}
