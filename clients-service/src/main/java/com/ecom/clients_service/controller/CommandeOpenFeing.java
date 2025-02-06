package com.ecom.clients_service.controller;

import com.ecom.clients_service.entity.Commande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name="commandes-service")
public interface CommandeOpenFeing {
    @GetMapping("/commandes")
    public List<Commande> findAllCommandes();

}
