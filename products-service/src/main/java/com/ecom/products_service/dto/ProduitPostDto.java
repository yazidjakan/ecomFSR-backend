package com.ecom.products_service.dto;

import com.ecom.products_service.enums.EtatProduit;

public record ProduitPostDto(Long id,
                             String nom,
                             String description,
                             double prix,
                             Integer quantite,
                             String image,
                             EtatProduit etatProduit) {
    private static Long categorieId;
    private static Long fournisseurId;

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }
}
