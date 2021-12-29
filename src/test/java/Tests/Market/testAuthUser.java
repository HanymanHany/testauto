package Tests.Market;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import steps.MainSteps;
import testPageLocator.market.authPage.AuthLocators;
import testPageLocator.market.mainPage.MainLocators;
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
import static org.junit.jupiter.api.Assertions.*;

@Feature("Web testing")
@Tag("AuthUser")
@Story("Клиент.Авторизация")
@DisplayName("Tests auth user.")
@Execution(ExecutionMode.SAME_THREAD)
public class testAuthUser extends Base_Container {
    static final DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());
    AuthLocators authPage;
    MainLocators mainPage;
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
        step("Нажатие на кнопку Войти", () -> { mainPage.LogInBtn.click(); });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> { authPage.SignUpBtn.click(); });

        // actions
        step("Оставляем пустое поле и нажимаем получить код", () -> { authPage.GetCodeSms.click(); });

        // verification
        step("Проверяем отображение ошибки под полем \"Обязательное поле\"", () -> {
            assertEquals(ErrorsMsg.obligatory_field.getErrorMsg(),authPage.ErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Проверка ввода некорректного номера телефона.")
    void testInputIncorrectPhoneNumber() {
        // prepare
        step("Нажатие на кнопку Войти", () -> { mainPage.LogInBtn.click(); });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> { authPage.SignUpBtn.click(); });

        // actions
        step("Оставляем пустое поле и нажимаем получить код", () -> {
            authPage.LoginField.setValue("123&*R#$&@#$");
            authPage.GetCodeSms.click(); });

        // verification
        step("Проверяем отображение ошибки под полем \"Неверно введен номер\"", () -> {
            assertEquals(ErrorsMsg.invalid_number.getErrorMsg(),authPage.ErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Проверка ввода пустого кода смс.")
    void testInputEmptyCodeSMS() {
        // prepare
        step("Нажатие на кнопку Войти", () -> { mainPage.LogInBtn.click(); });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> { authPage.SignUpBtn.click(); });
        step("Ввод номера телефона и получение кода", () -> {
            authPage.LoginField.setValue(data.getPhone());
            authPage.GetCodeSms.click();
        });

        // actions
        step("Оставляем пустое поле и нажимаем кнопку Подтвердить", () -> { authPage.ConfirmBtn.click();  });

        // verification
        step("Проверяем отображение ошибки под полем \"Обязательное поле\"", () -> {
            assertEquals(ErrorsMsg.obligatory_field.getErrorMsg(),authPage.ErrorMsg.getText(),"Ошибка должна отображаться."); });
    }

    @Test
    @Description("Проверка ввода некорректного кода смс.")
    void testInputIncorrectCodeSms() {
        // prepare
        step("Нажатие на кнопку Войти", () -> { mainPage.LogInBtn.click(); });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> { authPage.SignUpBtn.click(); });
        step("Ввод номера телефона и получение кода", () -> {
            authPage.LoginField.setValue(data.getPhone());
            authPage.GetCodeSms.click();
        });

        // actions
        step("Оставляем пустое поле и нажимаем кнопку Подтвердить", () -> {
            authPage.SmsField.setValue("#$23");
            authPage.ConfirmBtn.click();  });

        // verification
        step("Проверяем отображение ошибки под полем \"Неверно введен код\"", () -> {
            assertEquals(ErrorsMsg.wrong_code.getErrorMsg(),authPage.ErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Проверка входа как гость.")
    void testLogInAsGuest() {
        // prepare
        step("Нажатие на кнопку Войти", () -> { mainPage.LogInBtn.click(); });


        // actions
        step("Нажатие на кнопку Войти как гость", () -> { authPage.LogInGuestBtn.click(); });

        // verification
        step("Проверяем что кнопка \"Войти Оотображается\"", () -> {
            assertTrue( mainPage.LogInBtn.exists(),"Кнопка войти не должна отображаться.");
        });
    }
}
