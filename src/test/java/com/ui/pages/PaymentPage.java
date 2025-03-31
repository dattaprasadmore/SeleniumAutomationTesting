package com.ui.pages;

import com.ui.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage {

    private WebDriver driver;
    private static ElementUtil eleUtil;
    private static final By PAYMENT_BY_WIRE_BUTTON_LOCATOR = By.xpath("//a[@title='Pay by bank wire']");
    private static final By CONFIRM_MY_ORDER_BUTTON_LOCATOR = By.xpath("//p[@class='cart_navigation clearfix']//button");
    private static final By ALERT_SUCCESS_MESSAGE_LOCATOR = By.xpath("//p[contains(@class,'success')]");

    public PaymentPage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }
    public String makePaymentByWire(){
        eleUtil.clickOn(PAYMENT_BY_WIRE_BUTTON_LOCATOR);
        eleUtil.clickOn(CONFIRM_MY_ORDER_BUTTON_LOCATOR);
        return eleUtil.getVisibleText(ALERT_SUCCESS_MESSAGE_LOCATOR);
    }
}
