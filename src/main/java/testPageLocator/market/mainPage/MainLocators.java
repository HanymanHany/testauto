package testPageLocator.market.mainPage;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainLocators {
    public final static SelenideElement
            LogInBtn = $(byAttribute("data-test", "log-in")),
            ProductsMenu =  $(byXpath("//*[@class='main-nav__menu']//*[contains(text(),'Товары')]")),
            SearchField =  $(".search__input"),
            CartBtn =  $(".header__button--shopping-cart"),
            UserMenu =  $(".header__user"),
            ProfileBtn = $(byXpath("(//*[@class='header__user-control'])[1]")),
            OrdersBtn = $(byXpath("(//*[@class='header__user-control'])[2]"));
}
