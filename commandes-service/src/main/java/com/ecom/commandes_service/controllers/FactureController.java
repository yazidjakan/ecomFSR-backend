package com.ecom.commandes_service.controllers;

import com.ecom.commandes_service.entity.CommandeItem;
import com.ecom.commandes_service.entity.Facture;
import com.ecom.commandes_service.service.Impl.FactureServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/factures")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FactureController {
    private final FactureServiceImpl factureService;
    @GetMapping("/")
    public ResponseEntity<List<Facture>> findAll(){
        return ResponseEntity.ok(factureService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Facture> findById(@PathVariable Long id){
        return ResponseEntity.ok(factureService.findById(id));
    }
    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Facture> save(@RequestBody Facture dto){
        return new ResponseEntity<>(factureService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<Facture> update(@RequestBody Facture dto, @PathVariable Long id){
        return ResponseEntity.ok(factureService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        factureService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
