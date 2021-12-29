package testPageLocator.market.registrationOrderPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationOrderLocators {
    public final static SelenideElement
            CheckoutCartBtn=  $(".cart__checkout-link"),
            AddressBlock =  $(".address-edit__bottom"),
            AddressField =  $(byXpath("//*[contains(@class,'address-selection__search-input')]//*[@type='text']")),
            ContinueBtn =  $(".address-edit__button"),
            AddressItem = $(byXpath("//*[@class='address-selection__list']/li[1]")),
            SuccessOrder = $(byXpath("//*[@class='layout__main']//h1")),

            /** Доставка курьером блок **/
            ApartmentField = $(byId("input-id-73")),
            EntranceField = $(byId("input-id-75")),
            FloorField = $(byId("input-id-76")),
            CommentsField = $(byId("input-id-78")),
            NameField = $(byId("input-id-79")),
            PhoneField = $(byId("input-id-80")),
            SaveContinueBtn = $(".address-courier__submit"),

            /** Подтверждение телефона **/
            PasswordField = $(byId("input-id-97")),
            ConfirmBtn = $(byXpath("//*[@class='modal__body']//*[@type='submit']")),

            /** Страница с данными по заказу **/
            DeliveryBlock = $(".delivery__list"),
            NextOrderBtn = $(".button"),

            /** Страница оплаты **/
            PaymentBlock = $(".payment-confirmation-container__body"),
            PayBtn = $(".payment-scenario__back-link_indent_no");


}
