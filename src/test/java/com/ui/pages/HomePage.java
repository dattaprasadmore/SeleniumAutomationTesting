package com.ui.pages;

import com.ui.utils.PropertyUtils;
import com.ui.utils.ElementUtil;
import com.ui.enums.ConfigProperties;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ui.utils.LoggerUtility;
public class HomePage {
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private WebDriver driver;
    private static ElementUtil eleUtil;
    public HomePage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
        eleUtil.goToWebsite(PropertyUtils.get(ConfigProperties.URL));
    }
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath(".//a[contains(text(),\"Sign in\")]");

    public LoginPage gotoLoginPage(){
        logger.info("Trying to performing click to go to Sign in Page ");
        eleUtil.clickOn(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(driver);
    }
}