package com.abc.webui.automation.management;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.abc.webui.automation.utils.processUtils;
import com.abc.webui.automation.utils.resourceUtils;

public class DriverHandler {

	public WebDriver driver;

	public DriverHandler() {

	}

	public WebDriver createNewDriver() {
		ConfigManager config = new ConfigManager().load(Constants.relativeResourcePathOfConfig);

		if (config.getBrowser().equalsIgnoreCase(BrowserType.chrome.toString())) {
			String chromeDriverPath = resourceUtils.getFilePath("Resources/Driver/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			this.driver = new ChromeDriver();
		} else if (config.getBrowser().equalsIgnoreCase(BrowserType.firefox.toString())) {
			String firefoxDriverPath = resourceUtils.getFilePath("Resources/Driver/geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			this.driver = new FirefoxDriver();
		}

//			this.driver.manage().timeouts().pageLoadTimeout(config.getPageLoadTimeout(), TimeUnit.SECONDS);
//			this.driver.manage().timeouts().setScriptTimeout(config.getPageLoadTimeout(), TimeUnit.SECONDS);
//			this.driver.manage().timeouts().implicitlyWait(config.getImplicitlyWait(), TimeUnit.SECONDS);

		this.driver.manage().window().maximize();

		return this.driver;
	}

	public void quitDriver() {
		this.driver.quit();
	}

	public void closDriver() {
		this.driver.close();
	}

	public static void tryKillProcess() {
		processUtils.killProcess("chromedriver.exe");
		processUtils.killProcess("geckodriver.exe");
	}
}
