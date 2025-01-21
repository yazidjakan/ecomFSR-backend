package com.ecom.clients_service.dto.CommandeDto;

import com.ecom.clients_service.enums.EtatCommande;

public record CommandePostDto(
        Long id,
        EtatCommande etatCommande
) {
}
