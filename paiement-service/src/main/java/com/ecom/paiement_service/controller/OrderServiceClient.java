package com.ecom.paiement_service.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "commandes-service")
public interface OrderServiceClient {
    @PostMapping("/orders/{orderId}/pay")
    void payForOrder(@PathVariable("orderId") Long orderId);
}

