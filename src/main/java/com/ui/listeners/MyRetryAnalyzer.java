package com.ui.listeners;

import com.ui.enums.ConfigProperties;
import com.ui.utils.PropertyUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {
    private static final int maximumAtempt = Integer.parseInt(PropertyUtils.get(ConfigProperties.MAXIMUM_NUMBER_OF_ATTEMPTS));

    private static int currentAttempt = 1;
    @Override
    public boolean retry(ITestResult result) {
        if(currentAttempt<=maximumAtempt){
            currentAttempt++;
            return true;
        }
            return false;
    }
}