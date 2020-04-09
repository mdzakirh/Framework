package com.geico.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.geico.qa.pages.CustomerInformationPage;
import com.geico.qa.pages.LandingPage;
import com.geico.qa.util.TestUtil;
import com.geico.qa.util.WebEventListener;

public class TestBase {

	protected static WebDriver driver;
	protected static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase() {

		try {
			prop = new Properties();
			// FileInputStream fileLocation = new FileInputStream(
			// "C:\Users\Mohona2\eclipse-workspace\geico\src\main\java\com\crm\qa\config\config.properties");

			// to give the path dynamically
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/geico/" + "qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static WebDriver iniliatization() {

		// *********** Initiate browser **************
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			// to execute in chrome driver.

			// System.setProperty("webdriver.chrome.driver",
			// "C:\Users\Mohona2\eclipse-workspace\geico\src\main\resources\driver\chromedriver.exe");

			// to give the path dynamically
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/driver/chromedriver.exe");

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			// to execute in firefox driver.

			// System.setProperty("webdriver.chrome.driver",
			// "C:\Users\Mohona2\eclipse-workspace\geico\src\main\resources\driver\chromedriver.exe");

			// to give the path dynamically
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/resources/driver/geckodriver.exe");

			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
			// execute in IE driver.
		} else if (browserName.equalsIgnoreCase("safari")) {
			// execute in safari driver.
		}

		// ************* WebDriver Fire Event****************
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		// ************* Managing Selenium webdriver **************

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// By using TestUtil class
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
		return driver;

	}

//	public CustomerInformationPage CompleteLandingPage() throws InterruptedException
//	{
//		Thread.sleep(1000);
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.autoTextBoxOption();
//		landingPage.enterZipCode(prop.getProperty("zipCode"));
//		CustomerInformationPage c = landingPage.startQuote();
//		return c;
//	}
}
