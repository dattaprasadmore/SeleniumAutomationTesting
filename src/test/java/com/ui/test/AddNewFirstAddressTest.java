package com.ui.test;

import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPojo;
import com.ui.dataproviders.FakeAddress;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({com.ui.listeners.TestListener.class})
public class AddNewFirstAddressTest extends BaseTest {
    private MyAccountPage myAccountPage;
    private AddressPojo addressPojo;
    @Test(description = "Add New address into Addressbook")
    public void addNewAddressTest() {
        myAccountPage = HomePage.gotoLoginPage().doLoginWith("hitaci9893@gufutu.com", "Admin@12345");
        addressPojo = FakeAddress.getFakeAddress();

        String newAddress = myAccountPage.gotoAddAddressPage().saveAddress(addressPojo);
        Assert.assertEquals(newAddress,addressPojo.getAddressAlias().toUpperCase());
    }
}