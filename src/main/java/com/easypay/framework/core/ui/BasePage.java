package com.easypay.framework.core.ui;

import com.easypay.framework.config.ConfigManager;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Base class for all Page Objects. Implements common page functionality.
 */

public abstract class BasePage {
   protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
   protected final ConfigManager config = ConfigManager.getInstance();

   /**
    * Page initialization verification. Each page should implement this to verify
    * the page is loaded correctly.
    */

   public abstract void verifyPageLoaded();

   /**
    * Navigate to a specific URL
    * 
    * @param url the URL to navigate to
    */

   protected void navigateTo(String url) {
      logger.info("Navigating to URL: {}", url);
      com.codeborne.selenide.Selenide.open(url);
   }

   /**
    * Wait for element to be visible and clickable, then click it
    * 
    * @param locator element locator
    */

   protected void click(By locator) {
      logger.debug("Clicking on element: {}", locator);
      $(locator).shouldBe(Condition.visible, Condition.enabled).click();

   }

   /**
    * Type text into an input field
    * 
    * @param locator element locator
    * @param text    text to type
    */

   protected void type(By locator, String text) {
      logger.debug("Typing '{}' into element: {}", text, locator);
      $(locator).shouldBe(Condition.visible).setValue(text);
   }

   /**
    * Get text from an element
    * 
    * @param locator element locator
    * @return the text content
    */

   protected String getText(By locator) {
      return $(locator).shouldBe(Condition.visible).getText();
   }

   /**
    * Wait for element to be visible with timeout from config
    * 
    * @param locator element locator
    */

   protected void waitForElementVisible(By locator) {
      int timeout = config.getIntProperty("explicitWait");
      logger.debug("Waiting for element visible: {}, timeout: {}s", locator, timeout);
      $(locator).shouldBe(Condition.visible, Duration.ofSeconds(timeout));

   }

   /**
    * Navigate to another page and return the page object
    * 
    * @param <T>       the type of the page to return
    * @param pageClass the class of the page to instantiate
    * @return the new page object
    */

   protected <T extends BasePage> T navigateToPage(Class<T> pageClass) {
      logger.info("Navigating to page: {}", pageClass.getSimpleName());
      return page(pageClass);

   }
}