package com.abc.webui.automation.report;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.testng.ITestResult;

import com.abc.webui.automation.management.ConfigManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportHandler {
	private ConfigManager _config;

	// builds a new report using the html template
	public final ExtentHtmlReporter htmlReporter;
	public final ExtentHtmlReporterConfiguration extentConfig;

	public final ExtentReports extent;

	public ExtentReportHandler(ConfigManager config) {
		this._config = config;
//		this._config = ConfigManager.getDefault();

		htmlReporter = new ExtentHtmlReporter(this._config.getReportOutPath());
		this.extentConfig = htmlReporter.config();

		// initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		this.extentConfig.setDocumentTitle("'Company Name' WebUI Test");
		this.extentConfig.setReportName("'Company Name' WebUI test report");
		this.extentConfig.setTheme(Theme.STANDARD);
	}

	public void beforeClass() {
		// TODO
	}

	public void afterClass() {
		// to write or update test information to reporter
		extent.flush();
	}

	public void beforeTestMethod(ITestResult result) {
		startTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
	}

	public void afterTestMethod(ExtentTest test, ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	Map extentTestMap = new HashedMap();

	public synchronized int getCurrentThreadId() {
		return (int) Thread.currentThread().getId(); // try with test case
	}

	public synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get(getCurrentThreadId());
	}

	public synchronized void endTest() {
//		extent.removeTest(getTest());
//		extentTestMap.remove(getCurrentThreadId());
	}

	public synchronized ExtentTest startTest(String testName, String description) {
		ExtentTest test = extent.createTest(testName, description);
		return test;
	}

}
