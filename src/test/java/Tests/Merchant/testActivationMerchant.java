package Tests.Merchant;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import configs.DataConfig;
import configs.UrlsConfig;
import constants.ErrorsMsg;
import env.Base_Container;
import helpers.Generator;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import steps.MainSteps;
import testPageLocator.admin.MerchantsLocators;
import testPageLocator.merchant.RegisterLocators;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.ApiHelper.*;
import static helpers.Helper.clearInputForReact;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Web testing")
@Story("Мерчант. Активация")
@Execution(ExecutionMode.SAME_THREAD)
public class testActivationMerchant extends Base_Container {
    static final DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());
    static final UrlsConfig urlConfig = ConfigFactory.create(UrlsConfig.class, System.getProperties());
    String link;
    Generator generator = new Generator();
    String longName = generator.getLongName();
    String email = generator.getEmail();
    String nameCompany = generator.getCompany();
    RegisterLocators regPage;
    MerchantsLocators merchantsPage;

    MainSteps mainSteps = new MainSteps();

    @Override
    @BeforeEach
    public void profileSetup() {
        RestAssured.requestSpecification = RequestSpec(urlConfig.getAdminUrl()+"/api/merchant/confirm/email/link");

    }

    @Test
    @Description("Активация мерчанта.")
    void testCorrectActivationMerchant() throws InterruptedException, MalformedURLException, UnsupportedEncodingException {

        // prepare
        step("Открытие сайта " + urlConfig.getMerchantUrl()+"/registration", () -> open(urlConfig.getMerchantUrl()+"/registration"));
        mainSteps.createMerchant(email,nameCompany);
        mainSteps.loginInAdmin();
        step("Открытие страницы " +urlConfig.getAdminUrl()+"/merchant/list/registration", () -> open(urlConfig.getAdminUrl()+"/merchant/list/registration"));

        //action
        step("Нажимаем на название организации" + nameCompany, () -> {$(By.xpath("//*[@class='table']//*[contains(text(),'" + nameCompany + "')]")).click();});
        step("Нажимаем на кнопку Активировать" , () -> {merchantsPage.ActivateBtn.click();});
        step("Ожидаем, когда кнопка Активировать станет недоступна" , () -> {merchantsPage.ActivateBtn.should(disappear);});
        step("Получаем ссылку для активации мерчанта" , () -> { link = getParamJson(JsonPath.read(requestPost(email),"$..link").toString());});

        String userid = String.valueOf(getParamUrl(new URL(link)).get("user_id"));
        String token = String.valueOf(getParamUrl(new URL(link)).get("token"));
        step("Открытие страницы " + urlConfig.getMerchantUrl() + "/confirm-email?user_id="+userid+"&token="+token+"",
                () -> open(urlConfig.getMerchantUrl() + "/confirm-email?user_id="+clearQuery(userid)+"&token="+clearQuery(token)+""));

        step("Устанавливаем пароль для мерчанта" , () -> {
            merchantsPage.SetPasswordField.setValue("1234");
            merchantsPage.SetPasswordSaveBtn.click();
        });

    }




}
