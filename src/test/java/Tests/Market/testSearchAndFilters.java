package Tests.Market;

import env.Base_Container;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Keys;
import testPageLocator.market.mainPage.MainLocators;
import testPageLocator.market.searchAndFilters.SearchFilterLocators;

import static io.qameta.allure.Allure.step;

@Feature("Web testing")
@Story("Маркет. Поиск и фильтрация")
@Execution(ExecutionMode.SAME_THREAD)
public class testSearchAndFilters extends Base_Container {
    MainLocators mainPage;
    SearchFilterLocators searchFiltersPage;

    @Test
    @Description("Подсказки при поиске.")
    void testSearchItemThroughHint() {

        // actions
        step("Вводим в поле название товара", () -> { mainPage.SearchField.setValue("свитшот");});
        step("Нажимаем на товар из подсказки", () -> { searchFiltersPage.helpSearchBtn.click();});

        // verification
        step("Ожидаем карточку товара в поиске" , () -> {searchFiltersPage.productName.shouldBe();});
    }

   @Test
    @Description("Поиск товара.")
    void testSearchProduct() {

        // actions
        step("Вводим в поле название товара", () -> { mainPage.SearchField.setValue("свитшот").sendKeys(Keys.ENTER);});

        // verification
        step("Ожидаем карточку товара в поиске" , () -> {searchFiltersPage.productName.shouldBe();});
    }

   @Test
    @Description("Поиск несуществующего товара.")
    void testSearchNotExistProduct() {

        // actions
        step("Вводим в поле название товара", () -> { mainPage.SearchField.setValue("FGTR").sendKeys(Keys.ENTER);});

        // verification
        step("Ожидаем отсутствие карточек товара " , () -> {searchFiltersPage.EmptyProduct.shouldBe();});
    }

   @Test
    @Description("Фильтрация товара.")
    void testSearchProductWithFilter() {

        // actions
        step("Нажимаем на пункт бокового меню Товары", () -> { mainPage.ProductsMenu.click();});
        step("Нажимаем на кнопку Фильтры", () -> { searchFiltersPage.FilterBtn.click();});
        step("Нажимаем на пункт Категории", () -> { searchFiltersPage.CategoriesBtn.click();});
        step("Нажимаем на категорию Свитшоты", () -> { searchFiltersPage.CategoriesField.click();});
        step("Нажимаем на кнопку закрытия фильтра", () -> { searchFiltersPage.CloseBtn.click();});

       // verification
       step("Ожидаем карточку товара в поиске" , () -> {searchFiltersPage.productName.shouldBe();});
    }


}
