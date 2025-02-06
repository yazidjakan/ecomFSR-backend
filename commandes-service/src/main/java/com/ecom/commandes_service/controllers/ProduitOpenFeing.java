package com.ecom.commandes_service.controllers;

import com.ecom.commandes_service.modele.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name="products-service")
public interface ProduitOpenFeing {

    @GetMapping("/produits")
    public List<Produit> findAll();

    @GetMapping("/produits/{id}")
    public Produit findById(@PathVariable Long id);
}