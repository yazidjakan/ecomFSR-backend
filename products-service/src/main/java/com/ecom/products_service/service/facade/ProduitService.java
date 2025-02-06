package com.ecom.products_service.service.facade;

import com.ecom.products_service.dto.ProduitGetDto;
import com.ecom.products_service.dto.ProduitPostDto;
import com.ecom.products_service.entity.Produit;

import java.util.List;

public interface ProduitService extends AbstractService<ProduitGetDto, Long>{
    public List<ProduitGetDto> findByFournisseurId(Long fournisseurId);
    public List<ProduitGetDto> findByCategorieId(Long categorieId);
}
