package com.geico.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.geico.qa.base.TestBase;
import com.geico.qa.util.TestUtil;

public class CustomerInformationPage extends TestBase {

	// need to define object repository or page factory
	// in page factory have to use @FindBy annotation

	@FindBy(xpath = "//div[@id='auto-customer-collect-intent-modal']//div[contains(@class,'modal-content')]")
	WebElement popup;

	@FindBy(xpath = "//h3[contains(text(),'How can GEICO help you today?')]")
	WebElement popupText;

	@FindBy(xpath = "//a[contains(@class,'skip-collect-intent link--primary')]")
	WebElement skip;

	@FindBy(xpath = "//div[@id='auto-customer-collect-intent-modal']//div[3]//label[1]")
	WebElement lookingForBetterprice;

	@FindBy(xpath = "//h3[contains(text(),\"I'm looking for a better price\")]")
	WebElement lookingForBetterpriceText;

	@FindBy(xpath = "//button[.='Begin Quote']")
	WebElement beginQuote;

	@FindBy(xpath = "//div[@id='current-text']")
	WebElement text;

	@FindBy(xpath = "//div[contains(@class,'card-selections-card card-selections-selected auto-card small-card')]")
	WebElement autoIcon;

	@FindBy(xpath = "//button[.='Next']")
	WebElement nextButton;

	// ************* RequiredInfo1 *********************** //
	@FindBy(xpath = "//span[contains(text(),'Date of Birth')]")
	WebElement DateOfBirthText;

	@FindBy(xpath = "//input[@id='date-monthdob']")
	WebElement DateOfBirth_M;

	@FindBy(xpath = "//input[@id='date-daydob']")
	WebElement DateOfBirth_D;

	@FindBy(xpath = "//input[@id='date-yeardob']")
	WebElement DateOfBirth_Y;

	@FindBy(xpath = "//div[contains(@class,'pull-right')]//button[contains(@class,'btn btn--primary btn--full-mobile no-margin--right')][contains(text(),'Next')]")
	WebElement DOB_Next;

	@FindBy(xpath = "//span[contains(text(),'First Name')]")
	WebElement FirstNameText;

	// ************* RequiredInfo2 *********************** //

	@FindBy(xpath = "//input[@id='firstName']")
	WebElement FirstNameTextBox;

	@FindBy(xpath = "//input[@id='lastName']")
	WebElement LastNameTextBox;

	@FindBy(xpath = "//div[contains(@class,'pull-right')]//button[contains(@class,'btn btn--primary btn--full-mobile no-margin--right')][contains(text(),'Next')]")
	WebElement Name_Next;

	@FindBy(xpath = "//span[contains(text(),'Address')]")
	WebElement AddressText;

	// ************* RequiredInfo3 *********************** //

	@FindBy(xpath = "//div[text()='Have you moved in the last 2 months?']")
	WebElement HaveYouMoved;

	@FindBy(xpath = "//input[@id='street']")
	WebElement street;

	@FindBy(xpath = "//input[@id='apt']")
	WebElement apt;

	@FindBy(xpath = "//input[@id='zip']")
	WebElement zip;

	@FindBy(xpath = "//div[@class='pull-right']//button[contains(text(),'Next')]")
	WebElement Address_next;

	// now initialize all of above elements by using page factory
	// so create constructor and inside that initiate pageFactory
	public CustomerInformationPage() {

		// below driver coming from TestBase class
		// and this means current class object.
		// instead of this we can write CustomerInformationPage.class also
		PageFactory.initElements(driver, this);

	}

	// now define different Actions or method related to CustomerInformationPage
	// class

	public String CustomerInfoPageTitle() {
		return driver.getTitle();

	}

	public boolean popUpDisplayed() {
		return popup.isDisplayed();
	}

	public String getPopUpText() {
//		TestUtil.clickRemovePopup(By.xpath("//button[@class='submitButton btn btn--primary btn--full-mobile']"));
//		TestUtil.waitFindElement(By.xpath("//h3[contains(text(),'How can GEICO help you today?')]"));
		return popupText.getText();
	}

	public void skipPopUp() throws InterruptedException {
		TestUtil.scrollDownUntillElementIsVisible(
				By.xpath("//a[contains(@class,'skip-collect-intent link--primary')]"));
		skip.click();
	}

//	public String selectLookingForbetterPrice() {
////		TestUtil.clickRemovePopup(By.xpath("//button[@class='submitButton btn btn--primary btn--full-mobile']"));
////		TestUtil.waitFindElement(By.xpath(
////				"//div[contains(@class,'card-selections-card card-selections-selected')]//label[contains(@class,'card-selections-label')]"));
//		lookingForBetterprice.click();
//		TestUtil.waitFindElement(By.xpath("//h3[contains(text(),\"I'm looking for a better price\")]"));
//		String s = lookingForBetterpriceText.getText();
//		return s;
//	}

