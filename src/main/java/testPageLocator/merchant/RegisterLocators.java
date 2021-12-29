package testPageLocator.merchant;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RegisterLocators {
    public final static SelenideElement
            RegistrationPage = $(".page-registration"),
            SurnameField = $(byAttribute("data-test", "last_name")),
            SurnameErrorMsg = $(byAttribute("data-test", "last_name_alert")),
            NameField = $(byAttribute("data-test", "first_name")),
            NameErrorMsg = $(byAttribute("data-test", "first_name_alert")),
            MiddleNameField = $(byAttribute("data-test", "middle_name")),
            MiddleNameErrorMsg = $(byAttribute("data-test", "middle_name_alert")),
            PhoneField = $(byAttribute("data-test", "phone")),
            PhoneErrorMsg = $(byAttribute("data-test", "phone_alert")),
            EmailField = $(byAttribute("data-test", "email")),
            EmailErrorMsg = $(byAttribute("data-test", "email_alert")),
            InnField = $(byAttribute("data-test", "inn")),
            InnErrorMsg = $(byAttribute("data-test", "inn_alert")),
            OrganizationField = $(byAttribute("data-test", "legal_name")),
            OrganizationErrorMsg = $(byAttribute("data-test", "legal_name_alert")),
            AddressField = $(byAttribute("data-test", "storage_address")),
            AddressErrorMsg = $(byAttribute("data-test", "storage_address_alert")),
            ProductsFileField = $(byAttribute("data-test", "file_assortment")),
            ProductsFileErrorMsg = $(byAttribute("data-test", "file_assortment_alert")),
            ProductsFileProgressBar = $(By.xpath("//*[@role='progressbar']")),
            ProductsFileSuccess = $(".alert-success"),
            RegistrationBtn = $(By.xpath("//*[@type='submit']")),
            RegistrationText = $(By.xpath("//*[@class='page-registration']/div")),

            /**Locators page set password **/
            PasswordField = $(byAttribute("type", "password")),
            SaveBtn = $(byAttribute("type", "submit"));


}
