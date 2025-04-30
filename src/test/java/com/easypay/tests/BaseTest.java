package com.easypay.tests;

import com.codeborne.selenide.Configuration;
import com.easypay.framework.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.closeWebDriver;

/**
 * Base class for all test classes.
 * Handles common setup and teardown operations.
 */
public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected final ConfigManager config = ConfigManager.getInstance();

    @BeforeSuite
    public void setupSuite() {
        logger.info("Setting up test suite");

        // Configure Selenide
        Configuration.browser = config.getProperty("browser", "chrome");
        Configuration.headless = config.getBooleanProperty("headless");
        Configuration.timeout = config.getIntProperty("explicitWait") * 1000;
        Configuration.pageLoadTimeout = 30000;

        // Set up WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        logger.info("Setting up test method");
    }

    @AfterMethod
    public void tearDownTest() {
        logger.info("Tearing down test method");
        closeWebDriver();
    }
}
