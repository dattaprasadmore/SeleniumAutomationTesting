package com.ui.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ui.utils.PropertyUtils;
import com.ui.enums.ConfigProperties;
import com.ui.utils.ScreenshotUtils;

public final class ExtentLogger {

	private ExtentLogger() {}
	
	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}
	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}
	public static void skip(String message) {
		ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.YELLOW));
	}
	public static void info(String message){
		ExtentManager.getExtentTest().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
	}

	public static void pass(String message, boolean isScreenshotNeeded) {
			if(PropertyUtils.get(ConfigProperties.PASSEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
					&& isScreenshotNeeded ) {
				ExtentManager.getExtentTest().pass(message, 
						MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
			}
			else{
				pass(message);
			}
	}
	public static void fail(String message, boolean isScreenshotNeeded){
		if(PropertyUtils.get(ConfigProperties.FAILEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
				&& isScreenshotNeeded ) {
			ExtentManager.getExtentTest().fail(message, 
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}
		else {
			fail(message);
		}
	}
	public static void skipp(String message, boolean isScreenshotNeeded){
		if(PropertyUtils.get(ConfigProperties.SKIPPEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
				&& isScreenshotNeeded ) {
			ExtentManager.getExtentTest().skip(message, 
					MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}
		else {
			skip(message);
		}
	}
}