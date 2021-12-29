package testPageLocator.market.authPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class CityLocators {
    public final static SelenideElement
            LocationPage = $(".location"),
            ConfirmCity = $(byAttribute("data-test", "city-true")),
            RejectCity = $(byAttribute("data-test", "city-false"));

}
