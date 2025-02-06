package com.ecom.paiement_service.controller;

import com.ecom.paiement_service.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public PaymentIntent createPaymentIntent(@RequestParam Long amount) {
        try {
            // Crée un PaymentIntent avec le montant passé
            return paymentService.createPaymentIntent(amount);
        } catch (StripeException e) {
            throw new RuntimeException("Failed to create payment intent", e);
        }
    }
}

