package com.ecom.commandes_service.service.Impl;

import com.ecom.commandes_service.dto.CommandeItemGetDto;
import com.ecom.commandes_service.dto.CommandeItemPostDto;
import com.ecom.commandes_service.entity.CommandeItem;
import com.ecom.commandes_service.repository.CommandeItemRepository;
import com.ecom.commandes_service.service.facade.CommandeItemService;
import com.ecom.commandes_service.transformer.CommandeItemTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandeItemServiceImpl implements CommandeItemService {

    private final CommandeItemTransformer commandeItemTransformer;
    private final CommandeItemRepository commandeItemRepository;

    @Override
    public CommandeItem findById(Long id) {
        log.info("Fetching CommandeItem by ID: {}", id);
        return commandeItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("CommandeItem not found with ID: {}", id);
                    return new RuntimeException("Unable to find a CommandeItem with the given Id: " + id);
                });
    }

    @Override
    public List<CommandeItem> findAll() {
        log.info("Fetching all CommandeItems");
        List<CommandeItem> items = commandeItemRepository.findAll();
        if (items.isEmpty()) {
            throw new RuntimeException("List of CommandeItems is empty");
        }
        return items;
    }

    @Override
    public CommandeItem save(CommandeItem dto) {
        log.info("Saving CommandeItem: {}", dto);
        return commandeItemRepository.save(dto);
    }

    public CommandeItemPostDto save(CommandeItemPostDto dto) {
        log.info("Saving CommandeItemPostDto: {}", dto);
        CommandeItem entity = commandeItemTransformer.toEntityPost(dto);
        CommandeItem savedEntity = commandeItemRepository.save(entity);
        return commandeItemTransformer.toDtoPost(savedEntity);
    }

    @Override
    public CommandeItem update(CommandeItem dto, Long id) {
        log.info("Updating CommandeItem with ID: {}", id);
        CommandeItem existingEntity = commandeItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CommandeItem not found with ID: " + id));

        existingEntity.setQuantite(dto.getQuantite());
        existingEntity.setProduit(dto.getProduit());
        existingEntity.setCommande(dto.getCommande());
        return commandeItemRepository.save(existingEntity);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting CommandeItem with ID: {}", id);
        if (!commandeItemRepository.existsById(id)) {
            throw new RuntimeException("CommandeItem not found with ID: " + id);
        }
        commandeItemRepository.deleteById(id);
        log.info("Successfully deleted CommandeItem with ID: {}", id);
    }
}
