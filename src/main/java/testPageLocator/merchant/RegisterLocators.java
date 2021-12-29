package testPageLocator.merchant;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class RegisterLocators {
    public final static SelenideElement
            RegistrationPage = $(".page-registration"),
            SurnameField = $(By.id("v-input-id-23")),
            SurnameErrorMsg = $(By.id("v-input-id-23-alert")),
            NameField = $(By.id("v-input-id-24")),
            NameErrorMsg = $(By.id("v-input-id-24-alert")),
            MiddleNameField = $(By.id("v-input-id-25")),
            MiddleNameErrorMsg = $(By.id("v-input-id-25-alert")),
            PhoneField = $(By.id("v-input-id-26")),
            PhoneErrorMsg = $(By.id("v-input-id-26-alert")),
            EmailField = $(By.id("v-input-id-27")),
            EmailErrorMsg = $(By.id("v-input-id-27-alert")),
            InnField = $(By.id("v-input-id-28")),
            InnErrorMsg = $(By.id("v-input-id-28-alert")),
            OrganizationField = $(By.id("v-input-id-31")),
            OrganizationErrorMsg = $(By.id("v-input-id-31-alert")),
            AddressField = $(By.id("v-input-id-32")),
            AddressErrorMsg = $(By.id("v-input-id-32-alert")),
            ProductsFileField = $(By.xpath("//input[@type='file']")),
            ProductsFileErrorMsg = $(By.id("v-input-id-33-alert")),
            ProductsFileProgressBar = $(By.xpath("//*[@role='progressbar']")),
            ProductsFileSuccess = $(".alert-success"),
            RegistrationBtn = $(By.xpath("//*[@type='submit']")),
            RegistrationText = $(By.xpath("//*[@class='page-registration']/div")),

            /**Locators page set password **/
            PasswordField = $(byAttribute("type", "password")),
            SaveBtn = $(byAttribute("type", "submit"));


}
