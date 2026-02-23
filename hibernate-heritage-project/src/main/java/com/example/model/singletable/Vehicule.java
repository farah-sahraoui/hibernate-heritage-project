package com.example.model.singletable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "catalogue_vehicules")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "categorie", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Veuillez renseigner la marque du véhicule")
    private String marque;

    @NotBlank(message = "Le modèle ne peut pas être vide")
    private String modele;

    @NotNull(message = "La date de mise en circulation est requise")
    @PastOrPresent(message = "La date doit être antérieure ou égale à aujourd’hui")
    private LocalDate anneeFabrication;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être supérieur à zéro")
    private Double prix;

    public Vehicule() {
    }

    public Vehicule(String marque, String modele, LocalDate anneeFabrication, Double prix) {
        this.marque = marque;
        this.modele = modele;
        this.anneeFabrication = anneeFabrication;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public LocalDate getAnneeFabrication() {
        return anneeFabrication;
    }

    public void setAnneeFabrication(LocalDate anneeFabrication) {
        this.anneeFabrication = anneeFabrication;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "=== Véhicule Enregistré ===" +
                "\nIdentifiant : " + id +
                "\nMarque : " + marque +
                "\nModèle : " + modele +
                "\nAnnée : " + anneeFabrication +
                "\nTarif : " + prix + " €";
    }
}