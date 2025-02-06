package com.ecom.commandes_service.dto;

import com.ecom.commandes_service.entity.Commande;
import com.ecom.commandes_service.modele.Produit;

public record CommandeItemGetDto(Long id,
                                 Produit produit,
                                 Integer quantite,
                                 Commande commande) {
}
