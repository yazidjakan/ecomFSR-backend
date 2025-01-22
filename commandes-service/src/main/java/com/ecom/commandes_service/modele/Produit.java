package com.ecom.commandes_service.modele;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Produit {
    private Long id;
    private String nom;
    private Double prix;
}