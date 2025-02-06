package com.ecom.commandes_service.entity;

import com.ecom.commandes_service.enums.EtatCommande;
import com.ecom.commandes_service.modele.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;




@Entity
@AllArgsConstructor

@Data
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Transient
    private User user;

    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    public Commande() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EtatCommande getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(EtatCommande etatCommande) {
        this.etatCommande = etatCommande;
    }
}