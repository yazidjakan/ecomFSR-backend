package com.ecom.commandes_service.dto;

import com.ecom.commandes_service.enums.EtatCommande;

public record CommandePostDto(Long id,
                              EtatCommande etatCommande) {
}
