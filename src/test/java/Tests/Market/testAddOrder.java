package Tests.Market;

import configs.DataConfig;
import configs.DataOrderConfig;
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
    static final DataOrderConfig dataOrder = ConfigFactory.create(DataOrderConfig.class, System.getProperties());
    static final UrlsConfig urlConfig = ConfigFactory.create(UrlsConfig.class, System.getProperties());
    String product = "ПРОДАЖИ";
    String address = "г Москва, ул Земляной Вал, д 33";

    @Test
    @Description("Добавление в корзину и оформление заказа неавторизованным пользователем.")
    void testAddOrderUnAuthUser() {
        // actions
        mainSteps.addOrder(product,address);

    }

    @Test
    @Description("Добавление в корзину и оформление заказа пользователем, авторизованным с помощью номера телефона.")
    void testAddOrderAuthUser() {
        // prepare
        mainSteps.loginUser(data.getPhoneUser2());
        mainSteps.addProductsInCart(product);
        // actions
        step("Нажимаем на кнопку Перейти к оформлению", () -> { orderPage.CheckoutCartBtn.click(); });
        step("Отображается страница с выбором адреса" , () -> {orderPage.AddressBlock.shouldBe();});
        step("Вводим адрес в поле" , () -> {orderPage.AddressField.setValue(address);});
        step("Нажимаем на кнопку Продолжить" , () -> {orderPage.ContinueBtn.click();});
        step("Заполняем поле Квартира" , () -> {orderPage.ApartmentField.setValue(dataOrder.getFlat());});
        step("Заполняем поле Подъезд" , () -> {orderPage.EntranceField.setValue(dataOrder.getPorch());});
        step("Заполняем поле Этаж" , () -> {orderPage.FloorField.setValue(dataOrder.getFloor());});
        step("Заполняем поле Комментарий курьеру" , () -> {orderPage.CommentsField.setValue(dataOrder.getComments());});
        step("Нажимаем на кнопку Сохранить и Продолжить" , () -> {orderPage.SaveContinueBtn.click();});
        step("Отображается информация по заказу и блок с доставкой" , () -> {orderPage.DeliveryBlock.shouldBe();});
        step("Нажимаем кнопку Далее" , () -> {orderPage.NextOrderBtn.click();});
        step("Отображается страница оплаты" , () -> {orderPage.PaymentBlock.shouldBe();});
        step("Нажимаем кнопку Оплаты" , () -> {orderPage.PayBtn.click();});

        // verification
        step("Проверяем отображение успешности заказа ", () -> {
            orderPage.SuccessOrder.shouldBe();});

    }


    @Test
    @Description("Отмена неоплаченного заказа.")
    void testCancelOrder() {

        // prepare
        mainSteps.addOrder(product,address);

        // actions
        step("Открытие страницы " +urlConfig.getMarketUrl()+"/profile/orders/",
                () -> open(urlConfig.getMarketUrl()+"/profile/orders/"));
        step("Нажимаем на карточку заказа", () -> { orderPage.LastOrderCard.click(); });
        step("Нажимаем на кнопку отмены заказа", () -> { orderPage.CancelOrderBtn.click(); });
        step("Нажимаем на кнопку отправить", () -> { orderPage.SendBtn.click(); });

    }


}
