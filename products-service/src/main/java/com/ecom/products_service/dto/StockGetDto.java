package com.ecom.products_service.dto;

import com.ecom.products_service.entity.Produit;
import com.ecom.products_service.enums.StatutStock;

import java.util.List;

public record StockGetDto(Long id,
                          Integer Quantite,
                          StatutStock statutStock,
                          List<Produit> produits) {
}
