package com.abc.webui.automation.management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoginDataReader {
	XSSFWorkbook _workbook;
	XSSFSheet _loginSheet;
	FileInputStream fis;
	
	public void loginDataReader() throws FileNotFoundException {
		File file = new File("");
		fis = new FileInputStream(file);
		try {
			_workbook = new XSSFWorkbook(new FileInputStream(file));
		}catch(Exception ex) {
			System.out.println("File read error: "+file.getPath());
			System.out.println(ex);
		}
		_loginSheet = _workbook.getSheet("Login");
	}
	
	public String getUserName() {
		return _loginSheet.getRow(1).getCell(0).getStringCellValue();
	}
	
	public String getPassword() {
		return _loginSheet.getRow(1).getCell(1).getStringCellValue();
	}
	
	public void tearDownExcel() throws IOException {
		fis.close();
	}
}
