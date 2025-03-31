package com.ui.test;

import com.ui.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
@Listeners({com.ui.listeners.TestListener.class})
public class LoginPageTest extends BaseTest{
    @Test(description ="Verifies the valid user is able to login into the application using CSV data.",
            dataProviderClass=com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginTestCSVDataProvider")
    public void LoginCSVTest(User user) {
        //HomePage.gotoLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName();
        assertEquals(HomePage.gotoLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Dp dp");
    }
}