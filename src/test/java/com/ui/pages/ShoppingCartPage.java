package com.ui.pages;

import com.ui.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {
    private WebDriver driver;
    private static ElementUtil eleUtil;
    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.xpath("//p[contains(@class,'cart_navigation clearfix')]//a[@title='Proceed to checkout']");
    public ShoppingCartPage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }

    public ConfirmAddressPage goToConfirmAddressPage(){
        eleUtil.clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new ConfirmAddressPage(driver);
    }
}