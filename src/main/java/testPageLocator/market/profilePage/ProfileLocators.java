package testPageLocator.market.profilePage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class ProfileLocators {
    public final static SelenideElement
            NameField = $(byXpath("(//*[@id='profileForm']//input)[1]")),
            NameErrorMsg = $(byXpath("(//*[@id='profileForm']//input)[1]//ancestor::div[@class='profile__form-control']//*[contains(@class,'input__message')]")),
            PhoneField = $(byXpath("(//*[@id='profileForm']//input)[2]")),
            PhoneErrorMsg = $(byXpath("(//*[@id='profileForm']//input)[2]//ancestor::div[@class='profile__form-confirm']//*[contains(@class,'error-message')]")),
            EmailField = $(byXpath("(//*[@id='profileForm']//input)[3]")),
            EmailErrorMsg = $(byXpath("(//*[@id='profileForm']//input)[3]//ancestor::div[@class='profile__form-confirm']//*[contains(@class,'input__message')]")),
            DateField = $(".v-datepicker__input"),
            ConfirmBtn =  $(".profile__form-confirm-button"),
            ProfileReadyBtn =  $(".profile__ready"),
            CartBtn =  $(".header__button--shopping-cart"),
            UserMenu =  $(".header__user"),
            ProfileBtn = $(byXpath("(//*[@class='header__user-control'])[1]")),
            OrdersBtn = $(byXpath("(//*[@class='header__user-control'])[2]"));
}
