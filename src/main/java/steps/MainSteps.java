package steps;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import configs.UrlsConfig;
import constants.ErrorsMsg;
import helpers.Generator;
import org.openqa.selenium.Keys;
import testPageLocator.admin.LoginLocators;
import testPageLocator.market.authPage.*;
import testPageLocator.market.mainPage.MainLocators;
import com.codeborne.selenide.Condition;
import configs.DataConfig;
import env.Base_Container;
import org.aeonbits.owner.ConfigFactory;
import testPageLocator.market.productsPage.ProductsLocators;
import testPageLocator.market.registrationOrderPage.RegistrationOrderLocators;
import testPageLocator.merchant.RegisterLocators;

import java.io.File;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

import static io.qameta.allure.Allure.step;

public class MainSteps extends Base_Container {
    AuthLocators authPage;
    CityLocators cityPage;
    RegisterLocators regPage;
    MainLocators mainPage;
    LoginLocators loginAdminPage;
    ProductsLocators productsPage;
    RegistrationOrderLocators orderPage;

    Generator generator = new Generator();
    String email = generator.getEmail();

    static final DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());
    static final UrlsConfig urlConfig = ConfigFactory.create(UrlsConfig.class, System.getProperties());

    // Steps login user in system. Login with exist phone 79999999999 and sms 1234
    public void loginUser() {
        step("Нажатие на кнопку Войти", () -> {
            mainPage.LogInBtn.click();
        });
        step("Нажатие на кнопку Войти или зарегистрироваться", () -> {
            authPage.SignUpBtn.click();
        });
        step("Ввод номера телефона и получение кода", () -> {
            authPage.LoginField.setValue(data.getPhone());
            authPage.GetCodeSms.click();
        });
        step("Ввод кода из смс", () -> {
            authPage.SmsField.setValue(data.getCode());
            authPage.ConfirmBtn.click();

        });
        step("Проверяем отображение страницы с локацией и Подтверждаем город", () -> {
            cityPage.LocationPage.shouldBe(Condition.visible);
            cityPage.ConfirmCity.click();
        });
        step("Проверяем что кнопка \"Войти отсутствует\"", () -> {
            assertFalse( mainPage.LogInBtn.exists(),"Кнопка войти не должна отображаться.");
        });
    }

    // В витрине после авторизации открываем меню пользователя и переходим в профиль
    public void clickProfile(){
        step("Нажимаем на меню пользователя", () -> { mainPage.UserMenu.click(); });
        step("Нажимаем на пункт меню Личные данные", () -> { mainPage.ProfileBtn.click(); });
    }

    // Открытие админки и логин в нее
    public void loginInAdmin() throws InterruptedException {
        step("Открытие сайта " + urlConfig.getAdminUrl()+"/login", () -> open(urlConfig.getAdminUrl()+"/login"));
        step("Заполняем поле Email", () -> { loginAdminPage.EmailField.setValue(urlConfig.getAdminLogin()); });
        step("Заполняем поле Пароль", () -> { loginAdminPage.PasswordField.setValue(urlConfig.getAdminPassword()); });
        step("Нажимаем кнопку Войти", () -> { loginAdminPage.SigInBtn.click(); });
        Thread.sleep(1000);
    }

    // Создание мерчанта со страницы регистрации
    public void createMerchant(){
        // actions
        step("Заполняем поле Фамилия", () -> {regPage.SurnameField.setValue(generator.getLastName());});
        step("Заполняем поле Имя", () -> {regPage.NameField.setValue(generator.getFirstName());});
        step("Заполняем поле Отчество", () -> {regPage.MiddleNameField.setValue(generator.getMiddleName());});
        step("Заполняем поле Телефон", () -> {regPage.PhoneField.setValue(data.getPhoneMerchant());});
        step("Заполняем поле E-mail", () -> {regPage.EmailField.setValue(email);});
        step("Заполняем поле ИНН", () -> {regPage.InnField.setValue(data.getInnMerchant());});
        step("Заполняем поле Название организации", () -> {regPage.OrganizationField.setValue(generator.getCompany());});
        step("Заполняем поле Адреса складов отгрузки", () -> {regPage.AddressField.setValue(data.getAddressMerchant());});
        downloadFile("Загружаем файл",regPage.ProductsFileField,regPage.ProductsFileSuccess);
        step("Нажимаем на кнопку Зарегистрироваться", () -> {regPage.RegistrationBtn.click();});

        // verification
        step("Проверяем отображение успешного сообщения "+ ErrorsMsg.registration_merchant_msg.getErrorMsg()+"", () -> {
            assertEquals(ErrorsMsg.registration_merchant_msg.getErrorMsg(),regPage.RegistrationText.getText(),"Ошибка должна отображаться.");
        });
    }

    // Создание мерчанта со страницы регистрации c входными параметрами
    public void createMerchant(String email, String nameOrg){
        // actions
        step("Заполняем поле Фамилия", () -> {regPage.SurnameField.setValue(generator.getLastName());});
        step("Заполняем поле Имя", () -> {regPage.NameField.setValue(generator.getFirstName());});
        step("Заполняем поле Отчество", () -> {regPage.MiddleNameField.setValue(generator.getMiddleName());});
        step("Заполняем поле Телефон", () -> {regPage.PhoneField.setValue(data.getPhoneMerchant());});
        step("Заполняем поле E-mail", () -> {regPage.EmailField.setValue(email);});
        step("Заполняем поле ИНН", () -> {regPage.InnField.setValue(data.getInnMerchant());});
        step("Заполняем поле Название организации", () -> {regPage.OrganizationField.setValue(nameOrg);});
        step("Заполняем поле Адреса складов отгрузки", () -> {regPage.AddressField.setValue(data.getAddressMerchant());});
        downloadFile("Загружаем файл",regPage.ProductsFileField,regPage.ProductsFileSuccess);
        step("Нажимаем на кнопку Зарегистрироваться", () -> {regPage.RegistrationBtn.click();});

        // verification
        step("Проверяем отображение успешного сообщения "+ ErrorsMsg.registration_merchant_msg.getErrorMsg()+"", () -> {
            assertEquals(ErrorsMsg.registration_merchant_msg.getErrorMsg(),regPage.RegistrationText.getText(),"Ошибка должна отображаться.");
        });
    }

    // Загрузка файла
    public void downloadFile(String name,SelenideElement inputFile, SelenideElement successEl) {
        step(name, () -> {
            inputFile.uploadFile(new File("src/test/resources/files/testFile.xlsx"));
            Thread.sleep(3000);
            assertTrue( successEl.exists(),"Отображается успешная загрузка файла.");
        });

    }

    // Добавление товара в корзину
    public void addProductsInCart(String product) {
        step("Вводим в поле название товара", () -> {
            mainPage.SearchField.setValue(product).sendKeys(Keys.ENTER); });
        step("Нажимаем на кнопку В корзину на карточке товара", () -> {productsPage.AddCartBtn.click(); });
        step("Ожидаем, когда кнопка В корзину станет недоступна", () -> {productsPage.AddCartBtn.should(disappear); });
        step("Переходим в корзину", () -> { mainPage.CartBtn.click();});

    }

    // Оформление и оплата заказа
    public void addOrder(String product, String address) {
        // prepare
        addProductsInCart(product);

        // actions
        step("Нажимаем на кнопку Перейти к оформлению", () -> { orderPage.CheckoutCartBtn.click(); });
        step("Отображается страница с выбором адреса" , () -> {orderPage.AddressBlock.shouldBe();});
        step("Вводим адрес в поле" , () -> {orderPage.AddressField.setValue(address);});
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
            orderPage.PasswordField.setValue(data.getCode());
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
