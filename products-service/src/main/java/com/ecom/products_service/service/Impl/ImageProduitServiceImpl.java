package com.ecom.products_service.service.Impl;

import com.ecom.products_service.dto.*;
import com.ecom.products_service.entity.ImageProduit;
import com.ecom.products_service.entity.Produit;
import com.ecom.products_service.repository.ImageProduitRepository;
import com.ecom.products_service.repository.ProduitRepository;
import com.ecom.products_service.service.facade.ImageProduitService;
import com.ecom.products_service.transformer.ImageProduitTransformer;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ImageProduitServiceImpl implements ImageProduitService {
    private final ImageProduitRepository imageProduitRepository;
    private final ImageProduitTransformer transformer;
    private final ProduitRepository produitRepository;


    public ImageProduitServiceImpl(ImageProduitRepository imageProduitRepository,
                                   ImageProduitTransformer transformer,
                                   ProduitRepository produitRepository) {
        this.imageProduitRepository = imageProduitRepository;
        this.transformer = transformer;
        this.produitRepository = produitRepository;
    }

    @Override
    public ImageProduitGetDto findById(Long id) {
        log.info("Fetching category by ID: {}", id);
        return imageProduitRepository.findById(id)
                .map(transformer::toDto)
                .orElseThrow(() ->{
                    log.error("category not found with ID: {}", id);
                    return new RuntimeException("Unable to find a category with the given Id : "+id);});
    }

    @Override
    public List<ImageProduitGetDto> findAll() {
        List<ImageProduit> ImageProduits=imageProduitRepository.findAll();
        if(ImageProduits.isEmpty()){
            throw new RuntimeException("List of category is Empty");
        }
        return transformer.toDto(ImageProduits);
    }

    @Override
    public ImageProduitGetDto save(ImageProduitGetDto dto) {
        ImageProduit imageProduit = transformer.toEntity(dto);

        Produit produit = produitRepository.findById(dto.produit().getId())
                .orElseThrow(() -> new RuntimeException("Produit not found"));
        imageProduit.setProduit(produit);
        return transformer.toDto(imageProduitRepository.save(imageProduit));
    }
    public ImageProduitPostDto save(ImageProduitPostDto dto) {
        ImageProduit imageProduit = transformer.toEntityPost(dto);
        return transformer.toDtoPost(imageProduitRepository.save(imageProduit));
    }
    public ImageProduitGetDto update(ImageProduitGetDto dto, Long id) {
        id = dto.id();
        ImageProduitGetDto existingImageProduitDto = findById(id);
        ImageProduit existingImageProduit = transformer.toEntity(dto);
        existingImageProduit.setId(dto.id());
        existingImageProduit.setUrl(dto.url());
        existingImageProduit.setProduit(dto.produit());

        log.info("Successfully updated category with ID: {}", dto.id());
        return transformer.toDto(imageProduitRepository.save(existingImageProduit));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting category with ID: {}", id);
        ImageProduitGetDto foundCategory = findById(id);
        imageProduitRepository.deleteById(foundCategory.id());

        log.info("Successfully deleted category with ID: {}", id);
    }
}