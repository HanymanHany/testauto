package helpers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

public class Helper {

    public static void clearInputForReact(SelenideElement element) {
        int n =0;
        while (!element.getValue().equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
            n++;
            if (n == 50) {
                break;
            }
        }
    }
}
