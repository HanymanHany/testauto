package testPageLocator.market.searchAndFilters;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SearchFilterLocators {
    public final static SelenideElement

            /** Локаторы поиска **/
            //FIXME: сделать динамический локатор
           helpSearchBtn =  $(By.xpath("(//*[@class='search__suggestions']//*[contains(text(),'свитшот')])[1]")),
           productName =  $(By.xpath("(//*[@class='product-card']//*[contains(text(),'свитшот')])[1]")),
           EmptyProduct =  $(By.xpath("//*[@class='product-grid']//*[contains(text(),'Товары не найдены')]")),
           FilterBtn =  $(".catalog-page__filter-button"),
           CategoriesBtn =  $(".filter__button"),
           CategoriesField =  $(By.xpath("//*[@class='categories-list__link'][contains(text(),'Свитшоты')]")),
           CloseBtn =  $(".modal-right__close");
}
