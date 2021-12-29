package Tests.Market;

import constants.ErrorsMsg;
import env.Base_Container;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Keys;
import testPageLocator.market.mainPage.MainLocators;
import testPageLocator.market.productsPage.ProductsLocators;
import testPageLocator.market.searchAndFilters.SearchFilterLocators;

import static com.codeborne.selenide.Condition.disappear;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Web testing")
@Story("Маркет. Поиск и фильтрация")
@Execution(ExecutionMode.SAME_THREAD)
public class testShoppingCart extends Base_Container {
    MainLocators mainPage;
    ProductsLocators productsPage;

    @Test
    @Description("Добавление товаров в корзину.")
    void testAddProductInShoppingCart() {

        // prepare
        step("Вводим в поле название товара", () -> { mainPage.SearchField.setValue("ПРОДАЖИ").sendKeys(Keys.ENTER);});

        // actions
        step("Нажимаем на кнопку В корзину на карточке товара", () -> { productsPage.AddCartBtn.click();});
        step("Ожидаем, когда кнопка В корзину станет недоступна" , () -> {productsPage.AddCartBtn.should(disappear);});
        // verification
        step("Проверяем что число добавленных товаров обновилось ", () -> {
            assertEquals("1 шт",productsPage.CountProductsCartBtn.getText(),"Количество товаров должно быть корреткно");
        });
        step("Нажимаем на кнопку Плюс у товара", () -> { productsPage.PlusBtn.click();});
        // verification
        step("Проверяем что число добавленных товаров обновилось ", () -> {
            assertEquals("2 шт",productsPage.CountProductsCartBtn.getText(),"Количество товаров должно быть корреткно");
        });
        // actions
        step("Нажимаем на кнопку Минус у товара", () -> { productsPage.MinusBtn.click();});
        // verification
        step("Проверяем что число добавленных товаров обновилось ", () -> {
            assertEquals("1 шт",productsPage.CountProductsCartBtn.getText(),"Количество товаров должно быть корреткно");
        });

        // actions
        step("Переходим в корзину", () -> { mainPage.CartBtn.click();});
        // verification
        step("Проверяем что число добавленных товаров корректно ", () -> {
            assertEquals("1 шт",productsPage.CountProductsCartBtn.getText(),"Количество товаров должно быть корреткно");
        });

    }

    @Test
    @Description("Удаление товаров из корзины.")
    void testDeleteProductInShoppingCart() {

        // prepare
        step("Вводим в поле название товара", () -> {
            mainPage.SearchField.setValue("ПРОДАЖИ").sendKeys(Keys.ENTER); });
        step("Нажимаем на кнопку В корзину на карточке товара", () -> {productsPage.AddCartBtn.click(); });
        step("Ожидаем, когда кнопка В корзину станет недоступна", () -> {productsPage.AddCartBtn.should(disappear); });
        step("Переходим в корзину", () -> { mainPage.CartBtn.click();});

        // actions
        step("Нажимаем на кнопку Удалить под товаром", () -> {productsPage.DeleteProductBtn.click(); });

        // verification
        step("Отображается надпись Корзина пуста" , () -> {productsPage.CartEmpty.shouldBe();});
    }



}
