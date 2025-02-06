package com.ecom.products_service.transformer;

import com.ecom.products_service.dto.StockGetDto;
import com.ecom.products_service.dto.StockPostDto;
import com.ecom.products_service.entity.Produit;
import com.ecom.products_service.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockTransformer extends AbstractTransformer<Stock, StockGetDto> {
    @Override
    public Stock toEntity(StockGetDto dto) {
        if(dto == null) {
            return null;
        }else{
            Stock entity=new Stock();
            entity.setId(dto.id());
            entity.setQuantite(dto.Quantite());
            entity.setStatut(dto.statutStock());
            entity.setProduits(dto.produits());
            return entity;
        }
    }

    @Override
    public StockGetDto toDto(Stock entity) {
        if (entity == null) {
            return null;
        }else{
            StockGetDto dto=new StockGetDto(
                    entity.getId(),
                    entity.getQuantite(),
                    entity.getStatut(),
                    entity.getProduits()
            );
            return dto;
        }
    }

    public Stock toEntityPost(StockPostDto dto) {
        if(dto == null) {
            return null;
        }else{
            Stock entity=new Stock();
            entity.setId(dto.id());
            entity.setQuantite(dto.Quantite());
            entity.setStatut(dto.statutStock());
            return entity;
        }
    }

    public StockPostDto toDtoPost(Stock entity) {
        if (entity == null) {
            return null;
        }else{
            StockPostDto dto=new StockPostDto(
                    entity.getId(),
                    entity.getQuantite(),
                    entity.getStatut());
            return dto;
        }
    }
}