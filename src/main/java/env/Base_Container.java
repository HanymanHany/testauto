package env;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
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
    static public String url = config.getUrl();

    @BeforeAll
    public static void setup() {
        //addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browser = config.getWebBrowser();
        Configuration.baseUrl = config.getUrl();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = config.getBrowserTimeout();
        Configuration.headless = config.getHeadlessMode();
    }

    @BeforeEach
    public void profileSetup() {

        step("Открытие сайта " + url, () -> open(url));
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
