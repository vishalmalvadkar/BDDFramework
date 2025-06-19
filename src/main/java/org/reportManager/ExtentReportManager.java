package org.reportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("Test Execution Report");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void flush() {
        if (extent != null) extent.flush();
    }

    public static void logStepWithScreenshot(String stepText, String status, String screenshotPath) {
        ExtentTest test = extentTest.get();
        if (test != null) {
            switch (status.toUpperCase()) {
                case "PASSED":
                    test.pass(stepText, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    break;
                case "FAILED":
                    test.fail(stepText, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    break;
                case "SKIPPED":
                    test.skip(stepText, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    break;
                default:
                    test.info(stepText);
            }
        }
    }
}
