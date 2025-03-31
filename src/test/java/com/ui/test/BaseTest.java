package com.ui.test;

import com.ui.factory.DriverFactory;
import com.ui.utils.LoggerUtility;
import com.ui.utils.PropertyUtils;
import com.ui.utils.exceptionUtil.BrowserException;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import com.ui.pages.HomePage;
import java.util.Properties;

public class BaseTest {
    DriverFactory driverFactory;
    protected Properties prop;
    WebDriver driver;
    protected HomePage HomePage;

    @Parameters({"browserName","headless","executionMode"})
    @BeforeTest
    public void setup(
        @Optional("chrome") String browserName,
        @Optional("false") String headless,
        @Optional("local") String executionMode) {

        try {
            Logger logger = LoggerUtility.getLogger(this.getClass());
            prop = PropertyUtils.initProp("dit");
            driverFactory = new DriverFactory(prop);

            if (browserName != null && executionMode != null) {
                prop.setProperty("BROWSER_NAME", browserName);
                prop.setProperty("HEADLESS", headless);
                prop.setProperty("EXECUTION_MODE", String.valueOf(executionMode));
                switch (executionMode) {
                    case "lambdaTestGrid":
                        driver = driverFactory.initializeLambdaTestSession();
                        if (driver != null) {
                            HomePage = new HomePage(driver);
                        }else {
                            logger.error("LAMBDA Driver is NULL, Please check Driver initialization");
                            throw new BrowserException("LAMBDA Driver is NULL, Please check Driver initialization");
                        }
                        break;
                    case "seleniumGrid":
                        driver = driverFactory.initRemoteDriver();
                        if (driver != null) {
                            HomePage = new HomePage(driver);
                        }else {
                            logger.error("Selenium GRID Driver is NULL, Please check Driver initialization");
                            throw new BrowserException("Selenium GRID Driver is NULL, Please check Driver initialization");
                        }
                        break;
                    default:
                        driver = driverFactory.initDriver();
                        if (driver != null) {
                            HomePage = new HomePage(driver);
                        } else {
                            logger.error("Driver is NULL, Please check Driver initialization");
                            throw new BrowserException("Driver is NULL, Please check Driver initialization");
                        }
                        break;
                }
            }
            driver.manage().window().maximize();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @AfterTest
    public void tearDown() {
        if (driverFactory.getDriver() != null) {
            driverFactory.getDriver().quit();
        }
    }
}