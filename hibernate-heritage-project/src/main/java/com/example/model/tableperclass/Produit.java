package com.example.model.tableperclass;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Le titre du produit doit être renseigné")
    private String nom;

    @NotNull(message = "Le tarif est requis")
    @Positive(message = "Le tarif doit être supérieur à zéro")
    private Double prix;

    private String description;

    @NotNull(message = "La date d'enregistrement est obligatoire")
    private LocalDateTime dateCreation;

    public Produit() {
        this.dateCreation = LocalDateTime.now();
    }

    public Produit(String nom, Double prix, String description) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.dateCreation = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "\n=== Fiche Produit ===" +
                "\nIdentifiant : " + id +
                "\nNom : " + nom +
                "\nPrix : " + prix + " €" +
                "\nDescription : " + description +
                "\nAjouté le : " + dateCreation;
    }
}