package steps;

import testPageLocator.authPage.*;
import testPageLocator.mainPage.MainLocators;
import com.codeborne.selenide.Condition;
import configs.DataConfig;
import env.Base_Container;
import org.aeonbits.owner.ConfigFactory;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

import static io.qameta.allure.Allure.step;

public class MainSteps extends Base_Container {
    AuthLocators authLocators;
    CityLocators cityLocators;
    MainLocators mainLocators;
    static final DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());

    // Steps login user in system. Login with exist phone 79999999999 and sms 1234
    public void loginUser() {
        step("Нажатие на кнопку Войти", () -> {
            mainLocators.LogInBtn.click();
        });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> {
            authLocators.SignUpBtn.click();
        });
        step("Ввод номера телефона и получение кода", () -> {
            authLocators.LoginField.setValue(data.getPhone());
            authLocators.GetCodeSms.click();
        });
        step("Ввод кода из смс", () -> {
            authLocators.SmsField.setValue(data.getCode());
            authLocators.ConfirmBtn.click();

        });
        step("Проверяем отображение страницы с локацией и Подтверждаем город", () -> {
            cityLocators.LocationPage.shouldBe(Condition.visible);
            cityLocators.ConfirmCity.click();
        });
        step("Проверяем что кнопка \"Войти отсутствует\"", () -> {
            assertFalse( mainLocators.LogInBtn.exists(),"Кнопка войти не должна отображаться.");
        });
    }
}
