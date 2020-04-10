package com.geico.qa.cucumberStepDefination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.geico.qa.base.TestBase;
import com.geico.qa.pages.CustomerInformationPage;
import com.geico.qa.pages.LandingPage;
//import   ;
import com.geico.qa.util.TestUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberTestSteps extends TestBase {

	LandingPage landingPage = new LandingPage();
	CustomerInformationPage customerInfoPage;
	TestUtil testUtil;

	String sheetName = "LandingPage";
	Logger log = LogManager.getLogger(CucumberTestSteps.class);

	@Given("User is on Landing Page")
	public void user_is_on_Landing_Page() {
		log.info("**************** Navigating to                                         bgtgrree4433wqq							 Geico Landing Page ****************");
		driver = iniliatization();
		log.info("**************** User is on landing page ****************");
	}

	@Then("Verify the LandingPageTitle")
	public void verify_the_LandingPageTitle() {
		log.info("**************** Executing verifyLandingPageTitleTest ****************");
		String title = landingPage.landingPageTitle();
		Assert.assertEquals(title, "An Insurance Company For Your Car And More | GEICO", "Title is not matching");
		log.info("**************** verifyLandingPageTitleTest Passed *************");
	}

	@When("Use is selecting the autoText box option")
	public void use_is_selecting_the_autoText_box_option() {
		log.info("**************** use_is_selecting_the_autoText_box_option Test ****************");
		boolean autoT = landingPage.autoTextBoxOption();
		Assert.assertTrue(autoT);
		log.info("**************** use_is_selecting_the_autoText_box_option Test Passed *************");

	}

	@When("User is entring zipcode")
	public void user_is_entring_zipcode() {
		String zipc = landingPage.enterZipCode(prop.getProperty("zipCode"));
		Assert.assertEquals(zipc, "20164", "Zipcode is not same");
	}

	@When("clicking on start quote button")
	public void clicking_on_start_quote_button() {
		log.info("**************** Executing verifyStartQuoteTest ****************");
		try {
			customerInfoPage = landingPage.startQuote();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
	}

	@Then("User is able to move to customer information page successfully")
	public void user_is_able_to_move_to_customer_information_page_successfully() {
		String foundText = customerInfoPage.getPopUpText();

		Assert.assertEquals(foundText, "How can GEICO help you today?", "PopUp text is not matching");
		log.info("**************** verifyStartQuoteTest Passed *************");
	}

}

		
		
		
		
		
		
		
		
		
		