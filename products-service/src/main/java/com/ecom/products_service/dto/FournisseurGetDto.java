package com.ecom.products_service.dto;

import com.ecom.products_service.entity.Fournisseur;
import com.ecom.products_service.entity.Produit;

import java.util.List;

public record FournisseurGetDto(Long id,
                                String nom,
                                String adresse,
                                String telephone,
                                String email,
                                List<Produit> produits) {
}
