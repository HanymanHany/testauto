package Tests.Market;

import configs.DataConfig;
import configs.UrlsConfig;
import constants.ErrorsMsg;
import env.Base_Container;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Keys;
import steps.MainSteps;
import testPageLocator.market.mainPage.MainLocators;
import testPageLocator.market.productsPage.ProductsLocators;
import testPageLocator.market.registrationOrderPage.RegistrationOrderLocators;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Web testing")
@Story("Маркет. Поиск и фильтрация")
@Execution(ExecutionMode.SAME_THREAD)
public class testAddOrder extends Base_Container {
    RegistrationOrderLocators orderPage;

    MainSteps mainSteps = new MainSteps();
    static final DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());
    static final UrlsConfig urlConfig = ConfigFactory.create(UrlsConfig.class, System.getProperties());

    @Test
    @Description("Добавление в корзину и оформление заказа неавторизованным пользователем.")
    void testRegistrationOrder() {
        // actions
        mainSteps.addOrder("ПРОДАЖИ","г Москва, ул Земляной Вал, д 33");

    }

    //FIXME: тест будет доработан позже, проблемы с отменой заказа
    @Test
    @Description("Отмена неоплаченного заказа.")
    void testCancelOrder() {

//        // prepare
//        mainSteps.addOrder("ПРОДАЖИ","г Москва, ул Земляной Вал, д 33");
//
//        // actions
//        step("Открытие страницы " +urlConfig.getMarketUrl()+"/profile/orders/",
//                () -> open(urlConfig.getMarketUrl()+"/profile/orders/"));
//        step("Нажимаем на карточку заказа", () -> { orderPage.LastOrderCard.click(); });
//        step("Нажимаем на кнопку отмены заказа", () -> { orderPage.CancelOrderBtn.click(); });
//        step("Нажимаем на кнопку отправить", () -> { orderPage.SendBtn.click(); });

    }


}
