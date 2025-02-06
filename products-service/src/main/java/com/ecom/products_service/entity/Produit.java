package com.ecom.products_service.entity;

import com.ecom.products_service.enums.EtatProduit;
import com.ecom.products_service.modele.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Entity
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private int quantite;
    private String image;

    @Enumerated(EnumType.STRING)
    private EtatProduit etat;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    @JsonBackReference
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<ImageProduit> images;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @JsonBackReference(value = "produit-stock")
    private Stock stock;
    @Transient
    private User seller;

    @Enumerated(EnumType.STRING)
    private EtatProduit etatProduit;

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public EtatProduit getEtatProduit() {
        return etatProduit;
    }

    public void setEtatProduit(EtatProduit etatProduit) {
        this.etatProduit = etatProduit;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public EtatProduit getEtat() { return etat; }
    public void setEtat(EtatProduit etat) { this.etat = etat; }

    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

    public Fournisseur getFournisseur() { return fournisseur; }
    public void setFournisseur(Fournisseur fournisseur) { this.fournisseur = fournisseur; }

    public List<ImageProduit> getImages() { return images; }
    public void setImages(List<ImageProduit> images) { this.images = images; }

    public Stock getStock() { return stock; }
    public void setStock(Stock stock) { this.stock = stock; }

}
