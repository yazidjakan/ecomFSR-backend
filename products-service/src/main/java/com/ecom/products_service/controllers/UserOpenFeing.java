package com.ecom.products_service.controllers;



import com.ecom.products_service.modele.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name="clients-service")
public interface UserOpenFeing {
    @GetMapping("/clients")
    public List<User> findAll();
    @GetMapping("/clients/{id}")
    public User findById(@PathVariable Long id);
}