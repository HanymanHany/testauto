package Tests.Market;

import com.github.javafaker.Faker;
import configs.DataConfig;
import constants.ErrorsMsg;
import env.Base_Container;
import helpers.Generator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Keys;
import steps.MainSteps;
import testPageLocator.market.mainPage.MainLocators;
import testPageLocator.market.profilePage.ProfileLocators;

import static helpers.Helper.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Web testing")
@Story("Клиент. Профиль")
@Execution(ExecutionMode.SAME_THREAD)
public class testProfileUser extends Base_Container {
    Generator generator = new Generator();
    String longName = generator.getLongName();
    static final DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());
    MainLocators mainPage;
    ProfileLocators profilePage;
    MainSteps mainSteps = new MainSteps();


    @Test
    @Description("Проверка пустых полей.")
    void testCheckFieldNameOnObligatory() {
        // prepare
        mainSteps.loginUser(data.getPhone());
        mainSteps.clickProfile();

        // actions
        step("Оставляем пустое поле ФИО и переводим курсор на другое поле", () -> {
            clearInputForReact(profilePage.NameField);profilePage.PhoneField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.obligatory_field.getErrorMsg()+" под полем ФИО ", () -> {
            assertEquals(ErrorsMsg.obligatory_field.getErrorMsg(),profilePage.NameErrorMsg.getText(),"Ошибка должна отображаться.");
        });

        // actions
        step("Оставляем пустое поле E-mail и переводим курсор на другое поле", () -> {
            clearInputForReact(profilePage.EmailField);profilePage.PhoneField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.obligatory_field.getErrorMsg()+" под полем E-mail", () -> {
            assertEquals(ErrorsMsg.obligatory_field.getErrorMsg(),profilePage.EmailErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Превышение лимита на ФИО.")
    void testCheckExceedingLimitFieldName() {
        // prepare
        mainSteps.loginUser(data.getPhone());
        mainSteps.clickProfile();

        // actions
        step("В поле “ФИО” вводим более 128 символов ", () -> {
            profilePage.NameField.setValue(longName);profilePage.PhoneField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.maximum_length__surname_field.getErrorMsg()+" под полем ФИО ", () -> {
            assertEquals(ErrorsMsg.maximum_length__surname_field.getErrorMsg(),profilePage.NameErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Ввод неполное количество цифры в поле телефонного номера.")
    void testInputIncorrectPhoneNumber() {
        // prepare
        mainSteps.loginUser(data.getPhone());
        mainSteps.clickProfile();

        // actions
        step("Вводим в поле Телефон произвольные символы", () -> {
            clearInputForReact(profilePage.PhoneField);
            profilePage.PhoneField.setValue("1234");
            profilePage.EmailField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.invalid_number.getErrorMsg()+" под полем Телефон" , () -> {
            assertEquals(ErrorsMsg.invalid_number.getErrorMsg(),profilePage.PhoneErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Ввод некорректного формата емейла.")
    void testInputIncorrectEmail() {
        // prepare
        mainSteps.loginUser(data.getPhone());
        mainSteps.clickProfile();

        // actions
        step("Вводим в поле Телефон произвольные символы", () -> {
            clearInputForReact(profilePage.EmailField);
            profilePage.EmailField.setValue("email");
            profilePage.PhoneField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.incorrect_email.getErrorMsg()+" под полем E-mail" , () -> {
            assertEquals(ErrorsMsg.incorrect_email.getErrorMsg(),profilePage.EmailErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }




}
