package com.ecom.products_service.dto;

import com.ecom.products_service.enums.StatutStock;

public record StockPostDto(Long id,
                           Integer Quantite,
                           StatutStock statutStock) {
}
