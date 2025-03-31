package com.ui.test;

import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.ui.enums.Size.L;

@Listeners({com.ui.listeners.TestListener.class})
public class ProductCheckoutTest extends BaseTest{
    private static final String SEARCH_TERM = "Printed Summer dress";
    private SearchResultPage searchResultPage;
    @BeforeMethod(description = "Load the Home page of the website")
    public void setup() {

    }
    @Test(description = "Verify if the logged in User is able to bye a dress", groups ={"e2e","smoke","sanity"})
    public void checkoutTest() {
        searchResultPage = HomePage.gotoLoginPage().doLoginWith("hitaci9893@gufutu.com", "Admin@12345")
                .searchForProduct(SEARCH_TERM);
        String result = searchResultPage.clickOnTheProductAtIndex(1).changeSize(L).AddProductToCart().proceedToCheckout()
                .goToConfirmAddressPage().goToShipmentPage().goToPaymentPage().makePaymentByWire();
        Assert.assertTrue(result.contains("Your order on My Shop is complete."));
    }
}