package com.easypay.tests.ui

import com.easypay.framework.core.ui.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page Object representing the dashboard page after successful login.
 */
public class DashboardPage extends BasePage {
    // Locators
    private final By productsTitle = By.className("title");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    
    /**
     * Check if the dashboard is loaded and user is logged in.
     * 
     * @return true if user is logged in, false otherwise
     */
    public boolean isUserLoggedIn() {
        try {
            return getText(productsTitle).equals("Products");
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Logout the current user.
     */
    public void logout() {
        logger.info("Logging out current user");
        click(menuButton);
        click(logoutLink);
    }
    
    @Override
    public void verifyPageLoaded() {
        waitForElementVisible(productsTitle);
        waitForElementVisible(menuButton);
        logger.info("Dashboard page loaded successfully");
    }
}