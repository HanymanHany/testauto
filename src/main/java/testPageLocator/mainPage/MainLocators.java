package testPageLocator.mainPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainLocators {
    public final static SelenideElement
            LogInBtn = $(byAttribute("data-test", "log-in"));
}
