package com.easypay.tests.ui.pages.login;

import com.easypay.framework.core.ui.BasePage;
import org.openqa.selenium.By;

/**
 * Page Object representing the login page of the application.
 */
public class LoginPage extends BasePage {
    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    /**
     * Navigate to the login page.
     */
    public void open() {
        String baseUrl = config.getProperty("web.base.url");
        navigateTo(baseUrl);
        verifyPageLoaded();
    }

    /**
     * Login with the specified username and password.
     * 
     * @param username the username
     * @param password the password
     */
    public void login(String username, String password) {
        logger.info("Logging in with username: {}", username);
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    /**
     * Login with credentials from configuration properties.
     * 
     * @param userType the type of user (e.g., "standard", "admin")
     */
    public void loginAs(String userType) {
        String username = config.getProperty(userType + ".user.username");
        String password = config.getProperty(userType + ".user.password");
        login(username, password);
    }

    /**
     * Get the error message displayed on failed login.
     * 
     * @return the error message text
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Check if the error message is displayed.
     * 
     * @return true if error message is displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        return $(errorMessage).isDisplayed();
    }

    @Override
    public void verifyPageLoaded() {
        waitForElementVisible(usernameField);
        waitForElementVisible(passwordField);
        waitForElementVisible(loginButton);
        logger.info("Login page loaded successfully");
    }
}