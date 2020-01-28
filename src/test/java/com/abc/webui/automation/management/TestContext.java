package com.abc.webui.automation.management;

import org.openqa.selenium.WebDriver;

import com.abc.webui.automation.report.ExtentReportHandler;

public class TestContext {
	public static TestContext Instance = new TestContext();
	
	public final ExtentReportHandler reportHandler;
	public final ConfigManager config;
	public final DriverHandler driverHandler;
	
	private TestContext() {
		 this.config = new ConfigManager().load(Constants.relativeResourcePathOfConfig); 
//		this.config = ConfigManager.getDefault(); 
		this.reportHandler = new ExtentReportHandler(this.config);
		this.driverHandler = new DriverHandler();
	}
	
	
}
