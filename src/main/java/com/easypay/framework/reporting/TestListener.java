package com.easypay.framework.reporting;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.codeborne.selenide.WebDriverRunner;

/**
 * Custom TestNG listener for enhanced reporting capabilities.
 * Integrates with Allure reporting framework.
 */
public class TestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getName());
        logger.error("Exception: {}", result.getThrowable().getMessage());

        // Capture screenshot if WebDriver is available
        captureScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test suite started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite finished: {}", context.getName());
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        logger.info("Results - Passed: {}, Failed: {}, Skipped: {}", passed, failed, skipped);
    }

    /**
     * Capture screenshot on test failure for Allure report.
     * 
     * @param testName name of the failed test
     * @return the screenshot as byte array
     */
    @Attachment(value = "Screenshot of {0}", type = "image/png")
    private byte[] captureScreenshot(String testName) {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            if (driver instanceof TakesScreenshot) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        }
        return new byte[0];
    }
}