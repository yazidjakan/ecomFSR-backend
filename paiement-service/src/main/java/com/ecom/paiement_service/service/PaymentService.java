package com.ecom.paiement_service.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {


    private static final String STRIPE_API_KEY = "sk_test_51QjphWDEcBTmi6vEJe5qTTgilhiQ2LO2QmLGAaZTZO4FrG95dLjGUBebeHSj33qZSNRAS62O0pMJkgR0UNRMoIwP00a104CFT6";

    public PaymentIntent createPaymentIntent(Long amount) throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "usd");

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent;
    }
}

