package com.geico.qa.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.geico.qa.base.TestBase;
import com.geico.qa.pages.CustomerInformationPage;
import com.geico.qa.pages.LandingPage;
import com.geico.qa.testdata.Auto;
import com.geico.qa.util.TestUtil;

public class CustomerInformationPageTest extends TestBase {
	LandingPage landingPage;
	CustomerInformationPage customerInfoPage;
	TestUtil testUtil;
	Logger log;
	Auto auto = null;
	String text = null;

	public CustomerInformationPageTest() {
		// calling testBase class constructor to initialize properties
		super();
	}

	// Testcases should be independent with each other
	// Close the browser after each test cases
	@BeforeMethod
	public void setUp() throws InterruptedException {
		log = LogManager.getLogger(CustomerInformationPageTest.class);

		// call initialization method of super class to initialize driver
		log.info("**************** Initializing the browser ****************");
		iniliatization();
		// create object of CustomerInformationPage class.
		// declare the CustomerInformationPage variable at the class level so that can
		// use anywhere

		// customerInfoPage = new CustomerInformationPage(); //*****************???
		testUtil = new TestUtil();
		landingPage = new LandingPage(/* driver */); // ********************???
		log.info("**************** LandingPage: Clicking on Auto Text Box ****************");
		landingPage.autoTextBoxOption();
		log.info("**************** LandingPage: Entering ZipCode ****************");
		landingPage.enterZipCode(prop.getProperty("zipCode"));
		log.info("**************** LandingPage: Clicking on Start Quote ****************");
		customerInfoPage = landingPage.startQuote();

		// TestBase.CompleteLandingPage();

	}

//	@Test // this test case is passing
//	public void verifyCustomerInfoPageTitleTest() {
//		String s = customerInfoPage.CustomerInfoPageTitle();
//		Assert.assertEquals(s, "GEICO", "Title not macthed");
//
//	}
//
//	@Test // this test case is passing
//	public void verifyPopUpDisplayedTest() {
//		boolean popupPresent = customerInfoPage.popUpDisplayed();
//		Assert.assertTrue(popupPresent);
//
//	}
//
//	@Test // this test case is passing
//	public void verifyPopUpTextTest() throws InterruptedException {
//		log.info("**************** Executing verifyPopUpTextTest ****************");
//		String popupText = customerInfoPage.getPopUpText();
//		// log.info("Clicked on PopUpText");
//		Assert.assertEquals(popupText, "How can GEICO help you today?", "PopUp text is not matching");
//		log.info("**************** verifyPopUpTextTest Passed *************");
//
//	}
//
//	@Test // this test case is passing
//	public void verifyskipPopUpTest() throws InterruptedException {
//		log.info("**************** Executing verifyskipPopUpTest ****************");
//		customerInfoPage.skipPopUp();
//		String s = customerInfoPage.getText();
//		Assert.assertEquals(customerInfoPage.getText(), "What would you like to protect?", "Text not matched");
//		log.info("**************** verifyskipPopUpTest Passed *************");
//		System.out.println(s);
//	}
//
//	@Test // this test case is passing
//	public void verifySelectLookingForbetterPriceTest() throws InterruptedException {
//		// CompleteLandingPage();
//		log.info("**************** Executing verifySelectLookingForbetterPriceTest ****************");
//
//		// String text = null;
//		// Auto auto = null;
//
//		try {
//			auto = TestUtil.getTestData("Auto", new Auto());
//			text = customerInfoPage.selectOption(auto.InitialSelection);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//
//		}
//
//		Assert.assertEquals(text, auto.InitialSelection, "Text is not matching");
//		log.info("**************** verifySelectLookingForbetterPriceTest Passed *************");
//
//	}
//
//	@Test // this test case is passing
//	public void verifyClickOnBeginQuoteTest() throws InterruptedException {
//
//		log.info("**************** Executing verifyClickOnBeginQuoteTest ****************");
//		try {
//			auto = TestUtil.getTestData("Auto", new Auto());
//			text = customerInfoPage.selectOption(auto.InitialSelection);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//
//		}
//
//		customerInfoPage.clickOnBeginQuote();
//		String RequiredInfo1 = customerInfoPage.getText();
//		Assert.assertEquals(RequiredInfo1, "What would you like to protect?", "Text is not matching");
//		log.info("**************** verifyClickOnBeginQuoteTest Passed *************");
//
//	}
//
//	@Test // this test case is passing
//	public void verifySelectedAutoIconTest() throws InterruptedException {
//		log.info("**************** Executing verifySelectedAutoIconTest ****************");
//
//		try {
//			auto = TestUtil.getTestData("Auto", new Auto());
//			text = customerInfoPage.selectOption(auto.InitialSelection);
//			customerInfoPage.clickOnBeginQuote();
//			// while choosing skip pop up test
//			// customerInfoPage.skipPopUp();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//
//		}
//
//		boolean b = customerInfoPage.selectedAutoIcon();
//		Assert.assertTrue(b);
//		log.info("**************** verifySelectedAutoIconTest Passed *************");
//
//	}

//	@Test // clickOnNextButton method is not working while using tryClick method but
//			// working with clickRemovePopUp method.
//
//	public void verifyClickOnNextButtonTest() throws InterruptedException {
//		log.info("**************** Executing verifyClickOnNextButtonTest ****************");
//		try {
//			auto = TestUtil.getTestData("Auto", new Auto());
//			text = customerInfoPage.selectOption(auto.InitialSelection);
//			customerInfoPage.clickOnBeginQuote();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//
//		}
//
//		// customerInfoPage.skipPopUp();
//		// Thread.sleep(3000);
//
//		String DBtext = customerInfoPage.clickOnNextButton();
//		Assert.assertEquals(DBtext, "Date of Birth", "Not matched");
//		log.info("**************** verifyClickOnNextButtonTest Passed *************");
//
//	}

//	@Test
//	public void verifyDateOfBirthTest() {
//
//		log.info("**************** Executing verifyDateOfBirtTest ****************");
//
//		try {
//			auto = TestUtil.getTestData("Auto", new Auto());
//			customerInfoPage.selectOption(auto.InitialSelection);
//
//			customerInfoPage.clickOnBeginQuote();
//			customerInfoPage.clickOnNextButton();
//
//			customerInfoPage.enterDateOfBirth(auto.DateOfBirth_M, auto.DateOfBirth_D, auto.DateOfBirth_Y);
//
//		} catch (Exception e1) {
//
//			e1.printStackTrace();
//			log.error(e1);
//
//		}
//
//		String firstnameText = customerInfoPage.clickOnDOB_Next();
//
//		Assert.assertEquals(firstnameText, "First Name", "Not matched");
//
//		System.out.println(auto.DateOfBirth_M + " " + auto.DateOfBirth_D + " " + auto.DateOfBirth_Y);
//		log.info("**************** verifyDateOfBirtTest Passed *************");
//
//	}

//	@Test // Passing
//	public void verifyFirstAndLastNameTest() throws InterruptedException {
//
//		log.info("**************** Executing verifyFirstAndLastNameTest ****************");
//
//		try {
//			auto = TestUtil.getTestData("Auto", new Auto());
//			customerInfoPage.selectOption(auto.InitialSelection);
//
//			customerInfoPage.clickOnBeginQuote();
//			customerInfoPage.clickOnNextButton();
//
//			customerInfoPage.enterDateOfBirth(auto.DateOfBirth_M, auto.DateOfBirth_D, auto.DateOfBirth_Y);
//			customerInfoPage.clickOnDOB_Next();
//			customerInfoPage.enterFirstAndLastName(auto.FirstName, auto.LastName);
//
//		} catch (Exception e1) {
//
//			e1.printStackTrace();
//
//		}
//
//		String addressText = customerInfoPage.clickOnNameNext();
//
//		Assert.assertEquals(addressText, "Address", "Not matched");
//
//		log.info("**************** verifyFirstAndLastNameTest Passed *************");
//
//	}

