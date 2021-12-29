package env;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import configs.UrlsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
//import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.attachScreenshot;
import static io.qameta.allure.Allure.step;


public class Base_Container {
    WebDriver driver;
    static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    static final UrlsConfig urlConfig = ConfigFactory.create(UrlsConfig.class, System.getProperties());
    static public String URL = urlConfig.getMarketUrl();

    @BeforeAll
    public static void setup() {
        //addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browser = config.getWebBrowser();
        Configuration.baseUrl = urlConfig.getMarketUrl();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = config.getBrowserTimeout();
        Configuration.headless = config.getHeadlessMode();
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
}
