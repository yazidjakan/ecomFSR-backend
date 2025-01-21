package com.ecom.clients_service.dto.CommandeDto;

import com.ecom.clients_service.entity.User;
import com.ecom.clients_service.enums.EtatCommande;

public record CommandeGetDto(
        Long id,
        User user,
        EtatCommande etatCommande
) {
}
