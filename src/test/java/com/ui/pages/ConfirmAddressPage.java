package com.ui.pages;

import com.ui.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmAddressPage {
    private WebDriver driver;
    private static ElementUtil eleUtil;
    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.name("processAddress");
    public ConfirmAddressPage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }

    public ShipmentPage goToShipmentPage(){
        eleUtil.clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new ShipmentPage(driver);
    }
}