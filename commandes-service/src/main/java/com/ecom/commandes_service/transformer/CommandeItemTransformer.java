package com.ecom.commandes_service.transformer;

import com.ecom.commandes_service.dto.CommandeItemGetDto;
import com.ecom.commandes_service.dto.CommandeItemPostDto;
import com.ecom.commandes_service.entity.CommandeItem;
import org.springframework.stereotype.Component;

@Component
public class CommandeItemTransformer extends AbstractTransformer<CommandeItem, CommandeItemGetDto> {
    @Override
    public CommandeItem toEntity(CommandeItemGetDto dto) {
        if (dto == null) {
            return null;
        } else {
            CommandeItem entity = new CommandeItem();
            entity.setId(dto.id());
            entity.setProduit(dto.produit());
            entity.setQuantite(dto.quantite());
            entity.setCommande(dto.commande());
            return entity;
        }
    }

    @Override
    public CommandeItemGetDto toDto(CommandeItem entity) {
        if (entity == null) {
            return null;
        } else {
            return new CommandeItemGetDto(
                    entity.getId(),
                    entity.getProduit(),
                    entity.getQuantite(),
                    entity.getCommande()
            );
        }
    }

    public CommandeItem toEntityPost(CommandeItemPostDto dto) {
        if (dto == null) {
            return null;
        } else {
            CommandeItem entity = new CommandeItem();
            entity.setId(dto.id());
            entity.setQuantite(dto.quantite());
            return entity;
        }
    }

    public CommandeItemPostDto toDtoPost(CommandeItem entity) {
        if (entity == null) {
            return null;
        } else {
            return new CommandeItemPostDto(
                    entity.getId(),
                    entity.getQuantite()
            );
        }
    }
}