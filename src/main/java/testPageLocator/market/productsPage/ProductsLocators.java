package testPageLocator.market.productsPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ProductsLocators {
    public final static SelenideElement
            AddCartBtn = $(byXpath("(//*[contains(@class,'product-card__add-to-cart')])[1]")),
            PlusBtn = $(".quantity-counter__btn-plus"),
            MinusBtn = $(".quantity-counter__btn-minus"),
            DeleteProductBtn = $(".cart__product-remove"),
            CartEmpty = $(".cart__empty-wrapper"),
            CountProductsCartBtn = $(".quantity-counter__val");

}
