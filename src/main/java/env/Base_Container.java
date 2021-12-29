package env;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import configs.UrlsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
//import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.attachScreenshot;
import static io.qameta.allure.Allure.step;


public class Base_Container {
    static WebDriver driver;
    static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    static final UrlsConfig urlConfig = ConfigFactory.create(UrlsConfig.class, System.getProperties());
    static public String URL = urlConfig.getMarketUrl();

    @BeforeAll
    public static void setup() throws MalformedURLException {
        getConfiguration(config.getRunType());
        //addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

    }

    @BeforeEach
    public void profileSetup() {
        System.out.println("URL " + URL);
        step("Открытие сайта " + URL, () -> open(URL));
        driver = WebDriverRunner.getWebDriver();


    }
    @AfterEach
    public void tearDown(){
        attachScreenshot("Last screenshot");
        Selenide.clearBrowserCookies();
        Selenide.refresh();
    }

    @AfterAll
    public static void closeSession() {
        closeWebDriver();
    }

    public static void getConfiguration(String runType) throws MalformedURLException {

        switch (runType){
            case("local"):
                Configuration.browser = config.getWebBrowser();
                Configuration.baseUrl = urlConfig.getMarketUrl();
                Configuration.browserSize = config.getBrowserSize();
                Configuration.timeout = config.getBrowserTimeout();
                Configuration.headless = config.getHeadlessMode();
                break;
            case("remote"):
                Configuration.browser = config.getWebBrowser();
                DesiredCapabilities browser = new DesiredCapabilities();
                browser.setBrowserName(config.getWebBrowser());
                browser.setVersion(config.getBrowserVersion());
                browser.setCapability("enableVNC", true);
                driver = new RemoteWebDriver(URI.create(config.getRemoteUrl()).toURL(),browser);
                setWebDriver(driver);
                driver.manage().window().setSize(new Dimension(1920, 1080));
                break;
        }

    }
}
