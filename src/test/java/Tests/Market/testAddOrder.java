package Tests.Market;

import configs.DataConfig;
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
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Web testing")
@Story("Маркет. Поиск и фильтрация")
@Execution(ExecutionMode.SAME_THREAD)
public class testAddOrder extends Base_Container {
    RegistrationOrderLocators orderPage;

    MainSteps mainSteps = new MainSteps();
    static final DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());
    @Test
    @Description("Добавление в корзину и оформление заказа неавторизованным пользователем.")
    void testRegistrationOrder() {
        // prepare
        mainSteps.addProductsInCart("ПРОДАЖИ");

        // actions
        step("Нажимаем на кнопку Перейти к оформлению", () -> { orderPage.CheckoutCartBtn.click(); });
        step("Отображается страница с выбором адреса" , () -> {orderPage.AddressBlock.shouldBe();});
        step("Вводим адрес в поле" , () -> {orderPage.AddressField.setValue("г Москва, ул Земляной Вал, д 33");});
        step("Выбираем предложенный адрес" , () -> {orderPage.AddressItem.click();});
        step("Нажимаем на кнопку Продолжить" , () -> {orderPage.ContinueBtn.click();});
        step("Заполняем поле Квартира" , () -> {orderPage.ApartmentField.setValue("2");});
        step("Заполняем поле Подъезд" , () -> {orderPage.EntranceField.setValue("2");});
        step("Заполняем поле Этаж" , () -> {orderPage.FloorField.setValue("2");});
        step("Заполняем поле Комментарий курьеру" , () -> {orderPage.CommentsField.setValue("comment");});
        step("Заполняем поле Телефон" , () -> {orderPage.PhoneField.setValue(data.getPhone());});
        step("Заполняем поле Имя" , () -> {orderPage.NameField.setValue("name");});
        step("Нажимаем на кнопку Сохранить и Продолжить" , () -> {orderPage.SaveContinueBtn.click();});
        step("Вводим пароль в поле код из смс и нажимаем продолжить" , () -> {
            orderPage.PasswordField.setValue("1234");
            orderPage.ConfirmBtn.click();
        });
        step("Отображается информация по заказу и блок с доставкой" , () -> {orderPage.DeliveryBlock.shouldBe();});
        step("Нажимаем кнопку Далее" , () -> {orderPage.NextOrderBtn.click();});
        step("Отображается страница оплаты" , () -> {orderPage.PaymentBlock.shouldBe();});
        step("Нажимаем кнопку Оплаты" , () -> {orderPage.PayBtn.click();});

        // verification
        step("Проверяем отображение успешности заказа ", () -> {
            orderPage.SuccessOrder.shouldBe();});

    }


}
