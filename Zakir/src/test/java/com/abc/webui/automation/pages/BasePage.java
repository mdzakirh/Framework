package com.abc.webui.automation.pages;

import org.openqa.selenium.WebDriver;

import com.abc.webui.automation.management.ILogger;

public abstract class BasePage {
	
	protected WebDriver _driver;
	protected ILogger _logger;
	
	public BasePage(WebDriver driver, ILogger logger) {
		this._driver = driver;
		this._logger = logger;
	}
	
	public abstract boolean validate();
}
