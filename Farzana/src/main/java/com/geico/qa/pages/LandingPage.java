package com.geico.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.geico.qa.base.TestBase;
import com.geico.qa.util.TestUtil;

public class LandingPage extends TestBase { // extends BasePage

	// WebDriver driver;

	// need to define object repository or page factory
	// in page factory have to use @FindBy annotation

	@FindBy(xpath = "//h1[@id='section1heading']")
	WebElement section1Heading;

	@FindBy(xpath = "//div[@class='column1']//div[@class='auto card']")
	WebElement autoTextBox;

	@FindBy(xpath = "//div[@class='column2']//div[@class='homeowner card']")
	WebElement homeowners;

	@FindBy(xpath = "//div[@class='renters card']")
	WebElement renters;

	@FindBy(xpath = "//div[@class='condo card']//p[contains(text(),'Condo')]")
	WebElement condo;

	@FindBy(xpath = "//span[@id='icon-text']")
	WebElement auto;

	@FindBy(xpath = "//input[@id='zip']")
	// @CacheLookup // while automating the page it will loopUp the element in this
	// annotation instead of going to xpath, so automation will be faster
	// if we feel that any element will not change, then we can use @cachLookup
	// But it is very powerful to improve the performance of scripts
	// if DOM(documnet object model) has been changed then will state element
	// exception
	WebElement enterYourzip;

	// @FindBy(xpath = "//div[@class='selected-wrap']//button[@class='submitButton
	// btn btn--primary btn--full-mobile'][contains(text(),'Start Quote')]")
	@FindBy(xpath = "//button[@class='submitButton btn btn--primary btn--full-mobile']")
	WebElement startQuote;

	@FindBy(xpath = "//header[@id='primary-header']/div[2]/a[@class='icon-geico icon-geicoween']")
	WebElement geicoText;

	// now initialize all of above elements by using page factory
	// so create constructor and inside that initiate pageFactory
	public LandingPage(/* WebDriver webDriver */) {

		// below driver coming from TestBase class
		PageFactory.initElements(driver, this);

		// and this means current class object.
		// instead of this we can write LandingPage.class also
		// this.driver = webDriver;
		// PageFactory.initElements(webDriver, this);
	}

	// now define different Actions or method related to Landing Page class

	public String landingPageTitle() {
		return driver.getTitle();

	}

	public String section1HeaderText() {

		return section1Heading.getText();
	}

	public boolean geicoText() {
		return geicoText.isDisplayed();
	}

	public boolean autoTextBoxOption() {
		autoTextBox.click();
		return auto.isDisplayed();

	}

	public String enterZipCode(String zip) {
		// enterYourzip.sendKeys(prop.getProperty("zipCode"));
		enterYourzip.clear();
		enterYourzip.sendKeys(zip);
		return zip;
	}

	public String selectCatagory(String Catagory) {
		if (Catagory.equalsIgnoreCase("Auto")) {
			autoTextBox.click();
		} else if (Catagory.equalsIgnoreCase("Homeowners")) {
			homeowners.click();
		} else if (Catagory.equalsIgnoreCase("Renters")) {
			renters.click();
		} else if (Catagory.equalsIgnoreCase("Condo")) {
			condo.click();
		}

		return Catagory;
	}

	// startQuote method in landingPage class is failing while using tryClick method
	// with noSuchElementFound exception on 2nd page but while using
	// ClickRemovePopUp method passing
	public CustomerInformationPage startQuote() throws InterruptedException {
		TestUtil.scrollDownPageByPixel(By.xpath("//button[@class='submitButton btn btn--primary btn--full-mobile']"));
		TestUtil.clickRemovePopup(By.xpath("//button[@class='submitButton btn btn--primary btn--full-mobile']"));
		// TestUtil.tryClick(By.xpath("//button[@class='submitButton btn btn--primary
		// btn--full-mobile']"));

		TestUtil.waitFindElement(By.xpath("//h3[contains(text(),'How can GEICO help you today?')]"));

		return new CustomerInformationPage();

	}

}
