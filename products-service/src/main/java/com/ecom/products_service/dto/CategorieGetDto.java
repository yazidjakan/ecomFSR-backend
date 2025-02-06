package com.ecom.products_service.dto;

import com.ecom.products_service.entity.Categorie;
import com.ecom.products_service.entity.Produit;

import java.util.List;

public record CategorieGetDto(Long id,
                              String nom,
                              List<Produit> produits) {
}
