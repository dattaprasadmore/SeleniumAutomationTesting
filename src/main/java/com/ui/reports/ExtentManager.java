package com.ui.reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private ExtentManager() {}
	
	static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	static ExtentTest getExtentTest() { // static access --> it can be only access within the package --> Private package
		return extentTest.get();
	}
	
	static void setExtentTest(ExtentTest test) {
		extentTest.set(test);
	}
	
	static void unload() {
		extentTest.remove();
	}
}