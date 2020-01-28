package com.abc.webui.automation.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abc.webui.automation.pages.LoginPage;
import com.abc.webui.automation.pages.LoginPasswordPage;

public class Login extends BaseTestCase {
	public LoginPage _loginPage;
	
	public Login()	{
	}
	
	@Test
	public void TC1_Login_abiliti_to_provide_username() {
//		_loginPage = new LoginPage(this.driver, this._log);
		_loginPage = new LoginPage(this._driverHandler.driver, _log);
		_loginPage.Navigate();
		_loginPage.TypeUserName(this._config.getDefaultUserId());
		LoginPasswordPage loginPasswordPage = _loginPage.ClickNext();
		Assert.assertTrue(loginPasswordPage.validate());
	}
	/*
	 * TC1: Verify that after providing a valid suername user should be navigated to password page 
	 * Step 1: Navigate to login page
	 * Step 2: Type username into the username field 
	 * Step 3: Click on Next button
	 * Step 4: Verify that the password screen appears 
	 * 
	 */
	
}
