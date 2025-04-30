package com.easypay.tests.api.endpoints.payments;

import com.easypay.framework.core.api.BaseApiClient;
import com.easypay.tests.api.models.PaymentRequest;
import com.easypay.tests.api.models.PaymentResponse;
import io.restassured.response.Response;

/**
 * API client for payment-related endpoints.
 */
public class PaymentsClient extends BaseApiClient {
    private static final String PAYMENTS_ENDPOINT = "/posts"; // Using posts as a placeholder

    /**
     * Create a new payment.
     * 
     * @param paymentRequest the payment request details
     * @return the payment response
     */
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Response response = post(PAYMENTS_ENDPOINT, paymentRequest);
        return response.as(PaymentResponse.class);
    }

    /**
     * Get payment details by ID.
     * 
     * @param paymentId the payment ID
     * @return the payment response
     */
    public PaymentResponse getPayment(String paymentId) {
        Response response = get(PAYMENTS_ENDPOINT + "/" + paymentId);
        return response.as(PaymentResponse.class);
    }

    /**
     * Cancel a payment by ID.
     * 
     * @param paymentId the payment ID
     * @return the updated payment response
     */
    public PaymentResponse cancelPayment(String paymentId) {
        // In a real implementation, this would be a proper endpoint call
        // For this example, we're using DELETE as a placeholder
        Response response = delete(PAYMENTS_ENDPOINT + "/" + paymentId);
        return response.as(PaymentResponse.class);
    }
}