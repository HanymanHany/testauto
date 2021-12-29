package Tests.Merchant;

import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import configs.DataConfig;
import configs.UrlsConfig;
import constants.ErrorsMsg;
import env.Base_Container;
import helpers.Generator;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import steps.MainSteps;
import testPageLocator.merchant.RegisterLocators;


import static com.codeborne.selenide.Selenide.open;
import static helpers.Helper.clearInputForReact;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Web testing")
@Story("Мерчант. Регистрация")
@Execution(ExecutionMode.SAME_THREAD)
public class testRegisterMerchant extends Base_Container {
    static final DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());
    static final UrlsConfig urlConfig = ConfigFactory.create(UrlsConfig.class, System.getProperties());
    Generator generator = new Generator();
    String longName = generator.getLongName();
    String email = generator.getEmail();
    RegisterLocators regPage;

    MainSteps mainSteps = new MainSteps();

    @Override
    @BeforeEach
    public void profileSetup() {
        step("Открытие сайта " + urlConfig.getMerchantUrl()+"/registration", () -> open(urlConfig.getMerchantUrl()+"/registration"));

    }

    @Test
    @Description("Регистрация мерчанта.")
    void testCorrectRegistrationMerchant() {
        mainSteps.createMerchant();
    }

    @Test
    @Description("Регистрация мерчанта с пустыми полями.")
    void testRegistrationMerchantEmptyFields() {

        // actions
        step("Нажимаем на кнопку Зарегистрироваться", () -> {regPage.RegistrationBtn.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.obligatory_field_merchant.getErrorMsg()+" под полями", () -> {
            assertEquals(ErrorsMsg.obligatory_field_merchant.getErrorMsg(),regPage.SurnameErrorMsg.getText(),"Ошибка должна отображаться под полем Фамилия.");
            assertEquals(ErrorsMsg.obligatory_field_merchant.getErrorMsg(),regPage.NameErrorMsg.getText(),"Ошибка должна отображаться под полем Имя.");
            assertEquals(ErrorsMsg.obligatory_field_merchant.getErrorMsg(),regPage.MiddleNameErrorMsg.getText(),"Ошибка должна отображаться под полем Отчество.");
            assertEquals(ErrorsMsg.obligatory_field_merchant.getErrorMsg(),regPage.PhoneErrorMsg.getText(),"Ошибка должна отображаться под полем Телефон.");
            assertEquals(ErrorsMsg.obligatory_field_merchant.getErrorMsg(),regPage.EmailErrorMsg.getText(),"Ошибка должна отображаться под полем Email.");
            assertEquals(ErrorsMsg.obligatory_field_merchant.getErrorMsg(),regPage.InnErrorMsg.getText(),"Ошибка должна отображаться под полем INN.");
            assertEquals(ErrorsMsg.obligatory_field_merchant.getErrorMsg(),regPage.OrganizationErrorMsg.getText(),"Ошибка должна отображаться под полем Название организации.");
            assertEquals(ErrorsMsg.obligatory_field_merchant.getErrorMsg(),regPage.AddressErrorMsg.getText(),"Ошибка должна отображаться под полем Адреса складов отгрузки.");
            assertEquals(ErrorsMsg.obligatory_field_merchant.getErrorMsg(),regPage.ProductsFileErrorMsg.getText(),"Ошибка должна отображаться под полем Товарный ассортимент.");
        });

    }

    @Test
    @Description("Превышение лимита на ФИО.")
    void testCheckExceedingLimitFieldName() {
        // actions
        step("В поле “ФИО” вводим более 128 символов ", () -> {
            regPage.NameField.setValue(longName);regPage.PhoneField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.maximum_length_surname_field_merchant.getErrorMsg()+" под полем ФИО ", () -> {
            assertEquals(ErrorsMsg.maximum_length_surname_field_merchant.getErrorMsg(),regPage.NameErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Ввод неполное количество цифры в поле телефонного номера.")
    void testInputIncorrectPhoneNumber() {
        // actions
        step("Вводим в поле Телефон произвольные символы", () -> {
            clearInputForReact(regPage.PhoneField);
            regPage.PhoneField.setValue("1234");
            regPage.EmailField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.invalid_number_merchant.getErrorMsg()+" под полем Телефон" , () -> {
            assertEquals(ErrorsMsg.invalid_number_merchant.getErrorMsg(),regPage.PhoneErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Ввод некорректного формата емейла.")
    void testInputIncorrectEmail() {

        // actions
        step("Вводим в поле Email произвольные символы", () -> {
            clearInputForReact(regPage.EmailField);
            regPage.EmailField.setValue("email");
            regPage.PhoneField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.incorrect_email_merchant.getErrorMsg()+" под полем E-mail" , () -> {
            assertEquals(ErrorsMsg.incorrect_email_merchant.getErrorMsg(),regPage.EmailErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Ввод различных символов в поле ИНН.")
    void testInputIncorrectINN() {

        // actions
        step("Вводим в поле ИНН произвольные символы", () -> {
            clearInputForReact(regPage.InnField);
            regPage.InnField.setValue("#@$EWDRWF");
            regPage.PhoneField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.only_digits_merchant.getErrorMsg()+" под полем Inn" , () -> {
            assertEquals(ErrorsMsg.only_digits_merchant.getErrorMsg(),regPage.InnErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }

    @Test
    @Description("Ввод в поле ИНН 13 цифр.")
    void testInputHalfINN() {

        // actions
        step("Вводим в поле ИНН 13 цифр", () -> {
            clearInputForReact(regPage.InnField);
            regPage.InnField.setValue("2134123422322");
            regPage.PhoneField.click();});

        // verification
        step("Проверяем отображение ошибки "+ErrorsMsg.incorrect_inn_merchant.getErrorMsg()+" под полем Inn" , () -> {
            assertEquals(ErrorsMsg.incorrect_inn_merchant.getErrorMsg(),regPage.InnErrorMsg.getText(),"Ошибка должна отображаться.");
        });
    }


}
