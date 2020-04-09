package com.abc.webui.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.abc.webui.automation.management.ConfigManager;
import com.abc.webui.automation.management.ILogger;
import com.abc.webui.automation.management.TestContext;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver, ILogger logger) {
		super(driver, logger);
		PageFactory.initElements(driver, this);
		this._config = TestContext.Instance.config;
	}
	
	protected ConfigManager _config;
	
	@FindBy(how = How.XPATH, using = "//*[@id='identifierId']")
	private WebElement userNameTextBox;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/span")
	private WebElement nextButton;
	
	public void Navigate() {
		this._driver.navigate().to(this._config.getUrl());
	}
	
	public void TypeUserName(String userName) {
		this.userNameTextBox.sendKeys(userName); 
	}
	
	public LoginPasswordPage ClickNext() { 
		nextButton.click();
		return new LoginPasswordPage(this._driver, this._logger);
	}
	
	@Override
	public boolean validate(){
		return this.userNameTextBox.isEnabled();
	}
	

	public boolean isUserNameTextAvailableAndTextSend() {

		if(this.userNameTextBox.isDisplayed()) {
			this.userNameTextBox.sendKeys("mhossain254@wuv.edu");//(this._config.getDefaultUserId());
			this._logger.logSuccessStep("Text send successfully");
			this.nextButton.click();
			this._logger.logSuccessStep("Next button clicked successfully");
			return true;
		}
		else 
			this._logger.logFailedStep("Failed");
			return false;
	}
	

}
