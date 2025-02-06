package com.ecom.paiement_service.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class StripeConfig {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Bean
    public CommandLineRunner stripeConfiguration() {
        return args -> Stripe.apiKey = stripeApiKey; // Configuration correcte de Stripe
    }
}

