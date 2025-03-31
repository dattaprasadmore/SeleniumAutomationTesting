package com.ui.pages;

import com.ui.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShipmentPage {
    private WebDriver driver;
    private static ElementUtil eleUtil;
    private static final By ACCEPT_TERMS_AND_CONDITIONS_CHECKBOX_LOCATOR = By.id("uniform-cgv");
    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.name("processCarrier");

    public ShipmentPage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }
    public void selectTermsAndConditions(){
        eleUtil.clickOn(ACCEPT_TERMS_AND_CONDITIONS_CHECKBOX_LOCATOR);
    }

    public PaymentPage goToPaymentPage(){
        eleUtil.clickOnCheckBox(ACCEPT_TERMS_AND_CONDITIONS_CHECKBOX_LOCATOR);
        eleUtil.clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new PaymentPage(driver);
    }

}