	@Test
	public void verifyAddressTest() throws Exception {

		log.info("**************** Executing verifyFirstAndLastNameTest ****************");

		try {
			auto = TestUtil.getTestData("Auto", new Auto());
			customerInfoPage.selectOption(auto.InitialSelection);
		} catch (Exception e1) {

			e1.printStackTrace();
			throw e1;

		}

		customerInfoPage.clickOnBeginQuote();
		customerInfoPage.clickOnNextButton();

		customerInfoPage.enterDateOfBirth(auto.DateOfBirth_M, auto.DateOfBirth_D, auto.DateOfBirth_Y);
		customerInfoPage.clickOnDOB_Next();
		customerInfoPage.enterFirstAndLastName(auto.FirstName, auto.LastName);
		customerInfoPage.clickOnNameNext();
		customerInfoPage.enterStreetAddressAndAptNo(auto.Address, auto.Apt);
		customerInfoPage.checkZipCode(auto.ZipCode);

		String haveYouMovedText = customerInfoPage.getTextForAfterAddressValidation();

		Assert.assertEquals(haveYouMovedText, "Have you moved in the last 2 months?", "Not matched");

		log.info("**************** verifyAddressTest Passed *************");

	}

//	@Test
//	public void verifyMovedInLastTwoMonthsTest() throws Exception {
//
//		log.info("**************** Executing verifyMovedInLastTwoMonthsTest ****************");
//
//		try {
//			auto = TestUtil.getTestData("Auto", new Auto());
//			customerInfoPage.selectOption(auto.InitialSelection);
//		} catch (Exception e1) {
//
//			e1.printStackTrace();
//			throw e1;
//
//		}
//
//		customerInfoPage.clickOnBeginQuote();
//		customerInfoPage.clickOnNextButton();
//
//		customerInfoPage.enterDateOfBirth(auto.DateOfBirth_M, auto.DateOfBirth_D, auto.DateOfBirth_Y);
//		customerInfoPage.clickOnDOB_Next();
//		customerInfoPage.enterFirstAndLastName(auto.FirstName, auto.LastName);
//		customerInfoPage.clickOnNameNext();
//		customerInfoPage.enterStreetAddressAndAptNo(auto.Address, auto.Apt);
//		customerInfoPage.checkZipCode(auto.ZipCode);
//
////		String haveYouMovedText = customerInfoPage.getTextForAfterAddressValidation();
////
////		Assert.assertEquals(haveYouMovedText, "Have you moved in the last 2 months?", "Not matched");
//
//		log.info("**************** verifyMovedInLastTwoMonthsTest Passed *************");
//
//	}

	@AfterMethod
	public void tearDown() {
		log.info("**************** Closing the browser ****************");
		// driver.quit();
	}

}
