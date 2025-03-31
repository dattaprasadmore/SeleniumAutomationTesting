package com.ui.pages;

import com.ui.pojo.AddressPojo;
import com.ui.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressPage {
    private WebDriver driver;
    private static ElementUtil eleUtil;
    private static final By COMPANY_TEXTBOX_LOCATOR = By.id("company");
    private static final By ADDRESS1_TEXTBOX_LOCATOR = By.id("address1");
    private static final By ADDRESS2_TEXTBOX_LOCATOR = By.id("address2");
    private static final By CITY_TEXTBOX_LOCATOR = By.id("city");
    private static final By POSTCODE_TEXTBOX_LOCATOR = By.id("postcode");
    private static final By HOMEPHONE_TEXTBOX_LOCATOR = By.id("phone");
    private static final By MOBILEPHONE_TEXTBOX_LOCATOR = By.id("phone");
    private static final By ADDITIONAL_INFORMATION_TEXTAREA_LOCATOR = By.id("other");
    private static final By ADDRESS_ALIAS_TEXTBOX_LOCATOR = By.id("alias");
    private static final By STATE_DROPDOWN_LOCATOR = By.id("id_state");
    private static  final By SAVE_ADDRESS_BUTTON_LOCATOR = By.id("submitAddress");
    private static final By ADDRESS_HEADING = By.tagName("h3");
    public AddressPage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }

    public String saveAddress(){
        eleUtil.enterText(COMPANY_TEXTBOX_LOCATOR,"ABC");
        eleUtil.enterText(ADDRESS1_TEXTBOX_LOCATOR,"Address Line 1");
        eleUtil.enterText(ADDRESS2_TEXTBOX_LOCATOR,"Address Line 2");
        eleUtil.enterText(CITY_TEXTBOX_LOCATOR,"PUNE");
        eleUtil.enterText(POSTCODE_TEXTBOX_LOCATOR,"23456");
        eleUtil.enterText(HOMEPHONE_TEXTBOX_LOCATOR,"020-45789654");
        eleUtil.enterText(MOBILEPHONE_TEXTBOX_LOCATOR,"4578965412");
        eleUtil.enterText(ADDITIONAL_INFORMATION_TEXTAREA_LOCATOR,"Random ABV 12345");
        eleUtil.clearText(ADDRESS_ALIAS_TEXTBOX_LOCATOR);
        eleUtil.enterText(ADDRESS_ALIAS_TEXTBOX_LOCATOR,"Home Address");
        eleUtil.selectFromDropDown(STATE_DROPDOWN_LOCATOR,"Delaware");
        eleUtil.clickOn(SAVE_ADDRESS_BUTTON_LOCATOR);
        String newAddressType = eleUtil.getVisibleText(ADDRESS_HEADING);
        return newAddressType;

    }

    public String saveAddress(AddressPojo addressPojo){
        eleUtil.enterText(COMPANY_TEXTBOX_LOCATOR,addressPojo.getCompany());
        eleUtil.enterText(ADDRESS1_TEXTBOX_LOCATOR,addressPojo.getAddressLine1());
        eleUtil.enterText(ADDRESS2_TEXTBOX_LOCATOR,addressPojo.getAddressLine2());
        eleUtil.enterText(CITY_TEXTBOX_LOCATOR,addressPojo.getCity());
        eleUtil.enterText(POSTCODE_TEXTBOX_LOCATOR,addressPojo.getPostCode());
        eleUtil.enterText(HOMEPHONE_TEXTBOX_LOCATOR,addressPojo.getHomePhoneNumber());
        eleUtil.enterText(MOBILEPHONE_TEXTBOX_LOCATOR,addressPojo.getMobileNumber());
        eleUtil.enterText(ADDITIONAL_INFORMATION_TEXTAREA_LOCATOR,addressPojo.getOtherInformation());
        eleUtil.clearText(ADDRESS_ALIAS_TEXTBOX_LOCATOR);
        eleUtil.enterText(ADDRESS_ALIAS_TEXTBOX_LOCATOR,addressPojo.getAddressAlias());
        eleUtil.selectFromDropDown(STATE_DROPDOWN_LOCATOR,addressPojo.getState());
        eleUtil.clickOn(SAVE_ADDRESS_BUTTON_LOCATOR);
        String newAddressType = eleUtil.getVisibleText(ADDRESS_HEADING);
        return newAddressType;
    }
}