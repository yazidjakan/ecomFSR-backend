package com.ecom.products_service.repository;

import com.ecom.products_service.entity.Stock;
import com.ecom.products_service.enums.StatutStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock>  findByProduits_Id(Long produitId);
    List<Stock> findByStatut(StatutStock statut);
}