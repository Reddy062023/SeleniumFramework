package com.selenium.practice.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener implements ITestListener {
    ExtentReports extent = ExtentManager.getReports();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
