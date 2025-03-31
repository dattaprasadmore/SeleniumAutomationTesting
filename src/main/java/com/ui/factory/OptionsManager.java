package com.ui.factory;

import com.ui.enums.ConfigProperties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Collections;
import java.util.Properties;

public class OptionsManager {

    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();

        if (Boolean.parseBoolean(prop.getProperty(String.valueOf(ConfigProperties.HEADLESS)))) {
            System.out.println("====Running tests in headless======");
            co.addArguments("--headless");
            //co.addArguments("disabled-gpu");
            //co.addArguments("--window-size=1920,1080");
        }
        if (Boolean.parseBoolean(prop.getProperty(String.valueOf(ConfigProperties.INCOGNITO)))) {
            co.addArguments("--incognito");
        }

        if (Boolean.parseBoolean(prop.getProperty(String.valueOf(ConfigProperties.REMOTE)))) {
            co.setCapability("browserName", "chrome");
            co.setBrowserVersion(prop.getProperty(String.valueOf(ConfigProperties.BROWSER_VERSION)).trim());
            co.setCapability("browserName","chrome");
            //co.setBrowserVersion(prop.getProperty("browserversion").trim());
            co.addArguments("start-maximized");
            co.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));


            /*Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("screenResolution", "1280x1024x24");
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("name", prop.getProperty("testname"));
            co.setCapability("selenoid:options", selenoidOptions);*/
        }
        return co;
    }

    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();

        if (Boolean.parseBoolean(prop.getProperty(String.valueOf(ConfigProperties.HEADLESS)))) {
            System.out.println("====Running tests in headless======");
            fo.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty(String.valueOf(ConfigProperties.INCOGNITO)))) {
            fo.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(prop.getProperty(String.valueOf(ConfigProperties.REMOTE)))) {
            fo.setCapability("browserName", "firefox");
            co.setBrowserVersion(prop.getProperty(String.valueOf(ConfigProperties.BROWSER_VERSION)).trim());

            /*Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("screenResolution", "1280x1024x24");
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("name", prop.getProperty("testname"));
            fo.setCapability("selenoid:options", selenoidOptions);*/		}

        return fo;
    }

    public EdgeOptions getEdgeOptions() {
        eo = new EdgeOptions();

        if (Boolean.parseBoolean(prop.getProperty(String.valueOf(ConfigProperties.HEADLESS)))) {
            System.out.println("====Running tests in headless======");
            eo.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty(String.valueOf(ConfigProperties.INCOGNITO)))) {
            eo.addArguments("--inPrivate");
        }

        if (Boolean.parseBoolean(prop.getProperty(String.valueOf(ConfigProperties.REMOTE)))) {
            eo.setCapability("browserName", "edge");
            co.setBrowserVersion(prop.getProperty(String.valueOf(ConfigProperties.BROWSER_VERSION)).trim());
        }
        return eo;
    }

}
