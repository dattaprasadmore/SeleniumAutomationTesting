package com.ui.factory;

import com.ui.enums.ConfigProperties;
import com.ui.utils.exceptionUtil.BrowserException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DriverFactory {
    WebDriver driver;
    Properties prop;
    OptionsManager optionsManager;
    public static String highlight;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    private static ThreadLocal<DesiredCapabilities> desiredCapabilitiesLocal = new ThreadLocal<DesiredCapabilities>();
    public DriverFactory(Properties prop){
        this.prop = prop;
    }

    public WebDriver initDriver() {
        try{
            String browserName = prop.getProperty(String.valueOf(ConfigProperties.BROWSER_NAME));
            highlight = prop.getProperty("highlight");
            optionsManager = new OptionsManager(prop);

            switch (browserName.toLowerCase().trim()) {
                case "chrome":
                        tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                        break;
                case "firefox":
                        tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                    break;
                case "edge":
                        tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                    break;
                default:
                    //Log.error("plz pass the right browser name..." + browserName);
                    throw new BrowserException("Browser not found, Please check Browser");
            }
            getDriver().manage().deleteAllCookies();
            getDriver().manage().window().maximize();
            //Log.info("app url : " + prop.getProperty("url"));
            getDriver().get(prop.getProperty(String.valueOf(ConfigProperties.URL)));

        } catch (BrowserException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (SecurityException e){
            e.printStackTrace();
        }
        return getDriver();
    }

    public WebDriver initRemoteDriver() {
        String huburl = prop.getProperty(String.valueOf(ConfigProperties.GRID_HUB_URL));
        String browserName = prop.getProperty(String.valueOf(ConfigProperties.BROWSER_NAME));
        optionsManager = new OptionsManager(prop);
        System.out.println("Test is running on GRID with : " + browserName);

        try {
            switch (browserName) {
                case "chrome":
                    tlDriver.set(new RemoteWebDriver(new URL(huburl), optionsManager.getChromeOptions()));
                    break;
                case "firefox":
                    tlDriver.set(new RemoteWebDriver(new URL(huburl), optionsManager.getFirefoxOptions()));
                    break;
                case "edge":
                    tlDriver.set(new RemoteWebDriver(new URL(huburl), optionsManager.getEdgeOptions()));
                    break;
                default:
                    System.out.println("");
                    throw new BrowserException("Browser not found, Please check Browser");
            }
            getDriver().manage().deleteAllCookies();
            getDriver().manage().window().maximize();
            //Log.info("app url : " + prop.getProperty("url"));
            getDriver().get(prop.getProperty(String.valueOf(ConfigProperties.URL)));
        } catch (BrowserException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return getDriver();
    }

    public WebDriver initializeLambdaTestSession() {
        try {
            String lambda_hub_url = prop.getProperty(String.valueOf(ConfigProperties.LAMBDA_HUB_URL));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", prop.getProperty(String.valueOf(ConfigProperties.BROWSER_NAME)));
            capabilities.setCapability("browserVersion", "127");
            Map<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("user", "dattaprasad.more");
            ltOptions.put("accessKey", "wUhWHtuE3snLRUl7oRI2M8EzttvPNO0Z9l79xb4OxpeSupExwm");
            ltOptions.put("build", "Selenium 4");
            ltOptions.put("platformName", "Windows 10");
            ltOptions.put("seCdp", true);
            ltOptions.put("selenium_version", "4.23.0");
            capabilities.setCapability("LT:Options", ltOptions);
            desiredCapabilitiesLocal.set(capabilities);
            WebDriver driver = null;
            driver = new RemoteWebDriver(new URL(lambda_hub_url), capabilities);
            tlDriver.set(driver);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return getDriver();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }
}