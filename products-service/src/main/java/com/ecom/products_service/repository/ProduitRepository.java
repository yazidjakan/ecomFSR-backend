package com.ecom.products_service.repository;

import com.ecom.products_service.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategorieId(Long categorieId);
    List<Produit> findByFournisseurId(Long fournisseurId);
    List<Produit> findByPrixBetween(Double minPrix, Double maxPrix);
}