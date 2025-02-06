package com.ecom.products_service.transformer;

import com.ecom.products_service.dto.FournisseurGetDto;
import com.ecom.products_service.dto.FournisseurPostDto;
import com.ecom.products_service.entity.Fournisseur;
import org.springframework.stereotype.Component;

@Component
public class FournisseurTransformer extends AbstractTransformer<Fournisseur, FournisseurGetDto> {
    @Override
    public Fournisseur toEntity(FournisseurGetDto dto) {
        if(dto == null) {
            return null;
        }else{
            Fournisseur entity=new Fournisseur();
            entity.setId(dto.id());
            entity.setNom(dto.nom());
            entity.setAdresse(dto.adresse());
            entity.setTelephone(dto.telephone());
            entity.setEmail(dto.email());
            entity.setProduits(dto.produits());
            return entity;
        }
    }

    @Override
    public FournisseurGetDto toDto(Fournisseur entity) {
        if (entity == null) {
            return null;
        }else{
            FournisseurGetDto dto=new FournisseurGetDto(
                    entity.getId(),
                    entity.getNom(),
                    entity.getAdresse(),
                    entity.getTelephone(),
                    entity.getEmail(),
                    entity.getProduits()
            );
            return dto;
        }
    }

    public Fournisseur toEntityPost(FournisseurPostDto dto) {
        if(dto == null) {
            return null;
        }else{
            Fournisseur entity=new Fournisseur();
            entity.setId(dto.id());
            entity.setNom(dto.nom()
            );
            return entity;
        }
    }

    public FournisseurPostDto toDtoPost(Fournisseur entity) {
        if (entity == null) {
            return null;
        }else{
            FournisseurPostDto dto=new FournisseurPostDto(
                    entity.getId(),
                    entity.getNom()
            );
            return dto;
        }
    }
}
