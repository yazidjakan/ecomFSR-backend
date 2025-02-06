package com.ecom.products_service.controllers;

import com.ecom.products_service.dto.FournisseurGetDto;
import com.ecom.products_service.dto.FournisseurPostDto;
import com.ecom.products_service.service.Impl.FournisseurServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Fournisseurs")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FournisseurController {
    private final FournisseurServiceImpl FournisseurService;

    @GetMapping("/")
    public ResponseEntity<List<FournisseurGetDto>> findAll() {
        return ResponseEntity.ok(FournisseurService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FournisseurGetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(FournisseurService.findById(id));
    }

    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FournisseurPostDto> save(@RequestBody FournisseurPostDto dto) {
        return new ResponseEntity<>(FournisseurService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FournisseurGetDto> update(@RequestBody FournisseurGetDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(FournisseurService.update(dto, id));
    }

    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        FournisseurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}