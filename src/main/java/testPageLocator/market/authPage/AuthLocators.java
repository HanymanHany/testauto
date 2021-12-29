package testPageLocator.market.authPage;
import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class AuthLocators {
    public final static SelenideElement
            MainPage = $(".sign-up--main"),
            SignUpBtn = $(byAttribute("data-test", "registration")),
            LogInGuestBtn = $(byAttribute("data-test", "guest-entrance")),
            LoginField = $(byAttribute("data-test", "phone")),
            GetCodeSms = $(byAttribute("data-test", "get-code")),
            SmsField = $(byAttribute("data-test", "fill-code")),
            ConfirmBtn = $(byAttribute("data-test", "confirmation")),
            ErrorMsg = $(".error-message");



}
