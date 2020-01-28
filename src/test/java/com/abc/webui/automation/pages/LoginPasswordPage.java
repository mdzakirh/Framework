package com.abc.webui.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abc.webui.automation.management.ConfigManager;
import com.abc.webui.automation.management.ILogger;

public class LoginPasswordPage extends BasePage {
	
	public LoginPasswordPage(WebDriver driver, ILogger logger) {
		super(driver, logger);
		PageFactory.initElements(driver, this);

	}
	
	protected ConfigManager _config;
	
	@FindBy(how = How.XPATH, using = "//*[@name='password']")
	private WebElement passwordTextBox;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/span/span")
	private WebElement nextButton;
	
	public void typePassword(String password) {
		WebDriverWait wait = new WebDriverWait(this._driver,10);
		wait.until(ExpectedConditions.visibilityOf(this.passwordTextBox));
		this.passwordTextBox.sendKeys(password); 
	}
	
	public HomePage ClickNext() { 
		this.nextButton.click();
		return new HomePage(this._driver, this._logger);
	}
	
	@Override
	public boolean validate(){
		return this.passwordTextBox.isEnabled();
	}
	
//	public boolean isPasswordTextAvailableAndTextSend() {
//
//		if(this.userNameTextBox.isDisplayed()) {
//			this.userNameTextBox.sendKeys("123");//(this._config.getDefaultUserId());
//			this._logger.logSuccessStep("Text send successfully");
//			this.nextButton.click();
//			this._logger.logSuccessStep("Next button clicked successfully");
//			return true;
//		}
//		else 
//			this._logger.logFailedStep("Failed");
//			return false;
//	}
	

}
