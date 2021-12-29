package testPageLocator.market.registrationOrderPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationOrderLocators {
    public final static SelenideElement
            CheckoutCartBtn=  $(".cart__checkout-link"),
            AddressBlock =  $(".address-edit__bottom"),
            AddressField =  $(byAttribute("data-test", "checkout-delivery-address")),
            ContinueBtn =  $(byAttribute("data-test", "checkout-delivery-next")),
            AddressItem = $(byXpath("//*[@class='address-selection__list']/li[1]")),
            SuccessOrder = $(".thank-you__title"),

            /** Доставка курьером блок **/
            ApartmentField = $(byAttribute("data-test", "checkout-address-flat")),
            EntranceField = $(byAttribute("data-test", "checkout-address-porch")),
            FloorField = $(byAttribute("data-test", "checkout-address-floor")),
            IntercomField = $(byAttribute("data-test", "checkout-address-intercom")),
            CommentsField = $(byAttribute("data-test", "checkout-address-comment")),
            NameField = $(byAttribute("data-test", "checkout-recipient-name")),
            PhoneField = $(byAttribute("data-test", "checkout-recipient-phone")),
            SaveContinueBtn = $(byAttribute("data-test", "checkout-address-save")),

            /** Подтверждение телефона **/
            PasswordField = $(byAttribute("type", "password")),
            ConfirmBtn = $(byXpath("//*[@class='modal__body']//*[@type='submit']")),

            /** Страница с данными по заказу **/
            DeliveryBlock = $(".delivery__list"),
            NextOrderBtn = $(".button"),

            /** Страница оплаты **/
            PaymentBlock = $(".payment-confirmation-container__body"),
            PayBtn = $(".payment-scenario__back-link_indent_no"),

            /** Страница заказов **/
            LastOrderCard = $(byXpath("//*[@class='order-card'])[1]")),
            CancelOrderBtn = $(".info-link--cancel"),
            SendBtn = $(byAttribute("type", "submit"));


}
