package com.ecom.commandes_service.controllers;

import com.ecom.commandes_service.dto.CommandeGetDto;
import com.ecom.commandes_service.dto.CommandePostDto;
import com.ecom.commandes_service.entity.CommandeItem;
import com.ecom.commandes_service.service.Impl.CommandeItemServiceImpl;
import com.ecom.commandes_service.service.Impl.CommandeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/commande-items")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CommandeItemController {
    private final CommandeItemServiceImpl commandeItemService;
    @GetMapping("/")
    public ResponseEntity<List<CommandeItem>> findAll(){
        return ResponseEntity.ok(commandeItemService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<CommandeItem> findById(@PathVariable Long id){
        return ResponseEntity.ok(commandeItemService.findById(id));
    }
    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommandeItem> save(@RequestBody CommandeItem dto){
        return new ResponseEntity<>(commandeItemService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<CommandeItem> update(@RequestBody CommandeItem dto, @PathVariable Long id){
        return ResponseEntity.ok(commandeItemService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        commandeItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
