package com.easypay.tests.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model representing a payment request in the API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String paymentMethod;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private double amount;
    private String currency;
    private String description;
}