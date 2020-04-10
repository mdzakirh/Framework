//package com.geico.qa.util;
//
//import java.io.IOException;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.geico.qa.base.TestBase;
//
//
//public class TestngListeners extends TestBase implements ITestListener{
//	
//
//	public void onTestStart(ITestResult result) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	public void onTestSuccess(ITestResult result) {
//		
//		System.out.println("Test cases are successfully executed");
//		
//	}
//
//	public void onTestFailure(ITestResult result) {
//		// need to mention screenshot code to take for failed test cases
//		
//		try {
//			TestUtil.takeScreenshot(result.getName());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("************* WARNING --- FAILED ***************");
//		System.out.println("Test cases execution failed. See method name for more details: " +result.getName());
//		System.out.println("************* WARNING --- FAILED ***************");
//		
//	}
//
//	
//	public void onTestSkipped(ITestResult result) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	public void onStart(ITestContext context) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void onFinish(ITestContext context) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//
//}
