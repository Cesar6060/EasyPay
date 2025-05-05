package com.easypay.tests.ui;

import com.easypay.tests.BaseTest;
import com.easypay.tests.ui.pages.dashboard.DashboardPage;
import com.easypay.tests.ui.pages.login.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for the login functionality.
 */
@Feature("Login")
public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }
    
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        loginPage.open();
        loginPage.loginAs("standard");
        
        Assert.assertTrue(dashboardPage.isUserLoggedIn(), 
                "User should be logged in and dashboard should be displayed");
    }
    
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login fails with invalid credentials")
    public void testFailedLogin() {
        loginPage.open();
        loginPage.login("invalid_user", "invalid_password");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Error message should be displayed for invalid login");
    }
}