package com.ui.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ui.constants.FrameworkConstants;
import org.testng.annotations.Optional;

import java.util.Objects;

public final class ExtentReport {

	private ExtentReport() {}
	private static ExtentReports extentReports;
	//private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static void initReport(String reportName){
		if(Objects.isNull(extentReports)) {
			extentReports = new ExtentReports();
			ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"//"+ reportName);
			extentReports.attachReporter(extentSparkReporter);
			extentSparkReporter.config().setTheme(Theme.DARK);
			extentSparkReporter.config().setDocumentTitle("PDA Report");
			extentSparkReporter.config().setReportName("PDA Application Testing");
			extentSparkReporter.config().setEncoding("uft-8");
		}
	}
	public static void flushReports(){
		if(Objects.nonNull(extentReports)) {
			extentReports.flush();
		}
		/*try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	public static void createExtentTest(String testcasename, @Optional String  description) {
		ExtentManager.setExtentTest(extentReports.createTest(testcasename, description));
	}
	public static ExtentTest getTest(){
		return ExtentManager.getExtentTest();
	}
}
