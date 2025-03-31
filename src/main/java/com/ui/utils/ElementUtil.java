package com.ui.utils;

import com.ui.reports.ExtentLogger;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.ui.utils.exceptionUtil.InvalidSelectorFrameworkException;
public class ElementUtil {

    //private WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private JavaScriptUtil jsUtil;
    private WebDriverWait wait;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public ElementUtil(WebDriver driver) {
        this.driver.set(driver);
        jsUtil = new JavaScriptUtil(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    public void goToWebsite(String url) {
        //logger.info("Visiting the Website - " + url);
        //driver.get().get(url);

    }
    public void maximizeWindow() {
        logger.info("Maximizing the Browser window ");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator) {
        try {
            logger.info("Finding Element with locator - " + locator);
            WebElement element = waitForelementToBeClickable(locator,30,1);
            logger.info("Element found and now perfoming Click ");
            element.click();
            ExtentLogger.info("Clicked on the Element : " + locator);
        }catch (InvalidSelectorException e){
            throw new InvalidSelectorFrameworkException(" Invalid Locator : " + locator);
        }
    }
    public void clickOnCheckBox(By locator) {
        try {
            logger.info("Finding Element with locator - " + locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element found and now perfoming Click ");
            element.click();
            ExtentLogger.info("Clicked on the Element : " + element);
        }catch (InvalidSelectorException e){
            throw new InvalidSelectorFrameworkException(" Invalid Locator : " + locator);
        }
    }
    public void clickOn(WebElement element) {
        try{
            logger.info("Element found and now perfoming Click ");
            element.click();
            ExtentLogger.info("Clicked on the Element : " + element);
        }catch (InvalidSelectorException e){
            throw new InvalidSelectorFrameworkException(" Invalid Element : " + element);
        }
    }

    public void enterText(By locator, String value) {
        logger.info("Finding Element with locator - " + locator);
        //WebElement element = driver.get().findElement(locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now enter text : " + value);
        element.sendKeys(value);
        ExtentLogger.info(value + " is entered successfully in " + locator);
    }
    public void clearText(By textBoxLocator) {
        logger.info("Finding Element with locator - " + textBoxLocator);
        WebElement element = driver.get().findElement(textBoxLocator);
        logger.info("Element found and now clearing textbox field");
        element.clear();
    }
    public void selectFromDropDown(By dropDownLocator, String optionToSelect){
        logger.info("Finding Element with locator - " + dropDownLocator);
        WebElement element = driver.get().findElement(dropDownLocator);
        Select select = new Select(element);
        logger.info("Selecting the option : " + optionToSelect);
        select.selectByVisibleText(optionToSelect);
        //logger.info("Element found and now enter text : " + optionToSelect);
    }
    public String getVisibleText(By locator) {
        logger.info("Finding Element with locator - " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now returning the visible text : " + element.getText());
        return element.getText();
    }
    public String getVisibleText(WebElement element) {
        logger.info("Returning the visible text" + element.getText());
        return element.getText();
    }
    public List<String> getAlltVisibleText(By locator) {
        logger.info("Finding All Elements with locator - " + locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        logger.info("Elements found and printing the list");
        List<String> visibleTextList = new ArrayList<>();
        for(WebElement element : elementList){
            System.out.println(getVisibleText(element));
            visibleTextList.add(getVisibleText(element));
        }

        return visibleTextList;
    }

    public List<WebElement> getAlltElements(By locator) {
        logger.info("Finding All Elements with locator - " + locator);
        return driver.get().findElements(locator);
    }

    public void enterSpecialKey(By locator, Keys keysToEnter) {
        logger.info("Finding Element with locator - " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now enter special key : " + keysToEnter);
        element.sendKeys(keysToEnter);
    }

    // *******************Wait Utilities ***************//
    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeOut));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver.get())
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(intervalTime))
                .ignoring(NoSuchElementException.class)
                .withMessage("Element is not found");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForelementToBeClickable(By locator, int timeOut, int intervalTime){
        System.out.println("Waiting for element");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver.get())
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(intervalTime))
                .ignoring(ElementNotInteractableException.class)
                .withMessage("Element is NOT interactable");
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}