package org.Listeners;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.Utilities.LoggerUtil;
import org.Utilities.ScreenshotUtil;
import org.apache.logging.log4j.Logger;
import org.reportManager.ExtentReportManager;
import org.testng.*;

public class TestListener implements ITestListener , ISuiteListener {

    private static final Logger logger = LoggerUtil.getLogger(TestListener.class);
    private ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        extent = ExtentReportManager.getInstance();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReportManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: " + result.getMethod().getMethodName());
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentReportManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: " + result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getMethod().getMethodName(), result.getThrowable());
        ExtentTest test = ExtentReportManager.getTest();
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        String path = ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());
        test.addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped");
    }
}
