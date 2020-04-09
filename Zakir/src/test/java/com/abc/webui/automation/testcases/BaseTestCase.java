package com.abc.webui.automation.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.abc.webui.automation.management.ConfigManager;
import com.abc.webui.automation.management.DriverHandler;
import com.abc.webui.automation.management.ILogger;
import com.abc.webui.automation.management.ReportLogger;
import com.abc.webui.automation.management.TestContext;
import com.abc.webui.automation.pages.HomePage;
import com.abc.webui.automation.pages.LoginPage;
import com.abc.webui.automation.pages.LoginPasswordPage;
import com.abc.webui.automation.report.ExtentReportHandler;

public abstract class BaseTestCase {
	// public WebDriver driver;

	protected DriverHandler _driverHandler;
	protected TestContext _context = TestContext.Instance;
	protected ILogger _log;
	protected ConfigManager _config;
	protected ExtentReportHandler _retportHandler;

	protected LoginPage _login;
	protected LoginPasswordPage _loginPassword;
	protected HomePage _home;

	public BaseTestCase() {
		this._driverHandler = this._context.driverHandler;
		this._config = this._context.config;
		this._retportHandler = this._context.reportHandler;
		this._log = new ReportLogger();
	}

	@BeforeSuite
	public void login() {
		WebDriver driver = this._driverHandler.createNewDriver();
		System.out.println("Initialize Browser: Browser is initilized");
//		_login = new LoginPage(this.driver, this._log);
//		_loginPassword = new LoginPasswordPage(driver, this._log);
//		_home = new HomePage(driver, this._log);
//		_homePage = new HomePage(this.driver, this._log);
	}

	@AfterSuite
	public void afterQuit() {
		this._driverHandler.quitDriver();
	}

	public void logSuccessStep(String stepText) {
		if (this._log != null) {
			this._log.logSuccessStep(stepText);
		}
	}

	public void logFailedStep(String stepText) {
		if (this._log != null) {
			this._log.logFailedStep(stepText);
		}
	}
}
