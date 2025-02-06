package com.ecom.products_service.service.Impl;

import com.ecom.products_service.dto.*;
import com.ecom.products_service.entity.Categorie;
import com.ecom.products_service.repository.CategorieRepository;
import com.ecom.products_service.service.facade.CategorieService;
import com.ecom.products_service.transformer.CategorieTransformer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;
    private final CategorieTransformer transformer;

    public CategorieServiceImpl(CategorieRepository categorieRepository, CategorieTransformer transformer) {
        this.categorieRepository = categorieRepository;
        this.transformer = transformer;
    }

    @Override
    public CategorieGetDto save(CategorieGetDto dto) {
        Categorie categorie = transformer.toEntity(dto);
        return transformer.toDto(categorieRepository.save(categorie));
    }

    public CategoriePostDto save(CategoriePostDto dto) {
        Categorie categorie = transformer.toEntityPost(dto);
        return transformer.toDtoPost(categorieRepository.save(categorie));
    }

    @Override
    public CategorieGetDto update(CategorieGetDto dto,Long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie not found"));
        transformer.toEntity(dto);
        return transformer.toDto(categorieRepository.save(categorie));
    }

    @Override
    public CategorieGetDto findById(Long id) {
        return categorieRepository.findById(id)
                .map(transformer::toDto)
                .orElseThrow(() -> new RuntimeException("Categorie not found"));
    }

    @Override
    public List<CategorieGetDto> findAll() {
        return categorieRepository.findAll().stream()
                .map(transformer::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteById(Long id) {
        categorieRepository.deleteById(id);
    }
}