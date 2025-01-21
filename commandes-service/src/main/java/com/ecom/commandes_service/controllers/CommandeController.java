package com.ecom.commandes_service.controllers;


import com.ecom.commandes_service.dto.CommandeGetDto;
import com.ecom.commandes_service.dto.CommandePostDto;
import com.ecom.commandes_service.entity.Commande;
import com.ecom.commandes_service.repository.CommandeRepository;
import com.ecom.commandes_service.modele.Produit;
import com.ecom.commandes_service.modele.User;
import com.ecom.commandes_service.service.Impl.CommandeServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommandeController {
    private final UserOpenFeing userOpenFeing;
   private final CommandeRepository commandeRepositoriy;

   private UserOpenFeing UserOpenFeing;
    private final CommandeServiceImpl commandeService;
   private ProduitOpenFeing produitOpenFeing;
    public CommandeController(CommandeRepository commandeRepository,
                              UserOpenFeing userOpenFeing, ProduitOpenFeing produitOpenFeing, CommandeServiceImpl commandeService) {
        this.commandeService = commandeService;
        this.commandeRepositoriy = commandeRepository;
        this.userOpenFeing = userOpenFeing;
        this.produitOpenFeing = produitOpenFeing;
    }



    @GetMapping("/commandes")
    public List<Commande> allCommandes(){

        List<Commande> commandes = commandeRepositoriy.findAll();
        List<Long> Id= new ArrayList<>();

        List<User>  users = UserOpenFeing.findAll();
        List<Produit> produits =produitOpenFeing.findAll();

        List<Commande>  com = new ArrayList<>();


        for (Commande cm:commandes) {
            for (User c : users)
                if (c.getId() == cm.getId()) {
                    cm.setUser(c);
                    break;
                }
            for (Produit p : produits)
                if (p.getId() == cm.getId()) {
                    cm.setId(p.getId());
                }
            com.add(cm);
        }
        return com;
    }

    @GetMapping("/commandes/{id}")
    public Commande aCommandes(@PathVariable Long id){

        Commande cm = commandeRepositoriy.findById(id).get();
        User cl= UserOpenFeing.findById(cm.getId());
        cm.setUser(cl);

        Produit p = produitOpenFeing.findById(cm.getId());
        cm.setId(p.getId());

        return cm;
    }
    /*@Autowired
    private final CommandeServiceImpl commandeService;
    @GetMapping("/")
    public ResponseEntity<List<CommandeGetDto>> findAll(){
        return ResponseEntity.ok(commandeService.findAll());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<CommandeGetDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(commandeService.findById(id));
    }
    @PostMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommandePostDto> save(@RequestBody CommandePostDto dto){
        return new ResponseEntity<>(commandeService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<CommandeGetDto> update(@RequestBody CommandeGetDto dto, @PathVariable Long id){
        return ResponseEntity.ok(commandeService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        commandeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/
}