package com.abc.webui.automation.management;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ReportLogger implements ILogger{
	
	public ReportLogger() {
		
	}

	public void logSuccessStep(String stepText) {
		ExtentTest testCase = getCurrentTestCase();
		if(testCase != null) {
			testCase.log(Status.PASS, stepText);
		}
	}

	public void logFailedStep(String stepText) {
		ExtentTest testCase = getCurrentTestCase();
		if(testCase != null) {
			testCase.log(Status.FAIL, stepText);
		}
	}
	
	private ExtentTest getCurrentTestCase() {
		return TestContext.Instance.reportHandler.getTest();
	}
}
