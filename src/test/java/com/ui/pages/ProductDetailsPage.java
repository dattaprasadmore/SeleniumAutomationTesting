package com.ui.pages;

import com.ui.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ui.enums.Size;

public class ProductDetailsPage {
    private WebDriver driver;
    private static ElementUtil eleUtil;
    private static final By SIZE_DROP_DOWN_LOCATOR = By.id("group_1");
    private static final By ADD_TO_CART_LOCATOR = By.name("Submit");
    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.xpath("//a[@title='Proceed to checkout']");

    public ProductDetailsPage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }
    public ProductDetailsPage changeSize(Size size){
        eleUtil.selectFromDropDown(SIZE_DROP_DOWN_LOCATOR, size.toString());
        return new ProductDetailsPage(driver);
    }
    public ProductDetailsPage AddProductToCart(){
        eleUtil.clickOn(ADD_TO_CART_LOCATOR);
        return new ProductDetailsPage(driver);
    }
    public ShoppingCartPage proceedToCheckout() {
        eleUtil.clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new ShoppingCartPage(driver);
    }
}