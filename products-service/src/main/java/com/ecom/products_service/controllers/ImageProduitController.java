package com.ecom.products_service.controllers;

import com.ecom.products_service.dto.ImageProduitGetDto;
import com.ecom.products_service.dto.ImageProduitPostDto;
import com.ecom.products_service.service.Impl.ImageProduitServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ImageProduits")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ImageProduitController {
    private final ImageProduitServiceImpl ImageProduitService;

    @GetMapping("/")
    public ResponseEntity<List<ImageProduitGetDto>> findAll() {
        return ResponseEntity.ok(ImageProduitService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ImageProduitGetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ImageProduitService.findById(id));
    }

    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ImageProduitPostDto> save(@RequestBody ImageProduitPostDto dto) {
        return new ResponseEntity<>(ImageProduitService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ImageProduitGetDto> update(@RequestBody ImageProduitGetDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(ImageProduitService.update(dto, id));
    }

    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ImageProduitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}