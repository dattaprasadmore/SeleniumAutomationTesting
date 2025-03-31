package com.ui.pages;

import com.ui.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage {
    private WebDriver driver;
    private static ElementUtil eleUtil;
    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title='View my customer account']");
    private static final By SEARCH_TEXT_BOX_LOCATOR = By.xpath("//input[@id='search_query_top']");
    private static final By ADD_NEW_ADDRESS_LOCATOR = By.xpath("//a[@title='Add my first address']");

    public MyAccountPage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getUserName(){
        return eleUtil.getVisibleText(USER_NAME_LOCATOR);
    }
    public SearchResultPage searchForProduct(String productName){
        eleUtil.enterText(SEARCH_TEXT_BOX_LOCATOR,productName);
        eleUtil.enterSpecialKey(SEARCH_TEXT_BOX_LOCATOR, Keys.ENTER);
        return new SearchResultPage(driver);
    }

    public AddressPage gotoAddAddressPage(){
        eleUtil.clickOn(ADD_NEW_ADDRESS_LOCATOR);
        return new AddressPage(driver);
    }

}