package com.busyqa.test.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.busyqa.test.TestBase.TestBase;
import com.busyqa.test.extentReport.ExtentManager;
import com.busyqa.test.extentReport.ExtentTestManager;
import com.busyqa.test.screenShot.CaptureScreenshot;

public class ListenerTest extends TestBase implements ITestListener{
	CaptureScreenshot screen;
	String destination; 
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName()+"Test Case Started");
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("The name of the testcase passed is :"+result.getName());
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println(("The name of the testcase Failed is :"+result.getName()));
		String testname = result.getName();
		try {
			 destination = failed(testname);
			 ExtentTestManager.test.addScreenCaptureFromPath(destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		 	 
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("The name of the testcase Skipped is :"+result.getName());
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		
	}

}
