package com.example.model.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("AUTO")
public class Voiture extends Vehicule {

    @NotNull(message = "Le nombre de portières doit être précisé")
    @Min(value = 2, message = "Minimum 2 portières requises")
    @Max(value = 5, message = "Maximum 5 portières autorisées")
    private Integer nombrePortes;

    private Boolean climatisation;

    private String typeCarburant;

    public Voiture() {
    }

    public Voiture(String marque, String modele, LocalDate anneeFabrication, Double prix,
                   Integer nombrePortes, Boolean climatisation, String typeCarburant) {
        super(marque, modele, anneeFabrication, prix);
        this.nombrePortes = nombrePortes;
        this.climatisation = climatisation;
        this.typeCarburant = typeCarburant;
    }

    public Integer getNombrePortes() {
        return nombrePortes;
    }

    public void setNombrePortes(Integer nombrePortes) {
        this.nombrePortes = nombrePortes;
    }

    public Boolean getClimatisation() {
        return climatisation;
    }

    public void setClimatisation(Boolean climatisation) {
        this.climatisation = climatisation;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public void setTypeCarburant(String typeCarburant) {
        this.typeCarburant = typeCarburant;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n--- Détails Voiture ---" +
                "\nPortes : " + nombrePortes +
                "\nClimatisation : " + (climatisation ? "Disponible" : "Non disponible") +
                "\nÉnergie : " + typeCarburant;
    }
}