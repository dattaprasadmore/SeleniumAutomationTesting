package com.ui.pages;

import com.ui.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//import static com.ui.factory.DriverFactory.getDriver;

public final class LoginPage {
    private WebDriver driver;
    private static ElementUtil eleUtil;
    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    private static final By SUBMIT_LOGIN_BUTTON_LOCATOR = By.id("SubmitLogin");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[contains(@Class,\"alert-danger\")]/ol/li");
    public LoginPage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }

    public MyAccountPage doLoginWith(String emailAddress, String password){
        eleUtil.enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
        eleUtil.enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        eleUtil.clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
        return new MyAccountPage(driver);
    }

    public LoginPage doLoginWithInvalidCredentials(String emailAddress, String password) {
        eleUtil.enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
        eleUtil.enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        eleUtil.clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
        return new LoginPage(driver);
    }

    public String getErrorMessage(){
        return eleUtil.getVisibleText(ERROR_MESSAGE_LOCATOR);
    }
}