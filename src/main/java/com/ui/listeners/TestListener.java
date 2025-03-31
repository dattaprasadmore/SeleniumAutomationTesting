package com.ui.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.reports.ExtentReport;
import com.ui.utils.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TestListener implements ITestListener {
    Logger logger = LoggerUtility.getLogger(this.getClass());
    /*ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;*/

    public synchronized void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));

        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        ExtentReport.createExtentTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        ExtentReport.getTest().assignCategory(result.getTestContext().getSuite().getName());
        ExtentReport.getTest().assignCategory(className);
        ExtentReport.getTest().getModel().setStartTime(getTime(result.getStartMillis()));
    }
    public synchronized void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " " + "PASSED");
        ExtentReport.getTest().log(Status.PASS,result.getMethod().getMethodName() + " " + "PASSED");
    }
    public synchronized void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName() + " " + "FAILED" );
        logger.error(result.getThrowable().getMessage());
        ExtentReport.getTest().log(Status.FAIL,result.getMethod().getMethodName() + " " + "FAILED");
        ExtentReport.getTest().log(Status.FAIL,result.getThrowable().getMessage());

        /*Object testClass = result.getInstance();

        BrowserUtility browserUtility = ((TestBase)testClass).getInstance();
        logger.info("Capturing Screenshot for the failed tests");

        String screenshotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
        logger.info("Attaching Screenshot to the HTML file");

        ExtentReport.getTest().addScreenCaptureFromPath(screenshotPath);*/
    }
    public synchronized void onTestSkipped(ITestResult result) {
        logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED" );
        ExtentReport.getTest().log(Status.SKIP,result.getMethod().getMethodName() + " " + "SKIPPED");
    }
    public synchronized void onStart(ITestContext context) {
        logger.info("Test Suite started");
        ExtentReport.initReport("report.html");
    }
    public synchronized void onFinish(ITestContext context) {
        logger.info("Test Suite Completed");
        ExtentReport.flushReports();
    }

    private synchronized Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}