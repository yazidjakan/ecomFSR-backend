package com.ecom.commandes_service.service.Impl;

import com.ecom.commandes_service.entity.Facture;
import com.ecom.commandes_service.repository.FactureRepository;
import com.ecom.commandes_service.service.facade.FactureService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public List<Facture> findAll() {
        return factureRepository.findAll();
    }

    @Override
    public Facture findById(Long id) {
        return factureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture not found with id: " + id));
    }

    @Override
    public Facture save(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture update(Facture updatedFacture, Long id) {
        Facture existingFacture = findById(id);
        existingFacture.setReference(updatedFacture.getReference());
        existingFacture.setMontantTotal(updatedFacture.getMontantTotal());
        existingFacture.setCommandeId(updatedFacture.getCommandeId());
        existingFacture.setStatut(updatedFacture.getStatut());
        return factureRepository.save(existingFacture);
    }

    @Override
    public void deleteById(Long id) {
        Facture facture = findById(id);
        factureRepository.delete(facture);
    }
}