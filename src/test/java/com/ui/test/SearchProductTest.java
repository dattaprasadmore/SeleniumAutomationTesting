package com.ui.test;

import com.ui.pages.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({com.ui.listeners.TestListener.class})
public class SearchProductTest extends BaseTest{
    private MyAccountPage myAccountPage;
    //private static String  SEARCH_TERM = "printed summer dress";
    private static String  SEARCH_TERM = "Blouse";

    @Test(description = "Verify if the logged in user is able to search for a product and products are displayed.",
          groups={"smoke", "sanity"})
    public void verifyProductSearchTest(){
        myAccountPage = HomePage.gotoLoginPage().doLoginWith("hitaci9893@gufutu.com","Admin@12345");
        //myAccountPage.searchForProduct(SEARCH_TERM).getAllDressesName(SEARCH_TERM);
        boolean actualResult = myAccountPage.searchForProduct(SEARCH_TERM).isSearchTermPresentInProductList(SEARCH_TERM);
        Assert.assertEquals(actualResult ,true);
    }
}