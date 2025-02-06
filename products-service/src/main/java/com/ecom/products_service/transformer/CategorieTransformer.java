package com.ecom.products_service.transformer;

import com.ecom.products_service.dto.CategorieGetDto;
import com.ecom.products_service.dto.CategoriePostDto;
import com.ecom.products_service.entity.Categorie;
import org.springframework.stereotype.Component;

@Component
public class CategorieTransformer extends AbstractTransformer<Categorie, CategorieGetDto> {
    @Override
    public Categorie toEntity(CategorieGetDto dto) {
        if(dto == null) {
            return null;
        }else{
            Categorie entity=new Categorie();
            entity.setId(dto.id());
            entity.setNom(dto.nom());
            entity.setProduits(dto.produits());
            return entity;
        }
    }

    @Override
    public CategorieGetDto toDto(Categorie entity) {
        if (entity == null) {
            return null;
        }else{
            CategorieGetDto dto=new CategorieGetDto(
                    entity.getId(),
                    entity.getNom(),
                    entity.getProduits()
            );
            return dto;
        }
    }

    public Categorie toEntityPost(CategoriePostDto dto) {
        if(dto == null) {
            return null;
        }else{
            Categorie entity=new Categorie();
            entity.setId(dto.id());
            entity.setNom(dto.nom());
            return entity;
        }
    }

    public CategoriePostDto toDtoPost(Categorie entity) {
        if (entity == null) {
            return null;
        }else{
            CategoriePostDto dto=new CategoriePostDto(
                    entity.getId(),
                    entity.getNom()
            );
            return dto;
        }
    }


}