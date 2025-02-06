package com.ecom.products_service.service.Impl;

import com.ecom.products_service.dto.FournisseurGetDto;
import com.ecom.products_service.dto.FournisseurPostDto;
import com.ecom.products_service.entity.Fournisseur;
import com.ecom.products_service.repository.FournisseurRepository;
import com.ecom.products_service.service.facade.FournisseurService;
import com.ecom.products_service.transformer.FournisseurTransformer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {
    private static final Logger log = LoggerFactory.getLogger(FournisseurService.class);
    private final FournisseurRepository FournisseurDao;
    private final FournisseurTransformer fournisseurTransformer;
    @Override
    public FournisseurGetDto findById(Long id) {
        log.info("Fetching Fournisseur by ID: {}", id);
        return FournisseurDao.findById(id)
                .map(fournisseurTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("Fournisseur not found with ID: {}", id);
                    return new RuntimeException("Unable to find a Fournisseur with the given Id : "+id);
                });
    }

    @Override
    public List<FournisseurGetDto> findAll() {
        log.info("Fetching all Fournisseurs");
        List<Fournisseur> Fournisseurs=FournisseurDao.findAll();
        if(Fournisseurs.isEmpty()){
            throw new RuntimeException("List of users is Empty");
        }
        return fournisseurTransformer.toDto(Fournisseurs);
    }

    public FournisseurPostDto save(FournisseurPostDto dto) {

        log.info("Creating new Fournisseur with name: {}", dto.nom());
        try {
            Fournisseur fournisseur = fournisseurTransformer.toEntityPost(dto);
            log.info("Successfully created Fournisseur with name: {}", dto.nom());
            return fournisseurTransformer.toDtoPost(FournisseurDao.save(fournisseur));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating Fournisseur with name: {}", dto.nom(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the Fournisseur."+ ex);
        }
    }

    @Override
    public FournisseurGetDto save(FournisseurGetDto dto) {

        log.info("Creating new Fournisseur with name: {}", dto.nom());

        try {
            Fournisseur fournisseur = fournisseurTransformer.toEntity(dto);
            log.info("Successfully created Fournisseur with name: {}", dto.nom());
            return fournisseurTransformer.toDto(FournisseurDao.save(fournisseur));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating Fournisseur with name: {}", dto.nom(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the Fournisseur."+ ex);
        }
    }

    @Override
    public FournisseurGetDto update(FournisseurGetDto dto, Long id) {
        id = dto.id();
        FournisseurGetDto existingFournisseurDto = findById(id);
        Fournisseur existingFournisseur = fournisseurTransformer.toEntity(dto);
        existingFournisseur.setId(dto.id());
        existingFournisseur.setNom(dto.nom());
        existingFournisseur.setAdresse(dto.adresse());
        existingFournisseur.setTelephone(dto.telephone());
        existingFournisseur.setEmail(dto.email());
        if (existingFournisseur.getProduits() != null) {
            existingFournisseur.setProduits(dto.produits());
        }

        log.info("Successfully updated Fournisseur with ID: {}", dto.id());
        return fournisseurTransformer.toDto(FournisseurDao.save(existingFournisseur));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting Fournisseur with ID: {}", id);
        FournisseurGetDto foundFournisseur = findById(id);
        FournisseurDao.deleteById(foundFournisseur.id());

        log.info("Successfully deleted Fournisseur with ID: {}", id);
    }
}
