package com.ecom.commandes_service.dto;

import java.time.LocalDateTime;

public record FactureGetDto(Long id,
                            String reference,
                            Double montantTotal,
                            LocalDateTime dateCreation,
                            Long commandeId,
                            String statut) {
}
