package com.ecom.commandes_service.dto;

import com.ecom.commandes_service.enums.EtatCommande;
import com.ecom.commandes_service.modele.User;

public record CommandeGetDto(Long id,
                             User user,
                             EtatCommande etatCommande
) {
}
