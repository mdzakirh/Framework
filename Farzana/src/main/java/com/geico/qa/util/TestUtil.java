package com.geico.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.geico.qa.base.TestBase;
import com.geico.qa.testdata.Auto;
//import com.geico.qa.testcases.LandingPageTest;

public class TestUtil extends TestBase {
	public static int PAGE_LOAD_TIMEOUT = 30;
	public static int IMPLICIT_WAIT = 10;
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Mohona2\\eclipse-workspace\\geico\\"
			+ "src\\main\\java\\com\\geico\\qa\\testdata\\GeicoTestData.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static void scrollDownUntillElementIsVisible(By by) throws InterruptedException {
		// WebElement popUpScroll = driver.findElement(
		// By.xpath("//div[@id='auto-customer-collect-intent-modal']//div[contains(@class,'modal-content')]"));
		// WebElement popUpScrollTillElementFound =
		// driver.findElement(By.xpath("//button[contains(text(),'Begin Quote')]"));
		WebElement popUpScrollTillElementFound = driver.findElement(by);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		// This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView(true);", popUpScrollTillElementFound);
		Thread.sleep(5000);

		// This will scroll down the page by 1000 pixel vertical:
		// js.executeScript("window.scrollBy(0,1000)");
		// This will scroll the page till the element is found
		// js.executeScript("arguments[0].scrollIntoView();", Element);
		// This will scroll the web page till end: js.executeScript("window.scrollTo(0,
		// document.body.scrollHeight)");
		// This will scroll the page Horizontally till the element is found
		// js.executeScript("arguments[0].scrollIntoView();", Element);
		// for more details look into:
		// https://www.guru99.com/scroll-up-down-selenium-webdriver.html

	}

	public static void scrollDownPageByPixel(By by) throws InterruptedException {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		// This will scroll the page till the element is found
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(5000);

	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	// TestNG listener

//		public static void takeScreenshot(String failResult) throws IOException {
//			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(src,
//					new File(
//							"C:\\Users\\Mohona2\\eclipse-workspace\\geico\\screenshots"
//									+ failResult + "_screenshot.png"));
//		}

	public static WebElement waitFindElement(By by) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return driver.findElement(by);

	}

	public static WebElement waitElementClickable(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		return element;
	}

	public static WebElement waitFindElementDisapear(By by) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		return driver.findElement(by);

	}

	public static void clickRemovePopup(By by) throws InterruptedException {

		WebElement element = waitElementClickable(by);

		if (element == null) {

			WebElement cookiButton = driver.findElement(By.xpath("//input[@id='cookie-notice-close']"));
			if (cookiButton != null) {
				cookiButton.click();

			}
		}
		// to do: do same for any other popup:
//			WebElement cookiButton = driver.findElement(By.xpath("mention the xpath"));
//			if (cookiButton != null) {
//				cookiButton.click();
//
//			}

		element = waitElementClickable(by);
		element.click();

	}

	public static void tryClick(By by) {

		for (int i = 0; i < 5; i++) {
			try {
				clickRemovePopup(by);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
			}

		}

	}

//	public static Object[][] getTestData(String sheetName, Class classname) {
//		FileInputStream file = null;
//		try {
//			file = new FileInputStream(TESTDATA_SHEET_PATH);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		try {
//			book = WorkbookFactory.create(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		sheet = book.getSheet(sheetName);
//		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//		// System.out.println(sheet.getLastRowNum() + "--------" +
//		// sheet.getRow(0).getLastCellNum());
//		for (int i = 0; i < sheet.getLastRowNum(); i++) {
//			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
//				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
//				// System.out.println(data[i][k]);
//			}
//		}
//		return data;
//	}

//	public static <T> boolean isEqual(GenericsType<T> g1, GenericsType<T> g2){
//		return g1.get().equals(g2.get());
//	}

	public static <T extends Object> T getTestData(String sheetName, T newObj) throws Exception {

		FileInputStream file = null;
		try {
			// create FileInputStream object so that we can pass the argument to Workbook
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// Workbook accept fileInputStream argument
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2. Get access to sheet
		// sheet = book.getSheet(sheetName);
		int sheets = book.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (book.getSheetName(i).equalsIgnoreCase(sheetName)) {
				sheet = book.getSheetAt(i);

				// ******** Generics and Reflection ***********
				Class<? extends Object> reflection = newObj.getClass();

				Field[] allFields = reflection.getFields();
				for (int j = 0; j < allFields.length; j++) {
					System.out.println(allFields[j]);
					Iterator<Row> rows = sheet.iterator();
					while (rows.hasNext()) {
						Row getRows = rows.next();
//						Iterator<Cell> getCells = getRows.cellIterator();
						if ((getRows.getCell(0).toString().equalsIgnoreCase(allFields[j].getName()))) {
							
							// Actual fix needs to do in below line for enterDateOfBirth method (double dataType error)
							allFields[j].set(newObj, getRows.getCell(1).toString());
							break;
						}
					}
				}
			}
		}

		

		return newObj;
	}

//	public static ArrayList<String> getSheetData(String sheetName) throws IOException {
//		// declaring array to store the rows value
//		ArrayList<String> array = new ArrayList<String>();
//
//		// need to create FileInputStream object so that we can pass the
//		// argument to XSSFWorkbook
//		// make sure extension is xlsx for excel file
//		FileInputStream fileLocation = new FileInputStream(
//				"C:/Users/Mohona2/eclipse-workspace/geico/" + "src/main/java/com/geico/qa/testdata/GeicoTestData.xlsx");
//
////  XSSFWorkbook accept fileInputStream argument
//		XSSFWorkbook workbook = new XSSFWorkbook(fileLocation);
//
//		// 2. Get access to sheet
//		int sheets = workbook.getNumberOfSheets();
//		for (int i = 0; i < sheets; i++) {
//			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
//				XSSFSheet sheet = workbook.getSheetAt(i);
//
//				// 3. Get access to all rows of sheet
//
//				Iterator<Row> rows = sheet.iterator();
//				while (rows.hasNext()) {
//					Row getRows = rows.next();
//					Iterator<Cell> getCells = getRows.cellIterator();
//					while (getCells.hasNext()) {
//						// System.out.println(PurchaseCells.next().getStringCellValue());
//						// array.add(PurchaseCells.next().getStringCellValue());
//
//						Cell cellValue = getCells.next();
//						// c.getStringCellValue();
//						int k = 0;
//						int ColumnIndex = 0;
//						if (cellValue.getStringCellValue().equalsIgnoreCase("zipCode")) {
//							ColumnIndex = k;
//						}
//						k++;
//						System.out.println(ColumnIndex);
//						
////						if (cellValue.getCellTypeEnum() == CellType.STRING) {
////							array.add(cellValue.getStringCellValue());
////
////						} else {
////								array.add(NumberToTextConverter.toText(cellValue.getNumericCellValue()));
////						}
//					
//
//					}
//
//					
//
//				}
//			}
//		}
//
//		return array;
//	}

}
