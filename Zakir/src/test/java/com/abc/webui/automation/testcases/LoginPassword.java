package com.abc.webui.automation.testcases;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.abc.webui.automation.pages.HomePage;
import com.abc.webui.automation.pages.LoginPasswordPage;

public class LoginPassword extends BaseTestCase {

	public LoginPasswordPage _loginPasswordPage;

	public LoginPassword() {
		
	}

	@Test
	public void TC1_Login_ability_to_provide_password() {
		_loginPasswordPage = new LoginPasswordPage(this._driverHandler.driver, this._log);
		_loginPasswordPage.typePassword(this._config.getDefaultPassword());
		HomePage homePage = _loginPasswordPage.ClickNext();
		Assert.assertTrue(homePage.validate());

	}
}
