package testPageLocator.admin;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginLocators {
    public final static SelenideElement
            EmailField = $(By.id("email")),
            PasswordField = $(By.id("password")),
            SigInBtn = $(".btn-primary");


}
