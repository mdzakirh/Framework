package com.geico.qa.testcases;

//import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.geico.qa.util.TestUtil;
import com.geico.qa.base.TestBase;
import com.geico.qa.pages.CustomerInformationPage;
import com.geico.qa.pages.LandingPage;

public class LandingPageTest extends TestBase {

	LandingPage landingPage;
	CustomerInformationPage customerInfoPage;
	TestUtil testUtil;

	String sheetName = "LandingPage";

	// Not Working
	// Logger log = Logger.getLogger(LandingPageTest.class));
	// Working
	// Logger log = LogManager.getLogger(LandingPageTest.class.getName());

	Logger log;

	public LandingPageTest() {
		// calling testBase class constructor to initialize properties
		super();
	}

	/*
	 * TestNG internal code example: LandingPageTest obj = new LandingPageTest();
	 * obj.setUp(); obj.verifyLandingPageTitleTest(); obj.tearDown();
	 */

	@BeforeMethod
	public void setUp() {
		// Not Working
		// log =Logger.getLogger(LandingPageTest.class);

		// Working
		// log = LogManager.getLogger(LandingPageTest.class.getName());
		// Working

		log = LogManager.getLogger(LandingPageTest.class);

		// call initialization method of super class to initialize driver
		log.info("**************** Initializing the browser ****************");
		iniliatization();
		// create object of LandingPage class.
		// declare the LandingPage variable at the class level so that can use anywhere
		landingPage = new LandingPage(/* driver */);

	}

	@Test(priority = 1)
	public void verifyLandingPageTitleTest() {
		log.info("**************** Executing verifyLandingPageTitleTest ****************");
		String title = landingPage.landingPageTitle();
		// log.info("Clicked on landing page title");
		Assert.assertEquals(title, "An Insurance Company For Your Car And More | GEICO", "Title is not matching");
		log.info("**************** verifyLandingPageTitleTest Passed *************");
	}

	@Test(priority = 2)
	public void verifySection1HeaderTextTest() {
		log.info("**************** Executing verifySection1HeaderTextTest ****************");
		String sec1Header = landingPage.section1HeaderText();
		// log.info("Clicked on Section1Header text");
		Assert.assertEquals(sec1Header, "More than just car insurance", "Section1Header is not matching");
		log.info("**************** verifySection1HeaderTextTest Passed *************");
	}

//	@Test
//	public void verifyGeicoTextTest() {
//		boolean geicoText = landingPage.geicoText();
//		Assert.assertTrue(geicoText);
//
//	}

	@Test(priority = 3)
	public void verifyAutoTextBoxOptionTest() {
		log.info("**************** Executing verifyAutoTextBoxOptionTest ****************");
		boolean autoT = landingPage.autoTextBoxOption();
		// log.info("Clicked on autoTextBoxOption");
		Assert.assertTrue(autoT);
		log.info("**************** verifyAutoTextBoxOptionTest Passed *************");

	}

//	@Test(priority = 4)
//	public void verifyEnterZipCodeTestFromPropertyfile() {
//		String zipc = landingPage.enterZipCode(prop.getProperty("zipCode"));
//		Assert.assertEquals(zipc, "20164", "Zipcode is not same");
//	}

//	@Test(priority = 4, dataProvider = "getGeicoHomePageTestData")
//	public void verifyEnterZipCodeTestFromExcel(String zipCode) {
//		String zipc = landingPage.enterZipCode(zipCode);
//	}

	@Test(priority = 4, dataProvider = "getGeicoHomePageTestData")
	public void verifyEnterZipCodeWithCatagoryTestFromExcelTest(String zipCode, String Catagory) {
		log.info("**************** Executing verifyEnterZipCodeWithCatagoryTestFromExcelTest ****************");
		String zipc = landingPage.enterZipCode(zipCode);
		// log.info("Clicked on zipCode");
		String cat = landingPage.selectCatagory(Catagory);
		log.info("Clicked on Catagory");
		log.info("**************** verifyEnterZipCodeWithCatagoryTestFromExcelTest Passed *************");

	}

//	@DataProvider
//	public Object[][] getGeicoHomePageTestData() {
//		Object data[][] = testUtil.getTestData(sheetName);
//		return data;
//	}

	@Test(priority = 6)
	public void verifyStartQuoteTest() throws InterruptedException {
		log.info("**************** Executing verifyStartQuoteTest ****************");
		customerInfoPage = landingPage.startQuote();
		// log.info("Clicked on startQuote button");
		String foundText = customerInfoPage.getPopUpText();
		// System.out.println(s);
		Assert.assertEquals(foundText, "How can GEICO help you today?", "PopUp text is not matching");
		log.info("**************** verifyStartQuoteTest Passed *************");
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		// Thread.sleep(1000);
		log.info("**************** Closing the browser ****************");
		driver.quit();
	}

}
