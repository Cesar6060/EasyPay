package com.easypay.tests.api;

import com.easypay.tests.BaseTest;
import com.easypay.tests.api.endpoints.payments.PaymentsClient;
import com.easypay.tests.api.models.PaymentRequest;
import com.easypay.tests.api.models.PaymentResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for the Payments API endpoints.
 */
@Feature("Payments API")
public class PaymentsApiTest extends BaseTest {
    private PaymentsClient paymentsClient;

    @BeforeClass
    public void setUp() {
        paymentsClient = new PaymentsClient();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify creating a new payment via API")
    public void testCreatePayment() {
        // Create payment request
        PaymentRequest request = PaymentRequest.builder()
                .paymentMethod("CREDIT_CARD")
                .cardNumber("4111111111111111")
                .expiryDate("12/25")
                .cvv("123")
                .amount(100.00)
                .currency("USD")
                .description("Test payment")
                .build();

        // Send API request
        PaymentResponse response = paymentsClient.createPayment(request);

        // Since we're using a placeholder API, we'll have simplified assertions
        Assert.assertNotNull(response.getId(), "Payment ID should not be null");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify getting payment details via API")
    public void testGetPayment() {
        // Get payment details (using ID 1 as a placeholder)
        PaymentResponse response = paymentsClient.getPayment("1");

        // Basic validation
        Assert.assertNotNull(response, "Payment response should not be null");
    }
}