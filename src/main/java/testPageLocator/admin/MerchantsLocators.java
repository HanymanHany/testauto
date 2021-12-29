package testPageLocator.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MerchantsLocators {
     /**
      * Locators for merchant/list/registration
      **/
     public void NameCompanyLink(final String text) {
          Selenide.$(By.xpath("//*[@class='table']//*[contains(text(),'" + text + "')]"));
     }

     public final static SelenideElement
             ActivateBtn = $(".btn-outline-success"),
             SetPasswordField = $(By.xpath("//*[@type='password']")),
             SetPasswordSaveBtn = $(By.xpath("//*[@type='submit']"));



}