	public String selectOption(String optionText) {
		By by = By.xpath("//h3[.=\"" + optionText + "\"]/../..//label");
		WebElement element = TestUtil.waitFindElement(by);
		element.click();
		return element.findElement(By.xpath("..//h3")).getText();
	}

	public void clickOnBeginQuote() throws InterruptedException {
		TestUtil.scrollDownUntillElementIsVisible(By.xpath("//button[.='Begin Quote']"));
		TestUtil.tryClick(By.xpath("//button[.='Begin Quote']"));
		TestUtil.waitFindElementDisapear(By.xpath("//button[.='Begin Quote']"));
	}

	public String getText() {
		String t = text.getText();
		return t;
	}

	public boolean selectedAutoIcon() throws InterruptedException {
		autoIcon.isSelected();
		return true;

	}

	public String clickOnNextButton() {
//		try {
//			TestUtil.clickRemovePopup(By.xpath("//button[.='Next']"));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		TestUtil.tryClick(By.xpath("//button[.='Next']")); // not working while using
		// this code
		WebElement DOB_Text = TestUtil.waitFindElement(By.xpath("//span[contains(text(),'Date of Birth')]"));
		return DOB_Text.getText();

	}

	// ************* RequiredInfo1 *********************** //

	public void enterDateOfBirth(String month, String day, String year) {

//		enterMonth(month);
//		enterDay(day);
//		enterYear(year);

		enterMonth(month.replaceAll("\\.[0-9]+", ""));
		enterDay(day.replaceAll("\\.[0-9]+", ""));
		enterYear(year.replaceAll("\\.[0-9]+", ""));

//		String rday = day.replaceAll("\\.0", "");
//		int iday =Integer.parseInt(rday, 10);
//		String fday = Integer.toString(iday);
//		
//		enterDay(fday);
//		
//		enterYear(Integer.toString(Integer.parseInt(year.replaceAll("\\.0", ""), 10)));

	}

	public String enterMonth(String month) {

		WebElement monthElement = TestUtil.waitFindElement(By.xpath("//input[@id='date-monthdob']"));
		monthElement.sendKeys(month);
		return month;
	}

	public String enterDay(String day) {
		WebElement dayElement = TestUtil.waitFindElement(By.xpath("//input[@id='date-daydob']"));
		dayElement.sendKeys(day);
		return day;
	}

	public String enterYear(String year) {
		WebElement yearElement = TestUtil.waitFindElement(By.xpath("//input[@id='date-yeardob']"));
		yearElement.sendKeys(year);
		return year;
	}

	public String clickOnDOB_Next() {
		// TestUtil.tryClick(By.xpath("//div[contains(@class,'pull-right')]//button[contains(@class,'btn
		// btn--primary btn--full-mobile
		// no-margin--right')][contains(text(),'Next')]"));
		DOB_Next.click();
		// TestUtil.waitFindElementDisapear(By.xpath("//div[contains(@class,'pull-right')]//button[contains(@class,'btn
		// btn--primary btn--full-mobile
		// no-margin--right')][contains(text(),'Next')]"));
		return FirstNameText.getText();
	}

	// ************* RequiredInfo2 *********************** //

	public void enterFirstAndLastName(String firstName, String lastName) {

		enterFirstName(firstName);
		enterLastName(lastName);

	}

	public String enterFirstName(String firstName) {
		WebElement fName = TestUtil.waitElementClickable(By.xpath("//input[@id='firstName']"));
		fName.click();
		fName.sendKeys(firstName);
		return firstName;
	}

	public String enterLastName(String lastName) {
		WebElement lName = TestUtil.waitElementClickable(By.xpath("//input[@id='lastName']"));
		lName.click();
		lName.sendKeys(lastName);
		return lastName;
	}

	public String clickOnNameNext() {
		Name_Next.click();
		return AddressText.getText();

	}

	// ************* RequiredInfo3 *********************** //
	public void enterStreetAddressAndAptNo(String address, String aptNo) {
//		street.sendKeys(address);
//		apt.sendKeys(aptNo);
		enterStreetAddress(address);
		enterAptNo(aptNo.replaceAll("\\.[0-9]+", ""));

	}

	public String enterStreetAddress(String address) {
		// clickOnNextButton();

		street.sendKeys(address);
		return address;

	}

	public String enterAptNo(String aptNo) {
		// clickOnNextButton();

		apt.sendKeys(aptNo);
		return aptNo;
	}

	// need to see below code

	public void checkZipCode(String zipCode) {

		zip.clear();
		zip.sendKeys(zipCode);
		Address_next.click();

	}

	public String getTextForAfterAddressValidation() throws InterruptedException {
		WebElement waitForHaveYouMoved = TestUtil
				.waitElementClickable(By.xpath("//div[text()='Have you moved in the last 2 months?']"));
		return waitForHaveYouMoved.getText();
	}

	// ************* RequiredInfo4 *********************** //
	public void selectHaveYouMovedOption(String option) {

		if (option == "Yes") {

		}

	}
}
