package com.ecom.clients_service.controller;

import com.ecom.clients_service.dto.UserDto;
import com.ecom.clients_service.entity.Commande;
import com.ecom.clients_service.entity.User;
import com.ecom.clients_service.service.impl.UserServiceImpl;
import com.ecom.clients_service.transformer.CommandeTransformer;
import com.ecom.clients_service.transformer.UserTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {
    private final UserServiceImpl userService;
    private final UserTransformer userTransformer;
    private final CommandeOpenFeing commandeOpenFeing;
    private CommandeOpenFeing CommandeOpenFeing;

    public UserController(UserServiceImpl userService, UserTransformer userTransformer, CommandeOpenFeing commandeOpenFeing, CommandeOpenFeing commandeOpenFeing1) {
        this.userService = userService;
        this.userTransformer = userTransformer;
        this.commandeOpenFeing = commandeOpenFeing;
        CommandeOpenFeing = commandeOpenFeing1;
    }

    @GetMapping("/commandes")
    public List<Commande> findAllCommandes(){

        List<UserDto> users = userService.findAll();
        List<Long> Id= new ArrayList<>();

        List<Commande>  commandes = commandeOpenFeing.findAllCommandes();
        List<Commande> commandeList=new ArrayList<>();


        for (UserDto u: users) {
            for (Commande c : commandes) {
                if (u.id() == c.getUser().getId()) {
                    c.setUser(userTransformer.toEntity(u));
                    break;
                }

                commandeList.add(c);
            }
        }
        return commandeList;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }
    @PostMapping("/")
    public ResponseEntity<UserDto> save(@RequestBody UserDto dto){
        log.info("Données reçues pour l'utilisateur : {}", dto);
        return new ResponseEntity<>(userService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> update(@RequestBody UserDto dto, @PathVariable Long id){
        return ResponseEntity.ok(userService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}