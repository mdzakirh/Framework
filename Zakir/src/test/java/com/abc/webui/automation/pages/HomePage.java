package com.abc.webui.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.abc.webui.automation.management.ConfigManager;
import com.abc.webui.automation.management.ILogger;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver, ILogger logger) {
		super(driver, logger);
		PageFactory.initElements(driver, this);
	}

	protected ConfigManager _config;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[7]/div[3]/div/div[1]/div[3]/header/div[2]/div[1]/div[4]/div/a/img")
	private WebElement homePageLogo;
	
	@Override
	public boolean validate() {
		return this.homePageLogo.isDisplayed();
	}
	
	

}
