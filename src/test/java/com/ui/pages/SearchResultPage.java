package com.ui.pages;

import com.ui.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private static ElementUtil eleUtil;
    private static final By PRODUCT_LISTING_TITLE_LOCATOR = By.xpath("//span[@Class='lighter']");
    private static final By ALL_PRODUCT_LISTS_NAME = By.xpath("//h5[@itemprop='name']/a");

    public SearchResultPage(WebDriver driver) {
        this.driver=driver;
        eleUtil = new ElementUtil(driver);
    }
    public String getSearchResultTitle(){
        return eleUtil.getVisibleText(PRODUCT_LISTING_TITLE_LOCATOR);
    }

    public boolean isSearchTermPresentInProductList(String searchTerm){
        List<String> keywords = Arrays.asList(searchTerm.toLowerCase().split(""));
        List<String> productNamesList = eleUtil.getAlltVisibleText(ALL_PRODUCT_LISTS_NAME);
        boolean result = productNamesList.stream().anyMatch(name ->(keywords.stream().anyMatch(name.toLowerCase()::contains)));
        return result;
    }

    public ProductDetailsPage clickOnTheProductAtIndex(int index){
        eleUtil.clickOn(eleUtil.getAlltElements(ALL_PRODUCT_LISTS_NAME).get(index));
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        return productDetailsPage;

    }
}