package com.ecom.products_service.dto;

import com.ecom.products_service.entity.Produit;

public record ImageProduitGetDto(Long id,
                                 String url,
                                 Produit produit) {
}
