package Tests;

import steps.MainSteps;
import testPageLocator.authPage.AuthLocators;
import testPageLocator.mainPage.MainLocators;
import configs.DataConfig;
import constants.ErrorsMsg;
import env.Base_Container;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Web testing")
@Tag("AuthUser")
@DisplayName("Tests auth user.")

public class testAuthUser extends Base_Container {
    static final DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());
    AuthLocators authLocators;
    MainLocators mainLocators;
    MainSteps mainSteps = new MainSteps();

    @Test
    @Description("Корректная авторизация пользователя по номеру телефона.")
    void testAuthUserByPhone() {
        mainSteps.loginUser();
    }

    @Test
    @Description("Проверка отправки кода смс без ввода номера телефона.")
    void testGetCodeWithEmptyFieldPhoneNumber() {
        // prepare
        step("Нажатие на кнопку Войти", () -> { mainLocators.LogInBtn.click(); });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> { authLocators.SignUpBtn.click(); });

        // actions
        step("Оставляем пустое поле и нажимаем получить код", () -> { authLocators.GetCodeSms.click(); });

        // verification
        step("Проверяем отображение ошибки под полем \"Обязательное поле\"", () -> {
            assertEquals(ErrorsMsg.obligatory_field.getErrorMsg(),authLocators.ErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Проверка ввода некорректного номера телефона.")
    void testInputIncorrectPhoneNumber() {
        // prepare
        step("Нажатие на кнопку Войти", () -> { mainLocators.LogInBtn.click(); });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> { authLocators.SignUpBtn.click(); });

        // actions
        step("Оставляем пустое поле и нажимаем получить код", () -> {
            authLocators.LoginField.setValue("123&*R#$&@#$");
            authLocators.GetCodeSms.click(); });

        // verification
        step("Проверяем отображение ошибки под полем \"Неверно введен номер\"", () -> {
            assertEquals(ErrorsMsg.invalid_number.getErrorMsg(),authLocators.ErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Story("AuthUser")
    @Description("Проверка ввода пустого кода смс.")
    void testInputEmptyCodeSMS() {
        // prepare
        step("Нажатие на кнопку Войти", () -> { mainLocators.LogInBtn.click(); });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> { authLocators.SignUpBtn.click(); });
        step("Ввод номера телефона и получение кода", () -> {
            authLocators.LoginField.setValue(data.getPhone());
            authLocators.GetCodeSms.click();
        });

        // actions
        step("Оставляем пустое поле и нажимаем кнопку Подтвердить", () -> { authLocators.ConfirmBtn.click();  });

        // verification
        step("Проверяем отображение ошибки под полем \"Обязательное поле\"", () -> {
            assertEquals(ErrorsMsg.obligatory_field.getErrorMsg(),authLocators.ErrorMsg.getText(),"Ошибка должна отображаться."); });
    }

    @Test
    @Story("AuthUser")
    @Description("Проверка ввода некорректного кода смс.")
    void testInputIncorrectCodeSms() {
        // prepare
        step("Нажатие на кнопку Войти", () -> { mainLocators.LogInBtn.click(); });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> { authLocators.SignUpBtn.click(); });
        step("Ввод номера телефона и получение кода", () -> {
            authLocators.LoginField.setValue(data.getPhone());
            authLocators.GetCodeSms.click();
        });

        // actions
        step("Оставляем пустое поле и нажимаем кнопку Подтвердить", () -> {
            authLocators.SmsField.setValue("#$23");
            authLocators.ConfirmBtn.click();  });

        // verification
        step("Проверяем отображение ошибки под полем \"Неверно введен код\"", () -> {
            assertEquals(ErrorsMsg.wrong_code.getErrorMsg(),authLocators.ErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

}
