package com.ecom.products_service.service.Impl;

import com.ecom.products_service.dto.FournisseurGetDto;
import com.ecom.products_service.dto.ProduitGetDto;
import com.ecom.products_service.dto.ProduitPostDto;
import com.ecom.products_service.dto.StockGetDto;
import com.ecom.products_service.entity.Categorie;
import com.ecom.products_service.entity.Fournisseur;
import com.ecom.products_service.entity.Produit;
import com.ecom.products_service.repository.CategorieRepository;
import com.ecom.products_service.repository.FournisseurRepository;
import com.ecom.products_service.repository.ProduitRepository;
import com.ecom.products_service.service.facade.ProduitService;
import com.ecom.products_service.transformer.ProduitTransformer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final FournisseurRepository fournisseurRepository;
    private final ProduitTransformer transformer;

    public ProduitServiceImpl(ProduitRepository produitRepository,
                              CategorieRepository categorieRepository,
                              FournisseurRepository fournisseurRepository,
                              ProduitTransformer transformer) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.transformer = transformer;
    }

    @Override
    public ProduitGetDto save(ProduitGetDto dto) {
        Produit produit = transformer.toEntity(dto);
        return transformer.toDto(produitRepository.save(produit));
    }
    public ProduitPostDto save(ProduitPostDto dto) {
        Produit produit = transformer.toEntityPost(dto);
        setRelations(produit,dto);
        return transformer.toDtoPost(produitRepository.save(produit));
    }

    @Override
    public ProduitGetDto update(ProduitGetDto dto,Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit not found"));
        transformer.toEntity(dto);
        setRelationsGet(produit, dto);
        return transformer.toDto(produitRepository.save(produit));
    }

    private void setRelations(Produit produit, ProduitPostDto dto) {
        if (dto.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(dto.getCategorieId())
                    .orElseThrow(() -> new RuntimeException("Categorie not found"));
            produit.setCategorie(categorie);
        }

        if (dto.getFournisseurId() != null) {
            Fournisseur fournisseur = fournisseurRepository.findById(dto.getFournisseurId())
                    .orElseThrow(() -> new RuntimeException("Fournisseur not found"));
            produit.setFournisseur(fournisseur);
        }
    }
    private void setRelationsGet(Produit produit, ProduitGetDto dto) {
        if (dto.categorie().getId() != null) {
            Categorie categorie = categorieRepository.findById(dto.categorie().getId())
                    .orElseThrow(() -> new RuntimeException("Categorie not found"));
            produit.setCategorie(categorie);
        }

        if (dto.fournisseur().getId() != null) {
            Fournisseur fournisseur = fournisseurRepository.findById(dto.fournisseur().getId())
                    .orElseThrow(() -> new RuntimeException("Fournisseur not found"));
            produit.setFournisseur(fournisseur);
        }
    }

    @Override
    public ProduitGetDto findById(Long id) {
        return produitRepository.findById(id)
                .map(transformer::toDto)
                .orElseThrow(() -> new RuntimeException("Produit not found"));
    }

    @Override
    public List<ProduitGetDto> findAll() {
        List<Produit> produits=produitRepository.findAll();
        if(produits.isEmpty()){
            throw new RuntimeException("List of users is Empty");
        }
        return transformer.toDto(produits);
    }




    @Override
    public List<ProduitGetDto> findByCategorieId(Long categorieId) {
        return produitRepository.findByCategorieId(categorieId).stream()
                .map(transformer::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProduitGetDto> findByFournisseurId(Long fournisseurId) {
        return produitRepository.findByFournisseurId(fournisseurId).stream()
                .map(transformer::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        produitRepository.deleteById(id);
    }
}