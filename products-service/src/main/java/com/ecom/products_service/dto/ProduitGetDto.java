package com.ecom.products_service.dto;

import com.ecom.products_service.entity.Categorie;
import com.ecom.products_service.entity.Fournisseur;
import com.ecom.products_service.entity.Produit;
import com.ecom.products_service.entity.Stock;
import com.ecom.products_service.enums.EtatProduit;
import org.apache.catalina.User;

public record ProduitGetDto(Long id,
                            String nom,
                            String description,
                            double prix,
                            Integer quantite,
                            String image,
                            EtatProduit etatProduit,
                            Categorie categorie,
                            Stock stock,
                            Fournisseur fournisseur,
                            com.ecom.products_service.modele.User seller)  {

}
