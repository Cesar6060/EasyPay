package com.easypay.tests.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model representing a payment response from the API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String id;
    private String status;
    private double amount;
    private String currency;
    private String createdAt;
    private String paymentMethod;
    private String transactionId;
}