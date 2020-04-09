package com.abc.webui.automation.report;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.abc.webui.automation.management.TestContext;
import com.abc.webui.automation.testcases.BaseTestCase;
import com.aventstack.extentreports.Status;

public class ExtentReportTestListener implements ITestListener{
	protected ExtentReportHandler _reportHandler;
	
	public ExtentReportTestListener() {
		TestContext context = TestContext.Instance;
		this._reportHandler = context.reportHandler;
	}
	
	//Before starting all tests, below method runs
	public void onStart(ITestContext iTestContext) {
		System.out.println(iTestContext.getName()+" is starting...");
		
		//configure the report
		this._reportHandler.beforeClass();
	}
	
	//After ending all tests, below method runs
	public void onFinish(ITestContext iTestContext) {
		System.out.println(iTestContext.getName()+" is ending...");
		
		//do tier down operations for extentreport reporting
		this._reportHandler.afterClass();
	}
	
	private String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("onTestStart Method "+getTestMethodName(iTestResult)+" start");
		//start operation for extent report
		this._reportHandler.beforeTestMethod(iTestResult);
	}
	
	public void onTestSuccess(ITestResult iTestResult) {
		//System.out.println("onTestSuccess Method "+getTestMethodName(iTestResult)+" secceed");
		//Extent report log operation for passed test
		this._reportHandler.getTest().log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult iTestResult) {
		
		System.out.println("onTestFailure Method "+getTestMethodName(iTestResult)+" failed");
		
		//get driver from base test and assign to local webdriver variable
		Object testClass = iTestResult.getInstance();
		WebDriver driver = TestContext.Instance.driverHandler.driver;
		
		//take a screenshot
		String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
//		this._reportHandler.getTest().log(Status.FAIL, "Failed");
		//extent report log and screenshot operation for failed test case
		try {
			this._reportHandler.getTest().log(Status.FAIL, "Test Failed").addScreenCaptureFromPath(base64Screenshot);
		}catch(IOException ex) {
			//TODO: handle exception
			ex.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("onTestSkipped Method "+getTestMethodName(iTestResult)+ " skipped");
		//extent report log operation for skipped tests
		this._reportHandler.getTest().log(Status.SKIP, "Test Skipped");
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defines success ratio "+getTestMethodName(iTestResult));
	}
}
